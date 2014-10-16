package pl.semantyk.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "SYNSET")
public class Synset implements Serializable, Cloneable {

    private static final long serialVersionUID = -8089475906010261115L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_SYNSET", nullable = false, unique = true)
    private Integer id;

    @Column(name = "PODZIELNY")
    private Integer splited;

    @Column(name = "ABSTRACT")
    private Boolean isAbstract;

    @OneToMany(mappedBy = "synset", targetEntity = WnUnit.class)
    private List<WnUnit> units = new ArrayList<>();

    @OneToMany(mappedBy = "id.child", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SynsetRelation> parents = new HashSet<>();

    @OneToMany(mappedBy = "id.parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SynsetRelation> childrens = new HashSet<>();

    public Synset() {
    }

    public Synset(Integer splited, Boolean isAbstract, List<WnUnit> units,
			Set<SynsetRelation> parents, Set<SynsetRelation> childrens) {
		super();
		this.splited = splited;
		this.isAbstract = isAbstract;
		this.units = units;
		this.parents = parents;
		this.childrens = childrens;
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

	public void setUnits(List<WnUnit> units) {
		this.units = units;
	}

	public Set<SynsetRelation> getParents() {
		return parents;
	}

	public void setParents(Set<SynsetRelation> parents) {
		this.parents = parents;
	}

	public Set<SynsetRelation> getChildrens() {
		return childrens;
	}

	public void setChildrens(Set<SynsetRelation> childrens) {
		this.childrens = childrens;
	}
	
	public int getUnitsCount() {
		return this.units.size();
	}
	
	public WnUnit getUnit(Integer unitId) {
		for (WnUnit unit: units) {
			if(unit.getId().equals(unitId))
				return unit;
		}
		
		return null;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((childrens == null) ? 0 : childrens.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((isAbstract == null) ? 0 : isAbstract.hashCode());
		result = prime * result + ((parents == null) ? 0 : parents.hashCode());
		result = prime * result + ((splited == null) ? 0 : splited.hashCode());
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
		Synset other = (Synset) obj;
		if (childrens == null) {
			if (other.childrens != null)
				return false;
		} else if (!childrens.equals(other.childrens))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isAbstract == null) {
			if (other.isAbstract != null)
				return false;
		} else if (!isAbstract.equals(other.isAbstract))
			return false;
		if (parents == null) {
			if (other.parents != null)
				return false;
		} else if (!parents.equals(other.parents))
			return false;
		if (splited == null) {
			if (other.splited != null)
				return false;
		} else if (!splited.equals(other.splited))
			return false;
		return true;
	}

	@Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Synset{");
        sb.append("id=").append(id);
        sb.append(", split=").append(splited);
        sb.append(", abstractsynstet=").append(isAbstract);
        sb.append(", parents=").append(parents);
        sb.append(", childrens=").append(childrens);
        sb.append('}');
        return sb.toString();
    }
}
