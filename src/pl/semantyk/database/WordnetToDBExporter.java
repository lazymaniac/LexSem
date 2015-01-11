package pl.semantyk.database;

import org.apache.log4j.Logger;
import pl.semantyk.dao.CrudDao;
import pl.semantyk.domain.*;
import pl.semantyk.enums.DatabaseType;
import pl.semantyk.enums.TimeUnit;
import pl.semantyk.exceptions.SystemException;
import pl.semantyk.main.Dictionary;
import pl.semantyk.parse.entities.LexicalRelRaw;
import pl.semantyk.parse.entities.SynsetRaw;
import pl.semantyk.parse.entities.SynsetRelationRaw;
import pl.semantyk.utils.StopWatch;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.semantyk.dao.DaoFactory.getDaoFor;

/**
 * Persists all WordNet units into configured database.
 *
 * @author Sebastian Fabisz.
 */
public class WordnetToDBExporter {

	private static final Logger LOG = Logger.getLogger(WordnetToDBExporter.class);

	// WN export.
	private final CrudDao wnUnitDao = getDaoFor(WnUnit.class);
	private final CrudDao lexicalRelDao = getDaoFor(LexicalRel.class);
	private final CrudDao relationTypeDao = getDaoFor(RelationType.class);
	private final CrudDao synsetRelDao = getDaoFor(SynsetRelation.class);
	private final CrudDao synsetDao = getDaoFor(Synset.class);
	private final CrudDao wnUnitSynsetRelDao = getDaoFor(WnUnitSynsetRel.class);

	private final Dictionary dictionary;

	public WordnetToDBExporter(Dictionary dictionary) {
		this.dictionary = dictionary;
		init();
	}

	private void init() {
		typyRelIds.addAll(dictionary.getRelationTypes().stream().map(RelationType::getId).collect(Collectors.toList()));
		synsetIds.addAll(dictionary.getSynsetRaws().stream().map(SynsetRaw::getId).collect(Collectors.toList()));
		jednWnIds.addAll(dictionary.getWnUnits().stream().map(WnUnit::getId).collect(Collectors.toList()));
	}

	public void exportDatabase(DatabaseType databaseType) {
		if (databaseType.equals(DatabaseType.WORDNET))
			exportWordnet();
	}

	private void exportWordnet() {
		exportWnUnits();
		exportSynset();
		exportWnUnitsSynRel();
		exportRelationTypes();
		exportLexicalRelations();
		exportSynsetRelations();
	}

	private void exportWnUnits() {
		StopWatch watch = new StopWatch(CrudDao.class, "Persisting WordNet units...", TimeUnit.SECOND);
		watch.start();
		Set<WnUnit> noDup = new HashSet<>(dictionary.getWnUnits());
		try {
			wnUnitDao.persistAll(noDup);
		} catch (SystemException e) {
			LOG.debug(e);
		}
		watch.stop();
	}

	private void exportSynset() {
		StopWatch watch = new StopWatch(CrudDao.class,
				"Persisting synsets...", TimeUnit.SECOND);
		watch.start();
		LOG.info(dictionary.getSynsetRaws().get(0).toString());
		Set<SynsetRaw> noDup = new HashSet<>(dictionary.getSynsetRaws());
		try {
			synsetDao.persistAll(noDup);
		} catch (SystemException e) {
			LOG.debug(e);
		}
		watch.stop();
	}

	private void exportWnUnitsSynRel() {
		StopWatch watch = new StopWatch(CrudDao.class, "Persisting relations between WordNet units and Synset.");
		watch.start();
		LOG.info(dictionary.getWnUnitSynsetRels().size() + " relation to persist.");
		Set<WnUnitSynsetRel> validated = validateRelations();
		try {
			wnUnitSynsetRelDao.persistAll(validated);
		} catch (SystemException e) {
			LOG.debug(e);
		}
	}

	private Set<WnUnitSynsetRel> validateRelations() {
		Set<WnUnitSynsetRel> result = new HashSet<>();
		LOG.info("WordNet - Synset relations validation.");

		result.addAll(dictionary.getWnUnitSynsetRels().stream().filter(rel -> synsetIds.contains(rel.getSynsetId())
				&& jednWnIds.contains(rel.getWnUnitId())).collect(Collectors.toList()));
		return result;
	}

	private void exportRelationTypes() {
		StopWatch watch = new StopWatch(CrudDao.class, "Persisting relation types...", TimeUnit.MILISECOND);
		watch.start();
		LOG.info(dictionary.getRelationTypes().size()
				+ " relation types persisted.\n");

		try {
			relationTypeDao.persistAll(dictionary.getRelationTypes());
		} catch (SystemException e) {
			LOG.debug(e);
		}
		watch.stop();
	}

	private void exportLexicalRelations() {
		StopWatch watch = new StopWatch(CrudDao.class, "Persisting lexical relations", TimeUnit.SECOND);
		watch.start();
		Set<LexicalRelRaw> noDup = validateLexRelations();
		try {
			lexicalRelDao.persistAll(noDup);
		} catch (SystemException e) {
			LOG.debug(e);
		}
		watch.stop();
	}

	private Set<LexicalRelRaw> validateLexRelations() {
		Set<LexicalRelRaw> validated = new HashSet<>();
		LOG.info("Lexical relation to persist: " + dictionary.getLexicalRelRaws().size() + "\n Validation...");

		validated.addAll(dictionary.getLexicalRelRaws().stream().filter(rel -> jednWnIds.contains(rel.getChild())
				&& jednWnIds.contains(rel.getParent())
				&& typyRelIds.contains(rel.getRelation())).collect(Collectors.toList()));

		LOG.info("Numer of lexical relation after validation: " + validated.size());
		return validated;

	}

	private void exportSynsetRelations() {
		StopWatch watch = new StopWatch(CrudDao.class, "persisting synset relations", TimeUnit.SECOND);
		watch.start();
		Set<SynsetRelationRaw> noDup = validateSynRelation();
		try {
			synsetRelDao.persistAll(noDup);
		} catch (SystemException e) {
			LOG.debug(e);
		}
		watch.stop();
	}

	private Set<SynsetRelationRaw> validateSynRelation() {

		Set<SynsetRelationRaw> validated = dictionary.getSynsetRelations().stream().filter(rel -> synsetIds.contains(rel.getParent())
				&& synsetIds.contains(rel.getChild())
				&& typyRelIds.contains(rel.getRelation())).collect(Collectors.toSet());

		return validated;
	}

	private final Set<Integer> synsetIds = new HashSet<>();
	private final Set<Integer> jednWnIds = new HashSet<>();
	private final Set<Integer> typyRelIds = new HashSet<>();
}
