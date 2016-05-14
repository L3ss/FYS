package fys.fis;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.*;

import org.postgresql.ds.PGPoolingDataSource;

public class Database {

	private final String DB_URI = "localhost:5432";
	protected final String DB_NAME = "fys";
	private final String DB_LOGIN = "fys";
	private final String DB_PASSWORD = "fys";
	private PGPoolingDataSource psqldb;
	
	
	public Database() {
		this((new StringBuffer()).append("error"));
	}
	
	
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
	
	
	protected ResultSet query(String query) {
		ResultSet results = null;
    	
		try {
        	Connection db = psqldb.getConnection();
			Statement st = db.createStatement();
			results = st.executeQuery(query);

			// close connection
			//st.close();
			//results.close();
			db.close();
			
		} catch (SQLException e) {
			// cannot connect to postgresql database
			System.out.println("DATABASE: sql exception " + e.toString());
		}
		
		return results;
	}
}
