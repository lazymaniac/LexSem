package pl.semantyk.domain;

import pl.semantyk.dao.IdGenerator;
import pl.semantyk.domain.annotation.Column;
import pl.semantyk.domain.annotation.Id;
import pl.semantyk.domain.annotation.Table;
import pl.semantyk.wikiparser.WikiNumeration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "PRZYMIOTNIK_ODM")
public class AdjectiveVar implements Serializable, Cloneable {

    private static final long serialVersionUID = 2013397755540193631L;

    @Id
    @Column(name = "ID_PRZYMIOTNIK_ODM")
    private Integer id = IdGenerator.getId(this.getClass());

    private WikiNumeration numeration;

    @Column(name = "ID_ZNACZENIE")
    private Integer importance;

    private List<AdjectiveDegreeVar> degree = new ArrayList<>();

    public AdjectiveVar() {
    }

    public AdjectiveVar(WikiNumeration numeration) {
        this.numeration = numeration;
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

    public void setNumeration(WikiNumeration numeracja) {
        this.numeration = numeracja;
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    public List<AdjectiveDegreeVar> getDegree() {
        return degree;
    }

    public void addAdjectiveDegree(AdjectiveDegreeVar var) {
        this.degree.add(var);
    }

    public void setDegree(List<AdjectiveDegreeVar> degree) {
        this.degree = degree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdjectiveVar that = (AdjectiveVar) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (importance != null ? !importance.equals(that.importance) : that.importance != null) return false;
        return !(numeration != null ? !numeration.equals(that.numeration) : that.numeration != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (numeration != null ? numeration.hashCode() : 0);
        result = 31 * result + (importance != null ? importance.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AdjectiveVar{");
        sb.append("id=").append(id);
        sb.append(", numeration=").append(numeration);
        sb.append(", importance=").append(importance);
        sb.append('}');
        return sb.toString();
    }

}
