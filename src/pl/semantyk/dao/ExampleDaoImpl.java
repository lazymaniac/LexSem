package pl.semantyk.dao;

import pl.semantyk.domain.Example;
import pl.semantyk.exceptions.DataAccessCode;
import pl.semantyk.exceptions.SystemException;

import javax.persistence.TypedQuery;
import java.util.List;

public class ExampleDaoImpl extends AbstractDao implements ExampleDao {
	
	@Override
	public Example findById(Integer id) throws SystemException {
		Example result;

		try {
			result = em.find(Example.class, id);
		} catch (IllegalArgumentException ex) {
			SystemException se = new SystemException("Illegal argument.", ex,
					DataAccessCode.ILLEGAL_ARGUMENT).set("id:", id);
			throw se;
		}

		return result;
	}

	@Override
	public List<Example> findAll() throws SystemException {
		List<Example> result;
		TypedQuery<Example> query = em.createQuery("FROM Example",
				Example.class);
		result = query.getResultList();

		if (result.isEmpty()) {
			SystemException se = new SystemException(
					"No entity found.",
					DataAccessCode.NO_ENTITY_FOUND);
			throw se;
		}

		return result;
	}

	@Override
	public void remove(Example entity) throws SystemException {
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
	public void removeAll(List<Example> entities) throws SystemException {
		openTransaction();
		for (Example entity : entities) {
			try {
				em.remove(entity);
			} catch (IllegalArgumentException iae) {
				SystemException se = new SystemException("Illegal argument.", iae,
						DataAccessCode.ILLEGAL_ARGUMENT).set("Entity:",
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
