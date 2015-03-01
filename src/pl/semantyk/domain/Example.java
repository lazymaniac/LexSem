package pl.semantyk.domain;

import pl.semantyk.databaseutils.IdGenerator;
import pl.semantyk.domain.annotation.Column;
import pl.semantyk.domain.annotation.Id;
import pl.semantyk.domain.annotation.Table;
import pl.semantyk.wikiparser.WikiNumeration;

import java.io.Serializable;

@Table(name = "PRZYKLADY")
public class Example implements Serializable, Cloneable {

    private static final long serialVersionUID = -1290254787380309697L;

    @Id
    @Column(name = "ID_PRZYKLAD")
    private Integer id = IdGenerator.getId(this.getClass());

    private WikiNumeration numeration;

    @Column(name = "PRZYKLAD")
    private String example;

    @Column(name = "ID_ZNACZENIE")
    private Integer importance;

    public Example(WikiNumeration numeration, String example) {
        this.numeration = numeration;
        this.example = example;
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

    public String getExample() {
        return example;
    }

    public void setExample(String exmaple) {
        this.example = exmaple;
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

        Example example1 = (Example) o;

        if (example != null ? !example.equals(example1.example) : example1.example != null) return false;
        if (id != null ? !id.equals(example1.id) : example1.id != null) return false;
        if (importance != null ? !importance.equals(example1.importance) : example1.importance != null) return false;
        if (numeration != null ? !numeration.equals(example1.numeration) : example1.numeration != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (numeration != null ? numeration.hashCode() : 0);
        result = 31 * result + (example != null ? example.hashCode() : 0);
        result = 31 * result + (importance != null ? importance.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Example{");
        sb.append("id=").append(id);
        sb.append(", numeration=").append(numeration);
        sb.append(", example='").append(example).append('\'');
        sb.append(", importance=").append(importance);
        sb.append('}');
        return sb.toString();
    }
}
