package pl.semantyk.domain;

import pl.semantyk.enums.AdjectiveDegree;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRZYM_STOPIEN")
public class AdjectiveDegreeVar implements Serializable, Cloneable {

    private static final long serialVersionUID = -6597346253022582257L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PRZYM_STOP", nullable = false, unique = true)
    private Integer id;

    /**
     * Stopień przymiotnika(zerowy, wyższy, najwyższy).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "STOPIEN", nullable = false, length = 45)
    private AdjectiveDegree degree;

    @OneToMany(targetEntity = CasesVar.class,
            orphanRemoval = true,
            mappedBy = "adjectiveDegreeVar",
            cascade = CascadeType.ALL)
    private List<CasesVar> casesVar = new ArrayList<>();

    @ManyToOne(targetEntity = AdjectiveVar.class, optional = false)
    @JoinColumn(name = "ID_PRZYM_ODM")
    private AdjectiveVar adjectiveVar;

    public AdjectiveDegreeVar(AdjectiveDegree stopien) {
        this.degree = stopien;
    }

    public AdjectiveDegreeVar() {
    }

    public AdjectiveDegree getAdjectiveDegree() {
        return degree;
    }

    public void setAdjectiveDegree(AdjectiveDegree degree) {
        this.degree = degree;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CasesVar> getCasesVar() {
        return casesVar;
    }

    public void setCasesVar(List<CasesVar> casesVar) {
        this.casesVar = casesVar;
    }

    public AdjectiveVar getAdjectiveVar() {
        return adjectiveVar;
    }

    public void setAdjectiveVar(AdjectiveVar adjectiveVar) {
        this.adjectiveVar = adjectiveVar;
    }

    public void addCasesVar(CasesVar cases) {
        cases.setAdjectiveDegreeVar(this);
        this.casesVar.add(cases);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdjectiveDegreeVar that = (AdjectiveDegreeVar) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (casesVar != null ? !casesVar.equals(that.casesVar) : that.casesVar != null) return false;
        return degree == that.degree;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (degree != null ? degree.hashCode() : 0);
        result = 31 * result + (casesVar != null ? casesVar.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AdjectiveDegreeVar{" + "casesVar=" + casesVar + ", degree=" + degree + ", id=" + id + '}';
    }
}
