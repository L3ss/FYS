package fys.fis;

import java.util.Hashtable;

import javax.servlet.http.HttpSession;

public abstract class Communication {
	
	protected Database database;
	protected static String database_schema;
	protected HttpSession session;
	private static Hashtable<HttpSession, String> sessionTable = new Hashtable<HttpSession, String>();

	public Communication() {
		database = new Database();
		database_schema = database.DB_NAME;
	}
	
	protected abstract String run();

	
	private void addSessionID(HttpSession session, String email) {
		sessionTable.put(session, email);
	}
	
	private void removeSessionID(HttpSession session) {
		if( sessionTable.containsKey(session) ) sessionTable.remove(session);
	}

	/**
	 * Algemeen foutbericht in JSON
	 * @param errorMessage: melding string
	 * @return {"error":"<melding>"}
	 */
	protected String returnError(String errorMessage) {
		return "{\"error\":\"" + errorMessage + "\"}";
	}

}
