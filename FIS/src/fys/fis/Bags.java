package fys.fis;

/*
 * TODO
 */


public class Bags extends Communication {
	
	
	// GSON
	
	// local
	private String bags_reply;
	private String sql = "";
	
	
	public Bags() {
		super();
		bags_reply = "FAIL";
	}

	@Override
	protected String run() {
		
		
		// hardcoded reply
		return "{ \"function\" : \"bags_reply\", \"bags\" : \"" + bags_reply + "\" }";
	}

}
