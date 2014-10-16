package pl.semantyk.domain;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "RELACJA_LEKS")
public class LexicalRel implements Serializable, Cloneable {

	private static final long serialVersionUID = -2321670561709775678L;

	@EmbeddedId
	private LexicalRelId id;

	/**
	 * Identyfikator relacji.
	 */
	@ManyToOne(targetEntity = RelationType.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "RELACJA", nullable = false)
	private RelationType relation;

	public LexicalRel(LexicalRelId id, RelationType relation) {
		super();
		this.id = id;
		this.relation = relation;
	}

	public LexicalRel() {
		super();
	}

	public LexicalRelId getId() {
		return id;
	}

	public void setId(LexicalRelId id) {
		this.id = id;
	}

	public RelationType getRelation() {
		return relation;
	}

	public void setRelation(RelationType relation) {
		this.relation = relation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((relation == null) ? 0 : relation.hashCode());
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
		LexicalRel other = (LexicalRel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		return "LexicalRelation [id=" + id + ", relation=" + relation + "]";
	}

}