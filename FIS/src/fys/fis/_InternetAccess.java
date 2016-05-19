package fys.fis;

import javax.servlet.http.HttpSession;

/*
 * steps:
 * -login
 * -select flight
 * -request internet on chose flight
 * -(personcode), email, password, macaddress, (flightcode), flight, ip address, timeframe 
 * 
 * TODO
 * -
 */

/**
 * Provide internet access to a passenger on a flight
 * @author Arno
 *
 */
public class _InternetAccess extends Communication {

	// GSON
	
	// local
	private String internet_access_reply;
	private StringBuffer sql_read;
	private StringBuffer sql_write;
	
	
	public _InternetAccess() {
		super();
		internet_access_reply = "FAIL";
		
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
		return "{ \"function\" : \"internet_access_reply\", \"internet_access\" : \"" + internet_access_reply + "\" }";
	}

}
