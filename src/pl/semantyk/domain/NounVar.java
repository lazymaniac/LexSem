package pl.semantyk.domain;

import pl.semantyk.enums.NounGender;
import pl.semantyk.wikiparser.WikiNumeration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa przechowuje odmiane rzeczownika przez przypadki w liczbie pojedynczej i mnogiej.
 */
@Entity
@Table(name = "RZECZOWNIK_ODM")
public class NounVar implements Serializable, Cloneable {

    private static final long serialVersionUID = -6259828431906486481L;

    /**
     * Identyfiaktor encji w bazie danych.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_RZECZ_ODM", nullable = false, unique = true)
    private Integer id;

    /**
     * Zawiera ideksy znaczenie do którego należy odmiana.
     */
    @Transient
    private WikiNumeration numeration;

    /**
     * Temat słowotwórczy jednostki.
     */
    @Column(name = "TEMAT")
    private String topic;

    /**
     * Rodzaj rzeczownika.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "RODZAJ")
    private NounGender gender;

    /**
     * 2 rodzaj rzeczownika.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "DRUGI_RODZAJ")
    private NounGender secondGender;

    /**
     * Czy rzeczwonik jest odmienialny.
     */
    @Column(name = "ODMIENIALNY")
    private Boolean varietyAble = true;

    /**
     * Brak liczby pojedynczej.
     */
    @Column(name = "BRAK_LP")
    private Boolean noSingular = false;
    /**
     * Brak liczby mnogiej.
     */
    @Column(name = "BRAK_LM")
    private Boolean noPlural = false;

    @OneToMany(mappedBy = "nounVar",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            targetEntity = CasesVar.class)
    private List<CasesVar> casesVar = new ArrayList<>();

    /**
     * Referencja do znaczenia którego dotyczy odmiana.
     */
    @ManyToOne(targetEntity = Importance.class, optional = false)
    @JoinColumn(name = "ID_ZNACZENIE")
    private Importance importance;

    public NounVar() {
        // KONSTRUKTOR DOMYŚLNY
    }

    public NounVar(WikiNumeration numeracja) {
        this.numeration = numeracja;
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public NounGender getGender() {
        return gender;
    }

    public void setGender(NounGender gender) {
        this.gender = gender;
    }

    public NounGender getSecondGender() {
        return secondGender;
    }

    public void setSecondGender(NounGender secondGender) {
        this.secondGender = secondGender;
    }

    public Boolean getVarietyAble() {
        return varietyAble;
    }

    public void setVarietyAble(Boolean varietyAble) {
        this.varietyAble = varietyAble;
    }

    public Boolean getNoSingular() {
        return noSingular;
    }

    public void setNoSingular(Boolean noSingular) {
        this.noSingular = noSingular;
    }

    public Boolean getNoPlural() {
        return noPlural;
    }

    public void setNoPlural(Boolean noPlural) {
        this.noPlural = noPlural;
    }

    public List<CasesVar> getCasesVars() {
        return casesVar;
    }

    public void setCasesVars(List<CasesVar> casesVars) {
        this.casesVar = casesVars;
    }

    public Importance getImportance() {
        return importance;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }

    public void addCasesVar(CasesVar var) {
        this.casesVar.add(var);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NounVar that = (NounVar) o;

        if (noPlural != null ? !noPlural.equals(that.noPlural) : that.noPlural != null) return false;
        if (noSingular != null ? !noSingular.equals(that.noSingular) : that.noSingular != null)
            return false;
        if (secondGender != that.secondGender) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (numeration != null ? !numeration.equals(that.numeration) : that.numeration != null) return false;
        if (varietyAble != null ? !varietyAble.equals(that.varietyAble) : that.varietyAble != null) return false;
        if (casesVar != null ? !casesVar.equals(that.casesVar) : that.casesVar != null) return false;
        if (gender != that.gender) return false;
        return !(topic != null ? !topic.equals(that.topic) : that.topic != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (numeration != null ? numeration.hashCode() : 0);
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (secondGender != null ? secondGender.hashCode() : 0);
        result = 31 * result + (varietyAble != null ? varietyAble.hashCode() : 0);
        result = 31 * result + (noSingular != null ? noSingular.hashCode() : 0);
        result = 31 * result + (noPlural != null ? noPlural.hashCode() : 0);
        result = 31 * result + (casesVar != null ? casesVar.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NounVar {" + "CasesVar=" + casesVar + ", noPlural=" + noPlural + ", noSingular=" + noSingular + ", varietyAble=" + varietyAble + ", secondGender=" + secondGender + ", gender=" + gender + ", topic='" + topic + '\'' + ", numeration=" + numeration + ", id=" + id + '}';
    }
}
