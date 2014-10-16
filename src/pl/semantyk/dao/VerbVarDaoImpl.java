package pl.semantyk.dao;

import pl.semantyk.domain.VerbVar;
import pl.semantyk.exceptions.DataAccessCode;
import pl.semantyk.exceptions.SystemException;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Default implementation of VerbVarDao.
 */
public class VerbVarDaoImpl extends AbstractDao implements VerbVarDao {
	@Override
	public VerbVar findById(Integer id) throws SystemException {
		VerbVar result;

		try {
			result = em.find(VerbVar.class, id);
		} catch (IllegalArgumentException ex) {
			SystemException se = new SystemException("Illegal argument.", ex,
					DataAccessCode.ILLEGAL_ARGUMENT).set("id:", id);
			throw se;
		}

		return result;
	}

	@Override
	public List<VerbVar> findAll() throws SystemException {
		List<VerbVar> result;
		TypedQuery<VerbVar> query = em.createQuery("FROM VerbVar",
				VerbVar.class);
		result = query.getResultList();

		if (result.isEmpty()) {
			SystemException se = new SystemException("No entity found.",
					DataAccessCode.NO_ENTITY_FOUND);
			throw se;
		}

		return result;
	}

	@Override
	public void remove(VerbVar entity) throws SystemException {
		openTransaction();
		try {
			em.remove(entity);
		} catch (IllegalArgumentException iae) {
			SystemException se = new SystemException("Illegal argument.", iae,
					DataAccessCode.ILLEGAL_ARGUMENT).set("Entity:",
					entity.toString());
			throw se;
		}
		closeTransaction();
	}

	@Override
	public void removeAll(List<VerbVar> entities) throws SystemException {
		openTransaction();
		for (VerbVar entity : entities) {
			try {
				em.remove(entity);
			} catch (IllegalArgumentException iae) {
				SystemException se = new SystemException("Illegal argument.",
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
		em.createNativeQuery("DELETE FROM CZASOWNIK_ODM").executeUpdate();
		closeTransaction();
	}

	@Override
	public void closeEm() {
		em.close();
	}
}
