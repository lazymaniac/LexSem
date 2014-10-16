package pl.semantyk.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ZNACZENIA")
public class Importance implements Serializable, Cloneable {

    private static final long serialVersionUID = -896656011238652635L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ZNACZENIA", unique = true, nullable = false)
    private Integer id;

    @Column(name = "ZNACZENIE", nullable = false, length = 255)
    private String importance;

    @ManyToOne(targetEntity = PartOfSpeech.class,
            optional = false)
    @JoinColumn(name = "ID_CZESC_MOWY")
    private PartOfSpeech partOfSpeech;

    @OneToMany(mappedBy = "example",
            targetEntity = Example.class,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Example> examples = new ArrayList<>();

    @OneToMany(mappedBy = "importance",
            targetEntity = Collocation.class,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Collocation> collocations = new ArrayList<>();

    @OneToMany(mappedBy = "importance",
            targetEntity = Synonym.class,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Synonym> synonyms = new ArrayList<>();

    @OneToMany(mappedBy = "importance",
            targetEntity = Antonym.class,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Antonym> antonyms = new ArrayList<>();

    @OneToMany(mappedBy = "importance",
            targetEntity = Cognate.class,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Cognate> cognates = new ArrayList<>();

    @OneToMany(mappedBy = "importance",
            targetEntity = Phraseology.class,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Phraseology> phraseologies = new ArrayList<>();

    @OneToMany(mappedBy = "importance",
            targetEntity = NounVar.class,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<NounVar> nounVars = new ArrayList<>();

    @OneToMany(mappedBy = "importance",
            targetEntity = VerbVar.class,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<VerbVar> verbVars = new ArrayList<>();

    @OneToMany(mappedBy = "importance",
            targetEntity = AdjectiveVar.class,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<AdjectiveVar> adjectiveVars = new ArrayList<>();

    @OneToMany(mappedBy = "importance",
            targetEntity = AdverbVar.class,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<AdverbVar> adverbVars = new ArrayList<>();

    @OneToMany(mappedBy = "importance",
            targetEntity = PronounVar.class,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<PronounVar> pronounVars = new ArrayList<>();

    @Transient
    private List<NumeralVar> numeralVars = new ArrayList<>();

    public Importance(String importance, PartOfSpeech partOfSpeech) {
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

    public PartOfSpeech getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(PartOfSpeech partOfSpeech) {
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
        if (this == o) {
            return true;
        }
        if (!(o instanceof Importance)) {
            return false;
        }

        Importance that = (Importance) o;

        if (antonyms != null ? !antonyms.equals(that.antonyms) : that.antonyms != null) {
            return false;
        }
        if (verbVars != null ? !verbVars.equals(that.verbVars) : that.verbVars != null) {
            return false;
        }
        if (phraseologies != null ? !phraseologies.equals(that.phraseologies) : that.phraseologies != null) {
            return false;
        }
        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (collocations != null ? !collocations.equals(that.collocations) : that.collocations != null) {
            return false;
        }
        if (numeralVars != null ? !numeralVars.equals(that.numeralVars) : that.numeralVars != null) {
            return false;
        }
        if (cognates != null ? !cognates.equals(that.cognates) : that.cognates != null) {
            return false;
        }
        if (examples != null ? !examples.equals(that.examples) : that.examples != null) {
            return false;
        }
        if (adjectiveVars != null ? !adjectiveVars.equals(that.adjectiveVars) : that.adjectiveVars != null) {
            return false;
        }
        if (adverbVars != null ? !adverbVars.equals(that.adverbVars) : that.adverbVars != null) {
            return false;
        }
        if (nounVars != null ? !nounVars.equals(that.nounVars) : that.nounVars != null) {
            return false;
        }
        if (synonyms != null ? !synonyms.equals(that.synonyms) : that.synonyms != null) {
            return false;
        }
        if (pronounVars != null ? !pronounVars.equals(that.pronounVars) : that.pronounVars != null) {
            return false;
        }
        return !(importance != null ? !importance.equals(that.importance) : that.importance != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (importance != null ? importance.hashCode() : 0);
        result = 31 * result + (examples != null ? examples.hashCode() : 0);
        result = 31 * result + (collocations != null ? collocations.hashCode() : 0);
        result = 31 * result + (synonyms != null ? synonyms.hashCode() : 0);
        result = 31 * result + (antonyms != null ? antonyms.hashCode() : 0);
        result = 31 * result + (cognates != null ? cognates.hashCode() : 0);
        result = 31 * result + (phraseologies != null ? phraseologies.hashCode() : 0);
        result = 31 * result + (nounVars != null ? nounVars.hashCode() : 0);
        result = 31 * result + (verbVars != null ? verbVars.hashCode() : 0);
        result = 31 * result + (adjectiveVars != null ? adjectiveVars.hashCode() : 0);
        result = 31 * result + (adverbVars != null ? adverbVars.hashCode() : 0);
        result = 31 * result + (pronounVars != null ? pronounVars.hashCode() : 0);
        result = 31 * result + (numeralVars != null ? numeralVars.hashCode() : 0);
        return result;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nImportance [id=");
		builder.append(id);
		builder.append(", \nimportance=");
		builder.append(importance);
		builder.append(", \nexamples=");
		builder.append(examples);
		builder.append(", \ncollocations=");
		builder.append(collocations);
		builder.append(", \nsynonyms=");
		builder.append(synonyms);
		builder.append(", \nantonyms=");
		builder.append(antonyms);
		builder.append(", \ncognates=");
		builder.append(cognates);
		builder.append(", \nphraseologies=");
		builder.append(phraseologies);
		builder.append(", \nnounVars=");
		builder.append(nounVars);
		builder.append(", \nverbVars=");
		builder.append(verbVars);
		builder.append(", \nadjectiveVars=");
		builder.append(adjectiveVars);
		builder.append(", \nadverbVars=");
		builder.append(adverbVars);
		builder.append(", \npronounVars=");
		builder.append(pronounVars);
		builder.append(", \nnumeralVars=");
		builder.append(numeralVars);
		builder.append("]\n");
		return builder.toString();
	}

    
}
