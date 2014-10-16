package pl.semantyk.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import pl.semantyk.domain.Cognate;
import pl.semantyk.exceptions.DataAccessCode;
import pl.semantyk.exceptions.SystemException;

/**
 * Default implementation of Cognate interface.
 * 
 * @author Sebastian Fabisz.
 * 
 */
public class CognateDaoImpl extends AbstractDao implements CognateDao {

	public CognateDaoImpl() {
	}

	@Override
	public Cognate findById(Integer id) throws SystemException {
		Cognate result;

		try {
			result = em.find(Cognate.class, id);
		} catch (IllegalArgumentException ex) {
			SystemException se = new SystemException("Invalid argument.", ex,
					DataAccessCode.ILLEGAL_ARGUMENT).set("id:", id);
			throw se;
		}

		return result;
	}

	@Override
	public List<Cognate> findAll() throws SystemException {
		List<Cognate> result;
		TypedQuery<Cognate> query = em.createQuery("FROM Cognate",
				Cognate.class);
		result = query.getResultList();

		if (result.isEmpty()) {
			SystemException se = new SystemException("No entity found.",
					DataAccessCode.NO_ENTITY_FOUND);
			throw se;
		}

		return result;
	}

	@Override
	public void remove(Cognate entity) throws SystemException {
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
	}

	@Override
	public void removeAll(List<Cognate> entities) throws SystemException {
		openTransaction();
		for (Cognate entity : entities) {
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
		em.createNativeQuery("DELETE FROM POKREWNE");
		closeTransaction();
	}

	@Override
	public void closeEm() {
		em.close();
	}
}
