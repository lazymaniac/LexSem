package pl.semantyk.database;

import org.apache.log4j.Logger;
import pl.semantyk.utils.PropertyProvider;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

/**
 * Singleton initialize Connection instance across application.
 *
 * @author Sebastian.Fabisz
 */
public class ConnectionProvider {

	private static final Logger LOG = Logger.getLogger(ConnectionProvider.class);

	private final static String[] propsName = new String[] { "show_sql", "driver", "url", "user", "password", "dialect" };
	private static Map<String, String> properties = PropertyProvider.getProperties(Arrays.asList(propsName));

	private ConnectionProvider() {
	}

	static {
		try {
			Class.forName(properties.get(propsName[1]));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	public static Connection getConnection() {
		try {
			Connection connection1 = DriverManager.getConnection(
					properties.get(propsName[2]), properties.get(propsName[3]), properties.get(propsName[4]));

			return connection1;
		} catch (Exception ex) {
			LOG.debug("Connection initialization failed. Check database server and/or database connection.");
			LOG.debug(ex.getCause());
			LOG.debug(ex);
			System.exit(-1);
		}

		return null;
	}

	private static Connection initConnectionFromProperties() throws IOException, ClassNotFoundException, SQLException {
		LOG.info("Configurable Connection initialization.");
		return null ;
	}

}
