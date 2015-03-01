package pl.semantyk.domain;

import pl.semantyk.databaseutils.IdGenerator;
import pl.semantyk.domain.annotation.Column;
import pl.semantyk.domain.annotation.Id;
import pl.semantyk.domain.annotation.Table;

import java.io.Serializable;

@Table(name = "POKREWNE")
public class Cognate implements Serializable, Cloneable {

    private static final long serialVersionUID = -4583823147483265694L;

    @Id
    @Column(name = "ID_POKREWNY")
    private Integer id = IdGenerator.getId(this.getClass());

    @Column(name = "POKREWNY")
    private String cognate;
    
    @Column(name = "CZESC_MOWY")
    private String partOfSpeech;

    @Column(name = "ID_ZNACZENIE")
    private Integer importance;

    public Cognate(String cognate) {
        this.cognate = cognate;
    }

    public Cognate() {
    }

    public Cognate(String cognate, String partOfSpeech, Integer importance) {
        this.cognate = cognate;
        this.partOfSpeech = partOfSpeech;
        this.importance = importance;
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

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
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
        final StringBuffer sb = new StringBuffer("Cognate{");
        sb.append("id=").append(id);
        sb.append(", cognate='").append(cognate).append('\'');
        sb.append(", partOfSpeech='").append(partOfSpeech).append('\'');
        sb.append(", importance=").append(importance);
        sb.append('}');
        return sb.toString();
    }
}
