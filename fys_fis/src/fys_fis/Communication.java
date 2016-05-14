package fys_fis;

import java.util.Hashtable;

import javax.servlet.http.HttpSession;


public abstract class Communication {
	
	protected abstract String run();
	
	private String function;
	private static Hashtable<HttpSession, String> sessionTable = new Hashtable<HttpSession, String>();
	
	private static void addSessionID(HttpSession session, String email) {
		sessionTable.put(session, email);
	}
	
	private static void removeSessionID(HttpSession session) {
		if( sessionTable.containsKey(session) ) sessionTable.remove(session);
	}
	
	
	public Communication() {
		this((new StringBuffer()).append("error in COMM"));
	}
	
	public Communication(StringBuffer body) {
		
	}
	
	protected String run(StringBuffer body) {
		// to override!
		//return "COMMUNICATION - this is a " + this.function + " request.";
		return ("{\"error\":\"not implemented\"} " + body.toString());
	}
	
	
	
	/**
	 * Find values of keys in body
	 * @param body unformatted JSON String
	 * @param keysList keys to find in body
	 * @return ArrayList of Strings
	 * 
	 * TODO arrays as values, different types without quotes
	 * REWRITE AS REGEX STATE MACHINE
	 */
	protected void getValues(StringBuffer body, String[] keyArray, ArrayList<String> valueList) {
		
		// loop through all keys
		for(String key : keyArray) {
			// loop through body
			int keyLength = key.length();
			int loopLength = body.length() - keyLength;
			StringBuffer tmp = new StringBuffer();
			for(int i=0; i<loopLength; i++) {
				// check if char at this position is beginning of key
	    		if(body.subSequence(i, i + keyLength).equals(key)) {
	    			// found key, skip to value
	    			while(body.charAt(i++) != ':');
	    			
	    			// read value
	    			while(body.charAt(++i) != '"') tmp.append(body.charAt(i));
	    			
	    			// append value to return list
	    			valueList.add(tmp.toString());
	    		}
			}
		}
	}
}
