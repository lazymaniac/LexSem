package pl.semantyk.domain;

import pl.semantyk.wikiparser.WikiNumeration;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "PRZYKLADY")
public class Example implements Serializable, Cloneable {

    private static final long serialVersionUID = -1290254787380309697L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PRZYKLAD", nullable = false, unique = true)
    private Integer id;

    @Transient
    private WikiNumeration numeration;

    @Column(name = "PRZYKLAD")
    private String example;

    @ManyToOne(targetEntity = Importance.class, optional = false)
    @JoinColumn(name = "ID_ZNACZENIE")
    private Importance importance;

    public Example(WikiNumeration numeration, String example) {
        this.numeration = numeration;
        this.example = example;
    }

    public Example() {
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
        if (!(o instanceof Example)) {
            return false;
        }

        Example that = (Example) o;

        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (numeration != null ? !numeration.equals(that.numeration) : that.numeration != null) {
            return false;
        }
        return !(example != null ? !example.equals(that.example) : that.example != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (numeration != null ? numeration.hashCode() : 0);
        result = 31 * result + (example != null ? example.hashCode() : 0);
        return result;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nExample [id=");
		builder.append(id);
		builder.append(", \nnumeration=");
		builder.append(numeration);
		builder.append(", \nexample=");
		builder.append(example);
		builder.append("]\n");
		return builder.toString();
	}


}
