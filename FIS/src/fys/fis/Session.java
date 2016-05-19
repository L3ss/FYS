package fys.fis;

import javax.servlet.http.HttpSession;


/**
 * Session class to map email to a wildfly session
 * @author Arno
 *
 */
public class Session extends Communication {
	
	private String email;
	private HttpSession session;
	
	public Session(String email, HttpSession session) {

		this.setEmail(email);
		this.setSession(session);
	}
	

	@Override
	protected String run(HttpSession session) {
		// DUMMY
		return super.returnError("Session dummy");
	}

	
	/**
	 * Email getter
	 * @return email as a String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Email setter
	 * @param email provide as a String 
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	
	/**
	 * Session getter
	 * @return Session as a String
	 */
	public HttpSession getSession() {
		return session;
	}

	/**
	 * Session setter
	 * @param session provided as an HttpSession
	 */
	public void setSession(HttpSession session) {
		this.session = session;
	}
	
}
