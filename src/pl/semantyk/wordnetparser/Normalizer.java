package pl.semantyk.wordnetparser;

import static pl.semantyk.utils.GlobalSemaphores.engFinished;
import static pl.semantyk.utils.GlobalSemaphores.normFinished;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;

import pl.semantyk.domain.WnUnit;
import pl.semantyk.domain.WnUnitSynsetRel;
import pl.semantyk.main.Dictionary;
import pl.semantyk.parse.entities.LexicalRelRaw;
import pl.semantyk.parse.entities.SynsetRaw;
import pl.semantyk.parse.entities.SynsetRelationRaw;

/**
 * Class is numbering all units IDs from beginning and rewrites all connections.
 */
public class Normalizer implements Runnable {

	private final static Logger LOG = Logger.getLogger(Normalizer.class);

	class MapsInitiatedMonitor {
	}

	/**
	 * Konstruktor domyslny przekazuje referencje do głównego słownika.
	 * 
	 * @param aDict
	 *            referencja do głównego słownika.
	 */
	public Normalizer(Dictionary aDict) {
		this.dict = aDict;
		init();
	}

	@Override
	public void run() {
		synchronized (engFinished) {
			LOG.debug("Normalizing data...");
			doNormalize();
		}
	}

	/**
	 * Start normalizing data sets.
	 */
	public void doNormalize() {
		LOG.debug("\n------------------ normalizing data -----------------------");
		synchronized (normFinished) {
			Runnable renLexUnit = new RenumberLexicalUnits();
			Thread renLexUnitThread = new Thread(renLexUnit);
			renLexUnitThread.start();

			Runnable renSyn = new RenumberSynsets();
			Thread renSynThread = new Thread(renSyn);
			renSynThread.start();

			Runnable renLexRel = new RenumberLexRel();
			Thread renLexRelThread = new Thread(renLexRel);
			renLexRelThread.start();

			Runnable renSynRel = new RenumberSynRel();
			Thread renSynRelThread = new Thread(renSynRel);
			renSynRelThread.start();

			Runnable renJednWnSynRel = new RenumberJednWnSynRels();
			Thread renWnUnitSynRelThread = new Thread(renJednWnSynRel);
			renWnUnitSynRelThread.start();

			try {
				renLexUnitThread.join();
				renSynThread.join();
				renLexRelThread.join();
				renSynRelThread.join();
				renWnUnitSynRelThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			for (WnUnit jedn : wnUnits) {
				LOG.debug(jedn.getId().toString());
			}

			normFinished.notifyAll();
		}
	}

	/**
	 * Initialize all data sets.
	 */
	private void init() {
		synchronized (engFinished) {
			wnUnits = dict.getWnUnits();
			synsetList = dict.getSynsetRaws();
			lexicalRelations = dict.getLexicalRelRaws();
			synsetRelations = dict.getSynsetRelations();
			wnUnitSynsetRels = new ArrayList<>(dict.getWnUnitSynsetRels());
			initMaps();
		}
	}

	class RenumberLexicalUnits implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < wnUnits.size(); i++) {
				wnUnits.get(i).setId(i);
			}
			LOG.debug("Lexical Units Normalized.");
		}
	}

	class RenumberJednWnSynRels implements Runnable {

		@Override
		public void run() {
			for (WnUnitSynsetRel rel : wnUnitSynsetRels) {
				rel.setSynsetId(synMap.get(rel.getSynsetId()));
				rel.setWnUnitId(lexMap.get(rel.getWnUnitId()));
			}
		}
	}

	class RenumberSynsets implements Runnable {

		@Override
		public void run() {
			for (SynsetRaw syn : synsetList) {
				syn.setId(synMap.get(syn.getId()));
			}

			LOG.debug("Synsets normalized.");
		}
	}

	class RenumberLexRel implements Runnable {

		@Override
		public void run() {
			for (LexicalRelRaw lr : lexicalRelations) {
				lr.setParent(lexMap.get(lr.getParent()));
				lr.setChild(lexMap.get(lr.getChild()));
			}
			LOG.debug("Lexical relations normalized.");
		}
	}

	class RenumberSynRel implements Runnable {

		@Override
		public void run() {
			for (SynsetRelationRaw lr : synsetRelations) {
				lr.setParent(synMap.get(lr.getParent()));
				lr.setChild(synMap.get(lr.getChild()));
			}
			LOG.debug("Synset relations normalized.");
		}
	}

	class LUMapInit implements Runnable {

		public LUMapInit() {
			lexMap = new LinkedHashMap<>();
		}

		@Override
		public void run() {
			LOG.debug("LUMap init start");
			for (int i = 0; i < wnUnits.size(); i++) {
				lexMap.put(wnUnits.get(i).getId(), i);
				oldLeksIds.add(wnUnits.get(i).getId());
			}
			LOG.debug("LUMap init finished.");
			LOG.debug(lexMap.toString());
		}
	}

	class SynMapInit implements Runnable {

		public SynMapInit() {
			synMap = new LinkedHashMap<>();
		}

		@Override
		public void run() {
			LOG.debug("SynMap init started.");
			for (int i = 0; i < synsetList.size(); i++) {
				synMap.put(synsetList.get(i).getId(), i);
			}
			LOG.debug("SynMap init finished.");
			// notifyAll();
		}
	}

	/**
	 * Initialize map of indexes. Fill it with new indexes.
	 */
	private void initMaps() {
		Runnable lexMapInit = new LUMapInit();
		Thread lexThread = new Thread(lexMapInit);
		lexThread.start();

		Runnable synMapInit = new SynMapInit();
		Thread synThread = new Thread(synMapInit);
		synThread.start();

		try {
			lexThread.join();
			synThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		LOG.debug("Maps initialized.");
	}

	class LUMapInitMonitor {
	}

	class SynMapInitMonitor {
	}

	/**
	 * Contains old IDs mapped to new IDs of lexical units.
	 */
	private LinkedHashMap<Integer, Integer> lexMap;
	/**
	 * Contains old IDs mapped to new IDs of synsets.
	 */
	private LinkedHashMap<Integer, Integer> synMap;

	/**
	 * Reference to main dictionary.
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
	 * Set lexical relations.
	 */
	private List<LexicalRelRaw> lexicalRelations;
	private List<SynsetRelationRaw> synsetRelations;

	private List<WnUnitSynsetRel> wnUnitSynsetRels;

	private final List<Integer> oldLeksIds = new ArrayList<>();

}
