package fys.fis;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
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
			
			Parser parser = gson.fromJson(input, Parser.class);
			Communication communication = parser.createInstance(Communication.class);
			
			communication = gson.fromJson(input, communication.getClass());
			
			reply_post.append(communication.run());
			
		} else {
			
			reply_post.append("{\"error\":\"no input\"}");
		}
		
		response.getWriter().append(reply_post);
	}

}
