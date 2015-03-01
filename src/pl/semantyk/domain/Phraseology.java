package pl.semantyk.domain;

import pl.semantyk.databaseutils.IdGenerator;
import pl.semantyk.domain.annotation.Column;
import pl.semantyk.domain.annotation.Id;
import pl.semantyk.domain.annotation.Table;
import pl.semantyk.wikiparser.WikiNumeration;

import java.io.Serializable;

@Table(name = "FRAZEOLOGIE")
public class Phraseology implements Serializable, Cloneable {

    private static final long serialVersionUID = -1117875138075233478L;

    @Id
    @Column(name = "ID_FRAZOLOGIA")
    private Integer id = IdGenerator.getId(this.getClass());

    private WikiNumeration numeration;

    @Column(name = "FRAZEOLOGIA")
    private String phraseology;

    @Column(name = "ID_ZNACZENIE")
    private Integer importance;

    public Phraseology(WikiNumeration numeration, String phraseology) {
        this.numeration = numeration;
        this.phraseology = phraseology;
    }

    public Phraseology(String phraseology, Integer importance) {
        this.phraseology = phraseology;
        this.importance = importance;
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

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Phraseology that = (Phraseology) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (importance != null ? !importance.equals(that.importance) : that.importance != null) return false;
        if (numeration != null ? !numeration.equals(that.numeration) : that.numeration != null) return false;
        if (phraseology != null ? !phraseology.equals(that.phraseology) : that.phraseology != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (numeration != null ? numeration.hashCode() : 0);
        result = 31 * result + (phraseology != null ? phraseology.hashCode() : 0);
        result = 31 * result + (importance != null ? importance.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Phraseology{");
        sb.append("id=").append(id);
        sb.append(", numeration=").append(numeration);
        sb.append(", phraseology='").append(phraseology).append('\'');
        sb.append(", importance=").append(importance);
        sb.append('}');
        return sb.toString();
    }
}
