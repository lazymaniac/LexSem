package pl.semantyk.dao;

import pl.semantyk.domain.VerbVar;
import pl.semantyk.exceptions.SystemException;

import java.util.List;

/**
 * Verb variety Dao. Support CRUD operiations for Verb Variety entity.
 */
public interface VerbVarDao {

    /**
     * Find and return specified Verb variety entity in database.
     *
     * @param id key.
     * @return entity found in database.
     * @throws SystemException exception.
     */
    public VerbVar findById(Integer id) throws SystemException;

    /**
     * Query all rows from Verb Variety table.
     *
     * @return Collection containing all rows form Verb Veriety table.
     * @throws SystemException exception.
     */
    public List<VerbVar> findAll() throws SystemException;

    /**
     * Remove specified row Verb Variety table.
     *
     * @param entity Entity to remove
     * @throws SystemException exception.
     */
    public void remove(VerbVar entity) throws SystemException;

    /**
     * Remove rows in Verb Variety table which exists in provided collection.
     *
     * @param entities collection of etities to remove.
     * @throws SystemException exception.
     */
    public void removeAll(List<VerbVar> entities) throws SystemException;

    /**
     * Remove Verb Variety,
     * @throws SystemException exception.
     */
    public void removeAll() throws SystemException;
}
