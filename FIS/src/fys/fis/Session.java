package fys.fis;

import javax.servlet.http.HttpSession;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
}
