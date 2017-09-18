package sk.racek;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class has methods that use DDL language.
 * 
 */
public class QueryStatements {

	/**
	 * Creates a table from SQL query
	 * 
	 * @param dbConnection
	 *            is the database connection, type Connection
	 * @param sqlQuerry
	 *            is the query to execute.
	 * @throws SQLException
	 *             if there is SQL error while executing query
	 */
	public void executeQuery(Connection dbConnection, String sqlQuery) throws SQLException {
		Statement statement = dbConnection.createStatement();
		statement.executeUpdate(sqlQuery);
	}

}
