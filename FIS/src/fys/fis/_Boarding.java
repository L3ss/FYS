package fys.fis;

import javax.servlet.http.HttpSession;

/**
 * Passenger's boarding information
 * @author Arno
 *
 */
public class _Boarding extends Communication {

	// GSON
	
	// local
	private String boarding_reply;
	private StringBuffer sql_read;
	private StringBuffer sql_write;
	
	
	public _Boarding() {
		super();
		boarding_reply = "FAIL";
		
		sql_read = new StringBuffer();
		sql_read.append("");
		
		sql_write = new StringBuffer();
		sql_write.append("");
	}

	@Override
	protected String run(HttpSession session) {
		
		if(super.existSession(session.getId()) != Communication.NOSESSIONFOUND) {
			returnError("not logged in");
		}
		
		
		// hardcoded reply
		return "{ \"function\" : \"boarding_reply\", \"boarding\" : \"" + boarding_reply + "\" }";
	}

}
