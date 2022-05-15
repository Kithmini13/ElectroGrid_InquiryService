package model;

import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FeedbackAPI
 */

@WebServlet("/FeedbackAPI")
public class FeedbackAPI extends HttpServlet {
	Feedback feedbackObj = new Feedback(); 
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedbackAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		String output = feedbackObj.insertFeedback(request.getParameter("Feedback_id"), 
												   request.getParameter("name"), 
												   request.getParameter("email"), 
												   request.getParameter("rate"),
		                                           request.getParameter("phone"),
		                                           request.getParameter("comment"), 
		                                           request.getParameter("Cus_id")); 
												   response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException 
			{ 
			 Map paras = getParasMap(request); 
			 String output = feedbackObj.updateFeedback(paras.get("hidFeedback_idSave").toString(),  
			 paras.get("Customer_name").toString(), 
			 paras.get("email").toString(), 
			 paras.get("rate").toString(),
			 paras.get("phone").toString(),
			 paras.get("feedback_comment").toString(),
			 paras.get("feeback_reply").toString(),
			 paras.get("Cus_id").toString()); 
			 response.getWriter().write(output); 
			} 
	
	
	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 { 
	 String[] p = param.split("="); 
	 map.put(p[0], p[1]); 
	 } 
	 } 
	catch (Exception e) 
	 { 
	 } 
	return map; 
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException 
			{ 
			 Map paras = getParasMap(request); 
			 String output = feedbackObj.deleteFeedback(paras.get("Feedback_id").toString(),
					                                    paras.get("Cus_id").toString()); 
			response.getWriter().write(output); 
			}


}

