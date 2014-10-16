package pl.semantyk.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import pl.semantyk.domain.RelationType;
import pl.semantyk.exceptions.CommonCode;
import pl.semantyk.exceptions.DataAccessCode;
import pl.semantyk.exceptions.SystemException;

public class RelationTypeDaoImpl extends AbstractDao implements RelationTypeDao {

	private static final Logger LOG = Logger
			.getLogger(RelationTypeDaoImpl.class);

	@Override
	public RelationType findById(Integer id) throws SystemException {
		RelationType result;

		try {
			result = em.find(RelationType.class, id);
		} catch (IllegalArgumentException ex) {
			SystemException se = new SystemException("Illegal argument.", ex,
					DataAccessCode.ILLEGAL_ARGUMENT).set("id:", id);
			throw se;
		}

		return result;
	}

	@Override
	public List<RelationType> findAll() throws SystemException {
		List<RelationType> result;
		TypedQuery<RelationType> query = em.createQuery("FROM RelationType",
				RelationType.class);
		result = query.getResultList();

		if (result.isEmpty()) {
			SystemException se = new SystemException("No entity found.",
					DataAccessCode.NO_ENTITY_FOUND);
			throw se;
		}

		return result;
	}

	@Override
	public void persist(RelationType entity) throws SystemException {
		if (entity == null) {
			throw new SystemException("Entity is null.",
					CommonCode.NULL_POINTER);
		}

		em.persist(entity);
	}

	@Override
	public void persistNative(RelationType entity) throws SystemException {

		if (entity == null) {
			throw new SystemException("Entity is null.",
					CommonCode.NULL_POINTER);
		}

		String insertNativeSql = "INSERT INTO TYP_RELACJI "
				+ "(ID_TYP_RELACJI, " + "TYP, " + "RODZIC, " + "NAZWA, "
				+ "OPIS, " + "POSSTR, " + "WYSWIETL, " + "SKROT, "
				+ "AUTOODWRACANIE) " + " VALUES " + "(" + entity.getId()
				+ ",?, " + entity.getParent() + ", ?, ?, ?, ?, ?, ?" + ");";

		openTransaction();
		em.createNativeQuery(insertNativeSql).setParameter(1, entity.getType())
				.setParameter(2, entity.getName())
				.setParameter(3, entity.getDescription())
				.setParameter(4, entity.getPosstr())
				.setParameter(5, entity.getDisplay())
				.setParameter(6, entity.getAbbreviation())
				.setParameter(7, entity.getAutoOdwracanie()).executeUpdate();
		closeTransaction();
	}

	@Override
	public void persistAllNative(List<RelationType> entities)
			throws SystemException {

		String insertNativeSql = "INSERT INTO TYP_RELACJI "
				+ "(ID_TYP_RELACJI, " + "TYP, " + "RODZIC, " + "NAZWA, "
				+ "OPIS, " + "POSSTR, " + "WYSWIETL, " + "SKROT, "
				+ "AUTOODWRACANIE) " + " VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?);";

		openTransaction();
		for (RelationType entity : entities) {
			em.createNativeQuery(insertNativeSql)
					.setParameter(1, entity.getId())
					.setParameter(2, entity.getType())
					.setParameter(3, entity.getParent())
					.setParameter(4, entity.getName())
					.setParameter(5, entity.getDescription())
					.setParameter(6, entity.getPosstr())
					.setParameter(7, entity.getDisplay())
					.setParameter(8, entity.getAbbreviation())
					.setParameter(9, entity.getAutoOdwracanie())
					.executeUpdate();
		}
		closeTransaction();
	}

	@Override
	public void peristAll(List<RelationType> entities) throws SystemException {
		if (entities == null) {
			throw new SystemException("Colllection is null.",
					CommonCode.NULL_POINTER);
		}

		if (entities.isEmpty()) {
			throw new SystemException("List is empty.",
					CommonCode.ILLEGAL_ARGUMENT);
		}

		int count = 0;
		for (RelationType entity : entities) {
			count++;
			em.persist(entity);
			if (count % 10 == 0)
				LOG.info(count + " relation types persisted.");
		}
	}

	@Override
	public void remove(RelationType entity) throws SystemException {
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
	public void removeAll(List<RelationType> entities) throws SystemException {
		openTransaction();
		for (RelationType entity : entities) {
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
