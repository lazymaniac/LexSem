package pl.semantyk.domain;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "POKREWNE")
public class Cognate implements Serializable, Cloneable {

    private static final long serialVersionUID = -4583823147483265694L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_POKREWNY", nullable = false, unique = true)
    private Integer id;

    @Column(name = "POKREWNY")
    private String cognate;
    
    @Column(name = "CZESC_MOWY")
    private String partOfSpeech;

    @ManyToOne(targetEntity = Importance.class, optional = false)
    @JoinColumn(name = "ID_ZNACZENIE")
    private Importance importance;

    public Cognate(String cognate) {
        this.cognate = cognate;
    }

    public Cognate() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCognate() {
        return cognate;
    }

    public void setCognate(String cognate) {
        this.cognate = cognate;
    }

    public Importance getImportance() {
        return importance;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }
    
    public String getPartOfSpeech() {
		return partOfSpeech;
	}

	public void setPartOfSpeech(String partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cognate)) {
            return false;
        }

        Cognate that = (Cognate) o;

        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (partOfSpeech != null ? !partOfSpeech.equals(that.partOfSpeech) : that.partOfSpeech != null)
        	return false;
        return !(cognate != null ? !cognate.equals(that.cognate) : that.cognate != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cognate != null ? cognate.hashCode() : 0);
        result = 31 * result + (partOfSpeech != null ? partOfSpeech.hashCode() : 0);
        return result;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nCognate [id=");
		builder.append(id);
		builder.append(", \ncognate=");
		builder.append(cognate);
		builder.append(", \npartOfSpeech=");
		builder.append(partOfSpeech);
		builder.append("]\n");
		return builder.toString();
	}

    
}
