package pl.semantyk.domain;

import pl.semantyk.wikiparser.WikiNumeration;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ZAIMEK_ODM")
public class PronounVar implements Serializable, Cloneable {

    private static final long serialVersionUID = -4981784252995803075L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ZAIMEK_ODM", unique = true, nullable = false)
    private Integer id;

    @Transient
    private WikiNumeration numeration;

    @OneToOne(optional = false)
    @JoinColumn(name = "ID_ODM")
    private CasesVar casesVar;

    @ManyToOne(targetEntity = Importance.class,
            optional = false)
    @JoinColumn(name = "ID_ZNACZENIE")
    private Importance importance;

    public PronounVar(WikiNumeration numeracja) {
        this.numeration = numeracja;
    }

    public PronounVar() {
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

    public CasesVar getPrzypadki() {
        return casesVar;
    }

    public void setCasesVar(CasesVar casesVar) {
        this.casesVar = casesVar;
    }

    public Importance getImportance() {
        return importance;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PronounVar)) {
            return false;
        }

        PronounVar that = (PronounVar) o;

        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (numeration != null ? !numeration.equals(that.numeration) : that.numeration != null) {
            return false;
        }
        return !(casesVar != null ? !casesVar.equals(that.casesVar) : that.casesVar != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (numeration != null ? numeration.hashCode() : 0);
        result = 31 * result + (casesVar != null ? casesVar.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PronounVar {" + "casesVar=" + casesVar + ", numeration=" + numeration + ", id=" + id + '}';
    }
}
