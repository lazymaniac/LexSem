package pl.semantyk.dao;

import pl.semantyk.domain.Importance;
import pl.semantyk.exceptions.DataAccessCode;
import pl.semantyk.exceptions.SystemException;

import javax.persistence.TypedQuery;
import java.util.List;


public class ImportanceDaoImpl extends AbstractDao implements ImportanceDao {
    @Override
    public Importance findById(Integer id) throws SystemException {
        Importance result;

        try {
            result = em.find(Importance.class, id);
        } catch (IllegalArgumentException ex) {
            SystemException se = new SystemException("Illegal argument.", ex, DataAccessCode.ILLEGAL_ARGUMENT).set("id:", id);
            throw se;
        }

        return result;
    }

    @Override
    public List<Importance> findAll() throws SystemException {
        List<Importance> result;
        TypedQuery<Importance> query = em.createQuery("FROM Importance", Importance.class);
        result = query.getResultList();

        if (result.isEmpty()) {
            SystemException se = new SystemException("No entity found.", DataAccessCode.NO_ENTITY_FOUND);
            throw se;
        }

        return result;
    }

    @Override
    public void remove(Importance entity) throws SystemException {
        openTransaction();
        try {
            em.remove(entity);
        } catch (IllegalArgumentException iae) {
            SystemException se = new SystemException("Illegal argument.", iae, DataAccessCode.ILLEGAL_ARGUMENT)
                    .set("Entity:", entity.toString());
            throw se;
        }
        closeTransaction();
    }

    @Override
    public void removeAll(List<Importance> entities) throws SystemException {
        openTransaction();
        for (Importance entity : entities) {
            try {
                em.remove(entity);
            } catch (IllegalArgumentException iae) {
                SystemException se = new SystemException("Illegal argument.", iae, DataAccessCode.ILLEGAL_ARGUMENT)
                        .set("Entity:", entity.toString());
                throw se;
            }
        }
        closeTransaction();
    }

    @Override
    public void closeEm() {
        em.close();
    }
}
