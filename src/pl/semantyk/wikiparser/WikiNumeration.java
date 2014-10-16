package pl.semantyk.wikiparser;

public class WikiNumeration {

	private Integer partOfSpeechIdx;

	private Integer importanceIdx;

	public WikiNumeration(Integer partOfSpechIdx, Integer importanceIdx) {
		this.partOfSpeechIdx = partOfSpechIdx;
		this.importanceIdx = importanceIdx;
	}

	public WikiNumeration() {
	}

	public Integer getPartOfSpeech() {
		return partOfSpeechIdx;
	}

	public void setPartOfSpeechIdx(Integer partOfSpeechIdx) {
		this.partOfSpeechIdx = partOfSpeechIdx;
	}

	public Integer getImportanceIdx() {
		return importanceIdx;
	}

	public void setImportanceIdx(Integer importanceIdx) {
		this.importanceIdx = importanceIdx;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof WikiNumeration))
			return false;

		WikiNumeration that = (WikiNumeration) o;

		if (partOfSpeechIdx != null ? !partOfSpeechIdx
				.equals(that.partOfSpeechIdx) : that.partOfSpeechIdx != null)
			return false;
		return !(importanceIdx != null ? !importanceIdx
				.equals(that.importanceIdx) : that.importanceIdx != null);

	}

	@Override
	public int hashCode() {
		int result = partOfSpeechIdx != null ? partOfSpeechIdx.hashCode() : 0;
		result = 31 * result
				+ (importanceIdx != null ? importanceIdx.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "WikiNumeration{" + "partOfSpeechIdx=" + partOfSpeechIdx
				+ ", importanceIdx=" + importanceIdx + '}';
	}
}
