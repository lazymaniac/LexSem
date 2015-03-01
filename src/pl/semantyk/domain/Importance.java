package pl.semantyk.domain;

import pl.semantyk.databaseutils.IdGenerator;
import pl.semantyk.domain.annotation.Column;
import pl.semantyk.domain.annotation.Id;
import pl.semantyk.domain.annotation.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "ZNACZENIA")
public class Importance implements Serializable, Cloneable {

    private static final long serialVersionUID = -896656011238652635L;

    @Id
    @Column(name = "ID_ZNACZENIA")
    private Integer id = IdGenerator.getId(this.getClass());

    @Column(name = "ZNACZENIE")
    private String importance;

    @Column(name = "ID_CZESC_MOWY")
    private Integer partOfSpeech;

    private List<Example> examples = new ArrayList<>();

    private List<Collocation> collocations = new ArrayList<>();

    private List<Synonym> synonyms = new ArrayList<>();

    private List<Antonym> antonyms = new ArrayList<>();

    private List<Cognate> cognates = new ArrayList<>();

    private List<Phraseology> phraseologies = new ArrayList<>();

    private List<NounVar> nounVars = new ArrayList<>();

    private List<VerbVar> verbVars = new ArrayList<>();

    private List<AdjectiveVar> adjectiveVars = new ArrayList<>();

    private List<AdverbVar> adverbVars = new ArrayList<>();

    private List<PronounVar> pronounVars = new ArrayList<>();

    private List<NumeralVar> numeralVars = new ArrayList<>();

    public Importance(String importance, Integer partOfSpeech) {
        this.importance = importance;
        this.partOfSpeech = partOfSpeech;
    }

    public Importance() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public Integer getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(Integer partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public List<Example> getExamples() {
        return examples;
    }

    public void setExamples(List<Example> examples) {
        this.examples = examples;
    }

    public List<Collocation> getCollocations() {
        return collocations;
    }

    public void setCollocations(List<Collocation> collocations) {
        this.collocations = collocations;
    }

    public List<Synonym> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<Synonym> synonyms) {
        this.synonyms = synonyms;
    }

    public List<Antonym> getAntonyms() {
        return antonyms;
    }

    public void setAntonyms(List<Antonym> antonyms) {
        this.antonyms = antonyms;
    }

    public List<Cognate> getCognates() {
        return cognates;
    }

    public void setCognates(List<Cognate> cognates) {
        this.cognates = cognates;
    }

    public List<Phraseology> getPhraseology() {
        return phraseologies;
    }

    public void setPhraseologies(List<Phraseology> phraseologies) {
        this.phraseologies = phraseologies;
    }

    public List<NounVar> getNounVar() {
        return nounVars;
    }

    public void setNounVars(List<NounVar> nounVar) {
        this.nounVars = nounVar;
    }

    public List<VerbVar> getVerbVars() {
        return verbVars;
    }

    public void setVerbVars(List<VerbVar> verbVar) {
        this.verbVars = verbVar;
    }

    public List<AdjectiveVar> getAdjectiveVars() {
        return adjectiveVars;
    }

    public void setAdjectiveVars(List<AdjectiveVar> adjectiveVar) {
        this.adjectiveVars = adjectiveVar;
    }

    public List<AdverbVar> getAdverbVars() {
        return adverbVars;
    }

    public void setAdverbVars(List<AdverbVar> adverbVar) {
        this.adverbVars = adverbVar;
    }

    public List<PronounVar> getPronounVars() {
        return pronounVars;
    }

    public void setPronounVars(List<PronounVar> pronounVars) {
        this.pronounVars = pronounVars;
    }

    public List<NumeralVar> getNumeralVar() {
        return numeralVars;
    }

    public void setNumeralVars(List<NumeralVar> numeralVars) {
        this.numeralVars = numeralVars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Importance that = (Importance) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (importance != null ? !importance.equals(that.importance) : that.importance != null) return false;
        if (partOfSpeech != null ? !partOfSpeech.equals(that.partOfSpeech) : that.partOfSpeech != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (importance != null ? importance.hashCode() : 0);
        result = 31 * result + (partOfSpeech != null ? partOfSpeech.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Importance{");
        sb.append("id=").append(id);
        sb.append(", importance='").append(importance).append('\'');
        sb.append(", partOfSpeech=").append(partOfSpeech);
        sb.append('}');
        return sb.toString();
    }
}
