package pl.semantyk.domain;

import pl.semantyk.domain.annotation.Column;
import pl.semantyk.domain.annotation.Table;

import java.io.Serializable;

@Table(name = "RELACJA_SYNSETU")
public class SynsetRelation implements Serializable, Cloneable {

    private static final long serialVersionUID = -8714323640453453495L;

    @Column(name = "RODZIC")
    private Integer parent;

    @Column(name = "DZIECKO")
    private Integer child;

    @Column(name = "RELACJA")
    private RelationType relation;

    @Column(name = "SPRAWDZONY")
    private Boolean checked;

    public SynsetRelation() {
    }

	public SynsetRelation(Integer parent, Integer child, RelationType relacja,
			Boolean sprawdzony) {
		super();
		this.parent = parent;
        this.child = child;
		this.relation = relacja;
		this.checked = sprawdzony;
	}

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getChild() {
        return child;
    }

    public void setChild(Integer child) {
        this.child = child;
    }

    public RelationType getRelation() {
		return relation;
	}

	public void setRelation(RelationType relation) {
		this.relation = relation;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SynsetRelation that = (SynsetRelation) o;

        if (checked != null ? !checked.equals(that.checked) : that.checked != null) return false;
        if (child != null ? !child.equals(that.child) : that.child != null) return false;
        if (parent != null ? !parent.equals(that.parent) : that.parent != null) return false;
        if (relation != null ? !relation.equals(that.relation) : that.relation != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = parent != null ? parent.hashCode() : 0;
        result = 31 * result + (child != null ? child.hashCode() : 0);
        result = 31 * result + (relation != null ? relation.hashCode() : 0);
        result = 31 * result + (checked != null ? checked.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SynsetRelation{");
        sb.append("parent=").append(parent);
        sb.append(", child=").append(child);
        sb.append(", relation=").append(relation);
        sb.append(", checked=").append(checked);
        sb.append('}');
        return sb.toString();
    }
}
