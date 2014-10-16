package pl.semantyk.dao;

import pl.semantyk.domain.NumeralVar;
import pl.semantyk.exceptions.DataAccessCode;
import pl.semantyk.exceptions.SystemException;

import javax.persistence.TypedQuery;
import java.util.List;

public class NumeralVarDaoImpl extends AbstractDao implements NumeralVarDao {
	@Override
	public NumeralVar findById(Integer id) throws SystemException {
		NumeralVar result;

		try {
			result = em.find(NumeralVar.class, id);
		} catch (IllegalArgumentException ex) {
			SystemException se = new SystemException("Invalid argument.", ex,
					DataAccessCode.ILLEGAL_ARGUMENT).set("id:", id);
			throw se;
		}

		return result;
	}

	@Override
	public List<NumeralVar> findAll() throws SystemException {
		List<NumeralVar> result;
		TypedQuery<NumeralVar> query = em.createQuery("FROM NumeralVar",
				NumeralVar.class);
		result = query.getResultList();

		if (result.isEmpty()) {
			SystemException se = new SystemException("No entity found.",
					DataAccessCode.NO_ENTITY_FOUND);
			throw se;
		}

		return result;
	}

	@Override
	public void remove(NumeralVar entity) throws SystemException {
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
	public void removeAll(List<NumeralVar> entities) throws SystemException {
		openTransaction();
		for (NumeralVar entity : entities) {
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
	public void closeEm() {
		em.close();
	}
}
