package fys.fis;

import java.util.Hashtable;

import javax.servlet.http.HttpSession;

public abstract class Communication {
	
	protected Database database;
	protected HttpSession session;
	private static Hashtable<HttpSession, String> sessionTable = new Hashtable<HttpSession, String>();

	public Communication() {
		database = new Database();
	}
	
	protected abstract String run();

	
	private void addSessionID(HttpSession session, String email) {
		sessionTable.put(session, email);
	}
	
	private void removeSessionID(HttpSession session) {
		if( sessionTable.containsKey(session) ) sessionTable.remove(session);
	}
}
