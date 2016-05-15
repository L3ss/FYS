package fys.fis;

/*
 * TODO
 */


public class Bags extends Communication {
	
	
	// GSON
	
	// local
	private String bags_reply;
	private StringBuffer sql_read;
	private StringBuffer sql_write;
	
	
	public Bags() {
		super();
		bags_reply = "FAIL";
		
		sql_read = new StringBuffer();
		sql_read.append("");
		
		sql_write = new StringBuffer();
		sql_write.append("");
	}

	@Override
	protected String run() {
		
		
		// hardcoded reply
		return "{ \"function\" : \"bags_reply\", \"bags\" : \"" + bags_reply + "\" }";
	}

}
