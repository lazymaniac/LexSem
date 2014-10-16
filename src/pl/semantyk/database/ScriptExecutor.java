package pl.semantyk.database;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class ScriptExecutor {

	private static final Logger LOG = Logger.getLogger(ScriptExecutor.class);

	private final Connection connection;

	public ScriptExecutor(Connection connection) {
		this.connection = connection;
	}

	public void runSQL(InputStream in) {

		Scanner s = new Scanner(in);
		s.useDelimiter("(;(\r)?\n)|(--\n)");
		Statement st = null;
		try {
			st = connection.createStatement();
			while (s.hasNext()) {
				String line = s.next();
				if (line.startsWith("/*!") && line.endsWith("*/")) {
					int i = line.indexOf(' ');
					line = line
							.substring(i + 1, line.length() - " */".length());
				}

				if (line.trim().length() > 0) {
					st.execute(line);
				}
			}
		} catch (SQLException e) {
			LOG.debug(e);
		} finally {
			if (st != null)
				try {
					st.close();
				} catch (SQLException e) {
					LOG.debug(e);
				}
		}
	}
}
