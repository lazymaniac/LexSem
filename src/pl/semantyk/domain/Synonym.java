package pl.semantyk.domain;

import pl.semantyk.wikiparser.WikiNumeration;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SYNONIMY")
public class Synonym implements Serializable, Cloneable {

    private static final long serialVersionUID = 3433602626037604430L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_SYNONIM", nullable = false, unique = true)
    private Integer id;

    @Transient
    private WikiNumeration numeration;

    @Column(name = "SYNONIM")
    private String synonym;

    @ManyToOne(targetEntity = Importance.class, optional = false)
    @JoinColumn(name = "ID_ZNACZENIE")
    private Importance importance;

    public Synonym(WikiNumeration numeration, String synonym) {
        this.numeration = numeration;
        this.synonym = synonym;
    }

    public Synonym() {
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

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
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
        if (!(o instanceof Synonym)) {
            return false;
        }

        Synonym that = (Synonym) o;

        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (numeration != null ? !numeration.equals(that.numeration) : that.numeration != null) {
            return false;
        }
        return !(synonym != null ? !synonym.equals(that.synonym) : that.synonym != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (numeration != null ? numeration.hashCode() : 0);
        result = 31 * result + (synonym != null ? synonym.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Synonym {" + "id=" + id + ", numeration=" + numeration + ", synonym='" + synonym + '\'' + '}';
    }
}
