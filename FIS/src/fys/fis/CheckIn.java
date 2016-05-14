package fys.fis;

public class CheckIn extends Communication {

	// GSON
	
	// local
	private String check_in_reply;
	private String sql = "";
	
	
	public CheckIn() {
		super();
		check_in_reply = "FAIL";
	}

	@Override
	protected String run() {
		
		
		// hardcoded reply
		return "{ \"function\" : \"check_in_reply\", \"check_in\" : \"" + check_in_reply + "\" }";
	}

}
