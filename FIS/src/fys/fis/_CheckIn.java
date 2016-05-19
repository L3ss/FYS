package fys.fis;

import javax.servlet.http.HttpSession;

/**
 * Check a passenger in for a flight
 * @author Arno
 *
 */
public class _CheckIn extends Communication {

	// GSON
	
	// local
	private String check_in_reply;
	private StringBuffer sql_read;
	private StringBuffer sql_write;
	
	
	public _CheckIn() {
		super();
		check_in_reply = "FAIL";
		
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
		return "{ \"function\" : \"check_in_reply\", \"check_in\" : \"" + check_in_reply + "\" }";
	}

}
