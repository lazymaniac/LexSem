package pl.semantyk.domain;

import pl.semantyk.dao.IdGenerator;
import pl.semantyk.domain.annotation.Column;
import pl.semantyk.domain.annotation.Id;
import pl.semantyk.domain.annotation.Table;
import pl.semantyk.enums.NounGender;
import pl.semantyk.wikiparser.WikiNumeration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
/**
 * Klasa przechowuje odmiane rzeczownika przez przypadki w liczbie pojedynczej i mnogiej.
 */
@Table(name = "RZECZOWNIK_ODM")
public class NounVar implements Serializable, Cloneable {

    private static final long serialVersionUID = -6259828431906486481L;

    /**
     * Identyfiaktor encji w bazie danych.
     */
    @Id
    @Column(name = "ID_RZECZ_ODM")
    private Integer id = IdGenerator.getId(this.getClass());

    /**
     * Zawiera ideksy znaczenie do którego należy odmiana.
     */
    private WikiNumeration numeration;

    /**
     * Temat słowotwórczy jednostki.
     */
    @Column(name = "TEMAT")
    private String topic;

    /**
     * Rodzaj rzeczownika.
     */
    @Column(name = "RODZAJ")
    private NounGender gender;

    /**
     * 2 rodzaj rzeczownika.
     */
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

    private List<CasesVar> casesVar = new ArrayList<>();

    /**
     * Referencja do znaczenia którego dotyczy odmiana.
     */
    @Column(name = "ID_ZNACZENIE")
    private Integer importance;

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

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    public List<CasesVar> getCasesVar() {
        return casesVar;
    }

    public void setCasesVar(List<CasesVar> casesVar) {
        this.casesVar = casesVar;
    }

    public void addCasesVar(CasesVar var) {
        this.casesVar.add(var);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NounVar nounVar = (NounVar) o;

        if (gender != nounVar.gender) return false;
        if (id != null ? !id.equals(nounVar.id) : nounVar.id != null) return false;
        if (importance != null ? !importance.equals(nounVar.importance) : nounVar.importance != null) return false;
        if (noPlural != null ? !noPlural.equals(nounVar.noPlural) : nounVar.noPlural != null) return false;
        if (noSingular != null ? !noSingular.equals(nounVar.noSingular) : nounVar.noSingular != null) return false;
        if (numeration != null ? !numeration.equals(nounVar.numeration) : nounVar.numeration != null) return false;
        if (secondGender != nounVar.secondGender) return false;
        if (topic != null ? !topic.equals(nounVar.topic) : nounVar.topic != null) return false;
        if (varietyAble != null ? !varietyAble.equals(nounVar.varietyAble) : nounVar.varietyAble != null) return false;

        return true;
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
        result = 31 * result + (importance != null ? importance.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NounVar{");
        sb.append("id=").append(id);
        sb.append(", numeration=").append(numeration);
        sb.append(", topic='").append(topic).append('\'');
        sb.append(", gender=").append(gender);
        sb.append(", secondGender=").append(secondGender);
        sb.append(", varietyAble=").append(varietyAble);
        sb.append(", noSingular=").append(noSingular);
        sb.append(", noPlural=").append(noPlural);
        sb.append(", importance=").append(importance);
        sb.append('}');
        return sb.toString();
    }
}
