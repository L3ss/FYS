package fys.fis;

/*
 * -email niet in db -> user bestaat niet (login_reply:fail)
 * -verkeerd password -> onjuiste invoer (login_reply:fail)
 * -meerdere malen zelfde email -> ?
 * -
 * 
 * TODO:
 * 	-session ID bijhouden
 */

/**
 * JSON:
 * Client:
 * 		{ "function" : "login",
 * 			 "email" : "<email>",
 * 		  "password" : "<password>" }
 * Server:
 * 		{ "function" : "login_reply",
 * 			 "login" : "OK" || "FAIL" }
 */

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;


public class _Login extends Communication {
	
	// GSON
	private String email;
	private String password;
	
	// local
	private String db_email;
	private String db_password;
	
	private String login_reply;
	private StringBuffer sql_read;
	private StringBuffer sql_write;
	
	public _Login() {
		super();
		login_reply = "FAIL";
		
		sql_read = new StringBuffer();
		sql_read.append("SELECT email,password FROM " + Communication.database_schema + ".Person ");
		
		sql_write = new StringBuffer();
		sql_write.append("");
	}

	@Override
	protected String run(HttpSession session) {
		
		// check if session exists
		if(super.existSession(session.getId()) == email) {
			// already logged in (TODO: IS DIT GENOEG GECHECKT?)
			return "{ \"function\" : \"login_reply\", \"login\" : \"OK\" }";
		}
		
		
		sql_read.append("WHERE email='" + email + "' AND password='" + password + "';");
		ResultSet results = super.database.dbQuery(sql_read);
		
		// parse sql results
		if(!results.equals(null)) {

			try {
				while(results.next()) {
					db_email = results.getString("email");
					db_password = results.getString("password");
				}
				
				// close sql search
				results.close();
				
				// FIX THIS!!! (null reponse)
				if( !db_email.equals(null) && !db_password.equals(null)) {
					login_reply = "OK";
				}
				
			} catch (SQLException e) {
				
				// db error
				System.out.println("LOGIN: DATABASE ERROR: " + e.toString());
				return super.returnError("database error in login");
			
			} catch (java.lang.NullPointerException e) {
				// no results from db: null --> FIX THIS
				System.out.println("LOGIN: EMPTY RESULTS: " + e.toString());
			}
		}
		
		// add session to table
		super.addSession(session, email);
		
		return "{ \"function\" : \"login_reply\", " +
					"\"login\" : \"" + login_reply + "\" }";
	}

}
