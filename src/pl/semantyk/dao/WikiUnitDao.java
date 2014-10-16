package pl.semantyk.dao;

import pl.semantyk.domain.WikiUnit;
import pl.semantyk.exceptions.SystemException;

import java.util.Collection;
import java.util.List;

/**
 * CRUD operations for Wiki Unit.
 * @author Sebastian Fabisz.
 *
 */
public interface WikiUnitDao {

	/**
	 * Save Wiki Unit entity.
	 * @param entity Entity to save.
	 */
    public void persist(WikiUnit entity);

    /**
     * Save all Wiki Units in collection.
     * @param col collection of Wiki Units to save.
     */
    public void persistAll(Collection<WikiUnit> col);

    /**
     * Find Wiki Unit by ID.
     * @param id ID key.
     * @return Wiki Unit entity.
     * @throws SystemException exception.
     */
    public WikiUnit findById(Integer id) throws SystemException;

    /**
     * Finds all Wiki Units..
     * @return Collection of all Wiki Units.
     * @throws SystemException exception
     */
    public List<WikiUnit> findAll() throws SystemException;
 
    /**
     * Remove specified Wiki Unit.
     * @param entity Entity to remove.
     * @throws SystemException exception.
     */
    public void remove(WikiUnit entity) throws SystemException;
 
    /**
     * Remove all Wiki Units provided in collection.
     * @param entities Collection of Wiki Units to remove.
     * @throws SystemException exception.
     */
    public void removeAll(List<WikiUnit> entities) throws SystemException;

    /**
     * Remove all Wiki Units in database.
     * @throws SystemException exception
     */
    public void removeAll() throws SystemException;
    
    public void closeEm();
}
