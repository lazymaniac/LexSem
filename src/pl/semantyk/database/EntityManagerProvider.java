package pl.semantyk.database;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import pl.semantyk.utils.PropertyProvider;

/**
 * Singleton initialize EntityManager instance across application.
 * 
 * @author Sebastian.Fabisz
 */
public class EntityManagerProvider {

	private static final Logger LOG = Logger
			.getLogger(EntityManagerProvider.class);

	private static EntityManager em;
	private static EntityManagerFactory factory;

	private final static String[] paramsName = new String[] {
			"hibernate.show_sql", "javax.persistence.jdbc.driver",
			"javax.persistence.jdbc.url", "javax.persistence.jdbc.user",
			"javax.persistence.jdbc.password", "hibernate.dialect" };

	private final static String[] propsName = new String[] { "show_sql",
			"driver", "url", "user", "password", "dialect" };

	private static Map<String, String> propertyNameMapping;

	private EntityManagerProvider() {
	}

	private static void initializeMapping() {
		propertyNameMapping = new HashMap<>();
		for (int i = 0; i < propsName.length; i++) {
			propertyNameMapping.put(propsName[i], paramsName[i]);
		}
	}

	public static EntityManager getEm() {
		LOG.info("Configurable EntityManager initialization.");

		try {
			if (factory == null) {
				initializeEntityManagerFactoryFromProperties();
			}
			if (em == null)
				em = factory.createEntityManager();
		} catch (Exception ex) {
			LOG.debug("EntityManager initialization failed. Check database server and/or database connection.");
			LOG.debug(ex.getCause());
			LOG.debug(ex);
			System.exit(-1);
		}

		return em;
	}

	private static void initializeEntityManagerFactoryFromProperties()
			throws IOException {
		if (propertyNameMapping == null)
			initializeMapping();

		Map<String, String> properties = PropertyProvider.getProperties(Arrays
				.asList(propsName));

		Map<String, String> dbProps = new HashMap<>();

		for (String propName : propsName) {
			dbProps.put(propertyNameMapping.get(propName),
					properties.get(propName));
		}

		factory = Persistence.createEntityManagerFactory("persist_unit",
				dbProps);

	}

}
