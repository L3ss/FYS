package fys.fis;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import sun.util.calendar.BaseCalendar;
import sun.util.calendar.BaseCalendar.Date;


/*
 * TODO:
 * -waarom stuurt client een personcode?
 * -bepaald server of client de exp_date? -> server is veiliger
 * -password: md5?
 * -wanneer niet toelaten?
 * -expire date toevoegen aan db?
 * -na registreren meteen ingelogd? -> sessionID opgeslagen
 * 
 */
 
 
/**
 * Register a new user
 * @author Arno
 *  
 * JSON:
 * Client:
 * 		{ "function" : "register",
 * 			 "email" : "<email>",
 * 		 "firstname" : "<name>",
 * 		  "lastname" : "<surname>",
 * 			   "dob" : "<date of birth>",
 * 		"personcode" : "<number>",
 * 	   "ID_exp_date" : "<date>",
 *        "password" : "<password>" }
 *            
 * Server:
 * 		{ "function" : "register_reply",
 *			 "email" : "<fail_exisits/fail_missing_field/email>",  
 *		 "firstname" : "<fail_missing_field/name>",
 *		  "lastname" : "<fail_missing_field/surname>",
 *			   "dob" : "<fail_missing_field/date>",
 *		"personcode" : "<fail_exisits/fail_missing_field/number>",
 *	   "ID_exp_date" : "<fail_missing_field/date>",
 *		  "password" : "<fail_missing_field/password>",
 *		  "register" : "OK" || "FAIL" }
 *
 */
public class _Register extends Communication { 

	// GSON
	private String email;
	private String firstname;
	private String lastname;
	private String dob;
	//private Integer personcode;
	private LocalDateTime id_exp_date;
	private String password;
	
	// local
	private String db_email;
	private String db_firstname;
	private String db_lastname;
	private String db_dob;
	private String db_password;
	private Integer db_personcode;
	
	private String register_reply;
	private StringBuffer sql_read;
	private StringBuffer sql_write;
	
	
	public _Register() {
		super();
		register_reply = "FAIL";
		id_exp_date = LocalDateTime.now();
		id_exp_date.plusYears(1l);
		
		sql_read = new StringBuffer();
		sql_read.append("SELECT personcode,firstname,lastname,dob,password,email ");
		sql_read.append("FROM " + Communication.database_schema + ".Person ");
		
		sql_write = new StringBuffer();
		sql_write.append("INSERT INTO " + Communication.database_schema + ".Person ");
		sql_write.append("(firstname,lastname,dob,password,email) VALUES ");
	}

	
	@Override
	protected String run(HttpSession session) {
		
		sql_read.append("WHERE email='" + email + "' AND firstname='" + firstname + "' ");
		sql_read.append("AND lastname='" + lastname + "' AND dob='" + dob + "';");
		
		ResultSet results = super.database.dbQuery(sql_read);
		
		// check if exists
		if(!results.equals(null)) {
			try {
				while(results.next()) {
					db_email = results.getString("email");
					db_firstname = results.getString("firstname");
					db_lastname = results.getString("lastname");
					db_dob = results.getString("dob");
					db_password = results.getString("password");
					db_personcode = results.getInt("personcode");
					System.out.println("REGISTER: exists with personcode: " + db_personcode +
										" firstname: " + db_firstname +
										" lastname: " + db_lastname +
										" dob: " + db_dob +
										" password: " + db_password );
					
					results.close();
					
					return super.returnError("account already exists");
				}
			} catch (SQLException e) {
				// database error
				System.out.println("REGISTER: db error in check if exists");
			}
		}
		
		
		// append data to insert query
		sql_write.append("('" + firstname + "','" + lastname +
				         "'," + dob + ",'" + password + "','" + email + "');");
		
		// write to db
		results = super.database.dbInsert(sql_write);

		
		// test if insert successful
		results = super.database.dbQuery(sql_read);
		if(results.equals(null)) {
			// TEST: HOE LOSSEN WE DIT OP???
			return super.returnError("insert error");
		}

		try {
			// parse sql results
			while(results.next()) {
				db_email = results.getString("email");
				db_firstname = results.getString("firstname");
				db_lastname = results.getString("lastname");
				db_dob = results.getString("dob");
				db_password = results.getString("password");
				db_personcode = results.getInt("personcode");
			}
			
			// close sql search
			results.close();
			
		} catch (SQLException e) {
			// not everything in db
			System.out.println("REGISTER: DATABASE ERROR: " + e.toString());
			return super.returnError("couldn't retrieve all data after insert");
		}
		
		// build reply
		register_reply =  "{ \"function\" : \"register_reply\", " +
							   "\"email\" : \"" + db_email + "\", " +
						   "\"firstname\" : \"" + db_firstname + "\", " +
						    "\"lastname\" : \"" + db_lastname + "\", " +
						    	 "\"dob\" : \"" + db_dob + "\", " +
						  "\"personcode\" : "   + db_personcode + ", " +
						 "\"id_exp_date\" : "   + id_exp_date + ", " +
						 	"\"password\" : \"" + db_password + "\", " +
						 	"\"register\" : \"OK\" }";
		
		return register_reply;
	}

}
