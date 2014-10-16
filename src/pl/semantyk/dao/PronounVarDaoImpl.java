package pl.semantyk.dao;

import pl.semantyk.domain.PronounVar;
import pl.semantyk.exceptions.DataAccessCode;
import pl.semantyk.exceptions.SystemException;

import javax.persistence.TypedQuery;
import java.util.List;

public class PronounVarDaoImpl extends AbstractDao implements PronounVarDao {
    @Override
    public PronounVar findById(Integer id) throws SystemException {
        PronounVar result;

        try {
            result = em.find(PronounVar.class, id);
        } catch (IllegalArgumentException ex) {
            SystemException se = new SystemException("Illegal argument.", ex, DataAccessCode.ILLEGAL_ARGUMENT).set("id:", id);
            throw se;
        }

        return result;
    }

    @Override
    public List<PronounVar> findAll() throws SystemException {
        List<PronounVar> result;
        TypedQuery<PronounVar> query = em.createQuery("FROM PronounVar", PronounVar.class);
        result = query.getResultList();

        if (result.isEmpty()) {
            SystemException se = new SystemException("No entity found.", DataAccessCode.NO_ENTITY_FOUND);
            throw se;
        }

        return result;
    }

    @Override
    public void remove(PronounVar entity) throws SystemException {
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
    public void removeAll(List<PronounVar> entities) throws SystemException {
        openTransaction();
        for (PronounVar entity : entities) {
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
