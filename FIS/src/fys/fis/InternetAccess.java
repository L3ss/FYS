package fys.fis;

public class InternetAccess extends Communication {

	// GSON
	
	// local
	private String internet_access_reply;
	private StringBuffer sql_read;
	private StringBuffer sql_write;
	
	
	public InternetAccess() {
		super();
		internet_access_reply = "FAIL";
		
		sql_read = new StringBuffer();
		sql_read.append("");
		
		sql_write = new StringBuffer();
		sql_write.append("");
	}

	@Override
	protected String run() {
		
		
		// hardcoded reply
		return "{ \"function\" : \"internet_access_reply\", \"internet_access\" : \"" + internet_access_reply + "\" }";
	}

}
