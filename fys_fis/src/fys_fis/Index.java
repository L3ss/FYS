package fys_fis;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.fastinfoset.util.StringArray;


@WebServlet("/Index")
public class Index extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String input;
	private Parser parser;
	private BufferedReader reader;
	
	
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        parser = new Parser();
    }
    
	

	/**
	 * Takes care of GET requests
	 * @param request REST GET request to the servlet
	 * @param response REST GET reply from the servlet 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		reader = request.getReader();
		input = reader.readLine();
		StringBuffer reply_get = new StringBuffer();

		File file = new File("rtfm.html");
		Scanner html = new Scanner(file);
		StringArray rtfm = new StringArray();
		while(html.hasNext()) {
			rtfm.add(html.nextLine());
		}
		html.close();
		response.getWriter().append("this is it: " + rtfm.toString());
	}

	
	
	/**
	 * Takes care of POST requests
	 * @param request REST POST request to the servlet
	 * @param response REST POST reply from the servlet
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get input
		reader = request.getReader();
		input = reader.readLine();
		HttpSession session = request.getSession();
		
		
		// generate reply
		StringBuffer reply_post = new StringBuffer();

		if(input != null) {
			//reply_post.append(parser.run(input));
			//reply_post.append(parser.parse(session, input));
			reply_post.append(parser.run(session, input));
		} else {
			reply_post.append("{\"error\":\"no input\"}");
		}
				
		
		response.getWriter().append(reply_post);
	}
}
