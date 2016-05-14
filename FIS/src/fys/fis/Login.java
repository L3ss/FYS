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

public class Login extends Communication {
	
	// GSON
	private String email;
	private String password;
	
	// local
	private String db_email;
	private String db_password;
	private String login_reply;
	private String sql = "SELECT email, password FROM " + Communication.database_schema + ".Passenger P1 " + 
						  "INNER JOIN " + Communication.database_schema + ".Person P2 ON P1.personcode=P2.personcode;";
	
	public Login() {
		super();
		login_reply = "FAIL";
	}

	@Override
	protected String run() {
		
		ResultSet results = super.database.query(sql);
		
		// parse sql results
		if(results != null) {

			try {
				while(results.next()) {
					db_email = results.getString("email");
					db_password = results.getString("password");
				}
				
				// close sql search
				results.close();
				
				if(db_email.equals(email) && db_password.equals(password)) {
					login_reply = "OK";
				}
				
			} catch (SQLException e) {
				// no results from db
				System.out.println("LOGIN: DATABASE ERROR: " + e.toString());
				return super.returnError("database error in login");
			}	
		}
		
		return "{ \"function\" : \"login_reply\", \"login\" : \"" + login_reply + "\" }";
	}

}
