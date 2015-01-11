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

    private Dictionary dictionary;

    public WiktionaryToDBExporter(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void exportDatabase() {
        exportWikitionary();
    }

    private void exportWikitionary() {
        StopWatch watch = new StopWatch(CrudDao.class,
                "Persisting Wiktionary units...", TimeUnit.SECOND);
        watch.start();
        try {
            wikiUnitDao.persistAll(dictionary.getWikiUnits());
            for (WikiUnit unit: dictionary.getWikiUnits()) {
                exportPartOfSpeech(unit.getPartsOfSpeech());
            }
        } catch (SystemException e) {
            LOG.debug(e);
            e.printStackTrace();
        }
        watch.stop();
    }

    private void exportPartOfSpeech(List<PartOfSpeech> partsOfSpeech) throws SystemException {
        partOfSpeechDao.persistAll(partsOfSpeech);
        for (PartOfSpeech entity: partsOfSpeech)
            exportImportance(entity.getImportances());
    }

    private void exportImportance(List<Importance> importances) throws SystemException {
        importanceDao.persistAll(importances);
        for (Importance importance: importances) {
            exportSynonyms(importance.getSynonyms());
            exportExamples(importance.getExamples());
            exportAntonyms(importance.getAntonyms());
            exportPhraseology(importance.getPhraseology());
            exportCollocation(importance.getCollocations());
            exportCognates(importance.getCognates());
            exportNounVar(importance.getNounVar());
            exportPronounVar(importance.getPronounVars());
            exportAdjectiveVar(importance.getAdjectiveVars());
            exportVerbVar(importance.getVerbVars());
            exportAdverbVar(importance.getAdverbVars());
        }
    }

    private void exportAdverbVar(List<AdverbVar> adverbVars) throws SystemException {
        adverbVarDao.persistAll(adverbVars);
    }

    private void exportVerbVar(List<VerbVar> verbVars) throws SystemException {
        verbVarDao.persistAll(verbVars);
        for (VerbVar verbVar: verbVars) {
            personVarDao.persistAll(verbVar.getPersonalVars());
        }
    }

    private void exportAdjectiveVar(List<AdjectiveVar> adjectiveVars) throws SystemException {
        adjectiveVarDao.persistAll(adjectiveVars);
        for (AdjectiveVar adjectiveVar: adjectiveVars) {
            adjectiveDegreeVarDao.persistAll(adjectiveVar.getDegree());
            for (AdjectiveDegreeVar adjectiveDegreeVar: adjectiveVar.getDegree())
                casesVarDao.persistAll(adjectiveDegreeVar.getCasesVar());
        }
    }

    private void exportPronounVar(List<PronounVar> pronounVars) throws SystemException {
        pronounVarDao.persistAll(pronounVars);
        for (PronounVar pronounVar: pronounVars) {
            casesVarDao.persist(pronounVar.getCasesVar());
        }
    }

    private void exportNounVar(List<NounVar> nounVar) throws SystemException {
        nounVarDao.persistAll(nounVar);
        for (NounVar entity: nounVar) {
            casesVarDao.persistAll(entity.getCasesVar());
        }
    }

    private void exportCognates(List<Cognate> cognates) throws SystemException {
        cognatesDao.persistAll(cognates);
    }

    private void exportCollocation(List<Collocation> collocations) throws SystemException {
        collocationDao.persistAll(collocations);
    }

    private void exportPhraseology(List<Phraseology> phraseology) throws SystemException {
        phraseologyDao.persistAll(phraseology);
    }

    private void exportAntonyms(List<Antonym> antonyms) throws SystemException {
        antonymDao.persistAll(antonyms);
    }

    private void exportExamples(List<Example> examples) throws SystemException {
        exampleDao.persistAll(examples);
    }

    private void exportSynonyms(List<Synonym> synonyms) throws SystemException {
        synonymDao.persistAll(synonyms);
    }



}
