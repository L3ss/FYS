package fys.fis;

import javax.servlet.http.HttpSession;

/**
 * Book a flight
 * @author Arno
 *
 */
public class _BookFlights extends Communication {

	// GSON
	
	// local
	private String book_flights_reply;
	private StringBuffer sql_read;
	private StringBuffer sql_write;
	
	
	public _BookFlights() {
		super();
		book_flights_reply = "FAIL";
		
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
		return "{ \"function\" : \"book_flights_reply\", \"book_flights\" : \"" + book_flights_reply + "\" }";
	}

}
