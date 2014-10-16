package pl.semantyk.dao;

import pl.semantyk.domain.PartOfSpeech;
import pl.semantyk.exceptions.DataAccessCode;
import pl.semantyk.exceptions.SystemException;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Default implementation of Part of Spech interface.
 * @author Sebastian Fabisz.
 *
 */
public class PartOfSpeechDaoImpl extends AbstractDao implements PartOfSpechDao {
    @Override
    public PartOfSpeech findById(Integer id) throws SystemException {
        PartOfSpeech result;

        try {
            result = em.find(PartOfSpeech.class, id);
        } catch (IllegalArgumentException ex) {
            SystemException se = new SystemException("Invalid argumment.", ex, DataAccessCode.ILLEGAL_ARGUMENT).set("id:", id);
            throw se;
        }

        return result;
    }

    @Override
    public List<PartOfSpeech> findAll() throws SystemException {
        List<PartOfSpeech> result;
        TypedQuery<PartOfSpeech> query = em.createQuery("FROM PartOfSpeech", PartOfSpeech.class);
        result = query.getResultList();

        if (result.isEmpty()) {
            SystemException se = new SystemException("No entites found.", DataAccessCode.NO_ENTITY_FOUND);
            throw se;
        }

        return result;
    }

    @Override
    public void remove(PartOfSpeech entity) throws SystemException {
        openTransaction();
        try {
            em.remove(entity);
        } catch (IllegalArgumentException iae) {
            SystemException se = new SystemException("Invalid argument.", iae, DataAccessCode.ILLEGAL_ARGUMENT)
                    .set("Entity:", entity.toString());
            throw se;
        }
        closeTransaction();
    }

    @Override
    public void removeAll(List<PartOfSpeech> entities) throws SystemException {
        openTransaction();
        for (PartOfSpeech entity : entities) {
            try {
                em.remove(entity);
            } catch (IllegalArgumentException iae) {
                SystemException se = new SystemException("Invalid argument.", iae, DataAccessCode.ILLEGAL_ARGUMENT)
                        .set("Entity:", entity.toString());
                throw se;
            }
        }
        closeTransaction();
    }

    @Override
    public void removeAll() throws SystemException {
    	openTransaction();
    	em.createNativeQuery("DELETE FROM CZESCI_MOWY").executeUpdate();
    	closeTransaction();
    }
    
    @Override
    public void closeEm() {
        em.close();
    }
}
