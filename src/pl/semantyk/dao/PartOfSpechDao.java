package pl.semantyk.dao;

import pl.semantyk.domain.PartOfSpeech;
import pl.semantyk.exceptions.SystemException;

import java.util.List;

/**
 * CRUD operations for PartOfSpeech entity.
 * @author Sebastian Fabisz
 *
 */
public interface PartOfSpechDao {

	/**
	 * Find Part of Speech by ID.
	 * @param id ID key
	 * @return PartOfSpeech entity
	 * @throws SystemException exception
	 */
    public PartOfSpeech findById(Integer id) throws SystemException;

    /**
     * Return all Part of Speech entities.
     * @return Collection of all Part of Speech entities.
     * @throws SystemException
     */
    public List<PartOfSpeech> findAll() throws SystemException;

    /**
     * Remove specified row form Part of Speech table.
     * @param entity Entity to remove.
     * @throws SystemException exception
     */
    public void remove(PartOfSpeech entity) throws SystemException;

    /**
     * Remove entites provided in collection
     * @param entities collection of entities to be removed.
     * @throws SystemException exception
     */
    public void removeAll(List<PartOfSpeech> entities) throws SystemException;

    /**
     * Remove all Part of Speech entities from database.
     * @throws SystemException
     */
    public void removeAll() throws SystemException;
}
