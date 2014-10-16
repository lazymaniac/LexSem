package pl.semantyk.domain;

import pl.semantyk.wikiparser.WikiNumeration;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ANTONIMY")
public class Antonym implements Serializable, Cloneable {

    private static final long serialVersionUID = 8151576958527375565L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ANTONIM", unique = true, nullable = false)
    private Integer id;

    @Transient
    private WikiNumeration numeration;

    @Column(name = "ANTONIM")
    private String antonym;

    @ManyToOne
    @JoinColumn(name = "ID_ZNACZENIE")
    private Importance importance;

    public Antonym(WikiNumeration numeration, String antonym) {
        this.numeration = numeration;
        this.antonym = antonym;
    }

    public Antonym() {
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

    public String getAntonym() {
        return antonym;
    }

    public void setAntonym(String antonym) {
        this.antonym = antonym;
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
        if (!(o instanceof Antonym)) {
            return false;
        }

        Antonym that = (Antonym) o;

        if (antonym != null ? !antonym.equals(that.antonym) : that.antonym != null) {
            return false;
        }
        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        return !(numeration != null ? !numeration.equals(that.numeration) : that.numeration != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (numeration != null ? numeration.hashCode() : 0);
        result = 31 * result + (antonym != null ? antonym.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Antonym {" + "antonym='" + antonym + '\'' + ", numeration=" + numeration + ", id=" + id + '}';
    }
}
