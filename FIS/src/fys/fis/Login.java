package fys.fis;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends Communication {
	
	// GSON
	private String email;
	private String password;
	
	// local
	private String sql = "SELECT * FROM fys01.Person WHERE password='wasspord';";
	
	
	public Login() {
		super();
	}

	@Override
	protected String run() {
		
		ResultSet results = super.database.query(sql);
		
		// parse sql results along list of arguments
		if(results != null) {
			try {
				while(results.next()) {
					System.out.println("id: " + results.getInt("personcode"));
					System.out.println("firstname: " + results.getString("firstname"));
					System.out.println("lastname: " + results.getString("lastname"));
					System.out.println("dob: " + results.getString("dob"));
					System.out.println("password: " + results.getString("password"));
				}
				
				// close sql search
				results.close();
				
			} catch (SQLException e) {
				// no results from db
				System.out.println("LOGIN: DATABASE ERROR: " + e.toString());
			}	
		}
		
		return "Login: " + "email: " + email + ", password: " + password;
	}

}
