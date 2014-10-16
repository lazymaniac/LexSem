package pl.semantyk.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import pl.semantyk.domain.SynsetRelation;
import pl.semantyk.exceptions.CommonCode;
import pl.semantyk.exceptions.DataAccessCode;
import pl.semantyk.exceptions.SystemException;
import pl.semantyk.parse.entities.SynsetRelationRaw;

public class SynsetRelationDaoImpl extends AbstractDao implements
		SynsetRelationDao {

	private static final Logger LOG = Logger
			.getLogger(SynsetRelationDaoImpl.class);

	@Override
	public SynsetRelation findById(Integer id) throws SystemException {
		SynsetRelation result;

		try {
			result = em.find(SynsetRelation.class, id);
		} catch (IllegalArgumentException ex) {
			SystemException se = new SystemException("Illegal argument.", ex,
					DataAccessCode.ILLEGAL_ARGUMENT).set("id:", id);
			throw se;
		}

		return result;
	}

	@Override
	public List<SynsetRelation> findAll() throws SystemException {
		List<SynsetRelation> result;
		TypedQuery<SynsetRelation> query = em.createQuery(
				"FROM SynsetRelation", SynsetRelation.class);
		result = query.getResultList();

		if (result.isEmpty()) {
			SystemException se = new SystemException("No entity found.",
					DataAccessCode.NO_ENTITY_FOUND);
			throw se;
		}

		return result;
	}

	@Override
	public void persistNative(SynsetRelationRaw entity) throws SystemException {

		if (entity == null) {
			throw new SystemException("Entity is null", CommonCode.NULL_POINTER);
		}

		String insertNativeSql = "INSERT INTO RELACJA_SYNSETU " + "(RODZIC, "
				+ "DZIECKO, " + "RELACJA, " + "SPRAWDZONY) " + "VALUES "
				+ "(?,?,?,?)";

		openTransaction();
		em.createNativeQuery(insertNativeSql)
				.setParameter(1, entity.getParent())
				.setParameter(2, entity.getChild())
				.setParameter(3, entity.getRelation())
				.setParameter(4, entity.getChecked()).executeUpdate();
		closeTransaction();

	}

	@Override
	public void persistAllNative(Set<SynsetRelationRaw> entities)
			throws SystemException {

		String insertNativeSql = "INSERT INTO RELACJA_SYNSETU "
				+ "(RODZIC, DZIECKO, RELACJA, SPRAWDZONY) "
				+ "VALUES (?,?,?,?)";

		int count = 0;
		openTransaction();
		for (SynsetRelationRaw entity : entities) {

			em.createNativeQuery(insertNativeSql)
					.setParameter(1, entity.getParent())
					.setParameter(2, entity.getChild())
					.setParameter(3, entity.getRelation())
					.setParameter(4, entity.getChecked()).executeUpdate();

			count++;
			if (count % 1000 == 0) {
				em.flush();
				LOG.info(count + " synset relations persisted.");
			}
		}
		closeTransaction();
	}

	@Override
	public void remove(SynsetRelation entity) throws SystemException {
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
	public void removeAll(List<SynsetRelation> entities) throws SystemException {
		openTransaction();
		for (SynsetRelation entity : entities) {
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
