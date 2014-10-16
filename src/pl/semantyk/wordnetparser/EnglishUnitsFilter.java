package pl.semantyk.wordnetparser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import pl.semantyk.domain.WnUnit;
import pl.semantyk.domain.WnUnitSynsetRel;
import pl.semantyk.exceptions.CommonCode;
import pl.semantyk.exceptions.SystemException;
import pl.semantyk.main.Dictionary;
import pl.semantyk.parse.entities.LexicalRelRaw;
import pl.semantyk.parse.entities.SynsetRaw;
import pl.semantyk.parse.entities.SynsetRelationRaw;
import pl.semantyk.utils.StopWatch;

/**
 * This class removes English lexical units.
 * 
 * @author Sebastian Fabisz
 */
public class EnglishUnitsFilter implements IUnitsFilter, Runnable {

	private static final Logger LOG = Logger
			.getLogger(EnglishUnitsFilter.class);

	/**
	 * Konstruktor pobiera za argument referencje do słownika na którym ma
	 * pracować.
	 * 
	 * @param aDict
	 *            referencja do słownika.
	 */
	public EnglishUnitsFilter(final Dictionary aDict) {
		this.dict = aDict;
		init();
	}

	@Override
	public void run() {
		try {
			clearUnits();
		} catch (SystemException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inicjalizuje zmienne prywatne.
	 */
	private void init() {
		wnUnits = dict.getWnUnits();
		synsetList = dict.getSynsetRaws();
		lexicalRelations = dict.getLexicalRelRaws();
		synsetRelations = dict.getSynsetRelations();
		wnUnitSynsetRels = dict.getWnUnitSynsetRels();
		engUnitsIDs = new HashSet<>(dict.getEngUnitsIDs());
		engSynsetsID = new HashSet<>();
	}

	@Override
	public final void clearUnits() throws SystemException {
		findEnglishUnits();
		clearEnglishUnits();
	}

	/**
	 * Odpowiednio konfiguruje, uruchamia i łaczy wątki czysczące słowniki z
	 * angielskich jednostek.
	 */
	private void clearEnglishUnits() throws SystemException {
		LOG.info("\n---------------- REMOVING ENGLISH UNITS ------------------------\n");

		Runnable delUnitRunnable = new DelLexUnits();
		Thread delUnitsThread = new Thread(delUnitRunnable, "LU Thread");
		delUnitsThread.start();

		Runnable delJednWnSynRelRunnable = new DelJednWnSynRel();
		Thread delJednWnSynRelThread = new Thread(delJednWnSynRelRunnable,
				"JWN_SYN Thread");
		delJednWnSynRelThread.start();

		try {
			delJednWnSynRelThread.join();
		} catch (InterruptedException ex) {
			throw new SystemException("Error during joining threads.",
					CommonCode.INTERRUPTED_THREAD)
					.set("Cause: ", ex.getCause());
		}
		Runnable delSynRunnable = new DelSynsets();
		Thread delSynThread = new Thread(delSynRunnable, "Syn Thread");
		delSynThread.start();

		Runnable delLexRelRunnable = new DelLexRel();
		Thread delLexRelThread = new Thread(delLexRelRunnable, "LR Thread");
		delLexRelThread.start();

		Runnable delSynRelRunnable = new DelSynRel();
		Thread delSynRelThread = new Thread(delSynRelRunnable, "SR Thread");
		delSynRelThread.start();

		try {
			delSynRelThread.join();
			delLexRelThread.join();
			delUnitsThread.join();
			delSynThread.join();
		} catch (InterruptedException e) {
			throw new SystemException("Error during joining threads.",
					CommonCode.INTERRUPTED_THREAD).set("Casue: ", e.getCause());
		}
		// removeInvalidWnSynRels();
		LOG.info("English units removed, notify Normalizer.");
		dict.setEngUnitsIDs(new ArrayList<>(engUnitsIDs));
		// GlobalSemaphores.engFinished.notify();
	}

	private void removeInvalidWnSynRels() {
		LOG.info("Checking relations.");

		Set<Integer> synsetIds = new HashSet<Integer>();
		Set<Integer> unitIds = new HashSet<Integer>();

		for (SynsetRaw synset : synsetList) {
			synsetIds.add(synset.getId());
		}

		for (WnUnit unit : wnUnits) {
			unitIds.add(unit.getId());
		}

		Iterator<WnUnitSynsetRel> iterator = wnUnitSynsetRels.iterator();
		int i = 0;
		int removed = 0;
		boolean isSynset = false;
		boolean isUnit = false;
		while (iterator.hasNext()) {
			if (i++ % 100 == 0)
				LOG.info(i + " relations checked. " + removed + " removed.");
			WnUnitSynsetRel rel = iterator.next();

			isSynset = synsetIds.contains(rel.getSynsetId());
			isUnit = unitIds.contains(rel.getWnUnitId());

			if (!isSynset || !isUnit) {
				iterator.remove();
				removed++;
			}

			isSynset = false;
			isUnit = false;
		}
	}

	/**
	 * Wątek usuwa angielskie jednostki WordNet'u.
	 */
	private class DelLexUnits implements Runnable {

		@Override
		public void run() {
			StopWatch watch = new StopWatch(this.getClass(),
					"Removing english WordNet units...");
			watch.start();
			Set<WnUnit> englishUnits = new LinkedHashSet<>();

			int allLexicalUnitsCount = wnUnits.size();

			for (WnUnit l : wnUnits) {
				synchronized (engUnitsIDs) {
					if (engUnitsIDs.contains(l.getId())) {
						englishUnits.add(l);
					}
				}
			}

			if (engUnitsIDs.size() < 1) {
				watch.stop();
			} else {
				wnUnits.removeAll(englishUnits);
				LOG.info("Number of removed english WordNet units: "
						+ (allLexicalUnitsCount - wnUnits.size()));
				watch.stop();
			}
		}
	}

	/**
	 * Removes English synsets, by comparing unit array of synset to array of
	 * English units.
	 */
	private class DelSynsets implements Runnable {

		@Override
		public void run() {
			StopWatch watch = new StopWatch(this.getClass(),
					"Removig english Synsets...");
			watch.start();

			int synsetCount = synsetList.size();

			Set<SynsetRaw> goodSynsets = new HashSet<>();

			for (SynsetRaw s : synsetList) {
				synchronized (engSynsetsID) {
					if (!engSynsetsID.contains(s.getId())) {
						goodSynsets.add(s);
					}
				}
			}

			// retain good synsets.
			synsetList.retainAll(goodSynsets);

			if ((synsetCount - synsetList.size()) < 1) {
				LOG.info("No Synsets removed. Check input file.");
				watch.stop();
			} else {
				LOG.info("Number of english synsets deleted : "
						+ (synsetCount - synsetList.size()));

				watch.stop();
			}
		}
	}

	private class DelJednWnSynRel implements Runnable {

		@Override
		public void run() {
			StopWatch watch = new StopWatch(this.getClass(),
					"Removing invalid relations betwee Synset and WordNet units...");
			watch.start();

			int allWnUnitSynRels = wnUnitSynsetRels.size();
			Set<WnUnitSynsetRel> damagedRels = new HashSet<>();

			for (WnUnitSynsetRel rel : wnUnitSynsetRels) {
				synchronized (engUnitsIDs) {
					if (engUnitsIDs.contains(rel.getWnUnitId())) {
						damagedRels.add(rel);
						engSynsetsID.add(rel.getSynsetId());
					}
				}
			}

			wnUnitSynsetRels.removeAll(damagedRels);

			if ((allWnUnitSynRels - wnUnitSynsetRels.size()) < 1) {
				LOG.info("No invalid relations removed.");
				watch.stop();
			} else {
				LOG.info("Number of removed invalid relations: "
						+ (allWnUnitSynRels - wnUnitSynsetRels.size()));
			}

			watch.stop();
		}

	}

	/**
	 * Removes English lexical relations.
	 * 
	 * @return true if action successfully.
	 */
	private class DelLexRel implements Runnable {

		@Override
		public void run() {
			StopWatch watch = new StopWatch(this.getClass(),
					"Removing english lexical relations");
			watch.start();
			int allLexRelCount = lexicalRelations.size();
			Set<LexicalRelRaw> englishLexRel = new HashSet<>();

			for (LexicalRelRaw l : lexicalRelations) {
				synchronized (engUnitsIDs) {
					if (engUnitsIDs.contains(l.getChild())
							|| engUnitsIDs.contains(l.getParent())) {

						englishLexRel.add(l);
					}
				}
			}
			lexicalRelations.removeAll(englishLexRel);

			if ((allLexRelCount - lexicalRelations.size()) < 1) {
				watch.stop();
			} else {
				LOG.info("Number of english lexical relations removed : "
						+ (allLexRelCount - lexicalRelations.size()));
				watch.stop();
			}
		}
	}

	/**
	 * Removes English synset relations.
	 * 
	 * @return true if actions successfully.
	 */
	private class DelSynRel implements Runnable {

		@Override
		public void run() {
			StopWatch watch = new StopWatch(this.getClass(),
					"Removing english synset relations...");
			watch.start();
			LOG.info("engSynsetsIDs size: " + engSynsetsID.size());
			int allSynRelCount = synsetRelations.size();
			Set<SynsetRelationRaw> englishSynRel = new HashSet<>();
			for (SynsetRelationRaw s : synsetRelations) {
				if (engSynsetsID.contains(s.getChild())
						|| engSynsetsID.contains(s.getParent())) {
					englishSynRel.add(s);
				}
			}

			synsetRelations.removeAll(englishSynRel);
			if ((allSynRelCount - synsetRelations.size()) < 1) {
				LOG.info("No synset realtions removed. Check input file");
				watch.stop();
			} else {
				LOG.info("Number of english synset relations removed : "
						+ (allSynRelCount - synsetRelations.size()));
				watch.stop();
			}
		}
	}

	/**
	 * Find IDs of all English lexical units.
	 */
	private void findEnglishUnits() {
		StopWatch watch = new StopWatch(this.getClass(),
				"Search English lexical units IDs...");
		watch.start();
		for (WnUnit l : wnUnits) {
			if (l.getPosition().equals("rzeczownik pwn")
					|| l.getPosition().equals("przymiotnik pwn")
					|| l.getPosition().equals("czasownik pwn")
					|| l.getPosition().equals("przysłówek pwn")) {

				engUnitsIDs.add(l.getId());
			}
		}

		LOG.info("Search completed. Found: " + engUnitsIDs.size() + " units.");
		watch.stop();
	}

	/**
	 * Reference to main Dictionary.
	 */
	private final Dictionary dict;

	/**
	 * Set of lexical units.
	 */
	private List<WnUnit> wnUnits;
	/**
	 * Set of synsets.
	 */
	private List<SynsetRaw> synsetList;
	/**
	 * Set of lexical relations.
	 */
	private List<LexicalRelRaw> lexicalRelations;
	/**
	 * Set of synset relations.
	 */
	private List<SynsetRelationRaw> synsetRelations;

	/**
	 * Zbiór relacji pomiędzy jednostkami WordNet a synsetami.
	 */
	private Set<WnUnitSynsetRel> wnUnitSynsetRels;

	/**
	 * Set of English synsets IDs.
	 */
	private volatile Set<Integer> engSynsetsID;
	/**
	 * Set of English units IDs.
	 */
	private volatile Set<Integer> engUnitsIDs;
}
