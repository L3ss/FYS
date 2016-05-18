package fys.fis;

/*
 * TODO:
 * -sla gehele session op of alleen ID?
 * 
 */

import java.util.HashMap;

import javax.servlet.http.HttpSession;


public abstract class Communication {
	
	
	protected static final String NOSESSIONFOUND = "nosessionfound"; 
	
	
	protected Database database;
	protected static String database_schema;
	private static HashMap<String, Session> sessionMap;

	
	
	public Communication() {
		database = new Database();
		database_schema = database.DB_NAME;
		sessionMap = new HashMap<String, Session>();
	}
	
	
	protected abstract String run(HttpSession session);
	
	
	protected void addSession(HttpSession session, String email) {
		
		sessionMap.put(session.getId(), new Session(email,session));
	}
	
	
	protected void removeSession(String sessionID) {
		
		if( sessionMap.containsKey(sessionID) ) sessionMap.remove(sessionID);
	}
	
	
	protected String existSession(String sessionID) {
		
		// session exists -> return email
		if(!sessionMap.isEmpty() && sessionMap.containsKey(sessionID)) {
			return sessionMap.get(sessionID).getEmail();
		}
		return NOSESSIONFOUND;
	}


	protected String returnError(String errorMessage) {
		return "{ \"error\" : \"" + errorMessage + "\" }";
	}

}
