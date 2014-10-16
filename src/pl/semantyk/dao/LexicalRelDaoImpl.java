package pl.semantyk.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import pl.semantyk.domain.LexicalRel;
import pl.semantyk.exceptions.DataAccessCode;
import pl.semantyk.exceptions.SystemException;
import pl.semantyk.parse.entities.LexicalRelRaw;

public class LexicalRelDaoImpl extends AbstractDao implements LexicalRelDao {

	private static final Logger LOG = Logger.getLogger(LexicalRelDaoImpl.class);

	@Override
	public LexicalRel findById(Integer id) throws SystemException {
		LexicalRel result;

		try {
			result = em.find(LexicalRel.class, id);
		} catch (IllegalArgumentException ex) {
			SystemException se = new SystemException("Illegal argument.", ex,
					DataAccessCode.ILLEGAL_ARGUMENT).set("id:", id);
			throw se;
		}

		return result;
	}

	@Override
	public List<LexicalRel> findAll() throws SystemException {
		List<LexicalRel> result;
		TypedQuery<LexicalRel> query = em.createQuery("FROM LexicalRel",
				LexicalRel.class);
		result = query.getResultList();

		if (result.isEmpty()) {
			SystemException se = new SystemException("No entity found.",
					DataAccessCode.NO_ENTITY_FOUND);
			throw se;
		}

		return result;
	}

	@Override
	public void persistNative(LexicalRelRaw entity) throws SystemException {

		String insertNativeStr = "INSERT INTO RELACJA_LEKS" + "(RODZIC,"
				+ "DZIECKO," + "RELACJA)" + "VALUES" + "(?,?,?)";

		openTransaction();
		em.createNativeQuery(insertNativeStr)
				.setParameter(1, entity.getParent())
				.setParameter(2, entity.getChild())
				.setParameter(3, entity.getRelation()).executeUpdate();
		closeTransaction();
	}

	@Override
	public void persistAllNative(Set<LexicalRelRaw> entities)
			throws SystemException {

		String insertNativeStr = "INSERT INTO RELACJA_LEKS" + "(RODZIC,"
				+ "DZIECKO," + "RELACJA)" + "VALUES" + "(?,?,?)";

		int count = 0;
		openTransaction();
		for (LexicalRelRaw entity : entities) {
			em.createNativeQuery(insertNativeStr)
					.setParameter(1, entity.getParent())
					.setParameter(2, entity.getChild())
					.setParameter(3, entity.getRelation()).executeUpdate();

			count++;

			if (count % 1000 == 0) {
				LOG.info(count + " lexical relations persisted.");
			}
		}
		closeTransaction();
	}

	@Override
	public void remove(LexicalRel entity) throws SystemException {
		openTransaction();
		try {
			em.remove(entity);
		} catch (IllegalArgumentException iae) {
			SystemException se = new SystemException("Ilelgal argument.", iae,
					DataAccessCode.ILLEGAL_ARGUMENT).set("Entity:",
					entity.toString());
			throw se;
		}
		closeTransaction();
	}

	@Override
	public void removeAll(List<LexicalRel> entities) throws SystemException {
		openTransaction();
		for (LexicalRel entity : entities) {
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
