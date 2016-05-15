package fys.fis;

import java.lang.reflect.Type;

import com.google.gson.InstanceCreator;


public class Parser extends Index implements InstanceCreator<Communication> {

	private static final long serialVersionUID = 1L;
	
	// GSON
	private String function;
	

	@Override
	public Communication createInstance(Type type) {

		switch(function.toLowerCase()) {
			
		case "bags":
			return new Bags();
			
		case "boarding":
			return new Boarding();
			
		case "book_flights":
		case "bookflights":
			return new BookFlights();
			
		case "check_in":
		case "checkin":
			return new CheckIn();
			
		case "internet_access":
		case "internetaccess":
			return new InternetAccess();
			
		case "login":
			return new Login();

		case "register":
			return new Register();
			
		case "search_flights":
		case "searchflights":
			return new SearchFlights();
			
		default:
			return null;
		}
	}
	
	
	
}
