package fys.fis;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Servlet implementation class Index
 * @author Arno
 *
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Index" })
public class Index extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	
	protected String input;
	protected Gson gson;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        gson = new GsonBuilder().create();
    }

    
    
    
	/**
	 * GET requests
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		File root = new File(getServletContext().getRealPath("/WEB-INF/classes/rtfm.html"));
		Scanner html = new Scanner(root);
		StringBuffer rtfm = new StringBuffer();
		
		while(html.hasNext()) {
			rtfm.append(html.nextLine());
		}
		html.close();

		response.getWriter().append(rtfm);
	}

	
	
	
	/**
	 * POST requests
	 */	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		input = request.getReader().readLine();
		StringBuffer reply_post = new StringBuffer();

		if(input != null) {
			try {

				Parser parser = gson.fromJson(input, Parser.class);
				Communication communication = parser.createInstance(Communication.class);

			
				if(communication != null) {
					
					communication = gson.fromJson(input, communication.getClass());
					
					reply_post.append(communication.run(request.getSession()));
					
				} else {
					reply_post.append("{ \"error\" : \"not implemented\" }");
				}
			}
			catch (com.google.gson.JsonSyntaxException e) {
				reply_post.append("{ \"error\" : \"badly formed JSON\" }");
			}
			
		} else {
			
			reply_post.append("{ \"error\" : \"empty post request\" }");
		}
		
		response.getWriter().append(reply_post);
	}

}
