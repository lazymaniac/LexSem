package pl.semantyk.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class LexicalRelId implements Serializable {

	private static final long serialVersionUID = -1169621908610960764L;

	/**
	 * Identyfiaktor rodzica w relacji leksykalnej.
	 */
	@ManyToOne(targetEntity = WnUnit.class)
	@JoinColumn(name = "RODZIC", nullable = false)
	private WnUnit parent;

	/**
	 * Identyfikator rodzica w relacji leksykalnej.
	 */
	@ManyToOne(targetEntity = WnUnit.class)
	@JoinColumn(name = "DZIECKO", nullable = false)
	private WnUnit child;

	public LexicalRelId(WnUnit parent, WnUnit child) {
		super();
		this.parent = parent;
		this.child = child;
	}

	public LexicalRelId() {
		super();
	}

	public WnUnit getParent() {
		return parent;
	}

	public void setParent(WnUnit parent) {
		this.parent = parent;
	}

	public WnUnit getChild() {
		return child;
	}

	public void setChild(WnUnit child) {
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
		LexicalRelId other = (LexicalRelId) obj;
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
		return "LexicalRelId [parent=" + parent + ", child=" + child + "]";
	}

}
