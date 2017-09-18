package sk.racek;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBSQLPostgreConection {
	private static String dbUrl = "jdbc:postgresql://localhost:5432/dvd";
	private static String dbUssername = "David Racek";
	private static String dbPassword = "david";
	private static final String JDBC_DRIVER = "org.postgresql.Driver"; 
	
	/**
	 * Returns SQL Connection. For establishing db connection it uses private
	 * static variables dbUrl, dbUssername, dbPassword
	 * 
	 * @return Connection if there were no SQL errors
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public static Connection getConection() throws SQLException, ClassNotFoundException {
		Class.forName(JDBC_DRIVER);
		return DriverManager.getConnection(dbUrl, dbUssername, dbPassword);
	}

	// G + S
	public static String getDbUrl() {
		return dbUrl;
	}

	public static void setDbUrl(String dbUrl) {
		DBSQLPostgreConection.dbUrl = dbUrl;
	}

	public static String getDbUssername() {
		return dbUssername;
	}

	public static void setDbUssername(String dbUssername) {
		DBSQLPostgreConection.dbUssername = dbUssername;
	}


	public static void setDbPassword(String dbPassword) {
		DBSQLPostgreConection.dbPassword = dbPassword;
	}
}
