package pl.semantyk.domain;

import pl.semantyk.domain.annotation.Column;
import pl.semantyk.domain.annotation.Table;

import java.io.Serializable;

@Table(name = "RELACJA_LEKS")
public class LexicalRel implements Serializable, Cloneable {

	private static final long serialVersionUID = -2321670561709775678L;

    @Column(name = "RODZIC")
    private Long parent;

    @Column(name = "DZIECKO")
    private Long child;

	/**
	 * Identyfikator relacji.
	 */
	@Column(name = "RELACJA")
	private RelationType relation;

	public LexicalRel(Long parent, Long child, RelationType relation) {
		super();
		this.parent = parent;
        this.child = child;
		this.relation = relation;
	}

	public LexicalRel() {
		super();
	}

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public Long getChild() {
        return child;
    }

    public void setChild(Long child) {
        this.child = child;
    }

    public RelationType getRelation() {
		return relation;
	}

	public void setRelation(RelationType relation) {
		this.relation = relation;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LexicalRel that = (LexicalRel) o;

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
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LexicalRel{");
        sb.append("parent=").append(parent);
        sb.append(", child=").append(child);
        sb.append(", relation=").append(relation);
        sb.append('}');
        return sb.toString();
    }
}