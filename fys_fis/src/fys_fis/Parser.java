package fys_fis;

import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Parses the input, decides which class to load based on input
 * @author Arno Beekman
 *
 */
public class Parser {

	private String input;
	private HttpSession session;
	//private StringBuffer action;
	//private StringBuffer body;
	
	public Parser() {
	}
	
	protected StringBuffer run(HttpSession session, String input) {
		this.session = session;
		this.input = input;
		
		StringBuffer reply = new StringBuffer();
		//action = new StringBuffer();
		//body = new StringBuffer();
		Communication communication = this.parse(input);
		
		// decide where to go
		switch(action.toString().toLowerCase()) {
			case "login":
				communication = new Login(body);
				break;
			case "register":
				communication = new Register(body);
				break;
			default:
				body.append(" - ");
				body.append(action.toString());
				communication = new Communication(body);
		}
		
		// go there
		reply.append(communication.run(body));
		
		// return answer to client
		return reply;
	}
	
	
	// GSON parser
	//private void parse() {
	public Communication parse(String input) {
		Gson gson = new GsonBuilder().create();
		Communication communication = gson.fromJson(input, Communication.class);

		StringBuffer sbInput = new StringBuffer();
		sbInput.append(input);
		return communication.run(sbInput);
	};
	
/*
	private void parseOld() {
		// assumes {"function":"action","key1":"value1","key2":["value2a","value2b"]}
		int index = 12;
		if(input.substring(0, index+1).equals("{\"function\":\"")) {

			// read first key: action
			while(input.charAt(++index) != '"') {
				action.append(input.charAt(index));
			}
			
			// read until values start
			while(input.charAt(++index) != ',' ){}
			
			// split keys/values
			while(input.charAt(++index) != '}') {
				body.append(input.charAt(index));
			}

		} else {
			// unknown packet received
		}
	}
*/
}
