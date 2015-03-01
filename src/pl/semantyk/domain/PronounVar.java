package pl.semantyk.domain;

import pl.semantyk.databaseutils.IdGenerator;
import pl.semantyk.domain.annotation.Column;
import pl.semantyk.domain.annotation.Id;
import pl.semantyk.domain.annotation.Table;
import pl.semantyk.wikiparser.WikiNumeration;

import java.io.Serializable;

@Table(name = "ZAIMEK_ODM")
public class PronounVar implements Serializable, Cloneable {

    private static final long serialVersionUID = -4981784252995803075L;

    @Id
    @Column(name = "ID_ZAIMEK_ODM")
    private Integer id = IdGenerator.getId(this.getClass());

    private WikiNumeration numeration;

    @Column(name = "ID_ODM")
    private Integer casesVarId;

    private CasesVar casesVar;

    @Column(name = "ID_ZNACZENIE")
    private Integer importance;

    public PronounVar(WikiNumeration numeracja) {
        this.numeration = numeracja;
    }

    public PronounVar() {
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

    public Integer getCasesVarId() {
        return casesVarId;
    }

    public void setCasesVarId(Integer casesVarId) {
        this.casesVarId = casesVarId;
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    public CasesVar getCasesVar() {
        return casesVar;
    }

    public void setCasesVar(CasesVar casesVar) {
        this.casesVar = casesVar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PronounVar that = (PronounVar) o;

        if (casesVarId != null ? !casesVarId.equals(that.casesVarId) : that.casesVarId != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (importance != null ? !importance.equals(that.importance) : that.importance != null) return false;
        if (numeration != null ? !numeration.equals(that.numeration) : that.numeration != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (numeration != null ? numeration.hashCode() : 0);
        result = 31 * result + (casesVarId != null ? casesVarId.hashCode() : 0);
        result = 31 * result + (importance != null ? importance.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PronounVar{");
        sb.append("id=").append(id);
        sb.append(", numeration=").append(numeration);
        sb.append(", casesVarId=").append(casesVarId);
        sb.append(", importance=").append(importance);
        sb.append('}');
        return sb.toString();
    }
}
