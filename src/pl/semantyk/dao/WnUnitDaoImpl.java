package pl.semantyk.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import pl.semantyk.domain.WnUnit;
import pl.semantyk.exceptions.CommonCode;
import pl.semantyk.exceptions.DataAccessCode;
import pl.semantyk.exceptions.SystemException;

public class WnUnitDaoImpl extends AbstractDao implements WnUnitDao {

	private static final Logger LOG = Logger.getLogger(WnUnitDaoImpl.class);

	@Override
	public WnUnit findById(Integer id) throws SystemException {
		WnUnit result;

		try {
			result = em.find(WnUnit.class, id);
		} catch (IllegalArgumentException ex) {
			SystemException se = new SystemException("Invalid argument.", ex,
					DataAccessCode.ILLEGAL_ARGUMENT).set("id:", id);
			throw se;
		}

		return result;
	}

	@Override
	public List<WnUnit> findAll() throws SystemException {
		List<WnUnit> result;
		TypedQuery<WnUnit> query = em.createQuery("FROM WnUnit", WnUnit.class);
		result = query.getResultList();

		if (result.isEmpty()) {
			SystemException se = new SystemException("No entity found.",
					DataAccessCode.NO_ENTITY_FOUND);
			throw se;
		}

		return result;
	}

	@Override
	public void persist(WnUnit entity) throws SystemException {
		if (entity == null) {
			throw new SystemException("Entity is null.",
					CommonCode.NULL_POINTER);
		}

		em.persist(entity);
	}

	@Override
	public void persistNative(WnUnit entity) throws SystemException {
		String insertNativeSql = "INSERT INTO jednostka_wn" + "(ID_JEDN_WN,"
				+ "NAZWA," + "POZYCJA," + "DOMENA," + "WARIANT)" + "VALUES"
				+ "(?,?,?,?,?);";

		openTransaction();
		em.createNativeQuery(insertNativeSql).setParameter(1, entity.getId())
				.setParameter(2, entity.getName())
				.setParameter(3, entity.getPosition())
				.setParameter(4, entity.getDomain())
				.setParameter(5, entity.getVariant()).executeUpdate();
		closeTransaction();
	}

	@Override
	public void persisttAllNative(Set<WnUnit> entities) throws SystemException {
		int count = 0;

		String insertNativeSql = "INSERT INTO JEDNOSTKA_WN" + "(ID_JEDN_WN,"
				+ "NAZWA," + "POZYCJA," + "DOMENA," + "WARIANT)" + "VALUES"
				+ "(?,?,?,?,?);";

		openTransaction();
		for (WnUnit jednostkaWn : entities) {
			em.createNativeQuery(insertNativeSql)
					.setParameter(1, jednostkaWn.getId())
					.setParameter(2, jednostkaWn.getName())
					.setParameter(3, jednostkaWn.getPosition())
					.setParameter(4, jednostkaWn.getDomain())
					.setParameter(5, jednostkaWn.getVariant()).executeUpdate();
			count++;
			if (count % 1000 == 0) {
				em.flush();
				LOG.info(count + " WN units persisted.");
			}
		}
		closeTransaction();
	}

	@Override
	public void mergeJednWnWithJednWiki() {

		String nativeUpdateSql = "UPDATE JEDNOSTKA_WN wn "
				+ "SET ID_JEDN_WIKI = (" + "SELECT distinct wiki.ID_JEDNOSTKA "
				+ "FROM JEDNOSTKA_WIKI wiki " + "WHERE wiki.NAZWA = wn.NAZWA "
				+ "LIMIT 0, 1)";

		openTransaction();
		em.createNativeQuery(nativeUpdateSql).executeUpdate();
		closeTransaction();
	}

	@Override
	public void peristAll(List<WnUnit> entities) throws SystemException {
		if (entities == null) {
			throw new SystemException("Entity is null.",
					CommonCode.NULL_POINTER);
		}

		if (entities.isEmpty()) {
			throw new SystemException("List is empty.",
					CommonCode.ILLEGAL_ARGUMENT);
		}

		for (WnUnit entity : entities) {
			em.persist(entity);
		}
	}

	@Override
	public void remove(WnUnit entity) throws SystemException {
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
	public void removeAll(List<WnUnit> entities) throws SystemException {
		openTransaction();
		for (WnUnit entity : entities) {
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
	public void removeAll() throws SystemException {
		openTransaction();
		em.createNativeQuery("DELETE FROM JEDNOSTKA_WN").executeUpdate();
		closeTransaction();
	}

	@Override
	public void closeEm() {
		em.close();
	}
}
