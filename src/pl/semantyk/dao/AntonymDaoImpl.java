package pl.semantyk.dao;

import pl.semantyk.domain.Antonym;
import pl.semantyk.exceptions.DataAccessCode;
import pl.semantyk.exceptions.SystemException;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import java.util.List;

/**
 * Default implementation for AntonymDao interface.
 */
public class AntonymDaoImpl extends AbstractDao implements AntonymDao {
    
	private static final Logger LOG = Logger.getLogger(AntonymDaoImpl.class);
	
	public AntonymDaoImpl() {}
	
	@Override
    public Antonym findById(Integer id) throws SystemException {
        Antonym result;

        try {
            result = em.find(Antonym.class, id);
        } catch (IllegalArgumentException ex) {
            SystemException se = new SystemException("Illegal argument.", ex, DataAccessCode.ILLEGAL_ARGUMENT).set("id:", id);
            throw se;
        }

        return result;
    }

    @Override
    public List<Antonym> findAll() throws SystemException {
        List<Antonym> result;
        TypedQuery<Antonym> query = em.createQuery("FROM Antonym", Antonym.class);
        result = query.getResultList();

        if (result.isEmpty()) {
            SystemException se = new SystemException("No entity found.", DataAccessCode.NO_ENTITY_FOUND);
            throw se;
        }

        return result;
    }

    @Override
    public void remove(Antonym entity) throws SystemException {
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
    public void removeAll(List<Antonym> entities) throws SystemException {
        openTransaction();
        for (Antonym entity : entities) {
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
    	em.createNativeQuery("DELETE FROM ANTONIMY").executeUpdate();
    	closeTransaction();
    }

    @Override
    public void closeEm() {
        em.close();
        LOG.debug("EntityManager closed by AntonymDao.");
    }
}
