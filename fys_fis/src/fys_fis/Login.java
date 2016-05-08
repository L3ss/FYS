package fys_fis;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fys_fis.Database;

public class Login extends Communication {
	
	private StringBuffer sql;
	private StringBuffer reply;
	private fys_fis.Database database;
	
	private String[] listIDS = {"email","password"};
       
    public Login(StringBuffer body) {
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
    	
    	//try {
    		/*
    		// sql query
    		sql.append("SELECT * FROM fys01.Person WHERE " + listIDS[0] + "=" + valueList.get(0) + ";");
			ResultSet results = database.query(sql.toString());
			
			// parse sql results along list of arguments
			ArrayList<String> resultsList = new ArrayList<String>();
			if(results != null) {
				for(int i=0; i<listIDS.length; i++) {
					resultsList.add(results.getString(i+1));
				}
				
				// check if results match request
				reply.append("\nINPUT: " + valueList);
				reply.append("\nQUERY: " + resultsList + "\n");
				if(resultsList.equals(valueList)) reply.append("{\"login\" : \"OK\"}");
				
				// close sql search
				results.close();
				
				
			} else {
				resultsList.add("no sql entry.");
			}
			*/
    		if(valueList.get(0).equals("arno.beekman@hva.nl") && valueList.get(1).equals("welkom123") )
    			reply.append("{\"function\":\"login_reply\",\"login\":\"OK\"}");
    		else
    			reply.append("{\"function\":\"login_reply\",\"login\":\"FAIL\"}  " + valueList.get(0) + " - " + valueList.get(1));

    		/*
		} catch (SQLException e) {
			// nothing from database 
			System.out.println("LOGIN: SQL Exception error: " + e.toString());
			*/
		//}
		
    	return reply.toString();
	}
}
