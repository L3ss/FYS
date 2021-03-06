package fys.fis;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.*;

import org.postgresql.ds.PGPoolingDataSource;


/**
 * Takes care of all database communication
 * @author Arno
 *
 */
public class Database {

	private final String DB_URI = "localhost:5432";
	protected final String DB_NAME = "fys";
	private final String DB_LOGIN = "fys";
	private final String DB_PASSWORD = "fys";
	private PGPoolingDataSource psqldb;
	
	
	/**
	 * Empty constructor
	 */
	public Database() {
		this((new StringBuffer()).append("error"));
	}
	
	
	/**
	 * Overloaded constructor
	 * @param body data from post request
	 * 
	 * TO BE DELETED (body is not needed anymore)
	 */
	public Database(StringBuffer body) {

		psqldb = new PGPoolingDataSource();
		
		//psqldb.setDataSourceName("PostgreSQL Server");
		psqldb.setServerName(DB_URI);
		psqldb.setDatabaseName(DB_NAME);
		psqldb.setUser(DB_LOGIN);
		psqldb.setPassword(DB_PASSWORD);
		psqldb.setMaxConnections(10);
		
		
        try {
        	new InitialContext().rebind("PostgreSQL", psqldb);
        	
		} catch (NamingException e) {
			// JNDI binding failed
			System.out.println(e.toString());
		}
		
	}
	
	
	/**
	 * Query the database
	 * @param query a clear text SQL statement
	 * @return ResultSet with database results
	 */
	protected ResultSet dbQuery(StringBuffer query) {
		
		System.out.println("SQL Query: " + query.toString());
		ResultSet results = null;
    	
		try {
        	Connection db = psqldb.getConnection();
			Statement st = db.createStatement();
			results = st.executeQuery(query.toString());
			
			// close connection
			//st.close();
			//results.close();
			db.close();
			
		} catch (SQLException e) {
			// no results
			System.out.println("DATABASE: sql exception " + e.toString());
			//return null;
		}
		
		return results;
	}
	
	
	/**
	 * Insert SQL query, to store data in the database
	 * @param querya clear text SQL statement
	 * @return ResultSet if exists
	 * 
	 * TEST IF RETURN VALUE IS ACTUALLY EVER USED 
	 */
	protected ResultSet dbInsert(StringBuffer query) {
		
		query.append(" COMMIT;");
		System.out.println("SQL Insert: " + query.toString());
		
		return dbQuery(query);
	}

}
