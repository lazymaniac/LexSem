package pl.semantyk.domain;

import pl.semantyk.databaseutils.IdGenerator;
import pl.semantyk.domain.annotation.Column;
import pl.semantyk.domain.annotation.Id;
import pl.semantyk.domain.annotation.Table;
import pl.semantyk.enums.PersonVarType;


import java.io.Serializable;

@Table(name = "OSOBY_ODM")
public class PersonVar implements Serializable, Cloneable {

	private static final long serialVersionUID = 6695541427922976368L;

	@Id
	@Column(name = "ID_OSOBY_ODM")
	private Integer id = IdGenerator.getId(this.getClass());

	@Column(name = "TYP_ODMIANY")
	private PersonVarType varType;

	@Column(name = "OS_1_LP")
	private String per1sin;

	@Column(name = "OS_2_LP")
	private String per2sin;

	@Column(name = "OS_3_LP")
	private String per3sin;

	@Column(name = "OS_1_LM")
	private String per1plu;

	@Column(name = "OS_2_LM")
	private String per2plu;

	@Column(name = "OS_3_LM")
	private String per3plu;

	@Column(name = "ID_CZASOWNIK_ODM")
	private Integer verbVar;

    public PersonVar(PersonVarType type) {
        this.varType = type;
    }

    public PersonVar(PersonVar personVar) {
        this.varType = personVar.getVarType();
        this.per1sin = personVar.getPer1Sin();
        this.per2sin = personVar.getPer2Sin();
        this.per3sin = personVar.getPer3Sin();
        this.per1plu = personVar.getPer1Plu();
        this.per2plu = personVar.getPer2Plu();
        this.per3plu = personVar.getPer3Plu();
        this.verbVar = personVar.getVerbVar();
    }

    public PersonVar() {
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

    public Integer getVerbVar() {
        return verbVar;
    }

    public void setVerbVar(Integer verbVar) {
        this.verbVar = verbVar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonVar personVar = (PersonVar) o;

        if (id != null ? !id.equals(personVar.id) : personVar.id != null) return false;
        if (per1plu != null ? !per1plu.equals(personVar.per1plu) : personVar.per1plu != null) return false;
        if (per1sin != null ? !per1sin.equals(personVar.per1sin) : personVar.per1sin != null) return false;
        if (per2plu != null ? !per2plu.equals(personVar.per2plu) : personVar.per2plu != null) return false;
        if (per2sin != null ? !per2sin.equals(personVar.per2sin) : personVar.per2sin != null) return false;
        if (per3plu != null ? !per3plu.equals(personVar.per3plu) : personVar.per3plu != null) return false;
        if (per3sin != null ? !per3sin.equals(personVar.per3sin) : personVar.per3sin != null) return false;
        if (varType != personVar.varType) return false;
        if (verbVar != null ? !verbVar.equals(personVar.verbVar) : personVar.verbVar != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (varType != null ? varType.hashCode() : 0);
        result = 31 * result + (per1sin != null ? per1sin.hashCode() : 0);
        result = 31 * result + (per2sin != null ? per2sin.hashCode() : 0);
        result = 31 * result + (per3sin != null ? per3sin.hashCode() : 0);
        result = 31 * result + (per1plu != null ? per1plu.hashCode() : 0);
        result = 31 * result + (per2plu != null ? per2plu.hashCode() : 0);
        result = 31 * result + (per3plu != null ? per3plu.hashCode() : 0);
        result = 31 * result + (verbVar != null ? verbVar.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PersonVar{");
        sb.append("id=").append(id);
        sb.append(", varType=").append(varType);
        sb.append(", per1sin='").append(per1sin).append('\'');
        sb.append(", per2sin='").append(per2sin).append('\'');
        sb.append(", per3sin='").append(per3sin).append('\'');
        sb.append(", per1plu='").append(per1plu).append('\'');
        sb.append(", per2plu='").append(per2plu).append('\'');
        sb.append(", per3plu='").append(per3plu).append('\'');
        sb.append(", verbVar=").append(verbVar);
        sb.append('}');
        return sb.toString();
    }
}
