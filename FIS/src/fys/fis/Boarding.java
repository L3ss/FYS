package fys.fis;

public class Boarding extends Communication {

	// GSON
	
	// local
	private String boarding_reply;
	private String sql = "";
	
	
	public Boarding() {
		super();
		boarding_reply = "FAIL";
	}

	@Override
	protected String run() {
		
		
		// hardcoded reply
		return "{ \"function\" : \"boarding_reply\", \"boarding\" : \"" + boarding_reply + "\" }";
	}

}
