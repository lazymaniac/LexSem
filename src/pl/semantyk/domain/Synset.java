package pl.semantyk.domain;


import pl.semantyk.domain.annotation.Column;
import pl.semantyk.domain.annotation.Id;
import pl.semantyk.domain.annotation.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "SYNSET")
public class Synset implements Serializable, Cloneable {

    private static final long serialVersionUID = -8089475906010261115L;

    @Id
    @Column(name = "ID_SYNSET")
    private Integer id;

    @Column(name = "PODZIELNY")
    private Integer splited;

    @Column(name = "ABSTRACT")
    private Boolean isAbstract;

    private List<WnUnit> units = new ArrayList<>();

    public Synset() {
    }

    public Synset(Integer splited, Boolean isAbstract) {
		super();
		this.splited = splited;
		this.isAbstract = isAbstract;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSplited() {
		return splited;
	}

	public void setSplited(Integer splited) {
		this.splited = splited;
	}

	public Boolean getIsAbstract() {
		return isAbstract;
	}

	public void setIsAbstract(Boolean isAbstract) {
		this.isAbstract = isAbstract;
	}

    public List<WnUnit> getUnits() {
        return units;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Synset synset = (Synset) o;

        if (id != null ? !id.equals(synset.id) : synset.id != null) return false;
        if (isAbstract != null ? !isAbstract.equals(synset.isAbstract) : synset.isAbstract != null) return false;
        if (splited != null ? !splited.equals(synset.splited) : synset.splited != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (splited != null ? splited.hashCode() : 0);
        result = 31 * result + (isAbstract != null ? isAbstract.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Synset{");
        sb.append("id=").append(id);
        sb.append(", splited=").append(splited);
        sb.append(", isAbstract=").append(isAbstract);
        sb.append('}');
        return sb.toString();
    }
}
