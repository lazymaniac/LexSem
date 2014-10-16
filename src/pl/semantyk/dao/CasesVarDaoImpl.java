package pl.semantyk.dao;

import pl.semantyk.domain.CasesVar;
import pl.semantyk.exceptions.DataAccessCode;
import pl.semantyk.exceptions.SystemException;

import javax.persistence.TypedQuery;
import java.util.List;

public class CasesVarDaoImpl extends AbstractDao implements CasesVarDao {
	@Override
	public CasesVar findById(Integer id) throws SystemException {
		CasesVar result;

		try {
			result = em.find(CasesVar.class, id);
		} catch (IllegalArgumentException ex) {
			SystemException se = new SystemException("Illegal argument.", ex,
					DataAccessCode.ILLEGAL_ARGUMENT).set("id:", id);
			throw se;
		}

		return result;
	}

	@Override
	public List<CasesVar> findAll() throws SystemException {
		List<CasesVar> result;
		TypedQuery<CasesVar> query = em.createQuery("FROM CasesVar",
				CasesVar.class);
		result = query.getResultList();

		if (result.isEmpty()) {
			SystemException se = new SystemException("No entity found.",
					DataAccessCode.NO_ENTITY_FOUND);
			throw se;
		}

		return result;
	}

	@Override
	public void remove(CasesVar entity) throws SystemException {
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
	public void removeAll(List<CasesVar> entities) throws SystemException {
		openTransaction();
		for (CasesVar entity : entities) {
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
