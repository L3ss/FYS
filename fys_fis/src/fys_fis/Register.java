package fys_fis;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Register extends Communication {
	
	/*
	 * 1. split username/login, password
	 * 2. check if already in db
	 * 3. if exists: return error (maintain correct data? NO)
	 * 4. if cleared: ATOMIC (block id!): create new user: unique ID, name, password (md5?)
	 * 5. reply user added
	 */

	private StringBuffer sql;
	private StringBuffer reply;
	private fys_fis.Database database;
	
	private String[] listIDS = {"id","firstname","lastname","dob","password"};
	
	/*
	public Register() {
		//super(String);
		System.out.print("REGISTER: empty constructor error!");
	} */
	

    public Register(StringBuffer body) {
        super(body);
        sql = new StringBuffer();
        reply = new StringBuffer();
        database = new Database();
    }

    @Override 
    protected String run(StringBuffer body) {
    	
    	// parse body
    	ArrayList<String> valueList = new ArrayList<String>();
    	super.getValues(body, listIDS, valueList);
    	
    	try {
    		// sql query
			sql.append("SELECT * FROM fys01.Person;");
			ResultSet results = database.query(sql.toString());
			
			// parse sql results along list of arguments
			ArrayList<String> resultsList = new ArrayList<String>();
			for(int i=0; i<listIDS.length; i++) {
				resultsList.add(results.getString(i+1));
			}
			
			// check if results match request
			reply.append("\nINPUT: " + valueList);
			reply.append("\nQUERY: " + resultsList + "\n");
			if(resultsList.equals(valueList)) reply.append("{\"login\" : \"OK\"}");
			
			// close sql search
			results.close();

		} catch (SQLException e) {
			// nothing from database 
			System.out.println("LOGIN: SQL Exception error: " + e.toString());
		}
		
    	return reply.toString();
	}
}
