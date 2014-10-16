package pl.semantyk.domain;

import pl.semantyk.wikiparser.WikiNumeration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRZYMIOTNIK_ODM")
public class AdjectiveVar implements Serializable, Cloneable {

    private static final long serialVersionUID = 2013397755540193631L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PRZYMIOTNIK_ODM", nullable = false, unique = true)
    private Integer id;

    @Transient
    private WikiNumeration numeration;

    @OneToMany(mappedBy = "adjectiveVar",
            cascade = CascadeType.ALL,
            targetEntity = AdjectiveDegreeVar.class,
            orphanRemoval = true)
    private List<AdjectiveDegreeVar> degree = new ArrayList<>();

    @ManyToOne(targetEntity = Importance.class, optional = false)
    @JoinColumn(name = "ID_ZNACZENIE")
    private Importance importance;

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

    public List<AdjectiveDegreeVar> getDegree() {
        return degree;
    }

    public void setDegree(List<AdjectiveDegreeVar> degree) {
        this.degree = degree;
    }

    public Importance getImportance() {
        return importance;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }

    public void addAdjectiveDegree(AdjectiveDegreeVar var) {
        this.degree.add(var);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdjectiveVar that = (AdjectiveVar) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (numeration != null ? !numeration.equals(that.numeration) : that.numeration != null) return false;
        return !(degree != null ? !degree.equals(that.degree) : that.degree != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (numeration != null ? numeration.hashCode() : 0);
        result = 31 * result + (degree != null ? degree.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return  "AdjectiveVar" + "degree=" + degree + ", numeration=" + numeration + ", id=" + id + '}';
    }
}
