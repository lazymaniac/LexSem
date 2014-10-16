package pl.semantyk.dao;

import pl.semantyk.domain.AdjectiveDegreeVar;
import pl.semantyk.exceptions.DataAccessCode;
import pl.semantyk.exceptions.SystemException;

import javax.persistence.TypedQuery;
import java.util.List;

public class AdjectiveDegreeVarDaoImpl extends AbstractDao implements
		AdjectiveDegreeVarDao {

	@Override
	public AdjectiveDegreeVar findById(Integer id) throws SystemException {
		AdjectiveDegreeVar result;

		try {
			result = em.find(AdjectiveDegreeVar.class, id);
		} catch (IllegalArgumentException ex) {
			SystemException se = new SystemException("Illegal argument.", ex,
					DataAccessCode.ILLEGAL_ARGUMENT).set("id:", id);
			throw se;
		}

		return result;
	}

	@Override
	public List<AdjectiveDegreeVar> findAll() throws SystemException {
		List<AdjectiveDegreeVar> result;
		TypedQuery<AdjectiveDegreeVar> query = em.createQuery(
				"FROM AdjectiveDegreeVar", AdjectiveDegreeVar.class);
		result = query.getResultList();

		if (result.isEmpty()) {
			SystemException se = new SystemException("No entity found.",
					DataAccessCode.NO_ENTITY_FOUND);
			throw se;
		}

		return result;
	}

	@Override
	public void remove(AdjectiveDegreeVar entity) throws SystemException {
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
	public void removeAll(List<AdjectiveDegreeVar> entities)
			throws SystemException {
		openTransaction();
		for (AdjectiveDegreeVar entity : entities) {
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
	public void closeEm() {
		em.close();
	}
}
