package pl.semantyk.domain;

import pl.semantyk.wikiparser.WikiNumeration;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PRZYSLOWEK_ODM")
public class AdverbVar implements Serializable, Cloneable {

	private static final long serialVersionUID = -7590074968372388492L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Transient
	private WikiNumeration numeration;

	@Column(name = "STOP_WYZSZY")
	private String higherDegree;

	@Column(name = "STOP_NAJWYZSZY")
	private String highestDegree;

	@ManyToOne(targetEntity = Importance.class, optional = false)
	@JoinColumn(name = "ID_ZNACZENIE")
	private Importance importance;

	public AdverbVar(WikiNumeration numeration) {
		this.numeration = numeration;
	}

	public AdverbVar() {
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

	public String getStopienWyzszy() {
		return higherDegree;
	}

	public void setHigherDegree(String higherDegree) {
		this.higherDegree = higherDegree;
	}

	public String getHighestDegree() {
		return highestDegree;
	}

	public void setHighestDegree(String highestDegree) {
		this.highestDegree = highestDegree;
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
		if (!(o instanceof AdverbVar)) {
			return false;
		}

		AdverbVar that = (AdverbVar) o;

		if (id != null ? !id.equals(that.id) : that.id != null) {
			return false;
		}
		if (numeration != null ? !numeration.equals(that.numeration)
				: that.numeration != null) {
			return false;
		}
		if (highestDegree != null ? !highestDegree.equals(that.highestDegree)
				: that.highestDegree != null) {
			return false;
		}
		return !(higherDegree != null ? !higherDegree.equals(that.higherDegree)
				: that.higherDegree != null);

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (numeration != null ? numeration.hashCode() : 0);
		result = 31 * result
				+ (higherDegree != null ? higherDegree.hashCode() : 0);
		result = 31 * result
				+ (highestDegree != null ? highestDegree.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "AdverbVar {" + "highestDegree='" + highestDegree + '\''
				+ ", higherDegree='" + higherDegree + '\'' + ", numeration="
				+ numeration + ", id=" + id + '}';
	}
}
