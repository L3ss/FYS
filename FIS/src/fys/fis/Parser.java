package fys.fis;

import java.lang.reflect.Type;

import com.google.gson.InstanceCreator;


/**
 * Parser to reflect JSON packets onto Classes
 * @author Arno
 *
 */
public class Parser extends Index implements InstanceCreator<Communication> {

	private static final long serialVersionUID = 1L;
	
	// GSON
	private String function;
	

	/**
	 * Implements InstanceCreator to return an object for polymorphism
	 * @return Communication sub class depending on "function" in JSON packet
	 */
	@Override
	public Communication createInstance(Type type) {

		switch(function.toLowerCase()) {
			
		case "bags":
			return new _Bags();
			
		case "boarding":
			return new _Boarding();
			
		case "book_flights":
		case "bookflights":
			return new _BookFlights();
			
		case "check_in":
		case "checkin":
			return new _CheckIn();
			
		case "internet_access":
		case "internetaccess":
			return new _InternetAccess();
			
		case "login":
			return new _Login();

		case "register":
			return new _Register();
			
		case "search_flights":
		case "searchflights":
			return new _SearchFlights();
			
		default:
			return null;
		}
	}
	
	
	
}
