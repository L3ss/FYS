package fys.fis;

public class Boarding extends Communication {

	// GSON
	
	// local
	private String boarding_reply;
	private StringBuffer sql_read;
	private StringBuffer sql_write;
	
	
	public Boarding() {
		super();
		boarding_reply = "FAIL";
		
		sql_read = new StringBuffer();
		sql_read.append("");
		
		sql_write = new StringBuffer();
		sql_write.append("");
	}

	@Override
	protected String run() {
		
		
		// hardcoded reply
		return "{ \"function\" : \"boarding_reply\", \"boarding\" : \"" + boarding_reply + "\" }";
	}

}
