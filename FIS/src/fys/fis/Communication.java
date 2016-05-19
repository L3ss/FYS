package fys.fis;

/*
 * TODO:
 * -sla gehele session op of alleen ID?
 * 
 */

import java.util.HashMap;

import javax.servlet.http.HttpSession;

/**
 * Abstract super class, takes care of sessions, database connection, provides abstract method run 
 * @author Arno
 *
 */
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
	
	
	/**
	 * Abstract class run
	 * @param session current session object
	 * @return JSON packet
	 */
	protected abstract String run(HttpSession session);
	
	
	/**
	 * add map session ID & Session object (= wildfly session object & email) to HashMap
	 * @param session wildfly session
	 * @param email user's email used for account
	 */
	protected void addSession(HttpSession session, String email) {
		
		sessionMap.put(session.getId(), new Session(email,session));
	}
	
	
	/**
	 * delete a user's session object from the HashMap, after logout & timeout
	 * @param sessionID wildfly's session object ID
	 */
	protected void removeSession(String sessionID) {
		
		if( sessionMap.containsKey(sessionID) ) sessionMap.remove(sessionID);
	}
	
	
	/**
	 * test if there is a session for a user
	 * @param sessionID wildfly's session id
	 * @return if exists then return corresponding email, else magic "no session found"
	 */
	protected String existSession(String sessionID) {
		
		// session exists -> return email
		if(!sessionMap.isEmpty() && sessionMap.containsKey(sessionID)) {
			return sessionMap.get(sessionID).getEmail();
		}
		return NOSESSIONFOUND;
	}


	/**
	 * Default JSON error message 
	 * @param errorMessage text to be wrapped in JSON packet
	 * @return JSON String to be sent 
	 */
	protected String returnError(String errorMessage) {
		return "{ \"error\" : \"" + errorMessage + "\" }";
	}

}
