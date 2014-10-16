package pl.semantyk.dao;

import pl.semantyk.domain.Antonym;
import pl.semantyk.exceptions.SystemException;

import java.util.List;

/**
 * DAO class. Support CRUD operations for Antonym entity.
 */
public interface AntonymDao {

    /**
     * Search Antonym etity by Id
     *
     * @param id key.
     * @return existing entity in Database.
     * @throws SystemException exception.
     */
    public Antonym findById(Integer id) throws SystemException;

    /**
     * Query all rows of Antonym table.
     *
     * @return Collection of all Antonym table rows.
     * @throws SystemException exception.
     */
    public List<Antonym> findAll() throws SystemException;

    /**
     * Deletes specified row in database.
     *
     * @param entity Entity object to be removed.
     * @throws SystemException exception.
     */
    public void remove(Antonym entity) throws SystemException;

    /**
     * Removes all Antonyms in database existing in collection.
     *
     * @param entities collection of entities to remove.
     * @throws SystemException exception.
     */
    public void removeAll(List<Antonym> entities) throws SystemException;

    /**
     * Removes all Antonyms in database
     * @throws SystemException
     */
    public void removeAll() throws SystemException;
}
