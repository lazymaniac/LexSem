package pl.semantyk.domain;

import pl.semantyk.dao.IdGenerator;
import pl.semantyk.domain.annotation.Column;
import pl.semantyk.domain.annotation.Id;
import pl.semantyk.domain.annotation.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "CZESCI_MOWY")
public class PartOfSpeech implements Serializable, Cloneable {

	private static final long serialVersionUID = -3772867366261662688L;

	@Id
	@Column(name = "ID_CZESC_MOWY")
	private Integer id = IdGenerator.getId(this.getClass());

	@Column(name = "CZESC_MOWY")
	private String partOfSpeech;

    private List<Importance> importance = new ArrayList<>();

	@Column(name = "ID_JEDNOSTKA")
	private Integer wikiUnit;

	public PartOfSpeech(final String partOfSpeech, final Integer wikiUnit) {
		this.partOfSpeech = partOfSpeech;
		this.wikiUnit = wikiUnit;
	}

	public PartOfSpeech() {
	}

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(final String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public List<Importance> getImportances() {
        return importance;
    }

    public void setImportances(final List<Importance> importances) {
        importance = importances;
    }

    public Integer getWikiUnit() {
        return wikiUnit;
    }

    public void setWikiUnit(final Integer wikiUnit) {
        this.wikiUnit = wikiUnit;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartOfSpeech that = (PartOfSpeech) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (partOfSpeech != null ? !partOfSpeech.equals(that.partOfSpeech) : that.partOfSpeech != null) return false;
        if (wikiUnit != null ? !wikiUnit.equals(that.wikiUnit) : that.wikiUnit != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (partOfSpeech != null ? partOfSpeech.hashCode() : 0);
        result = 31 * result + (wikiUnit != null ? wikiUnit.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PartOfSpeech{");
        sb.append("id=").append(id);
        sb.append(", partOfSpeech='").append(partOfSpeech).append('\'');
        sb.append(", wikiUnit=").append(wikiUnit);
        sb.append('}');
        return sb.toString();
    }
}
