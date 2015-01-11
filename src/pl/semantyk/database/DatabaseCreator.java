package pl.semantyk.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import pl.semantyk.utils.PropertyProvider;

/**
 * Loads database schema from file and run script to initalize database if user
 * run program with setup-db flag.
 * 
 * @author Sebastian Fabisz
 * 
 */
public class DatabaseCreator {

	private static final Logger LOG = Logger.getLogger(DatabaseCreator.class);

	private final String SCHEMA_PATH = "SQL/postqresql_schema.sql";

	private final static String[] propsName = new String[] { "driver", "url", "user", "password", };

	private final int URL = 1;
	private final int USER = 2;
	private final int PASSWORD = 3;

	public DatabaseCreator() { }

	public void setupDatabase() {
		createSchema();
	}

	private void createSchema() {
		LOG.info("Creating empty database...");
		ScriptExecutor scriptExecutor = new ScriptExecutor(getConnection());
		scriptExecutor.runSQL(getSchemaInputStream());
		LOG.info("Database created");
	}

	public Connection getConnection() {
		Map<String, String> properties = PropertyProvider.getProperties(Arrays.asList(propsName));
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", properties.get(propsName[USER]));
		connectionProps.put("password", properties.get(propsName[PASSWORD]));

		try {
			conn = DriverManager.getConnection(properties.get(propsName[URL]), connectionProps);
		} catch (SQLException e) {
			LOG.debug(e);
		}
		if (conn != null)
			LOG.info("Connected to database");
		return conn;
	}

	private InputStream getSchemaInputStream() {
		InputStream is = null;
		try {
			is = new FileInputStream(new File(SCHEMA_PATH));
		} catch (FileNotFoundException e) {
			LOG.debug(e);
		}

		return is;
	}

}
