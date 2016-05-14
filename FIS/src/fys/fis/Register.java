package fys.fis;


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
	private String sql = "SELECT * FROM fys01.;";
	

	
	@Override
	protected String run() {
		return "Register: " + email + " " + firstname + " " +
			lastname + " " + dob + " " + personcode + " " + id_exp_date + " " + password;
	}

}
