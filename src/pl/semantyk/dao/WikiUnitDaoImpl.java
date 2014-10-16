package pl.semantyk.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import pl.semantyk.domain.WikiUnit;
import pl.semantyk.exceptions.DataAccessCode;
import pl.semantyk.exceptions.SystemException;

/**
 * Default implementation of WikiUnitDao.
 * 
 * @author Sebastian Fabisz.
 * 
 */
public class WikiUnitDaoImpl extends AbstractDao implements WikiUnitDao {

	private static final Logger LOG = Logger.getLogger(WikiUnitDaoImpl.class);

	public WikiUnitDaoImpl() {
		super();
	}

	@Override
	public void closeEm() {
		em.close();
		em = null;
	}

	@Override
	public void persist(WikiUnit entity) {
		openTransaction();
		em.merge(entity);
		closeTransaction();
	}

	@Override
	public void persistAll(Collection<WikiUnit> col) {
		int count = 0;

		openTransaction();

		for (WikiUnit jednostkaWiki : col) {
			count++;
			em.persist(jednostkaWiki);
			if (count % 1000 == 0) {
				LOG.info(count + " units saved.");
				em.flush();
				closeTransaction();
				openTransaction();
			}
		}
		em.flush();
		closeTransaction();
	}

	@Override
	public WikiUnit findById(Integer id) throws SystemException {
		WikiUnit result;

		try {
			result = em.find(WikiUnit.class, id);
		} catch (IllegalArgumentException ex) {
			SystemException se = new SystemException("Invalid argument.", ex,
					DataAccessCode.ILLEGAL_ARGUMENT).set("id:", id);
			throw se;
		}

		return result;
	}

	@Override
	public List<WikiUnit> findAll() throws SystemException {
		List<WikiUnit> result;
		TypedQuery<WikiUnit> query = em.createQuery("FROM WikiUnit",
				WikiUnit.class);
		result = query.getResultList();

		if (result != null && result.isEmpty()) {
			SystemException se = new SystemException("No entites found.",
					DataAccessCode.NO_ENTITY_FOUND);
			throw se;
		}

		return result;
	}

	@Override
	public void remove(WikiUnit entity) throws SystemException {
		openTransaction();
		try {
			em.remove(entity);
		} catch (IllegalArgumentException iae) {
			SystemException se = new SystemException("Invalid argument.", iae,
					DataAccessCode.ILLEGAL_ARGUMENT).set("Entity:",
					entity.toString());
			throw se;
		}
		closeTransaction();
		em.close();
	}

	@Override
	public void removeAll(List<WikiUnit> entities) throws SystemException {
		openTransaction();
		for (WikiUnit entity : entities) {
			try {
				em.remove(entity);
			} catch (IllegalArgumentException iae) {
				SystemException se = new SystemException("Invalid argument.",
						iae, DataAccessCode.ILLEGAL_ARGUMENT).set("Entity:",
						entity.toString());
				throw se;
			}
		}
		closeTransaction();
	}

	@Override
	public void removeAll() throws SystemException {
		openTransaction();
		em.createNativeQuery("DELETE FROM JEDNOSTKA_WIKI");
		closeTransaction();
	}
}
