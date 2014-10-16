package pl.semantyk.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class SynsetRelationId implements Serializable {

	private static final long serialVersionUID = 1007880301234970847L;

	@ManyToOne
	@JoinColumn(name = "RODZIC")
	private Synset parent;

	@ManyToOne
	@JoinColumn(name = "DZIECKO")
	private Synset child;

	public SynsetRelationId() {
	}

	public SynsetRelationId(Synset parent, Synset child) {
		super();
		this.parent = parent;
		this.child = child;
	}

	public Synset getParent() {
		return parent;
	}

	public void setParent(Synset parent) {
		this.parent = parent;
	}

	public Synset getChild() {
		return child;
	}

	public void setChild(Synset child) {
		this.child = child;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((child == null) ? 0 : child.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
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
		SynsetRelationId other = (SynsetRelationId) obj;
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
		return true;
	}

	@Override
	public String toString() {
		return "SynsetRelationId [parent=" + parent + ", child=" + child + "]";
	}

}
