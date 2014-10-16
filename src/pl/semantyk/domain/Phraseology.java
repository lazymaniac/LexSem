package pl.semantyk.domain;

import pl.semantyk.wikiparser.WikiNumeration;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FRAZEOLOGIE")
public class Phraseology implements Serializable, Cloneable {

    private static final long serialVersionUID = -1117875138075233478L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_FRAZOLOGIA", nullable = false, unique = true)
    private Integer id;

    @Transient
    private WikiNumeration numeration;

    @Column(name = "FRAZEOLOGIA")
    private String phraseology;

    @ManyToOne(targetEntity = Importance.class, optional = false)
    @JoinColumn(name = "ID_ZNACZENIE")
    private Importance importance;

    public Phraseology(WikiNumeration numeration, String phraseology) {
        this.numeration = numeration;
        this.phraseology = phraseology;
    }

    public Phraseology() {
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

    public String getPhraseology() {
        return phraseology;
    }

    public void setPhraseology(String phraseology) {
        this.phraseology = phraseology;
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
        if (!(o instanceof Phraseology)) {
            return false;
        }

        Phraseology that = (Phraseology) o;

        if (phraseology != null ? !phraseology.equals(that.phraseology) : that.phraseology != null) {
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
        result = 31 * result + (phraseology != null ? phraseology.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\nPhraseology {" + "\nid=" + id + ", \nnumeration=" + numeration + ", \nphraseology='" + phraseology + '\'' + '}';
    }
}
