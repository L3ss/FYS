package fys_fis;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
		
		reply_get.append("<html><head><title>FYS2015/2016</title></head>");
		reply_get.append("<body>This is a GET request (.33),<br>");
		reply_get.append("with these arguments:<br>");
		reply_get.append(input + "<br><br>");
		
		reply_get.append("currently implemented:<br><br>");
		reply_get.append("<table border=\"1\">");
		
		reply_get.append("<tr>");
		reply_get.append("<td>POST REQUEST</td>");
		reply_get.append("<td>POST REPLY</td>");
		reply_get.append("<tr>");
		
		reply_get.append("<tr>");
		reply_get.append("<td></td>");
		reply_get.append("<td></td>");
		reply_get.append("<tr>");
		
		reply_get.append("<tr>");
		reply_get.append("<td>{\"function\":\"echo\",\"&lt;key&gt;\":\"&lt;value&gt;\",...}</td>");
		reply_get.append("<td>{\"function\":\"echo_reply\",\"&lt;key&gt;\":\"&lt;value&gt;\",...}</td>");
		reply_get.append("<tr>");
		
		reply_get.append("<tr>");
		reply_get.append("<td>null</td>");
		reply_get.append("<td>{\"error\":\"no input\"}</td>");
		reply_get.append("<tr>");
		
		reply_get.append("<tr>");
		reply_get.append("<td>&lt;unknown request&gt;</td>");
		reply_get.append("<td>{\"error\":\"not implemented\"}</td>");
		reply_get.append("<tr>");
		
		reply_get.append("<tr>");
		reply_get.append("<td>{\"function\":\"login\",\"email\":\"arno.beekman@hva.nl\",\"password\":\"welkom123\"}\"</td>");
		reply_get.append("<td>{\"function\":\"login_reply\",\"login\":\"OK\"}</td>");
		reply_get.append("<tr>");
		
		
		reply_get.append("</table>");
		reply_get.append("</body></html>");
		
		response.getWriter().append(reply_get);
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
		
		// generate reply
		StringBuffer reply_post = new StringBuffer();

		if(input != null) {
			reply_post.append(parser.run(input));
		} else {
			reply_post.append("{\"error\":\"no input\"}");
		}
				
		response.getWriter().append(reply_post);
	}
}
