package pl.semantyk.domain;

import org.apache.commons.lang3.StringUtils;
import pl.semantyk.utils.ConjugationGenerator;
import pl.semantyk.wikiparser.WikiNumeration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CZASOWNIK_ODM")
public class VerbVar implements Serializable, Cloneable {

	private static final long serialVersionUID = -4928762484910864537L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_CZASOWNIK_ODM", nullable = false, unique = true)
	private Integer id;

	@Transient
	private WikiNumeration numeration;

	@ManyToOne(targetEntity = Importance.class, optional = false)
	@JoinColumn(name = "ID_ZNACZENIE", nullable = false)
	private Importance importance;

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
	@OneToMany(mappedBy = "verbVar", cascade = CascadeType.ALL, targetEntity = PersonVar.class, orphanRemoval = true)
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

	public Importance getImportance() {
		return importance;
	}

	public void setImportance(Importance importance) {
		this.importance = importance;
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
		var.setVerbVar(this);
		this.personVars.add(var);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		VerbVar that = (VerbVar) o;

		if (inifitive != null ? !inifitive.equals(that.inifitive)
				: that.inifitive != null)
			return false;
		if (perfective != null ? !perfective.equals(that.perfective)
				: that.perfective != null)
			return false;
		if (isPerfective != null ? !isPerfective.equals(that.isPerfective)
				: that.isPerfective != null)
			return false;
		if (impersonalFormPast != null ? !impersonalFormPast
				.equals(that.impersonalFormPast)
				: that.impersonalFormPast != null)
			return false;
		if (id != null ? !id.equals(that.id) : that.id != null)
			return false;
		if (adverbialParticiplePrior != null ? !adverbialParticiplePrior
				.equals(that.adverbialParticiplePrior)
				: that.adverbialParticiplePrior != null)
			return false;
		if (adverbialParticipleContemporary != null ? !adverbialParticipleContemporary
				.equals(that.adverbialParticipleContemporary)
				: that.adverbialParticipleContemporary != null)
			return false;
		if (conjugation != null ? !conjugation.equals(that.conjugation)
				: that.conjugation != null)
			return false;
		if (imperfective != null ? !imperfective.equals(that.imperfective)
				: that.imperfective != null)
			return false;
		if (numeration != null ? !numeration.equals(that.numeration)
				: that.numeration != null)
			return false;
		if (personVars != null ? !personVars.equals(that.personVars)
				: that.personVars != null)
			return false;
		if (gerund != null ? !gerund.equals(that.gerund) : that.gerund != null)
			return false;
		if (topic != null ? !topic.equals(that.topic) : that.topic != null)
			return false;
		if (reflexivePronoun != null ? !reflexivePronoun
				.equals(that.reflexivePronoun) : that.reflexivePronoun != null)
			return false;
		return !(isReflexivVerb != null ? !isReflexivVerb
				.equals(that.isReflexivVerb) : that.isReflexivVerb != null);

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (numeration != null ? numeration.hashCode() : 0);
		result = 31 * result
				+ (isPerfective != null ? isPerfective.hashCode() : 0);
		result = 31 * result
				+ (isReflexivVerb != null ? isReflexivVerb.hashCode() : 0);
		result = 31 * result + (topic != null ? topic.hashCode() : 0);
		result = 31 * result
				+ (conjugation != null ? conjugation.hashCode() : 0);
		result = 31 * result
				+ (reflexivePronoun != null ? reflexivePronoun.hashCode() : 0);
		result = 31 * result + (perfective != null ? perfective.hashCode() : 0);
		result = 31 * result
				+ (imperfective != null ? imperfective.hashCode() : 0);
		result = 31 * result + (inifitive != null ? inifitive.hashCode() : 0);
		result = 31
				* result
				+ (impersonalFormPast != null ? impersonalFormPast.hashCode()
						: 0);
		result = 31 * result + (personVars != null ? personVars.hashCode() : 0);
		result = 31
				* result
				+ (adverbialParticipleContemporary != null ? adverbialParticipleContemporary
						.hashCode() : 0);
		result = 31
				* result
				+ (adverbialParticiplePrior != null ? adverbialParticiplePrior
						.hashCode() : 0);
		result = 31 * result + (gerund != null ? gerund.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "VerbVar {" + "id=" + id + ", numeration=" + numeration
				+ ", isPerfective=" + isPerfective + ", isReflexive="
				+ isReflexivVerb + ", topic='" + topic + '\''
				+ ", conjugation='" + conjugation + '\''
				+ ", reflexivePronoun='" + reflexivePronoun + '\''
				+ ", perfective='" + perfective + '\'' + ", imperfective='"
				+ imperfective + '\'' + ", infinitive='" + inifitive + '\''
				+ ", impersonalFormPast='" + impersonalFormPast + '\''
				+ ", personVars=" + personVars
				+ ", adverbialParticipleContemporary='"
				+ adverbialParticipleContemporary + '\''
				+ ", adverbialParticiplePrior='" + adverbialParticiplePrior
				+ '\'' + ", gerund='" + gerund + '\'' + '}';
	}
}
