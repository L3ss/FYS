package fys.fis;


/*
 * TODO
 * 	-verschillende vluchten in Arrays??? -> GSON verwacht Arrays
 */


/**
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
 *               {"flightcode" : <flightcode>, 
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
 *               "total_price" : <totalprice> }
 *  }
 *
 */

public class SearchFlights extends Communication {
	
	// GSON
	private String airportcode_orig;
	private String airportcode_dest;
	private String flightdate;
	private String over18;
	private String till17;
	private String under2;
	
	// local
	private String search_flights_reply;
	private String sql = "";

	public SearchFlights() {
		super();
		search_flights_reply = "FAIL";
	}
	
	@Override
	protected String run() {
		
		// hard coded reply
		return "{ \"function\" : \"search_flight_reply\", " +
				   "\"flight\" : " +
			 "{ \"flightcode\" : 2001, " +
		 "\"airportcode_orig\" : 0051, " +
		 "\"airportcode_dest\" : 0052, " +
		 	   "\"flightdate\" : 20010911, " +
	  "\"departuretimeoffset\" : -0600, " + 
	    "\"arrivaltimeoffset\" : 0047, " +
	     	  "\"total_price\" : 250 }, " +
			 "{ \"flightcode\" : 2002, " +
	       "\"origin_airport\" : 0052, " +
	         "\"dest_airport\" : 0051, " +
	          		 "\"date\" : 20010912, " +
     "\"departure_timeoffset\" : 0047, " +
       "\"arrival_timeoffset\" : -0600, " +
              "\"total_price\" : 248 } }";
	}

}
