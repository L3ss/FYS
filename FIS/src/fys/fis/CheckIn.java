package fys.fis;

public class CheckIn extends Communication {

	// GSON
	
	// local
	private String check_in_reply;
	private StringBuffer sql_read;
	private StringBuffer sql_write;
	
	
	public CheckIn() {
		super();
		check_in_reply = "FAIL";
		
		sql_read = new StringBuffer();
		sql_read.append("");
		
		sql_write = new StringBuffer();
		sql_write.append("");
	}

	@Override
	protected String run() {
		
		
		// hardcoded reply
		return "{ \"function\" : \"check_in_reply\", \"check_in\" : \"" + check_in_reply + "\" }";
	}

}
