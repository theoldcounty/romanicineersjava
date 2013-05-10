/*
*
* Author: Rob Shan Lone
* Copyright (c) 2012 The Old County Limited.
*
* All rights reserved.
*/

package net.oldcounty.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@SuppressWarnings("serial")
public class ServiceServlet extends HttpServlet{
	
	/*
	public void setSession(HttpServletRequest request){
		HttpSession session = getSession(request);
		
	}

	public HttpSession getSession(HttpServletRequest request){
		return request.getSession(true);
	}
	
	
	public addUser(HttpServletRequest request, User user){
		HttpSession session = getSession(request);
		session.setAttribute("user", user);		
	}
	
	public void removeUser(HttpServletRequest request){
		HttpSession session = getSession(request);
		session.removeAttribute("user");
	}
	*/
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		//TestDao testDao=new TestDao();
		response.setContentType("text/html");
		
		HttpSession session = request.getSession(true);
		
		/*
		User user=(User) session.getAttribute("user");
		if(user == null){
			//not logged in
		}else{
			//logged in
		}
		*/
		
		String op = request.getParameter("op");
		if (op==null){
			op="";
		}
		if(op.equals("dologin")){
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			
			/*
			user=testDao.retrieveUser(login,password);
			if (user == null){
				
			}
			else{
				session.setAttribute("user", user);
				response.sendRedirect("TestController2");
			}*/
		}
		if(op.equals("logout")){
			session.removeAttribute("user");
		} 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}	
	
	
	
	
	
	/*
	
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
	*/
}