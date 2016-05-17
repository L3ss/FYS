package fys.fis;

import javax.servlet.http.HttpSession;

/*
 * TODO
 */


public class _Bags extends Communication {
	
	
	// GSON
	
	// local
	private String bags_reply;
	private StringBuffer sql_read;
	private StringBuffer sql_write;
	
	
	public _Bags() {
		super();
		bags_reply = "FAIL";
		
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
		return "{ \"function\" : \"bags_reply\", \"bags\" : \"" + bags_reply + "\" }";
	}

}
