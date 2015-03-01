package pl.semantyk.database;

import org.apache.log4j.Logger;
import pl.semantyk.dao.CrudDao;
import pl.semantyk.domain.*;
import pl.semantyk.enums.TimeUnit;
import pl.semantyk.main.Dictionary;
import pl.semantyk.utils.StopWatch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2015-02-23.
 */
public class WikiCollector {

    private final Dictionary dictionary;

    public WikiCollector(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void collect() {
        StopWatch watch = new StopWatch(WikiCollector.class, "WikiCollector starts...", TimeUnit.SECOND);
        watch.start();
        collectWikiUnits();
        watch.stop();
    }

    private void collectWikiUnits() {
        allWikiUnits = dictionary.getWikiUnits();
        for (WikiUnit wikiUnit : allWikiUnits) {
            collectPartOfSpeches(wikiUnit.getPartsOfSpeech());
        }
    }

    private void collectPartOfSpeches(List<PartOfSpeech> partOfSpeeches) {
        allPartOfSpeeches.addAll(partOfSpeeches);
        for (PartOfSpeech partOfSpeech : partOfSpeeches) {
            collectImportance(partOfSpeech.getImportances());
        }
    }

    private void collectImportance(List<Importance> importances) {
        allImportances.addAll(importances);
        for (Importance importance : importances) {
            collectSynonyms(importance.getSynonyms());
            collectExamples(importance.getExamples());
            collectAntonyms(importance.getAntonyms());
            collectPhraseology(importance.getPhraseology());
            collectCollocation(importance.getCollocations());
            collectCognates(importance.getCognates());
            collectNounVar(importance.getNounVar());
            collectPronounVar(importance.getPronounVars());
            collectAdjectiveVar(importance.getAdjectiveVars());
            collectVerbVar(importance.getVerbVars());
            collectAdverbVar(importance.getAdverbVars());
        }
    }

    private void collectAdverbVar(List<AdverbVar> adverbVars) {
        allAdverbVars.addAll(adverbVars);
    }

    private void collectVerbVar(List<VerbVar> verbVars) {
        allVerbVars.addAll(verbVars);
        for (VerbVar verbVar: verbVars) {
            collectPersonVars(verbVar.getPersonalVars());
        }
    }

    private void collectPersonVars(List<PersonVar> personalVars) {
        allPersonVars.addAll(personalVars);
    }

    private void collectAdjectiveVar(List<AdjectiveVar> adjectiveVars) {
        allAdjectiveVars.addAll(adjectiveVars);
        for (AdjectiveVar adjectiveVar: adjectiveVars) {
            collectAdjectiveVarDegrees(adjectiveVar.getDegrees());
        }
    }

    private void collectAdjectiveVarDegrees(List<AdjectiveDegreeVar> degrees) {
        allAdjectiveDegreeVars.addAll(degrees);
        for (AdjectiveDegreeVar adjectiveDegreeVar: degrees) {
            collectCasesVars(adjectiveDegreeVar.getCasesVar());
        }
    }

    private void collectPronounVar(List<PronounVar> pronounVars) {
        allPronounVars.addAll(pronounVars);
        for (PronounVar pronounVar: pronounVars) {
            collectCasesVars(pronounVar.getCasesVar());
        }
    }

    private void collectCasesVars(CasesVar casesVar) {
        allCasesVars.add(casesVar);
    }

    private void collectNounVar(List<NounVar> nounVars) {
        allNounVars.addAll(nounVars);
        for (NounVar nounVar : nounVars) {
            collectCasesVars(nounVar.getCasesVar());
        }
    }

    private void collectCasesVars(List<CasesVar> casesVar) {
        allCasesVars.addAll(casesVar);
    }

    private void collectCognates(List<Cognate> cognates) {
        allCognates.addAll(cognates);
    }

    private void collectCollocation(List<Collocation> collocations) {
        allCollocations.addAll(collocations);
    }

    private void collectPhraseology(List<Phraseology> phraseology) {
        allPhraseologies.addAll(phraseology);
    }

    private void collectAntonyms(List<Antonym> antonyms) {
        allAntonyms.addAll(antonyms);
    }

    private void collectExamples(List<Example> examples) {
        allExamples.addAll(examples);
    }

    private void collectSynonyms(List<Synonym> synonyms) {
        allSynonyms.addAll(synonyms);
    }

    public List<WikiUnit> getAllWikiUnits() {
        return allWikiUnits;
    }

    public List<PartOfSpeech> getAllPartOfSpeeches() {
        return allPartOfSpeeches;
    }

    public List<Importance> getAllImportances() {
        return allImportances;
    }

    public List<Synonym> getAllSynonyms() {
        return allSynonyms;
    }

    public List<Example> getAllExamples() {
        return allExamples;
    }

    public List<Antonym> getAllAntonyms() {
        return allAntonyms;
    }

    public List<Phraseology> getAllPhraseologies() {
        return allPhraseologies;
    }

    public List<Collocation> getAllCollocations() {
        return allCollocations;
    }

    public List<Cognate> getAllCognates() {
        return allCognates;
    }

    public List<NounVar> getAllNounVars() {
        return allNounVars;
    }

    public List<PronounVar> getAllPronounVars() {
        return allPronounVars;
    }

    public List<AdjectiveVar> getAllAdjectiveVars() {
        return allAdjectiveVars;
    }

    public List<AdjectiveDegreeVar> getAllAdjectiveDegreeVars() {
        return allAdjectiveDegreeVars;
    }

    public List<CasesVar> getAllCasesVars() {
        return allCasesVars;
    }

    public List<VerbVar> getAllVerbVars() {
        return allVerbVars;
    }

    public List<PersonVar> getAllPersonVars() {
        return allPersonVars;
    }

    public List<AdverbVar> getAllAdverbVars() {
        return allAdverbVars;
    }

    private List<WikiUnit> allWikiUnits = new ArrayList<>();
    private List<PartOfSpeech> allPartOfSpeeches = new ArrayList<>();
    private List<Importance> allImportances = new ArrayList<>();
    private List<Synonym> allSynonyms = new ArrayList<>();
    private List<Example> allExamples = new ArrayList<>();
    private List<Antonym> allAntonyms = new ArrayList<>();
    private List<Phraseology> allPhraseologies = new ArrayList<>();
    private List<Collocation> allCollocations = new ArrayList<>();
    private List<Cognate> allCognates = new ArrayList<>();
    private List<NounVar> allNounVars = new ArrayList<>();
    private List<PronounVar> allPronounVars = new ArrayList<>();
    private List<AdjectiveVar> allAdjectiveVars = new ArrayList<>();
    private List<AdjectiveDegreeVar> allAdjectiveDegreeVars = new ArrayList<>();
    private List<CasesVar> allCasesVars = new ArrayList<>();
    private List<VerbVar> allVerbVars = new ArrayList<>();
    private List<PersonVar> allPersonVars = new ArrayList<>();
    private List<AdverbVar> allAdverbVars = new ArrayList<>();
}

