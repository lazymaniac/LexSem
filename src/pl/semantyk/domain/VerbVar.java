package pl.semantyk.domain;

import org.apache.commons.lang3.StringUtils;
import pl.semantyk.dao.IdGenerator;
import pl.semantyk.domain.annotation.Column;
import pl.semantyk.domain.annotation.Id;
import pl.semantyk.domain.annotation.Table;
import pl.semantyk.utils.ConjugationGenerator;
import pl.semantyk.wikiparser.WikiNumeration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "CZASOWNIK_ODM")
public class VerbVar implements Serializable, Cloneable {

	private static final long serialVersionUID = -4928762484910864537L;

	@Id
	@Column(name = "ID_CZASOWNIK_ODM")
	private Integer id = IdGenerator.getId(this.getClass());

	private WikiNumeration numeration;

	@Column(name = "ID_ZNACZENIE")
	private Integer importance;

	@Column(name = "IS_DOKONANY")
	private Boolean isPerfective = false;

	/**
	 * Określa czy czasownik jest zwrotny.
	 */
	@Column(name = "ZWROTNY")
	private Boolean isReflexivVerb = false;

	/**
	 * Zawiera temat czasownika.
	 */
	@Column(name = "TEMAT")
	private String topic;

	/**
	 * Określa typ koniugacji czasownika.
	 */
	@Column(name = "KONIUGACJA")
	private String conjugation;

	// TODO dopisac zaimek zwrotny do koniugacji
	/**
	 * Zaimek zwrotny czasownika.
	 */
	@Column(name = "ZAIMEK_ZWROTNY")
	private String reflexivePronoun;

	/**
	 * Zawiera formę dokonaną czasownka.
	 */
	@Column(name = "DOKONANY")
	private String perfective;

	/**
	 * Zawiera forme niedokonana czasownika.
	 */
	@Column(name = "NIE_DOKONANY")
	private String imperfective;

	/**
	 * Froma bezokolicznikowa czasownika.
	 */
	@Column(name = "BEZOKOLICZNIK")
	private String inifitive;

	/**
	 * Forma bezosobowa czasu przeszłego.
	 */
	@Column(name = "FORM_BEZOSOB_CZAS_PRZESZ")
	private String impersonalFormPast;

	/**
	 * Lista odmian przez osoby.
	 */
	private List<PersonVar> personVars = new ArrayList<>();

	/**
	 * Imieslow przyslowkowy wspolczesny.
	 */
	@Column(name = "IMIES_PRZYS_WSPOL")
	private String adverbialParticipleContemporary;

	/**
	 * Imieslow przyslowkowy uprzedni (dokonany).
	 */
	@Column(name = "IMIES_PRZYS_UPRZED")
	private String adverbialParticiplePrior;

	/**
	 * Rzeczownik odczasownikowy.
	 */
	@Column(name = "RZECZ_ODCZAS")
	private String gerund;

	public VerbVar() {
	}

	public VerbVar(String topic, String conjugation) {
		this.topic = topic;
		this.conjugation = conjugation;
		fillConjugationVariety();
	}

	/**
	 * Wypelnia formularz odmiany czasownika. Wykorzystuje klase Koniugacja.
	 */
	public final void fillConjugationVariety() {
		ConjugationGenerator kon = new ConjugationGenerator(this, conjugation);
		if (StringUtils.isNotBlank(topic)
				&& StringUtils.isNotBlank(conjugation)) {
			kon.fillForm();
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public WikiNumeration getNumeration() {
		return numeration;
	}

	public void setNumeration(WikiNumeration numeration) {
		this.numeration = numeration;
	}

	public Boolean isPerfective() {
		return isPerfective;
	}

	public void setIsPerfective(Boolean isPerfective) {
		this.isPerfective = isPerfective;
	}

	public Boolean isReflexiveVerb() {
		return isReflexivVerb;
	}

	public void setIsReflexiveVerb(Boolean isReflexive) {
		this.isReflexivVerb = isReflexive;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getConjugation() {
		return conjugation;
	}

	public void setConjugation(String conjugation) {
		this.conjugation = conjugation;
	}

	public String getReflexivePronoun() {
		return reflexivePronoun;
	}

	public void setReflexivePronoun(String reflexivePronoun) {
		this.reflexivePronoun = reflexivePronoun;
	}

	public String getPerfective() {
		return perfective;
	}

	public void setPerfective(String perfective) {
		this.perfective = perfective;
	}

	public String getImperfective() {
		return imperfective;
	}

	public void setImperfective(String imperfective) {
		this.imperfective = imperfective;
	}

	public String getInfinitive() {
		return inifitive;
	}

	public void setInfinitive(String infinitive) {
		this.inifitive = infinitive;
	}

	public String getImpersonalFormPast() {
		return impersonalFormPast;
	}

	public void setImpersonalFormPast(String impersonalFormPast) {
		this.impersonalFormPast = impersonalFormPast;
	}

    public List<PersonVar> getPersonalVars() {
        return personVars;
    }

    public void setPersonalVars(List<PersonVar> peronalVars) {
        this.personVars = peronalVars;
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    public Boolean getIsPerfective() {
        return isPerfective;
    }

    public Boolean getIsReflexivVerb() {
        return isReflexivVerb;
    }

    public void setIsReflexivVerb(Boolean isReflexivVerb) {
        this.isReflexivVerb = isReflexivVerb;
    }

    public String getInifitive() {
        return inifitive;
    }

    public void setInifitive(String inifitive) {
        this.inifitive = inifitive;
    }

    public String getAdverbialParticipleContemporary() {
		return adverbialParticipleContemporary;
	}

	public void setAdverbialParticipleContemporary(
			String adverbialParticipleContemporary) {
		this.adverbialParticipleContemporary = adverbialParticipleContemporary;
	}

	public String getAdverbialParticiplePrior() {
		return adverbialParticiplePrior;
	}

	public void setAdverbialParticiplePrior(String adverbialParticiplePrior) {
		this.adverbialParticiplePrior = adverbialParticiplePrior;
	}

	public String getGerund() {
		return gerund;
	}

	public void setGerund(String gerund) {
		this.gerund = gerund;
	}

    public void addPersonVar(PersonVar var) {
        this.personVars.add(var);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VerbVar verbVar = (VerbVar) o;

        if (adverbialParticipleContemporary != null ? !adverbialParticipleContemporary.equals(verbVar.adverbialParticipleContemporary) : verbVar.adverbialParticipleContemporary != null)
            return false;
        if (adverbialParticiplePrior != null ? !adverbialParticiplePrior.equals(verbVar.adverbialParticiplePrior) : verbVar.adverbialParticiplePrior != null)
            return false;
        if (conjugation != null ? !conjugation.equals(verbVar.conjugation) : verbVar.conjugation != null) return false;
        if (gerund != null ? !gerund.equals(verbVar.gerund) : verbVar.gerund != null) return false;
        if (id != null ? !id.equals(verbVar.id) : verbVar.id != null) return false;
        if (imperfective != null ? !imperfective.equals(verbVar.imperfective) : verbVar.imperfective != null)
            return false;
        if (impersonalFormPast != null ? !impersonalFormPast.equals(verbVar.impersonalFormPast) : verbVar.impersonalFormPast != null)
            return false;
        if (importance != null ? !importance.equals(verbVar.importance) : verbVar.importance != null) return false;
        if (inifitive != null ? !inifitive.equals(verbVar.inifitive) : verbVar.inifitive != null) return false;
        if (isPerfective != null ? !isPerfective.equals(verbVar.isPerfective) : verbVar.isPerfective != null)
            return false;
        if (isReflexivVerb != null ? !isReflexivVerb.equals(verbVar.isReflexivVerb) : verbVar.isReflexivVerb != null)
            return false;
        if (numeration != null ? !numeration.equals(verbVar.numeration) : verbVar.numeration != null) return false;
        if (perfective != null ? !perfective.equals(verbVar.perfective) : verbVar.perfective != null) return false;
        if (reflexivePronoun != null ? !reflexivePronoun.equals(verbVar.reflexivePronoun) : verbVar.reflexivePronoun != null)
            return false;
        if (topic != null ? !topic.equals(verbVar.topic) : verbVar.topic != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (numeration != null ? numeration.hashCode() : 0);
        result = 31 * result + (importance != null ? importance.hashCode() : 0);
        result = 31 * result + (isPerfective != null ? isPerfective.hashCode() : 0);
        result = 31 * result + (isReflexivVerb != null ? isReflexivVerb.hashCode() : 0);
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (conjugation != null ? conjugation.hashCode() : 0);
        result = 31 * result + (reflexivePronoun != null ? reflexivePronoun.hashCode() : 0);
        result = 31 * result + (perfective != null ? perfective.hashCode() : 0);
        result = 31 * result + (imperfective != null ? imperfective.hashCode() : 0);
        result = 31 * result + (inifitive != null ? inifitive.hashCode() : 0);
        result = 31 * result + (impersonalFormPast != null ? impersonalFormPast.hashCode() : 0);
        result = 31 * result + (adverbialParticipleContemporary != null ? adverbialParticipleContemporary.hashCode() : 0);
        result = 31 * result + (adverbialParticiplePrior != null ? adverbialParticiplePrior.hashCode() : 0);
        result = 31 * result + (gerund != null ? gerund.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("VerbVar{");
        sb.append("id=").append(id);
        sb.append(", numeration=").append(numeration);
        sb.append(", importance=").append(importance);
        sb.append(", isPerfective=").append(isPerfective);
        sb.append(", isReflexivVerb=").append(isReflexivVerb);
        sb.append(", topic='").append(topic).append('\'');
        sb.append(", conjugation='").append(conjugation).append('\'');
        sb.append(", reflexivePronoun='").append(reflexivePronoun).append('\'');
        sb.append(", perfective='").append(perfective).append('\'');
        sb.append(", imperfective='").append(imperfective).append('\'');
        sb.append(", inifitive='").append(inifitive).append('\'');
        sb.append(", impersonalFormPast='").append(impersonalFormPast).append('\'');
        sb.append(", adverbialParticipleContemporary='").append(adverbialParticipleContemporary).append('\'');
        sb.append(", adverbialParticiplePrior='").append(adverbialParticiplePrior).append('\'');
        sb.append(", gerund='").append(gerund).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
