package pl.semantyk.domain;

import pl.semantyk.databaseutils.IdGenerator;
import pl.semantyk.domain.annotation.Column;
import pl.semantyk.domain.annotation.Id;
import pl.semantyk.domain.annotation.Table;
import pl.semantyk.wikiparser.WikiNumeration;

import java.io.Serializable;

@Table(name = "ANTONIMY")
public class Antonym implements Serializable, Cloneable {

    private static final long serialVersionUID = 8151576958527375565L;

    @Id
    @Column(name = "ID_ANTONIM")
    private Integer id = IdGenerator.getId(this.getClass());

    private WikiNumeration numeration;

    @Column(name = "ANTONIM")
    private String antonym;

    @Column(name = "ID_ZNACZENIE")
    private Integer importance;

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

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
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
        final StringBuffer sb = new StringBuffer("Antonym{");
        sb.append("id=").append(id);
        sb.append(", numeration=").append(numeration);
        sb.append(", antonym='").append(antonym).append('\'');
        sb.append(", importance=").append(importance);
        sb.append('}');
        return sb.toString();
    }
}
