package pl.semantyk.dao;

import pl.semantyk.domain.Phraseology;
import pl.semantyk.exceptions.DataAccessCode;
import pl.semantyk.exceptions.SystemException;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Default implementation of PhraseologyDao interface.
 * @author Sebastian Fabisz.
 *
 */
public class PhraseologyDaoImpl extends AbstractDao implements PhraseologyDao {

	public PhraseologyDaoImpl() { }
	
	@Override
	public Phraseology findById(Integer id) throws SystemException {
		Phraseology result;

		try {
			result = em.find(Phraseology.class, id);
		} catch (IllegalArgumentException ex) {
			SystemException se = new SystemException("Invalid argument.", ex,
					DataAccessCode.ILLEGAL_ARGUMENT).set("id:", id);
			throw se;
		}

		return result;
	}

	@Override
	public List<Phraseology> findAll() throws SystemException {
		List<Phraseology> result;
		TypedQuery<Phraseology> query = em.createQuery("FROM Phraseology",
				Phraseology.class);
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
	public void remove(Phraseology entity) throws SystemException {
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
	public void removeAll(List<Phraseology> entities) throws SystemException {
		openTransaction();
		for (Phraseology entity : entities) {
			try {
				em.remove(entity);
			} catch (IllegalArgumentException iae) {
				SystemException se = new SystemException("Invalid argument.", iae,
						DataAccessCode.ILLEGAL_ARGUMENT).set("Entity:",
						entity.toString());
				throw se;
			}
		}
		closeTransaction();
	}

	@Override
	public void removeAll() throws SystemException {
		openTransaction();
		em.createNativeQuery("DELETE FROM FRAZEOLOGIE");
	}
	
	@Override
	public void closeEm() {
		em.close();
	}
}
