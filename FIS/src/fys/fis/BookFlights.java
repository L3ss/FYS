package fys.fis;

public class BookFlights extends Communication {

	// GSON
	
	// local
	private String book_flights_reply;
	private StringBuffer sql_read;
	private StringBuffer sql_write;
	
	
	public BookFlights() {
		super();
		book_flights_reply = "FAIL";
		
		sql_read = new StringBuffer();
		sql_read.append("");
		
		sql_write = new StringBuffer();
		sql_write.append("");
	}

	@Override
	protected String run() {
		
		
		// hardcoded reply
		return "{ \"function\" : \"book_flights_reply\", \"book_flights\" : \"" + book_flights_reply + "\" }";
	}

}
