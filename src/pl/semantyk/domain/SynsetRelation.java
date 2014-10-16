package pl.semantyk.domain;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "RELACJA_SYNSETU")
public class SynsetRelation implements Serializable, Cloneable {

    private static final long serialVersionUID = -8714323640453453495L;

    @EmbeddedId
    private SynsetRelationId id;

    @ManyToOne(targetEntity = RelationType.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "RELACJA")
    private RelationType relation;

    @Column(name = "SPRAWDZONY", nullable = true)
    private Boolean checked;

    public SynsetRelation() {
    }

	public SynsetRelation(SynsetRelationId id, RelationType relacja,
			Boolean sprawdzony) {
		super();
		this.id = id;
		this.relation = relacja;
		this.checked = sprawdzony;
	}

	public SynsetRelationId getId() {
		return id;
	}

	public void setId(SynsetRelationId id) {
		this.id = id;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((relation == null) ? 0 : relation.hashCode());
		result = prime * result
				+ ((checked == null) ? 0 : checked.hashCode());
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
		SynsetRelation other = (SynsetRelation) obj;
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
		if (checked == null) {
			if (other.checked != null)
				return false;
		} else if (!checked.equals(other.checked))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SynsetRelation [id=" + id + ", relation=" + relation
				+ ", checked=" + checked + "]";
	}

}
