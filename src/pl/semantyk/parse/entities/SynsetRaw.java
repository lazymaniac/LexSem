package pl.semantyk.parse.entities;

import pl.semantyk.domain.annotation.Column;
import pl.semantyk.domain.annotation.Id;
import pl.semantyk.domain.annotation.Table;

@Table(name = "SYNSET")
public class SynsetRaw {

	@Id
	@Column(name = "id_synset")
	private Integer id;

	@Column(name = "podzielny")
	private Integer split;

	@Column(name = "abstract")
	private Boolean abstractsynstet;

	public SynsetRaw() {
	}

	/**
	 * @param id
	 * @param split
	 * @param abstractsynstet
	 */
	public SynsetRaw(Integer id, Integer split, Boolean abstractsynstet) {
		this.id = id;
		this.split = split;
		this.abstractsynstet = abstractsynstet;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSplit() {
		return split;
	}

	public void setSplit(Integer split) {
		this.split = split;
	}

	public Boolean getAbstractsynstet() {
		return abstractsynstet;
	}

	public void setAbstractsynstet(Boolean abstractsynstet) {
		this.abstractsynstet = abstractsynstet;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		SynsetRaw synsetRaw = (SynsetRaw) o;

		if (id != null ? !id.equals(synsetRaw.id) : synsetRaw.id != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Synset{" + "id=" + id + ", split=" + split
				+ ", abstractsynstet=" + abstractsynstet + '}';
	}
}
