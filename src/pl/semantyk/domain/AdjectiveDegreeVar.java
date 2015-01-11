package pl.semantyk.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.semantyk.dao.IdGenerator;
import pl.semantyk.domain.annotation.Column;
import pl.semantyk.domain.annotation.Id;
import pl.semantyk.domain.annotation.Table;
import pl.semantyk.enums.AdjectiveDegree;

@Table(name = "PRZYM_STOPIEN")
public class AdjectiveDegreeVar implements Serializable, Cloneable {

	private static final long serialVersionUID = -6597346253022582257L;

	@Id
	@Column(name = "ID_PRZYM_STOP")
	private Integer id = IdGenerator.getId(this.getClass());

	/**
	 * Stopień przymiotnika(zerowy, wyższy, najwyższy).
	 */
	@Column(name = "STOPIEN")
	private AdjectiveDegree degree;

    private List<CasesVar> casesVar = new ArrayList<>();

	@Column(name = "ID_PRZYM_ODM")
	private Integer adjectiveVarId;

	public AdjectiveDegreeVar(final AdjectiveDegree stopien) {
		degree = stopien;
	}

	public AdjectiveDegreeVar() {
	}

	public AdjectiveDegree getAdjectiveDegree() {
		return degree;
	}

	public void setAdjectiveDegree(final AdjectiveDegree degree) {
		this.degree = degree;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Integer getAdjectiveVar() {
		return adjectiveVarId;
	}

	public void setAdjectiveVar(final Integer adjectiveVarId) {
		this.adjectiveVarId = adjectiveVarId;
	}

    public AdjectiveDegree getDegree() {
        return degree;
    }

    public void setDegree(AdjectiveDegree degree) {
        this.degree = degree;
    }

    public List<CasesVar> getCasesVar() {
        return casesVar;
    }

    public void setCasesVar(List<CasesVar> casesVar) {
        this.casesVar = casesVar;
    }

    public Integer getAdjectiveVarId() {
        return adjectiveVarId;
    }

    public void setAdjectiveVarId(Integer adjectiveVarId) {
        this.adjectiveVarId = adjectiveVarId;
    }

    public void addCasesVar(final CasesVar cases) {
        cases.setAdjectiveDegreeVar(this.getId());
        casesVar.add(cases);
    }

    @Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if ((o == null) || (getClass() != o.getClass()))
			return false;

		AdjectiveDegreeVar that = (AdjectiveDegreeVar) o;

		if (id != null ? !id.equals(that.id) : that.id != null)
			return false;
		return degree == that.degree;

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = (31 * result) + (degree != null ? degree.hashCode() : 0);
		return result;
	}

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AdjectiveDegreeVar{");
        sb.append("id=").append(id);
        sb.append(", degree=").append(degree);
        sb.append(", adjectiveVarId=").append(adjectiveVarId);
        sb.append('}');
        return sb.toString();
    }
}
