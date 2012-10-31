package net.oldcounty.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


class ServiceSerlvet {
	public static HttpSession serlvetService(HttpServletRequest request){
		
	   //Obtain the session object, create a new session if doesn't exist
	    HttpSession session =  request.getSession(true);
	   
	    return session;		
	}
	
	
	public static String getSessionVar(HttpSession session, String var){
	    String value = (String) session.getAttribute(var);
	    
		return value;
	}
	
	public static void setSessionVar(HttpSession session, String var, String value){
	    session.setAttribute(var, value);
	}
	
	public static void removeSessionVar(HttpSession session, String var){
	    session.removeAttribute(var);
	}	
	
	public static void appendSesssion(HttpServletRequest request){		
		//view name append with .jsp
		HttpSession session = serlvetService(request);
    	String inSession = PersonController.inSession(session);		
		
		request.setAttribute("inSession", inSession);	
		
	}	
	
}