package net.oldcounty.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mongodb.DBObject;


@SuppressWarnings("serial")
public class SessionController extends HttpServlet{
	
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