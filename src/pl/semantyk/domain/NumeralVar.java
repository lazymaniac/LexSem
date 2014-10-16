package pl.semantyk.domain;

import pl.semantyk.enums.NumeralType;
import pl.semantyk.wikiparser.WikiNumeration;

import java.io.Serializable;

public class NumeralVar implements Serializable, Cloneable {

	private static final long serialVersionUID = 2213964672850883654L;

	private Integer id;

	private WikiNumeration numeration;

	private NumeralType numeralType;

	private CasesVar variety;

	private Importance importance;

	public NumeralVar(WikiNumeration numeration) {
		this.numeration = numeration;
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

	public NumeralType getNumeralType() {
		return numeralType;
	}

	public void setNumeralType(NumeralType numeralType) {
		this.numeralType = numeralType;
	}

	public CasesVar getVariety() {
		return variety;
	}

	public void setVariety(CasesVar variety) {
		this.variety = variety;
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
		if (!(o instanceof NumeralVar)) {
			return false;
		}

		NumeralVar that = (NumeralVar) o;

		if (id != null ? !id.equals(that.id) : that.id != null) {
			return false;
		}
		if (numeration != null ? !numeration.equals(that.numeration)
				: that.numeration != null) {
			return false;
		}
		if (variety != null ? !variety.equals(that.variety)
				: that.variety != null) {
			return false;
		}
		return numeralType == that.numeralType;

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (numeration != null ? numeration.hashCode() : 0);
		result = 31 * result
				+ (numeralType != null ? numeralType.hashCode() : 0);
		result = 31 * result + (variety != null ? variety.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "NumeralVar {" + "variety=" + variety + ", numeralType="
				+ numeralType + ", numeration=" + numeration + ", id=" + id
				+ '}';
	}

}
