package fys.fis;

public class BookFlights extends Communication {

	// GSON
	
	// local
	private String book_flights_reply;
	private String sql = "";
	
	
	public BookFlights() {
		super();
		book_flights_reply = "FAIL";
	}

	@Override
	protected String run() {
		
		
		// hardcoded reply
		return "{ \"function\" : \"book_flights_reply\", \"book_flights\" : \"" + book_flights_reply + "\" }";
	}

}
