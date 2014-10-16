package pl.semantyk.domain;

import pl.semantyk.wikiparser.WikiNumeration;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "KOLOKACJE")
public class Collocation implements Serializable, Cloneable {

    private static final long serialVersionUID = -8842889924621984709L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_KOLOKACJA", unique = true, nullable = false)
    private Integer id;

    @Transient
    private WikiNumeration numeration;

    @Column(name = "KOLOKACJA")
    private String collocation;

    @ManyToOne(targetEntity = Importance.class, optional = false)
    @JoinColumn(name = "ID_ZNACZENIE")
    private Importance importance;

    public Collocation(WikiNumeration numeration, String collocation) {
        this.numeration = numeration;
        this.collocation = collocation;
    }

    public Collocation() {
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
		StringBuilder builder = new StringBuilder();
		builder.append("\nCollocation [id=");
		builder.append(id);
		builder.append(", \nnumeration=");
		builder.append(numeration);
		builder.append(", \ncollocation=");
		builder.append(collocation);
		builder.append("]\n");
		return builder.toString();
	}

 
}
