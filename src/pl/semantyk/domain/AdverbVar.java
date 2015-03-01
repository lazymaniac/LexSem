package pl.semantyk.domain;

import java.io.Serializable;

import pl.semantyk.databaseutils.IdGenerator;
import pl.semantyk.domain.annotation.Column;
import pl.semantyk.domain.annotation.Id;
import pl.semantyk.domain.annotation.Table;
import pl.semantyk.wikiparser.WikiNumeration;

@Table(name = "PRZYSLOWEK_ODM")
public class AdverbVar implements Serializable, Cloneable {

	private static final long serialVersionUID = -7590074968372388492L;

	@Id
	private Integer id = IdGenerator.getId(this.getClass());

	private WikiNumeration numeration;

	@Column(name = "STOP_WYZSZY")
	private String higherDegree;

	@Column(name = "STOP_NAJWYZSZY")
	private String highestDegree;

	@Column(name = "ID_ZNACZENIE")
	private Integer importance;

	public AdverbVar(final WikiNumeration numeration) {
		this.numeration = numeration;
	}

	public AdverbVar() {
	}

	public AdverbVar(AdverbVar adverbVar) {
		this.numeration = adverbVar.getNumeration();
		this.higherDegree = adverbVar.getHigherDegree();
		this.highestDegree = adverbVar.getHighestDegree();
		this.importance = adverbVar.getImportance();
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public WikiNumeration getNumeration() {
		return numeration;
	}

	public void setNumeration(final WikiNumeration numeration) {
		this.numeration = numeration;
	}

	public void setHigherDegree(final String higherDegree) {
		this.higherDegree = higherDegree;
	}

    public String getHigherDegree() {
        return higherDegree;
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }

	public String getHighestDegree() {
		return highestDegree;
	}

	public void setHighestDegree(String highestDegree) {
		this.highestDegree = highestDegree;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof AdverbVar)) {
			return false;
		}

		AdverbVar that = (AdverbVar) o;

		if (id != null ? !id.equals(that.id) : that.id != null) {
			return false;
		}
		if (numeration != null ? !numeration.equals(that.numeration) : that.numeration != null) {
			return false;
		}

		if (highestDegree != null ? !highestDegree.equals(that.highestDegree) : that.highestDegree != null) {
			return false;
		}
		return !(higherDegree != null ? !higherDegree.equals(that.higherDegree) : that.higherDegree != null);

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = (31 * result) + (numeration != null ? numeration.hashCode() : 0);
		result = (31 * result) + (higherDegree != null ? higherDegree.hashCode() : 0);
		result = (31 * result) + (highestDegree != null ? highestDegree.hashCode() : 0);
		return result;
	}

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AdverbVar{");
        sb.append("id=").append(id);
        sb.append(", numeration=").append(numeration);
        sb.append(", higherDegree='").append(higherDegree).append('\'');
		sb.append(", highestDegree='").append(highestDegree).append('\'');
        sb.append(", importance=").append(importance);
        sb.append('}');
        return sb.toString();
    }


}
