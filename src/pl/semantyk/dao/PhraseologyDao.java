package pl.semantyk.dao;


import pl.semantyk.domain.Phraseology;
import pl.semantyk.exceptions.SystemException;

import java.util.List;

/**
 * CRUD operations for Phraseology table.
 * @author Sebastian Fabisz.
 *
 */
public interface PhraseologyDao {

	/**
	 * Find Phraseology by ID.
	 * @param id ID key.
	 * @return Phraseology entity.
	 * @throws SystemException exception.
	 */
    public Phraseology findById(Integer id) throws SystemException;

    /**
     * Find all Phraseology entites.
     * @return Collection of all Phraseology entities.
     * @throws SystemException exception.
     */
    public List<Phraseology> findAll() throws SystemException;

    /**
     * Remove specified Phraseology.
     * @param entity Entity to remove.
     * @throws SystemException exception.
     */
    public void remove(Phraseology entity) throws SystemException;
 
    /**
     * Remove entities from provided collection.
     * @param entities Collection of entities to remove.
     * @throws SystemException exception.
     */
    public void removeAll(List<Phraseology> entities) throws SystemException;

    /**
     * Remove all Phraseology entities from database.
     * @throws SystemException exception.
     */
    public void removeAll() throws SystemException;
}
