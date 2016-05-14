package fys.fis;

/*
 * TODO:
 * 
 */
 
 
/**
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


public class Register extends Communication { 

	// GSON
	private String email;
	private String firstname;
	private String lastname;
	private String dob;
	private Integer personcode;
	private String id_exp_date;
	private String password;
	
	// local
	private String register_reply;
	private String sql = "";
	
	public Register() {
		super();
		register_reply = "FAIL";
	}

	
	@Override
	protected String run() {
		
		
		// hard coded reply
		return "{ \"function\" : \"register_reply\", " +
				    "\"email\" : \"arno.beekman@hva.nl\", " +
			    "\"firstname\" : \"arno\", " +
			     "\"lastname\" : \"beekman\", " +
				      "\"dob\" : 19711214, " +
			   "\"personcode\" : 1001, " +
		      "\"id_exp_date\" : 20200515, " +
			     "\"password\" : \"welkom123\", " +
			     "\"register\" : \"OK\" }";
	}

}
