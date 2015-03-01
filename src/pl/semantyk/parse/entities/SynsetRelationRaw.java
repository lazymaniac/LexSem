package pl.semantyk.parse.entities;

import pl.semantyk.domain.annotation.Column;
import pl.semantyk.domain.annotation.Table;

@Table(name = "relacja_synsetu")
public class SynsetRelationRaw {

	@Column(name = "rodzic")
	private Integer parent;

	@Column(name = "dziecko")
	private Integer child;

	@Column(name = "relacja")
	private Integer relation;

	@Column(name = "sprawdzony")
	private Boolean checked;

	public SynsetRelationRaw() {
	}

	public SynsetRelationRaw(Integer parent, Integer child, Integer relation,
			Boolean checked) {
		super();
		this.parent = parent;
		this.child = child;
		this.relation = relation;
		this.checked = checked;
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

	public Integer getRelation() {
		return relation;
	}

	public void setRelation(Integer relation) {
		this.relation = relation;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((checked == null) ? 0 : checked.hashCode());
		result = prime * result + ((child == null) ? 0 : child.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result
				+ ((relation == null) ? 0 : relation.hashCode());
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
		SynsetRelationRaw other = (SynsetRelationRaw) obj;
		if (checked == null) {
			if (other.checked != null)
				return false;
		} else if (!checked.equals(other.checked))
			return false;
		if (child == null) {
			if (other.child != null)
				return false;
		} else if (!child.equals(other.child))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (relation == null) {
			if (other.relation != null)
				return false;
		} else if (!relation.equals(other.relation))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SynsetRelationRaw [parent=" + parent + ", child=" + child
				+ ", relation=" + relation + ", checked=" + checked + "]";
	}
}
