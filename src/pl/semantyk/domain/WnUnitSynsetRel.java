package pl.semantyk.domain;

import java.io.Serializable;

public class WnUnitSynsetRel implements Serializable {

	private static final long serialVersionUID = -7965865793311335462L;

	private Integer synsetId;

	private Integer wnUnitId;

	public WnUnitSynsetRel() {
	}

	public WnUnitSynsetRel(Integer synsetId, Integer wnUnitId) {
		this.synsetId = synsetId;
		this.wnUnitId = wnUnitId;
	}

	public Integer getSynsetId() {
		return synsetId;
	}

	public void setSynsetId(Integer synsetId) {
		this.synsetId = synsetId;
	}

	public Integer getWnUnitId() {
		return wnUnitId;
	}

	public void setWnUnitId(Integer wnUnitId) {
		this.wnUnitId = wnUnitId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		WnUnitSynsetRel that = (WnUnitSynsetRel) o;

		if (wnUnitId != null ? !wnUnitId.equals(that.wnUnitId)
				: that.wnUnitId != null)
			return false;
		if (synsetId != null ? !synsetId.equals(that.synsetId)
				: that.synsetId != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = synsetId != null ? synsetId.hashCode() : 0;
		result = 31 * result + (wnUnitId != null ? wnUnitId.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("JednWnSynsetRel{");
		sb.append("synsetId=").append(synsetId);
		sb.append(", wnUnitId=").append(wnUnitId);
		sb.append('}');
		return sb.toString();
	}
}
