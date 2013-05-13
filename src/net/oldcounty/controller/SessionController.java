package net.oldcounty.controller;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mongodb.DBObject;


public class SessionController extends HttpServlet{


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		processRequest(request,response);
	}

	public static void processRequest(HttpServletRequest request, HttpServletResponse response) {

			//response.setContentType("text/html");
		
			
			HttpSession session = request.getSession(true);
			
			System.out.println("session>>>> " + session);
			
			
			/*
			
			User user=(User) session.getAttribute("user");
			if(user== null){
			    //output=template.replace("#login#",convertStreamToString(getClass().getResourceAsStream("login.html")));	
			}else{
				//output=template.replace("#login#","hello:"+user.getName());
			}
			String op=request.getParameter("op");
			if (op==null){
				op="";
			}
			if(op.equals("dologin")){
				String login=request.getParameter("login");
				String password=request.getParameter("password");
				user=testDao.retrieveUser(login,password);
				if (user == null){
					
				}
				else{
					session.setAttribute("user", user);
					response.sendRedirect("TestController");
				}
			}
			if(op.equals("logout")){
				session.removeAttribute("user");
			}
			out.print(output);
			
			out.close();
			
			*/
		
	}
	
	@SuppressWarnings("unchecked")
	public static List<DBObject> getLoggedUser(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		List<DBObject> user= (List<DBObject>) session.getAttribute("user");
		
		return user;
	}	

	public static void logUser(List<DBObject> object, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.setAttribute("user", object);
	}
	
	public static void logOutUser(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.removeAttribute("user");
	}
	
	public static void isSession(HttpServletRequest request){
    	List<DBObject> user = SessionController.getLoggedUser(request);
    	
    	if(user != null){
    		request.setAttribute("inSession", true);
    		
    		String personName = (String) user.get(0).get("username").toString();    		
    		request.setAttribute("personName", personName);    		
    	}
    	else{
    		request.setAttribute("inSession", false);    		
    	}
	}
	
}