package pl.semantyk.dao;

import pl.semantyk.domain.Cognate;
import pl.semantyk.exceptions.SystemException;

import java.util.List;

/**
 * CRUD operations for Cognate entity.
 * @author Sebastian Fabisz.
 *
 */
public interface CognateDao {

	/**
	 * Find Cognate entity by ID.
	 * @param id ID key.
	 * @return Cognate entity.
	 * @throws SystemException exception
	 */
    public Cognate findById(Integer id) throws SystemException;

    /**
     * Find all Cognate entities.
     * @return Collection of all cognate entities.
     * @throws SystemException exception.
     */
    public List<Cognate> findAll() throws SystemException;

    /**
     * Remove specified entity.
     * @param entity Cognate to romove.
     * @throws SystemException
     */
    public void remove(Cognate entity) throws SystemException;

    /**
     * Remove entities provided in collection.
     * @param entities Collection of entites to remove.
     * @throws SystemException exception.
     */
    public void removeAll(List<Cognate> entities) throws SystemException;

    /**
     * Remove all Cognate entities from database.
     * @throws SystemException exception.
     */
    public void removeAll() throws SystemException;
}
