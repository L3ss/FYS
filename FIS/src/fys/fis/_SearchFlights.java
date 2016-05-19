package fys.fis;

import javax.servlet.http.HttpSession;

/*
 * TODO
 * 	-verschillende vluchten in Arrays??? -> GSON verwacht Arrays
 */


/**
 * Search in the system for avialble flights
 * @author Arno
 * 
 * Client: 
 *          {"function" : "search_flight", 
 *   "airportcode_orig" : <origin>, 
 *   "airportcode_dest" : <destination>, 
 *         "flightdate" : <date>, 
 *             "over18" : <number>, 
 *             "till17" : <number>, 
 *             "under2" : <number> }
 *             
 * Server:
 * {"function" : "search_flight_reply", 
 *    "flight" : 
 *             [ {"flightcode" : <flightcode>, 
 *          "airportcode_orig" : <origin>, 
 *          "airportcode_dest" : <destination>, 
 *                "flightdate" : <date>, 
 *       "departuretimeoffset" : <departuretimeoffset>, 
 *         "arrivaltimeoffset" : <arrivaltimeoffset>, 
 *               "total_price" : <totalprice> },
 * 
 *               {"flightcode" : <flightcode>, 
 *            "origin_airport" : <origin>, 
 *              "dest_airport" : <destination>, 
 *                      "date" : <date>, 
 *            "departure_time" : <departuretimeoffset>, 
 *              "arrival_time" : <arrivaltimeoffset>, 
 *               "total_price" : <totalprice> } ]
 *  }
 *
 */

public class _SearchFlights extends Communication {
	
	// GSON
	private String airportcode_orig;
	private String airportcode_dest;
	private String flightdate;
	private String over18;
	private String till17;
	private String under2;
	
	// local
	private String search_flights_reply;
	private StringBuffer sql_read;
	private StringBuffer sql_write;
	

	public _SearchFlights() {
		super();
		search_flights_reply = "FAIL";
		
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

		
		// hard coded reply
		return "{ \"function\" : \"search_flight_reply\", " +
				   "\"flight\" : " +
			"[{ \"flightcode\" : 2001, " +
		 "\"airportcode_orig\" : 51, " +
		 "\"airportcode_dest\" : 52, " +
		 	   "\"flightdate\" : 20010911, " +
	  "\"departuretimeoffset\" : -600, " + 
	    "\"arrivaltimeoffset\" : 47, " +
	     	  "\"total_price\" : 250 }, " +
			 "{ \"flightcode\" : 2002, " +
	       "\"origin_airport\" : 52, " +
	         "\"dest_airport\" : 51, " +
	          		 "\"date\" : 20010912, " +
     "\"departure_timeoffset\" : 47, " +
       "\"arrival_timeoffset\" : -600, " +
              "\"total_price\" : 248 }] }";
	}

}
