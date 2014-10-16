package pl.semantyk.dao;

import pl.semantyk.domain.Synonym;
import pl.semantyk.exceptions.DataAccessCode;
import pl.semantyk.exceptions.SystemException;

import javax.persistence.TypedQuery;
import java.util.List;

public class SynonimDaoImpl extends AbstractDao implements SynonimDao {
    @Override
    public Synonym findById(Integer id) throws SystemException {
        Synonym result;

        try {
            result = em.find(Synonym.class, id);
        } catch (IllegalArgumentException ex) {
            SystemException se = new SystemException("Błędny argument.", ex, DataAccessCode.ILLEGAL_ARGUMENT).set("id:", id);
            throw se;
        }

        return result;
    }

    @Override
    public List<Synonym> findAll() throws SystemException {
        List<Synonym> result;
        TypedQuery<Synonym> query = em.createQuery("FROM Synonim", Synonym.class);
        result = query.getResultList();

        if (result.isEmpty()) {
            SystemException se = new SystemException("Nie znaleziono żandych encji.", DataAccessCode.NO_ENTITY_FOUND);
            throw se;
        }

        return result;
    }

    @Override
    public void remove(Synonym entity) throws SystemException {
        openTransaction();
        try {
            em.remove(entity);
        } catch (IllegalArgumentException iae) {
            SystemException se = new SystemException("Błędny argument.", iae, DataAccessCode.ILLEGAL_ARGUMENT)
                    .set("Entity:", entity.toString());
            throw se;
        }
        closeTransaction();
    }

    @Override
    public void removeAll(List<Synonym> entities) throws SystemException {
        openTransaction();
        for (Synonym entity : entities) {
            try {
                em.remove(entity);
            } catch (IllegalArgumentException iae) {
                SystemException se = new SystemException("Zły argument.", iae, DataAccessCode.ILLEGAL_ARGUMENT)
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
