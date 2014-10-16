package pl.semantyk.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pl.semantyk.domain.LexicalRel;
import pl.semantyk.domain.RawWikiUnit;
import pl.semantyk.domain.RelationType;
import pl.semantyk.domain.Synset;
import pl.semantyk.domain.SynsetRelation;
import pl.semantyk.domain.WikiUnit;
import pl.semantyk.domain.WnUnit;
import pl.semantyk.domain.WnUnitSynsetRel;
import pl.semantyk.parse.entities.LexicalRelRaw;
import pl.semantyk.parse.entities.SynsetRaw;
import pl.semantyk.parse.entities.SynsetRelationRaw;

/**
 * Contains all parsed units from plWordNet and plWiktionary databases and
 * prepare them to write into sql database.
 * 
 * @author Sebastian Fabisz
 */
public class Dictionary {

	/**
	 * Default constructor Initializes data.
	 */
	public Dictionary() {
	}

	public final List<WnUnit> getWnUnits() {
		return wnUnits;
	}

	public final void setWnUnits(final List<WnUnit> wnUnits) {
		this.wnUnits = wnUnits;
	}

	/**
	 * Returns reference to LexcialUnit object found by ID filed.
	 * 
	 * @param id
	 *            identifier of search lexical unit.
	 * @return reference to LexicalUnit object or null.
	 */
	public final WnUnit getUnitByID(final long id) {
		for (WnUnit lu : wnUnits) {
			if (lu.getId().equals(id)) {
				return lu;
			}
		}
		return null;
	}

	/* <--------------------- Private variables -------------------------> */

	/**
	 * Check of dictionary contains lexical unit which name is passed as
	 * argument.
	 * 
	 * @param name
	 *            lexical unit name.
	 * @return if contains return true.
	 */
	public final boolean hasLexicalUnit(String name) {
		for (WnUnit lu : wnUnits) {
			if (lu.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

	public final List<SynsetRaw> getSynsetRaws() {
		return synsetRaws;
	}

	public final void setSynsetRaws(final List<SynsetRaw> aSynsets) {
		this.synsetRaws = aSynsets;
	}

	/**
	 * Returns a reference to Synset object found by id.
	 * 
	 * @param id
	 *            identifier of search Synset.
	 * @return reference to Synset object or null.
	 */
	public final Synset getSynsetByID(final long id) {
		for (Synset s : synsets) {
			if (s.getId().equals(id)) {
				return s;
			}
		}
		return null;
	}

	/**
	 * Return a reference to Synset object found by LexicalUnit stored in
	 * ArrayList.
	 * 
	 * @param id
	 *            identifier of LexicalUnit.
	 * @return reference to Synset object of null.
	 */
	public final Synset getSynsetByUnit(final long id) {
		for (Synset s : synsets) {
			for (WnUnit unit : s.getUnits()) {
				if (unit.getId().equals(id)) {
					return s;
				}
			}
		}
		return null;
	}

	/**
	 * Find a reference to RelationType object by identifier filed.
	 * 
	 * @param id
	 *            identifier of search RelationType object.
	 * @return reference to found object or null.
	 */
	public final RelationType getRelTypeByID(final Integer id) {
		for (RelationType rt : relationTypes) {
			if (rt.getId().equals(id)) {
				return rt;
			}
		}
		return null;
	}

	public final List<LexicalRelRaw> getLexicalRelRaws() {
		return lexicalRelations;
	}

	public final void setLexicalRelRaws(List<LexicalRelRaw> lexicalRelations) {
		this.lexicalRelations = lexicalRelations;
	}

	// <------------------------- Tools ------------------------>

	/**
	 * Search a RelacjaLeksykalna object by parent identifier and return a
	 * reference to this object.
	 * 
	 * @param parentId
	 *            identifier of parent field.
	 * @return reference to RelacjaLeksykalna object or null.
	 */
	public final LexicalRelRaw getLexRelByParent(final Integer parentId) {
		for (LexicalRelRaw lr : lexicalRelations) {
			if (lr.getParent().equals(parentId)) {
				return lr;
			}
		}
		return null;
	}

	/**
	 * Search a LexicalRelRaw object by children identifier and return a
	 * reference to this object.
	 * 
	 * @param id
	 *            identifier of children field.
	 * @return reference to RelacjaLeksykalna object or null.
	 */
	public final LexicalRelRaw getLexRelByChild(final Integer id) {
		for (LexicalRelRaw lr : lexicalRelations) {
			if (lr.getChild().equals(id)) {
				return lr;
			}
		}
		return null;
	}

	public final List<SynsetRelationRaw> getSynsetRelations() {
		return synsetRelations;
	}

	public final void setSynsetRelations(
			final List<SynsetRelationRaw> aSynsetRelations) {
		this.synsetRelations = aSynsetRelations;
	}

	/**
	 * Returns a SynsetRelation object by parent field whose identifier was
	 * passed as argument. If object was not found return null.
	 * 
	 * @param id
	 *            identifier of parent object.
	 * @return reference to desired SynsetRelation object or null.
	 */
	public final SynsetRelationRaw getSynRelByParent(final Integer id) {
		for (SynsetRelationRaw sr : synsetRelations) {
			if (sr.getParent().equals(id)) {
				return sr;
			}
		}
		return null;
	}

	/**
	 * Returns a SynsetRelation object by children field whose identifier was
	 * passed as argument. If object was not found return null.
	 * 
	 * @param id
	 *            identifier of children object.
	 * @return reference to desired SynsetRelation object or null.
	 */
	public final SynsetRelationRaw getSynRelByChild(final Integer id) {
		for (SynsetRelationRaw sr : synsetRelations) {
			if (sr.getChild().equals(id)) {
				return sr;
			}
		}
		return null;
	}

	public final List<Integer> getEngUnitsIDs() {
		return engUnitsIDs;
	}

	public final void setEngUnitsIDs(final List<Integer> angIdList) {
		this.engUnitsIDs = angIdList;
	}

	public final List<RawWikiUnit> getRawWikiUnits() {
		return rawWikiUnits;
	}

	public final void setRawWikiUnits(final List<RawWikiUnit> rawUnits) {
		this.rawWikiUnits = rawUnits;
	}

	public List<Synset> getSynsets() {
		return synsets;
	}

	public void setSynsets(List<Synset> synsety) {
		this.synsets = synsety;
	}

	public List<RelationType> getRelationTypes() {
		return relationTypes;
	}

	public void setRelationTypes(List<RelationType> relationTypes) {
		this.relationTypes = relationTypes;
	}

	public RelationType getRelTypeById(Integer relationTypeId) {
		for (RelationType typRel : relationTypes) {
			if (typRel.getId().equals(relationTypeId))
				return typRel;
		}
		return null;
	}

	public List<LexicalRel> getLexicalRels() {
		return lexicalRels;
	}

	public void setLexicalRels(List<LexicalRel> lexicalRels) {
		this.lexicalRels = lexicalRels;
	}

	public List<SynsetRelation> getSynsetRels() {
		return synsetRels;
	}

	public void setSynsetRels(List<SynsetRelation> synsetRels) {
		this.synsetRels = synsetRels;
	}

	public final List<WikiUnit> getWikiUnits() {
		return wikiUnits;
	}

	public final void setWikiUnits(List<WikiUnit> wikiUnits) {
		this.wikiUnits = wikiUnits;
	}

	public final WikiUnit getWikiUnitById(Integer wikiUnitid) {
		for (WikiUnit unit : wikiUnits) {
			if (unit.getId() != null && unit.getId().equals(wikiUnitid)) {
				return unit;
			}
		}
		return null;
	}

	public Set<WnUnitSynsetRel> getWnUnitSynsetRels() {
		return wnUnitSynsetRels;
	}

	public void setWnUnitSynsetRels(Set<WnUnitSynsetRel> wnUnitSynsetRels) {
		this.wnUnitSynsetRels = wnUnitSynsetRels;
	}

	/**
	 * Collection of lexical units.
	 */
	private List<WnUnit> wnUnits = new ArrayList<>();
	/**
	 * Kolekcja synsetów w postaci nie znormalizowanej.
	 */
	private List<SynsetRaw> synsetRaws = new ArrayList<>();
	/**
	 * Kolekcja relacji leksykalnych w postacji nie znormalizowanej.
	 */
	private List<LexicalRelRaw> lexicalRelations = new ArrayList<>();
	/**
	 * Kolekcja relacji syntaktycznych
	 */
	private List<SynsetRelationRaw> synsetRelations = new ArrayList<>();
	/**
	 * Collection of English lexical units IDs.
	 */
	private List<Integer> engUnitsIDs = new ArrayList<>();
	/**
	 * Collection of not porsed wiki units.
	 */
	private List<RawWikiUnit> rawWikiUnits = new ArrayList<>();
	/**
	 * Collection of parsed wiki units.
	 */
	private List<WikiUnit> wikiUnits = new ArrayList<>();

	private List<Synset> synsets = new ArrayList<>();

	private List<RelationType> relationTypes = new ArrayList<>();

	private List<LexicalRel> lexicalRels = new ArrayList<>();

	private List<SynsetRelation> synsetRels = new ArrayList<>();

	private Set<WnUnitSynsetRel> wnUnitSynsetRels = new HashSet<>();
}
