package pl.semantyk.domain;

import pl.semantyk.dao.IdGenerator;
import pl.semantyk.domain.annotation.Column;
import pl.semantyk.domain.annotation.Id;
import pl.semantyk.domain.annotation.Table;
import pl.semantyk.wikiparser.WikiNumeration;

import java.io.Serializable;

@Table(name = "SYNONIMY")
public class Synonym implements Serializable, Cloneable {

    private static final long serialVersionUID = 3433602626037604430L;

    @Id
    @Column(name = "ID_SYNONIM")
    private Integer id = IdGenerator.getId(this.getClass());

    private WikiNumeration numeration;

    @Column(name = "SYNONIM")
    private String synonym;

    @Column(name = "ID_ZNACZENIE")
    private Integer importance;

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

        Synonym synonym1 = (Synonym) o;

        if (id != null ? !id.equals(synonym1.id) : synonym1.id != null) return false;
        if (importance != null ? !importance.equals(synonym1.importance) : synonym1.importance != null) return false;
        if (numeration != null ? !numeration.equals(synonym1.numeration) : synonym1.numeration != null) return false;
        if (synonym != null ? !synonym.equals(synonym1.synonym) : synonym1.synonym != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (numeration != null ? numeration.hashCode() : 0);
        result = 31 * result + (synonym != null ? synonym.hashCode() : 0);
        result = 31 * result + (importance != null ? importance.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Synonym{");
        sb.append("id=").append(id);
        sb.append(", numeration=").append(numeration);
        sb.append(", synonym='").append(synonym).append('\'');
        sb.append(", importance=").append(importance);
        sb.append('}');
        return sb.toString();
    }
}
