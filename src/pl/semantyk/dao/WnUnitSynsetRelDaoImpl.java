package pl.semantyk.dao;

import java.util.Set;

import org.apache.log4j.Logger;

import pl.semantyk.domain.WnUnitSynsetRel;
import pl.semantyk.exceptions.SystemException;

/**
 * @author Sebastian Fabisz
 */
public class WnUnitSynsetRelDaoImpl extends AbstractDao implements
		WnUnitSynsetRelDao {

	private static final Logger LOG = Logger
			.getLogger(WnUnitSynsetRelDaoImpl.class);

	@Override
	public void closeEm() {
		em.close();
	}

	@Override
	public void persistNative(WnUnitSynsetRel entity) throws SystemException {

		String insertNativeStr = "INSERT INTO JEDN_WN_SYN_REL" + "(ID_JEDN_WN,"
				+ "ID_SYNSET)" + "VALUES" + "(?,?)";

		openTransaction();
		em.createNativeQuery(insertNativeStr)
				.setParameter(1, entity.getWnUnitId())
				.setParameter(2, entity.getSynsetId()).executeUpdate();
		closeTransaction();
	}

	@Override
	public void perisistAllNative(Set<WnUnitSynsetRel> entities)
			throws SystemException {

		String insertNativeStr = "INSERT INTO JEDN_WN_SYN_REL" + "(ID_JEDN_WN,"
				+ "ID_SYNSET)" + "VALUES" + "(?,?)";

		int count = 0;
		openTransaction();
		for (WnUnitSynsetRel entity : entities) {
			em.createNativeQuery(insertNativeStr)
					.setParameter(1, entity.getWnUnitId())
					.setParameter(2, entity.getSynsetId()).executeUpdate();
			count++;
			if (count % 1000 == 0) {
				em.flush();
				LOG.info(count + " relations saved.\n");
			}
		}
		closeTransaction();
	}
}
