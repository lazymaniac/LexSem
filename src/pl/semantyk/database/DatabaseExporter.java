package pl.semantyk.database;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import pl.semantyk.dao.LexicalRelDao;
import pl.semantyk.dao.LexicalRelDaoImpl;
import pl.semantyk.dao.RelationTypeDao;
import pl.semantyk.dao.RelationTypeDaoImpl;
import pl.semantyk.dao.SynsetDao;
import pl.semantyk.dao.SynsetDaoImpl;
import pl.semantyk.dao.SynsetRelationDao;
import pl.semantyk.dao.SynsetRelationDaoImpl;
import pl.semantyk.dao.WikiUnitDao;
import pl.semantyk.dao.WikiUnitDaoImpl;
import pl.semantyk.dao.WnUnitDao;
import pl.semantyk.dao.WnUnitDaoImpl;
import pl.semantyk.dao.WnUnitSynsetRelDao;
import pl.semantyk.dao.WnUnitSynsetRelDaoImpl;
import pl.semantyk.domain.RelationType;
import pl.semantyk.domain.WnUnit;
import pl.semantyk.domain.WnUnitSynsetRel;
import pl.semantyk.enums.DatabaseType;
import pl.semantyk.enums.TimeUnit;
import pl.semantyk.exceptions.SystemException;
import pl.semantyk.main.Dictionary;
import pl.semantyk.parse.entities.LexicalRelRaw;
import pl.semantyk.parse.entities.SynsetRaw;
import pl.semantyk.parse.entities.SynsetRelationRaw;
import pl.semantyk.utils.StopWatch;

/**
 * Class save all parsed collections from database dumps to database specified
 * by user.
 * 
 * @author Sebastian Fabisz.
 */
public class DatabaseExporter {

	private static final Logger LOG = Logger.getLogger(DatabaseExporter.class);

	private final WikiUnitDao wikiUnitDao = new WikiUnitDaoImpl();
	private final WnUnitDao wnUnitDao = new WnUnitDaoImpl();
	private final LexicalRelDao lexicalRelDao = new LexicalRelDaoImpl();
	private final RelationTypeDao relationTypeDao = new RelationTypeDaoImpl();
	private final SynsetRelationDao synsetRelDao = new SynsetRelationDaoImpl();
	private final SynsetDao synsetDao = new SynsetDaoImpl();
	private final WnUnitSynsetRelDao wnUnitSynsetRelDao = new WnUnitSynsetRelDaoImpl();

	private final Dictionary dictionary;

	public DatabaseExporter(Dictionary dictionary) {
		this.dictionary = dictionary;
		init();
	}

	private void init() {
		for (RelationType rel : dictionary.getRelationTypes())
			typyRelIds.add(rel.getId());

		for (SynsetRaw syn : dictionary.getSynsetRaws())
			synsetIds.add(syn.getId());

		for (WnUnit jWn : dictionary.getWnUnits())
			jednWnIds.add(jWn.getId());
	}

	public void exportDatabase(DatabaseType databaseType) {
		if (databaseType.equals(DatabaseType.WIKTIONARY))
			exportWikitionary();

		if (databaseType.equals(DatabaseType.WORDNET))
			exportWordnet();
	}

	private void exportWordnet() {
		exportWnUnits();
		exportSynset();
		exportWnUnitsSynRel();
		exportRelationTypes();
		exportLexicalRels();
		exportSynsetRels();
	}

	private void exportWikitionary() {
		StopWatch watch = new StopWatch(WikiUnitDao.class,
				"persisting Wiki units...", TimeUnit.SECOND);
		watch.start();
		wikiUnitDao.persistAll(dictionary.getWikiUnits());
		watch.stop();
	}

	private void exportWnUnits() {
		StopWatch watch = new StopWatch(WnUnitDao.class,
				"persisting WordNet units...", TimeUnit.SECOND);
		watch.start();
		Set<WnUnit> noDup = new HashSet<>(dictionary.getWnUnits());
		try {
			wnUnitDao.persisttAllNative(noDup);
		} catch (SystemException e) {
			LOG.debug(e);
		}
		watch.stop();
	}

	private void exportSynset() {
		StopWatch watch = new StopWatch(SynsetDao.class,
				"persisting synsets...", TimeUnit.SECOND);
		watch.start();
		LOG.info(dictionary.getSynsetRaws().get(0).toString());
		Set<SynsetRaw> noDup = new HashSet<>(dictionary.getSynsetRaws());
		try {
			synsetDao.persistAllNative(noDup);
		} catch (SystemException e) {
			LOG.debug(e);
		}
		watch.stop();
	}

	private void exportWnUnitsSynRel() {
		StopWatch watch = new StopWatch(WnUnitSynsetRelDao.class,
				"persisting relations between WordNet units and Synset.");
		watch.start();
		LOG.info(dictionary.getWnUnitSynsetRels().size()
				+ "relation to persist.");
		Set<WnUnitSynsetRel> validated = validateRelations();
		try {
			wnUnitSynsetRelDao.perisistAllNative(validated);
		} catch (SystemException e) {
			LOG.debug(e);
		}
	}

	private Set<WnUnitSynsetRel> validateRelations() {
		Set<WnUnitSynsetRel> result = new HashSet<>();
		LOG.info("WordNet - Synset relations validation.");

		for (WnUnitSynsetRel rel : dictionary.getWnUnitSynsetRels()) {
			if (synsetIds.contains(rel.getSynsetId())
					&& jednWnIds.contains(rel.getWnUnitId())) {
				result.add(rel);
			}
		}
		return result;
	}

	private void exportRelationTypes() {
		StopWatch watch = new StopWatch(RelationTypeDao.class,
				"persisting reltion types...", TimeUnit.MILISECOND);
		watch.start();
		LOG.info(dictionary.getRelationTypes().size()
				+ "relation types persisted.\n");

		try {
			relationTypeDao.persistAllNative(dictionary.getRelationTypes());
		} catch (SystemException e) {
			LOG.debug(e);
		}
		watch.stop();
	}

	private void exportLexicalRels() {
		StopWatch watch = new StopWatch(LexicalRelDao.class,
				"persisting lexical relations", TimeUnit.SECOND);
		watch.start();
		Set<LexicalRelRaw> noDup = validateLexRelations();
		try {
			lexicalRelDao.persistAllNative(noDup);
		} catch (SystemException e) {
			LOG.debug(e);
		}
		watch.stop();
	}

	private Set<LexicalRelRaw> validateLexRelations() {
		Set<LexicalRelRaw> validated = new HashSet<>();
		LOG.info("Lexical relation to persist: "
				+ dictionary.getLexicalRelRaws().size() + "\n Validation...");
		for (LexicalRelRaw rel : dictionary.getLexicalRelRaws()) {
			if (jednWnIds.contains(rel.getChild())
					&& jednWnIds.contains(rel.getParent())
					&& typyRelIds.contains(rel.getRelation()))
				validated.add(rel);
		}
		LOG.info("Numer of lexical relation after validation: "
				+ validated.size());
		return validated;

	}

	private void exportSynsetRels() {
		StopWatch watch = new StopWatch(SynsetRelationDao.class,
				"persisting synset relations", TimeUnit.SECOND);
		watch.start();
		Set<SynsetRelationRaw> noDup = validateSynRelation();
		try {
			synsetRelDao.persistAllNative(noDup);
		} catch (SystemException e) {
			LOG.debug(e);
		}
		watch.stop();
	}

	private Set<SynsetRelationRaw> validateSynRelation() {
		Set<SynsetRelationRaw> validated = new HashSet<>();

		for (SynsetRelationRaw rel : dictionary.getSynsetRelations()) {
			if (synsetIds.contains(rel.getParent())
					&& synsetIds.contains(rel.getChild())
					&& typyRelIds.contains(rel.getRelation()))
				validated.add(rel);
		}
		return validated;
	}

	private final Set<Integer> synsetIds = new HashSet<>();
	private final Set<Integer> jednWnIds = new HashSet<>();
	private final Set<Integer> typyRelIds = new HashSet<>();
}
