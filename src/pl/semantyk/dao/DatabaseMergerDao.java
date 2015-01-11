package pl.semantyk.dao;

import pl.semantyk.database.ConnectionProvider;
import pl.semantyk.exceptions.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Creates a relation between Wiktionary units and WordNet units which name matches.
 *
 * @author Sebastian Fabisz.
 */
public class DatabaseMergerDao  {

	public void mergeWnWithWikt() throws SystemException {

		String mergeSql = "UPDATE semantyk.JEDNOSTKA_WN wn "
				+ "SET ID_JEDN_WIKI = ("
				+ "SELECT distinct wiki.ID_JEDNOSTKA "
				+ "FROM semantyk.JEDNOSTKA_WIKI wiki "
				+ "WHERE wiki.NAZWA = wn.NAZWA "
				+ "LIMIT 1)";

		Connection connection = ConnectionProvider.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(mergeSql);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
