package pl.semantyk.dao;

import pl.semantyk.domain.RawWikiUnit;
import pl.semantyk.exceptions.DataAccessCode;
import pl.semantyk.exceptions.SystemException;

import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;

public class RawWikiUnitDaoImpl extends AbstractDao implements RawWikiUnitDao {
    @Override
    public void persist(RawWikiUnit entity) {
        openTransaction();
        em.persist(entity);
        closeTransaction();
    }

    @Override
    public void persistNative(RawWikiUnit entity) {

        String insertNativeStr =
                "INSERT INTO raw_wiki_unit" +
                        "(NAZWA," +
                        "BODY)" +
                        "VALUES" +
                        "(?,?);";

        openTransaction();
        em.createNativeQuery(insertNativeStr)
                .setParameter(1, entity.getTitle())
                .setParameter(2, entity.getText())
                .executeUpdate();
        closeTransaction();

    }

    @Override
    public void persistAllNative(Collection<RawWikiUnit> entities) {
        for (RawWikiUnit entity : entities) {
            persistNative(entity);
        }
    }

    @Override
    public RawWikiUnit findById(Integer id) throws SystemException {
        RawWikiUnit result;

        try {
            result = em.find(RawWikiUnit.class, id);
        } catch (IllegalArgumentException ex) {
            SystemException se = new SystemException("Illegal argument.", ex, DataAccessCode.ILLEGAL_ARGUMENT).set("id:", id);
            throw se;
        }

        return result;
    }

    @Override
    public List<RawWikiUnit> findAll() throws SystemException {
        List<RawWikiUnit> result;
        TypedQuery<RawWikiUnit> query = em.createQuery("FROM RawWikiUnit", RawWikiUnit.class);
        result = query.getResultList();

        if (result.isEmpty()) {
            SystemException se = new SystemException("No entity found.", DataAccessCode.NO_ENTITY_FOUND);
            throw se;
        }

        return result;
    }

    @Override
    public void remove(RawWikiUnit entity) throws SystemException {
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
    public void removeAll(List<RawWikiUnit> entities) throws SystemException {
        openTransaction();
        for (RawWikiUnit entity : entities) {
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
