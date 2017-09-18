package sk.racek;

import java.sql.Connection;
import java.sql.SQLException;

public class DBModelCreationDemo {
	private static QueryStatements myQueryExecutor = new QueryStatements();
	private static Connection dbConnection = null;
	private static String sqlQuery = null;

	public static void main(String[] args) {
		try {
			dbConnection = DBSQLPostgreConection.getConection();
			System.out.println("Connected to DB");
			
		   // DDL
		   // create User table
		   sqlQuery = "CREATE TABLE dvd.User " +
					 "(id INTEGER NOT NULL, " +
					 "first_name VARCHAR, " +
					 "last_name VARCHAR, " +
				     "address_street VARCHAR, " +
					 "address_city VARCHAR, " +
					 "address_zip VARCHAR, " +
					 "email VARCHAR, " +
					 "tel_number VARCHAR, " +
					 "PRIMARY KEY ( id ))";
			myQueryExecutor.executeQuery(dbConnection, sqlQuery);
			System.out.println("created table dvd.User");
		
			// create Rental table
			sqlQuery = "CREATE TABLE dvd.Rental " +
					 "(id INTEGER NOT NULL, " +
					 "user_id INTEGER REFERENCES dvd.User(id), " +
					 "date_from DATE, " +
					 "date_to DATE, " +
					 "PRIMARY KEY ( id ))";
			myQueryExecutor.executeQuery(dbConnection, sqlQuery);
			System.out.println("created table dvd.Rental");
			
			sqlQuery = "CREATE TYPE dvd.Genere AS ENUM ('Comedy', 'Action', 'Horror', 'Sci-Fi', 'Drama', 'Musical', 'Sport', 'Documentary', 'Adult')";
			myQueryExecutor.executeQuery(dbConnection, sqlQuery);
			System.out.println("created Enum dvd.Genere");
			
			sqlQuery = "CREATE TYPE dvd.PrizeCategory AS ENUM ('A', 'B', 'C')";
			myQueryExecutor.executeQuery(dbConnection, sqlQuery);
			System.out.println("created Enum dvd.PrizeCategory");
			
			
			// create DVD table
			sqlQuery = "CREATE TABLE dvd.DVD " +
					 "(id INTEGER NOT NULL, " +
					 "rental_id INTEGER REFERENCES dvd.Rental(id), " +
					 "name VARCHAR, " +
					 "genere dvd.Genere , " +
					 "prize_category dvd.PrizeCategory, " +
					 "count NUMERIC(3), " +
					 "rented_count NUMERIC(3), " +
					 "PRIMARY KEY (id))";
			myQueryExecutor.executeQuery(dbConnection, sqlQuery);
			System.out.println("created table dvd.DVD");
			
			//DML
			//insert to dvd.User
			sqlQuery = "INSERT INTO dvd.User (id, first_name, last_name, address_street, address_city, address_zip, email, tel_number) VALUES "
					+ "(1, 'Adam', 'Pika', 'Zahradna 503', 'Michalovce', '071 01', 'adam@pika.com', '+421 902 111 222'), "
					+ "(2, 'Fero', 'Tiso', 'Fiala 7', 'Trencin', '911 01', 'fero@tiso.com', '+421 915 421 421'), "
					+ "(3, 'Martin', 'Podhradsky', 'Na Strani 88', 'Kosice', '044 99', 'martin@podhradsky.com', '+421 902 894 654'), "
					+ "(4, 'Filip', 'Figliar', 'Mateja Belu 77/2', 'Poprad', ' 058 01', 'filip@figliar.com', '+421 915 777 888')";
			myQueryExecutor.executeQuery(dbConnection, sqlQuery);
			System.out.println("Inserted values into dvd.User");
			
			//insert to dvd.Rental
			sqlQuery = "INSERT INTO dvd.Rental (id, user_id, date_from, date_to) VALUES "
					+ "(1, 1, '2016-11-29', '2016-12-20'), "
					+ "(2, 2, '2017-02-02', '2017-03-03'), "
					+ "(3, 1, '2017-04-20', '2017-04-10'), "
					+ "(4, 2, '2016-12-29', '2017-01-02'), "
					+ "(5, 3, '2017-06-09', '2017-06-12'), "
					+ "(6, 2, '2017-05-29', '2017-06-01'), "
					+ "(7, 3, '2016-11-25', '2017-11-29'), "
					+ "(8, 4, '2017-09-15', '2017-09-17')";
			myQueryExecutor.executeQuery(dbConnection, sqlQuery);
			System.out.println("Inserted values into dvd.Rental");
			
			//insert to dvd.DVD
			sqlQuery = "INSERT INTO dvd.DVD (id, rental_id, name, genere, prize_category, count, rented_count) VALUES "
					+ "(1, 1, 'Live Hard', 'Action', 'C', 10, 1), "
					+ "(2, 2, 'Beginator', 'Action', 'C', 5, 1), "
					+ "(3, 2, 'Shawshang Damnation', 'Drama', 'B', 8, 1), "
					+ "(4, 3, 'Highway to Perdition', 'Musical', 'C', 3, 1), "
					+ "(5, 3, 'Jurasic Aquarium', 'Documentary', 'A', 1, 1), "
					+ "(6, 1, 'Cloathed Gun', 'Comedy', 'C', 9, 1), "
					+ "(7, 2, 'Morthy and Rick', 'Comedy', 'A', 4, 1), "
					+ "(8, 4, 'Lord of the Earrings', 'Sci-Fi', 'B', 15, 2)";
			myQueryExecutor.executeQuery(dbConnection, sqlQuery);
			System.out.println("Inserted values into dvd.Generes");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//close connection
		try {
			dbConnection.close();
			System.out.println("closing connection");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
