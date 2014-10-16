package pl.semantyk.dao;

import pl.semantyk.domain.AdjectiveVar;
import pl.semantyk.exceptions.DataAccessCode;
import pl.semantyk.exceptions.SystemException;

import javax.persistence.TypedQuery;
import java.util.List;

public class AdjectiveVarDaoImpl extends AbstractDao implements AdjectiveVarDao {
	
	@Override
	public AdjectiveVar findById(Integer id) throws SystemException {
		AdjectiveVar result;

		try {
			result = em.find(AdjectiveVar.class, id);
		} catch (IllegalArgumentException ex) {
			SystemException se = new SystemException("Illegal argument.", ex,
					DataAccessCode.ILLEGAL_ARGUMENT).set("id:", id);
			throw se;
		}

		return result;
	}

	@Override
	public List<AdjectiveVar> findAll() throws SystemException {
		List<AdjectiveVar> result;
		TypedQuery<AdjectiveVar> query = em.createQuery("FROM AdjectiveVar",
				AdjectiveVar.class);
		result = query.getResultList();

		if (result.isEmpty()) {
			SystemException se = new SystemException("No entity found.",
					DataAccessCode.NO_ENTITY_FOUND);
			throw se;
		}

		return result;
	}

	@Override
	public void remove(AdjectiveVar entity) throws SystemException {
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
	public void removeAll(List<AdjectiveVar> entities) throws SystemException {
		openTransaction();
		for (AdjectiveVar entity : entities) {
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
