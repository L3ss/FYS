package fys_fis;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.*;
//import javax.sql.DataSource;

import org.postgresql.ds.PGPoolingDataSource;


public class Database {
	
	private final String DB_URI = "localhost:5432";
	private final String DB_NAME = "fys";
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
		psqldb.setMaxConnections(1);
		
		
        try {
        	// JNDI
        	new InitialContext().rebind("PostgreSQL", psqldb);
        	//DataSource psqldb1 = (DataSource)new InitialContext().lookup("java:comp/env/jdbc/postgres");
        	
		} catch (NamingException e) {
			// JNDI binding failed
			System.out.println(e.toString());
		}
		
	}
	
	
	protected ResultSet query(String query) {
		ResultSet results = null;
    	
		try {
			// sql statement
			//new InitialContext().rebind("PostgreSQL", psqldb);
        	//DataSource psqldb = (DataSource)new InitialContext().lookup("java:comp/env/jdbc/postgres");
			
        	Connection db = psqldb.getConnection();
			Statement st = db.createStatement();
			results = st.executeQuery(query);
			results.next();

			// close connection
			//st.close();
			//results.close();
			db.close();
			
		} catch (SQLException e) {
			// cannot connect to postgresql database
			System.out.println("DATABASE: sql exception " + e.toString());
		} /*catch (NamingException e) {
			// cannot find JNDI context
			System.out.println("DATABASE: JNDI exception " + e.toString());
		} */
		
		return results;
	}
	
	
	/**
	 * Find values of keys in body
	 * @param body unformatted JSON String
	 * @param keysList keys to find in body
	 * @return ArrayList of Strings
	 * 
	 * TODO arrays as values, different types without quotes
	 * REWRITE AS REGEX STATE MACHINE
	 */
	/*
	protected void getValues(StringBuffer body, String[] keyArray, ArrayList<String> valueList) {
		
		// loop through all keys
		for(String key : keyArray) {
			// loop through body
			int keyLength = key.length();
			int loopLength = body.length() - keyLength;
			StringBuffer tmp = new StringBuffer();
			for(int i=0; i<loopLength; i++) {
				// check if char at this position is beginning of key
	    		if(body.subSequence(i, i + keyLength).equals(key)) {
	    			// found key, skip to value
	    			while(body.charAt(i++) != ':');
	    			
	    			// read value
	    			while(body.charAt(++i) != '"') tmp.append(body.charAt(i));
	    			
	    			// append value to return list
	    			valueList.add(tmp.toString());
	    		}
			}
		}
	} */
}
