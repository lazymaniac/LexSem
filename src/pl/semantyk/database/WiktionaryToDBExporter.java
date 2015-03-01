package pl.semantyk.database;

import org.apache.log4j.Logger;
import pl.semantyk.dao.CrudDao;
import pl.semantyk.domain.AdjectiveDegreeVar;
import pl.semantyk.domain.AdjectiveVar;
import pl.semantyk.domain.AdverbVar;
import pl.semantyk.domain.Antonym;
import pl.semantyk.domain.CasesVar;
import pl.semantyk.domain.Cognate;
import pl.semantyk.domain.Collocation;
import pl.semantyk.domain.Example;
import pl.semantyk.domain.Importance;
import pl.semantyk.domain.NounVar;
import pl.semantyk.domain.PartOfSpeech;
import pl.semantyk.domain.PersonVar;
import pl.semantyk.domain.Phraseology;
import pl.semantyk.domain.PronounVar;
import pl.semantyk.domain.Synonym;
import pl.semantyk.domain.VerbVar;
import pl.semantyk.domain.WikiUnit;
import pl.semantyk.enums.TimeUnit;
import pl.semantyk.exceptions.SystemException;
import pl.semantyk.main.Dictionary;
import pl.semantyk.utils.StopWatch;

import java.util.List;

import static pl.semantyk.dao.DaoFactory.getDaoFor;

/**
 * Persists all Wiktionary entities into configured database.
 * @author Sebastian Fabisz
 */
public class WiktionaryToDBExporter {
    private static final Logger LOG = Logger.getLogger(WiktionaryToDBExporter.class);

    // Wikt export.
    private final CrudDao wikiUnitDao = getDaoFor(WikiUnit.class);
    private final CrudDao partOfSpeechDao = getDaoFor(PartOfSpeech.class);
    private final CrudDao importanceDao = getDaoFor(Importance.class);
    private final CrudDao synonymDao = getDaoFor(Synonym.class);
    private final CrudDao exampleDao = getDaoFor(Example.class);
    private final CrudDao antonymDao = getDaoFor(Antonym.class);
    private final CrudDao phraseologyDao = getDaoFor(Phraseology.class);
    private final CrudDao collocationDao = getDaoFor(Collocation.class);
    private final CrudDao cognatesDao = getDaoFor(Cognate.class);
    private final CrudDao nounVarDao = getDaoFor(NounVar.class);
    private final CrudDao pronounVarDao = getDaoFor(PronounVar.class);
    private final CrudDao adjectiveVarDao = getDaoFor(AdjectiveVar.class);
    private final CrudDao adjectiveDegreeVarDao = getDaoFor(AdjectiveDegreeVar.class);
    private final CrudDao casesVarDao = getDaoFor(CasesVar.class);
    private final CrudDao verbVarDao = getDaoFor(VerbVar.class);
    private final CrudDao personVarDao = getDaoFor(PersonVar.class);
    private final CrudDao adverbVarDao = getDaoFor(AdverbVar.class);

    private WikiCollector wikiCollector;

    private Dictionary dictionary;

    public WiktionaryToDBExporter(Dictionary dictionary) {
        this.dictionary = dictionary;
        wikiCollector = new WikiCollector(dictionary);
        wikiCollector.collect();
    }

    public void exportDatabase() {
        exportWikitionary();
    }


    private void exportWikitionary() {
        StopWatch watch = new StopWatch(CrudDao.class,
                "Persisting Wiktionary units...", TimeUnit.SECOND);
        watch.start();
        try {
            wikiUnitDao.persistAll(wikiCollector.getAllWikiUnits());
            exportPartOfSpeech(wikiCollector.getAllPartOfSpeeches());
            exportImportance(wikiCollector.getAllImportances());
            exportAdverbVar(wikiCollector.getAllAdverbVars());
            exportVerbVar(wikiCollector.getAllVerbVars());
            exportAdjectiveVar(wikiCollector.getAllAdjectiveVars());
            exportPronounVar(wikiCollector.getAllPronounVars());
            exportNounVar(wikiCollector.getAllNounVars());
            exportCognates(wikiCollector.getAllCognates());
            exportCollocation(wikiCollector.getAllCollocations());
            exportPhraseology(wikiCollector.getAllPhraseologies());
            exportAntonyms(wikiCollector.getAllAntonyms());
            exportExamples(wikiCollector.getAllExamples());
            exportSynonyms(wikiCollector.getAllSynonyms());
            exportAdjectiveDegreeVars(wikiCollector.getAllAdjectiveDegreeVars());
            exportCasesVars(wikiCollector.getAllCasesVars());
            exportPersonsVars(wikiCollector.getAllPersonVars());
        } catch (SystemException e) {
            LOG.debug(e);
        }
        watch.stop();
    }

    private void exportPersonsVars(List<PersonVar> allPersonVars) {
        StopWatch watch = new StopWatch(CrudDao.class,
                "Persisting personal vars...", TimeUnit.SECOND);
        watch.start();
        try {
            personVarDao.persistAll(allPersonVars);
        } catch (SystemException e) {
            LOG.debug(e);
        }
        watch.stop();
    }

    private void exportCasesVars(List<CasesVar> allCasesVars) {
        StopWatch watch = new StopWatch(CrudDao.class,
                "Persisting cases varieties...", TimeUnit.SECOND);
        watch.start();
        try {
            casesVarDao.persistAll(allCasesVars);
        } catch (SystemException e) {
            LOG.debug(e);
        }
        watch.stop();
    }

    private void exportPartOfSpeech(List<PartOfSpeech> partsOfSpeech) throws SystemException {
        StopWatch watch = new StopWatch(CrudDao.class,
                "Persisting parts of speech...", TimeUnit.SECOND);
        watch.start();
        try {
            partOfSpeechDao.persistAll(partsOfSpeech);
        } catch (SystemException e) {
            LOG.debug(e);
        }
        watch.stop();
    }

    private void exportImportance(List<Importance> importances) throws SystemException {
        StopWatch watch = new StopWatch(CrudDao.class,
                "Persisting importances...", TimeUnit.SECOND);
        watch.start();
        try {
            importanceDao.persistAll(importances);
        } catch (SystemException e) {
            LOG.debug(e);
        }
        watch.stop();
    }

    private void exportAdverbVar(List<AdverbVar> adverbVars) throws SystemException {
        StopWatch watch = new StopWatch(CrudDao.class,
                "Persisting adverb varieties...", TimeUnit.SECOND);
        watch.start();
        try {
            adverbVarDao.persistAll(adverbVars);
        } catch (SystemException e) {
            LOG.debug(e);
        }
        watch.stop();
    }

    private void exportVerbVar(List<VerbVar> verbVars) throws SystemException {
        StopWatch watch = new StopWatch(CrudDao.class,
                "Persisting verb vars...", TimeUnit.SECOND);
        watch.start();
        try {
            verbVarDao.persistAll(verbVars);
        } catch (SystemException e) {
            LOG.debug(e);
        }
        watch.stop();
    }

    private void exportAdjectiveVar(List<AdjectiveVar> adjectiveVars) throws SystemException {
        StopWatch watch = new StopWatch(CrudDao.class,
                "Persisting adjective varieties...", TimeUnit.SECOND);
        watch.start();
        try {
            adjectiveVarDao.persistAll(adjectiveVars);
        } catch (SystemException e) {
            LOG.debug(e);
        }
        watch.stop();
    }

    private void exportPronounVar(List<PronounVar> pronounVars) throws SystemException {
        StopWatch watch = new StopWatch(CrudDao.class,
                "Persisting pronoun varieties...", TimeUnit.SECOND);
        watch.start();
        try {
            pronounVarDao.persistAll(pronounVars);
        } catch (SystemException e) {
            LOG.debug(e);
        }
        watch.stop();
    }

    private void exportNounVar(List<NounVar> nounVar) throws SystemException {
        StopWatch watch = new StopWatch(CrudDao.class, "Persisting noun varieties...", TimeUnit.SECOND);
        watch.start();
        try {
            nounVarDao.persistAll(nounVar);
        } catch (SystemException e) {
            LOG.debug(e);
        }
        watch.stop();
    }

    private void exportCognates(List<Cognate> cognates) throws SystemException {
        StopWatch watch = new StopWatch(CrudDao.class,
                "Persisting cognates...", TimeUnit.SECOND);
        watch.start();
        try {
            cognatesDao.persistAll(cognates);
        } catch (SystemException e) {
            LOG.debug(e);
        }
        watch.stop();
    }

    private void exportCollocation(List<Collocation> collocations) throws SystemException {
        StopWatch watch = new StopWatch(CrudDao.class,
                "Persisting collocations...", TimeUnit.SECOND);
        watch.start();
        try {
            collocationDao.persistAll(collocations);
        } catch (SystemException e) {
            LOG.debug(e);
        }
        watch.stop();
    }

    private void exportPhraseology(List<Phraseology> phraseology) throws SystemException {
        StopWatch watch = new StopWatch(CrudDao.class,
                "Persisting phraseology...", TimeUnit.SECOND);
        watch.start();
        try {
            phraseologyDao.persistAll(phraseology);
        } catch (SystemException e) {
            LOG.debug(e);
        }
        watch.stop();
    }

    private void exportAntonyms(List<Antonym> antonyms) throws SystemException {
        StopWatch watch = new StopWatch(CrudDao.class,
                "Persisting antonyms...", TimeUnit.SECOND);
        watch.start();
        try {
            antonymDao.persistAll(antonyms);
        } catch (SystemException e) {
            LOG.debug(e);
        }
        watch.stop();
    }

    private void exportExamples(List<Example> examples) throws SystemException {
        StopWatch watch = new StopWatch(CrudDao.class,
                "Persisting exaples...", TimeUnit.SECOND);
        watch.start();
        try {
            exampleDao.persistAll(examples);
        } catch (SystemException e) {
            LOG.debug(e);
        }
        watch.stop();
    }

    private void exportSynonyms(List<Synonym> synonyms) throws SystemException {
        StopWatch watch = new StopWatch(CrudDao.class,
                "Persisting synonyms...", TimeUnit.SECOND);
        watch.start();
        try {
            synonymDao.persistAll(synonyms);
        } catch (SystemException e) {
            LOG.debug(e);
        }
        watch.stop();
    }

    private void exportAdjectiveDegreeVars(List<AdjectiveDegreeVar> adjectiveDegreeVars) {
        StopWatch watch = new StopWatch(CrudDao.class,
                "Persisting adjective degree vars...", TimeUnit.SECOND);
        watch.start();
        try {
            adjectiveDegreeVarDao.persistAll(adjectiveDegreeVars);
        } catch (SystemException e) {
            LOG.debug(e);
        }
        watch.stop();
    }
    
}
