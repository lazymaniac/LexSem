package pl.semantyk.domain;

import pl.semantyk.databaseutils.IdGenerator;
import pl.semantyk.domain.annotation.Column;
import pl.semantyk.domain.annotation.Id;
import pl.semantyk.domain.annotation.Table;
import pl.semantyk.wikiparser.WikiNumeration;

import java.io.Serializable;

@Table(name = "KOLOKACJE")
public class Collocation implements Serializable, Cloneable {

    private static final long serialVersionUID = -8842889924621984709L;

    @Id
    @Column(name = "ID_KOLOKACJA")
    private Integer id = IdGenerator.getId(this.getClass());

    private WikiNumeration numeration;

    @Column(name = "KOLOKACJA")
    private String collocation;

    @Column(name = "ID_ZNACZENIE")
    private Integer importance;

    public Collocation(WikiNumeration numeration, String collocation) {
        this.numeration = numeration;
        this.collocation = collocation;
    }

    public Collocation(String collocation, Integer importance) {
        this.collocation = collocation;
        this.importance = importance;
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

    public String getCollocation() {
        return collocation;
    }

    public void setCollocation(String collocation) {
        this.collocation = collocation;
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
        if (!(o instanceof Collocation)) {
            return false;
        }

        Collocation that = (Collocation) o;

        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (collocation != null ? !collocation.equals(that.collocation) : that.collocation != null) {
            return false;
        }
        return !(numeration != null ? !numeration.equals(that.numeration) : that.numeration != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (numeration != null ? numeration.hashCode() : 0);
        result = 31 * result + (collocation != null ? collocation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Collocation{");
        sb.append("id=").append(id);
        sb.append(", numeration=").append(numeration);
        sb.append(", collocation='").append(collocation).append('\'');
        sb.append(", importance=").append(importance);
        sb.append('}');
        return sb.toString();
    }
}
