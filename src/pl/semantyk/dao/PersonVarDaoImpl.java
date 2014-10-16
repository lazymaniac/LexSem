package pl.semantyk.dao;

import pl.semantyk.domain.PersonVar;
import pl.semantyk.exceptions.DataAccessCode;
import pl.semantyk.exceptions.SystemException;

import javax.persistence.TypedQuery;
import java.util.List;

public class PersonVarDaoImpl extends AbstractDao implements PersonVarDao {

	@Override
	public PersonVar findById(Integer id) throws SystemException {
		PersonVar result;

		try {
			result = em.find(PersonVar.class, id);
		} catch (IllegalArgumentException ex) {
			SystemException se = new SystemException("Invalid argument.", ex,
					DataAccessCode.ILLEGAL_ARGUMENT).set("id:", id);
			throw se;
		}

		return result;
	}

	@Override
	public List<PersonVar> findAll() throws SystemException {
		List<PersonVar> result;
		TypedQuery<PersonVar> query = em.createQuery("FROM PersonVar",
				PersonVar.class);
		result = query.getResultList();

		if (result.isEmpty()) {
			SystemException se = new SystemException("No entity found.",
					DataAccessCode.NO_ENTITY_FOUND);
			throw se;
		}

		return result;
	}

	@Override
	public void remove(PersonVar entity) throws SystemException {
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
	public void removeAll(List<PersonVar> entities) throws SystemException {
		openTransaction();
		for (PersonVar entity : entities) {
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
