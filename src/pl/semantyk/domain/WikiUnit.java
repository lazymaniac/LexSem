package pl.semantyk.domain;

import org.apache.log4j.Logger;
import pl.semantyk.databaseutils.IdGenerator;
import pl.semantyk.domain.annotation.Column;
import pl.semantyk.domain.annotation.Id;
import pl.semantyk.domain.annotation.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "JEDNOSTKA_WIKI")
public class WikiUnit implements Serializable, Cloneable {
	// TODO add counters.
	private static final long serialVersionUID = 4340005967634872221L;
	
	private static final Logger LOG = Logger.getLogger(WikiUnit.class);

	@Id
	@Column(name = "ID_JEDNOSTKA")
	private Integer id = IdGenerator.getId(this.getClass());

	@Column(name = "NAZWA")
	private String name; // nazwa jednostki

	private List<PartOfSpeech> partsOfSpeech = new ArrayList<>();

	private List<WnUnit> wnUnits = new ArrayList<>();

	@Column(name = "TEMAT")
	private String topic;

	public WikiUnit() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PartOfSpeech> getPartsOfSpeech() {
		return partsOfSpeech;
	}

	public void setPartsOfSpeech(List<PartOfSpeech> partsOfSpeech) {
		this.partsOfSpeech = partsOfSpeech;
	}

	public List<WnUnit> getWnUnits() {
		return wnUnits;
	}

	public void setWnUnits(List<WnUnit> wnUnits) {
		this.wnUnits = wnUnits;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void addExample(Example example) {
		try {
			int idxPartOfSpeech = example.getNumeration().getPartOfSpeech();
			int idxImportance = example.getNumeration().getImportanceIdx();
			example.setImportance(partsOfSpeech.get(idxPartOfSpeech).getImportances().get(idxImportance).getId());
			this.partsOfSpeech.get(idxPartOfSpeech).getImportances().get(idxImportance).getExamples().add(example);
		} catch (IndexOutOfBoundsException ed) {
			LOG.debug("Numeration incorrect. Name of unit: " + this.getName());
			LOG.debug(ed);
		}
	}

	public void addPartOfSpeech(PartOfSpeech partOfSpeech) {
		this.partsOfSpeech.add(partOfSpeech);
	}

	public void addCollocation(Collocation collocation) {
		try {
			if (collocation.getNumeration() != null) {
				int idxPartOfSpeech = collocation.getNumeration().getPartOfSpeech();
				int idxImportance = collocation.getNumeration().getImportanceIdx();
				collocation.setImportance(partsOfSpeech.get(idxPartOfSpeech).getImportances().get(idxImportance).getId());
				this.partsOfSpeech.get(idxPartOfSpeech).getImportances().get(idxImportance).getCollocations().add(collocation);
			} else {
				for (PartOfSpeech partOfSpeech : partsOfSpeech) {
					for (Importance importance : partOfSpeech.getImportances()) {
						Collocation cloned = new Collocation(collocation.getCollocation(), importance.getId());
						importance.getCollocations().add(cloned);
					}
				}
			}
		} catch (IndexOutOfBoundsException ex) {
			LOG.debug("Numeration incorrect. Name of unit: " + this.getName());
			LOG.debug(ex);
		}
	}

	public void addSynonym(Synonym synonym) {
		try {
			int idxPartOfSpeech = synonym.getNumeration().getPartOfSpeech();
			int idxImportance = synonym.getNumeration().getImportanceIdx();
			synonym.setImportance(partsOfSpeech.get(idxPartOfSpeech).getImportances().get(idxImportance).getId());
			this.partsOfSpeech.get(idxPartOfSpeech).getImportances().get(idxImportance).getSynonyms().add(synonym);
		} catch (IndexOutOfBoundsException ex) {
			LOG.debug("Numeration incorrect. Name of unit: " + this.getName());
			LOG.debug(ex);
		}
	}

	public void addAntonym(Antonym antonym) {
		try {
			int idxPartOfSpeech = antonym.getNumeration().getPartOfSpeech();
			int idxImportance = antonym.getNumeration().getImportanceIdx();
			Importance importance = partsOfSpeech.get(idxPartOfSpeech).getImportances().get(idxImportance);
			antonym.setImportance(importance.getId());
			importance.getAntonyms().add(antonym);
		} catch (IndexOutOfBoundsException ex) {
			LOG.debug("Numeration incorrect. Name of unit: " + this.getName());
			LOG.debug(ex);
		}
	}

	public void addCognate(Cognate cognate) {
		for (PartOfSpeech partOfSpeech : partsOfSpeech) {
			for (Importance importance : partOfSpeech.getImportances()) {
				Cognate cloned = new Cognate(cognate.getCognate(), cognate.getPartOfSpeech(), importance.getId());
				importance.getCognates().add(cloned);
			}
		}
	}

	public void addPhraseology(Phraseology phraseology) {
		try {
			if (phraseology.getNumeration() != null) {
				int idxPartOfSpeech = phraseology.getNumeration().getPartOfSpeech();
				int idxImportance = phraseology.getNumeration().getImportanceIdx();
				phraseology.setImportance(partsOfSpeech.get(idxPartOfSpeech).getImportances().get(idxImportance).getId());
				this.partsOfSpeech.get(idxPartOfSpeech).getImportances().get(idxImportance).getPhraseology().add(phraseology);
			} else {
				for (PartOfSpeech partOfSpeech : partsOfSpeech) {
					for (Importance importance : partOfSpeech.getImportances()) {
						Phraseology cloned = new Phraseology(phraseology.getPhraseology(), importance.getId());
						importance.getPhraseology().add(cloned);
					}
				}
			}
		} catch (IndexOutOfBoundsException ex) {
			LOG.debug("Numeration incorrect. Name of unit: " + this.getName());
			LOG.debug(ex);
		}
	}

	public void addNounVar(NounVar var) {
		try {
			int idxPartOfSpeech = var.getNumeration().getPartOfSpeech();
			int idxImportance = var.getNumeration().getImportanceIdx();
			var.setImportance(partsOfSpeech.get(idxPartOfSpeech).getImportances().get(idxImportance).getId());
			this.partsOfSpeech.get(idxPartOfSpeech).getImportances().get(idxImportance).getNounVar().add(var);
		} catch (IndexOutOfBoundsException ex) {
			LOG.debug("Numeration incorrect. Name of unit: " + this.getName());
			LOG.debug(ex);
		}
	}

	public void addVerbVar(VerbVar var) {
		try {
			int idxPartOfSpeech = var.getNumeration().getPartOfSpeech();
			int idxImportance = var.getNumeration().getImportanceIdx();
			var.setImportance(partsOfSpeech.get(idxPartOfSpeech).getImportances().get(idxImportance).getId());
			this.partsOfSpeech.get(idxPartOfSpeech).getImportances().get(idxImportance).getVerbVars().add(var);
		} catch (IndexOutOfBoundsException ex) {
			LOG.debug("Numeration incorrect. Name of unit: " + this.getName());
			LOG.debug(ex);
		}
	}

	public void addAdjectiveVar(AdjectiveVar var) {
		try {
			int idxPartOfSpeech = var.getNumeration().getPartOfSpeech();
			int idxImportance = var.getNumeration().getImportanceIdx();
			var.setImportance(partsOfSpeech.get(idxPartOfSpeech).getImportances().get(idxImportance).getId());
			this.partsOfSpeech.get(idxPartOfSpeech).getImportances().get(idxImportance).getAdjectiveVars().add(var);
		} catch (IndexOutOfBoundsException ex) {
			LOG.debug("Numeration incorrect. Name of unit: " + this.getName());
			LOG.debug(ex);
		}
	}

	public void addAdverbVar(AdverbVar var) {
		try {
			int idxPartOfSpeech = var.getNumeration().getPartOfSpeech();
			int idxImportance = var.getNumeration().getImportanceIdx();
			var.setImportance(partsOfSpeech.get(idxPartOfSpeech).getImportances().get(idxImportance).getId());
			this.partsOfSpeech.get(idxPartOfSpeech).getImportances().get(idxImportance).getAdverbVars().add(var);
		} catch (IndexOutOfBoundsException ex) {
			LOG.debug("Numeration incorrect. Name of unit: " + this.getName());
			LOG.debug(ex);
		}
	}

	public void addPronounVar(PronounVar var) {
		try {
			int idxPartOfSpeech = var.getNumeration().getPartOfSpeech();
			int idxImportance = var.getNumeration().getImportanceIdx();
			var.setImportance(partsOfSpeech.get(idxPartOfSpeech).getImportances().get(idxImportance).getId());
			this.partsOfSpeech.get(idxPartOfSpeech).getImportances().get(idxImportance).getPronounVars().add(var);
		} catch (IndexOutOfBoundsException ex) {
			LOG.debug("Numeration incorrect. Name of unit: " + this.getName());
			LOG.debug(ex);
		}
	}

	public void addNumeralVar(NumeralVar var) {
		try {
			int idxPartOfSpeech = var.getNumeration().getPartOfSpeech();
			int idxImportance = var.getNumeration().getImportanceIdx();
			var.setImportance(partsOfSpeech.get(idxPartOfSpeech).getImportances().get(idxImportance));
			this.partsOfSpeech.get(idxPartOfSpeech).getImportances().get(idxImportance).getNumeralVar().add(var);
		} catch (IndexOutOfBoundsException ex) {
			LOG.debug("Numeration incorrect. Name of unit: " + this.getName());
			LOG.debug(ex);
		}
	}
	
	public Importance getImportance(int partOfSpeechIdx, int importanceIdx) {
		return partsOfSpeech.get(partOfSpeechIdx).getImportances().get(importanceIdx);
	}

	public void addWnUnit(WnUnit wnUnit) {
		this.wnUnits.add(wnUnit);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		WikiUnit that = (WikiUnit) o;

		if (partsOfSpeech != null ? !partsOfSpeech.equals(that.partsOfSpeech)
				: that.partsOfSpeech != null)
			return false;
		if (id != null ? !id.equals(that.id) : that.id != null)
			return false;
		if (wnUnits != null ? !wnUnits.equals(that.wnUnits)
				: that.wnUnits != null)
			return false;
		if (name != null ? !name.equals(that.name) : that.name != null)
			return false;
		if (topic != null ? !topic.equals(that.topic) : that.topic != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result
				+ (partsOfSpeech != null ? partsOfSpeech.hashCode() : 0);
		result = 31 * result + (wnUnits != null ? wnUnits.hashCode() : 0);
		result = 31 * result + (topic != null ? topic.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("JednostkaWiki{");
		sb.append("id=").append(id);
		sb.append(", name=").append(name).append('\'');
		sb.append(", partsOfSpeech=").append(partsOfSpeech);
		sb.append(", wnUnits=").append(wnUnits);
		sb.append(", topic='").append(topic).append('\'');
		sb.append('}');
		return sb.toString();
	}
}