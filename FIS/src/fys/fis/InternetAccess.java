package fys.fis;

public class InternetAccess extends Communication {

	// GSON
	
	// local
	private String internet_access_reply;
	private String sql = "";
	
	
	public InternetAccess() {
		super();
		internet_access_reply = "FAIL";
	}

	@Override
	protected String run() {
		
		
		// hardcoded reply
		return "{ \"function\" : \"internet_access_reply\", \"internet_access\" : \"" + internet_access_reply + "\" }";
	}

}
