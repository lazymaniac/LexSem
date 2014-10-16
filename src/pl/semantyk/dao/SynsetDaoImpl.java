package pl.semantyk.dao;

import static pl.semantyk.utils.CommonUtils.printf;

import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;

import pl.semantyk.domain.Synset;
import pl.semantyk.exceptions.DataAccessCode;
import pl.semantyk.exceptions.SystemException;
import pl.semantyk.parse.entities.SynsetRaw;

public class SynsetDaoImpl extends AbstractDao implements SynsetDao {
	@Override
	public Synset findById(Integer id) throws SystemException {
		Synset result;

		try {
			result = em.find(Synset.class, id);
		} catch (IllegalArgumentException ex) {
			SystemException se = new SystemException("Błędny argument.", ex,
					DataAccessCode.ILLEGAL_ARGUMENT).set("id:", id);
			throw se;
		}

		return result;
	}

	@Override
	public List<Synset> findAll() throws SystemException {
		List<Synset> result;
		TypedQuery<Synset> query = em.createQuery("FROM Synset", Synset.class);
		result = query.getResultList();

		if (result.isEmpty()) {
			SystemException se = new SystemException("No entity found.",
					DataAccessCode.NO_ENTITY_FOUND);
			throw se;
		}

		return result;
	}

	@Override
	public void persistNative(SynsetRaw entity) throws SystemException {

		String insertNativeSql = "INSERT INTO SYNSET " + "(ID_SYNSET,"
				+ "PODZIELNY," + "ABSTRACT)" + "VALUES" + "(?,?,?)";

		String insertNativeStr = "INSERT INTO JEDN_WN_SYN_REL "
				+ "(ID_JEDN_WN," + "ID_SYNSET)" + "VALUES" + "(?,?)";

		openTransaction();
		em.createNativeQuery(insertNativeSql).setParameter(1, entity.getId())
				.setParameter(2, entity.getSplit())
				.setParameter(3, entity.getAbstractsynstet()).executeUpdate();
		closeTransaction();

		openTransaction();
		for (Integer jednId : entity.getUnits()) {
			em.createNativeQuery(insertNativeStr).setParameter(1, jednId)
					.setParameter(2, entity.getId()).executeUpdate();
		}
		closeTransaction();

	}

	@Override
	public void persistAllNative(Set<SynsetRaw> entities)
			throws SystemException {
		String insertNativeSql = "INSERT INTO SYNSET" + "(ID_SYNSET,"
				+ "PODZIELNY," + "ABSTRACT)" + "VALUES" + "(?,?,?)";

		int count = 0;
		openTransaction();
		for (SynsetRaw entity : entities) {
			em.createNativeQuery(insertNativeSql)
					.setParameter(1, entity.getId())
					.setParameter(2, entity.getSplit())
					.setParameter(3, entity.getAbstractsynstet())
					.executeUpdate();
			count++;
			if (count % 1000 == 0) {
				em.flush();
				printf("%d synsets persisted.\n", count);
			}
		}
		closeTransaction();
	}

	@Override
	public void remove(Synset entity) throws SystemException {
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
	public void removeAll(List<Synset> entities) throws SystemException {
		openTransaction();
		for (Synset entity : entities) {
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
