package pl.semantyk.domain;

import pl.semantyk.enums.PersonVarType;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "OSOBY_ODM")
public class PersonVar implements Serializable, Cloneable {

	private static final long serialVersionUID = 6695541427922976368L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_OSOBY_ODM", unique = true, nullable = false)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(name = "TYP_ODMIANY", nullable = false, length = 45)
	private PersonVarType varType;

	@Column(name = "OS_1_LP", nullable = false, length = 255)
	private String per1sin;

	@Column(name = "OS_2_LP", nullable = false, length = 255)
	private String per2sin;

	@Column(name = "OS_3_LP", nullable = false, length = 255)
	private String per3sin;

	@Column(name = "OS_1_LM", nullable = false, length = 255)
	private String per1plu;

	@Column(name = "OS_2_LM", nullable = false, length = 255)
	private String per2plu;

	@Column(name = "OS_3_LM", nullable = false, length = 255)
	private String per3plu;

	@ManyToOne(targetEntity = VerbVar.class, optional = false)
	@JoinColumn(name = "ID_CZASOWNIK_ODM")
	private VerbVar verbVar;

	public PersonVar() {
	}

	public PersonVar(PersonVarType type) {
		this.varType = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PersonVarType getVarType() {
		return varType;
	}

	public void setVarType(PersonVarType varType) {
		this.varType = varType;
	}

	public String getPer1Sin() {
		return per1sin;
	}

	public void setPer1Sin(String per1sin) {
		this.per1sin = per1sin;
	}

	public String getPer2Sin() {
		return per2sin;
	}

	public void setPer2Sin(String per2sin) {
		this.per2sin = per2sin;
	}

	public String getPer3Sin() {
		return per3sin;
	}

	public void setPer3Sin(String per3sin) {
		this.per3sin = per3sin;
	}

	public String getPer1Plu() {
		return per1plu;
	}

	public void setPer1Plu(String per1plu) {
		this.per1plu = per1plu;
	}

	public String getPer2Plu() {
		return per2plu;
	}

	public void setPer2Plu(String per2plu) {
		this.per2plu = per2plu;
	}

	public String getPer3Plu() {
		return per3plu;
	}

	public void setPer3Plu(String per3plu) {
		this.per3plu = per3plu;
	}

	public VerbVar getVerbVar() {
		return verbVar;
	}

	public void setVerbVar(VerbVar verbVar) {
		this.verbVar = verbVar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((per1plu == null) ? 0 : per1plu.hashCode());
		result = prime * result + ((per1sin == null) ? 0 : per1sin.hashCode());
		result = prime * result + ((per2plu == null) ? 0 : per2plu.hashCode());
		result = prime * result + ((per2sin == null) ? 0 : per2sin.hashCode());
		result = prime * result + ((per3plu == null) ? 0 : per3plu.hashCode());
		result = prime * result + ((per3sin == null) ? 0 : per3sin.hashCode());
		result = prime * result + ((varType == null) ? 0 : varType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonVar other = (PersonVar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (per1plu == null) {
			if (other.per1plu != null)
				return false;
		} else if (!per1plu.equals(other.per1plu))
			return false;
		if (per1sin == null) {
			if (other.per1sin != null)
				return false;
		} else if (!per1sin.equals(other.per1sin))
			return false;
		if (per2plu == null) {
			if (other.per2plu != null)
				return false;
		} else if (!per2plu.equals(other.per2plu))
			return false;
		if (per2sin == null) {
			if (other.per2sin != null)
				return false;
		} else if (!per2sin.equals(other.per2sin))
			return false;
		if (per3plu == null) {
			if (other.per3plu != null)
				return false;
		} else if (!per3plu.equals(other.per3plu))
			return false;
		if (per3sin == null) {
			if (other.per3sin != null)
				return false;
		} else if (!per3sin.equals(other.per3sin))
			return false;
		if (varType != other.varType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PersonVar [id=");
		builder.append(id);
		builder.append(", varType=");
		builder.append(varType);
		builder.append(", per1sin=");
		builder.append(per1sin);
		builder.append(", per2sin=");
		builder.append(per2sin);
		builder.append(", per3sin=");
		builder.append(per3sin);
		builder.append(", per1plu=");
		builder.append(per1plu);
		builder.append(", per2plu=");
		builder.append(per2plu);
		builder.append(", per3plu=");
		builder.append(per3plu);
		builder.append("]");
		return builder.toString();
	}

}
