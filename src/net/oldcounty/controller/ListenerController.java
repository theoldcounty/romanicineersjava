package net.oldcounty.controller;
 
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

@Controller
public class ListenerController extends ServiceSerlvet{
	

    /**
     * Get Home
    */	
	@RequestMapping("/index")
	public ModelAndView getHome(
			HttpServletRequest request,
			String message
	)
	{
		ServiceSerlvet.appendSesssion(request);
		return new ModelAndView("../../index", "message", message);	
	}	
	
	
	
	
	/**
     * jsoninterests
     * @throws MongoException 
     * @throws UnknownHostException 
    */
    @RequestMapping("/jsoninterests")
    public ModelAndView jsonInterests(
    		HttpServletRequest request, 
    		HttpServletResponse response
    		) throws UnknownHostException, MongoException 
    {	    	
    	
		String message = "test";
		return new ModelAndView("json/jsoninterests", "message", message); 
    }    

    
	/**
     * jsonlocations
     * @throws MongoException 
     * @throws UnknownHostException 
    */
    @RequestMapping("/jsonlocations")
    public ModelAndView jsonLocations(
    		HttpServletRequest request, 
    		HttpServletResponse response
    		) throws UnknownHostException, MongoException 
    {	    	
    	
		String message = "test";
		return new ModelAndView("json/jsonlocations", "message", message); 
    }    
	
	/**
     * jsonmembers
     * @throws MongoException 
     * @throws UnknownHostException 
    */
    @RequestMapping("/jsonmembers")
    public ModelAndView jsonMembers(
    		HttpServletRequest request, 
    		HttpServletResponse response
    		) throws UnknownHostException, MongoException 
    {	    	
    	
		String message = "test";
		return new ModelAndView("json/jsonmembers", "message", message); 
    }    
	
	
	/**
     * jsonuniqueuser
     * @throws MongoException 
     * @throws UnknownHostException 
    */
    @RequestMapping("/jsonuniqueuser")
    public ModelAndView jsonUniqueUser(
    		HttpServletRequest request, 
    		HttpServletResponse response
    		) throws UnknownHostException, MongoException 
    {	    	
    	
		String message = "test";
		return new ModelAndView("json/jsonuniqueuser", "message", message); 
    }    
	
	
	
	
	
	
	
	
	
	
	
	
    /**
     * Login
     * @throws MongoException 
     * @throws UnknownHostException 
    */
    @RequestMapping("/login")
    public ModelAndView loginDisplay(
    		HttpServletRequest request, 
    		HttpServletResponse response,
    		@RequestParam(value="emailaddress", required=false) String emailaddress,
    		@RequestParam(value="password", required=false) String password,
    		@RequestParam(value="submit", required=false) String submit
    		) throws UnknownHostException, MongoException 
    {    	
    	HttpSession session = serlvetService(request);
    	String inSession = PersonController.inSession(session);
    	ServiceSerlvet.appendSesssion(request);
    	
    	System.out.println("Session inSession " + inSession);	 
		if(inSession == null){ 	
			if(submit != null){
				System.out.println("emailaddress"+emailaddress);
				System.out.println("password"+password);
				
				List<DBObject> dataresponse = PersonController.loginUser(emailaddress, password, session);
				System.out.println("user logging in response"+dataresponse);
				
				if(dataresponse.get(0).get("response") == "OK"){
					String message = "sucessfully logged in, redirected back to the home page";
					return getHome(request, message);
				}				
			}
			String message = "login";
			return new ModelAndView("user/login", "message", message);			
		}
		else
		{	
			String message = "Already logged in";
			return getHome(request, message);	
		}
    }    
    
    /**
     * Login
     * @throws MongoException 
     * @throws UnknownHostException 
    */
    @RequestMapping("/logout")
    public ModelAndView logoutDisplay(
    		HttpServletRequest request, 
    		HttpServletResponse response
    		) throws UnknownHostException, MongoException 
    {	
    	HttpSession session = serlvetService(request);    	
    	String inSession = PersonController.inSession(session); //get from java cookie
    	ServiceSerlvet.appendSesssion(request);
    	
    	//if the user has logged into the session
    	if(inSession != null){
    		List<DBObject> dataresponse = PersonController.logoutUser(inSession, session);
    		System.out.println("user logging out response"+dataresponse);
    		
    		//can send the log out data response to the jsp page
			if(dataresponse.get(0).get("response") == "OK"){
				String message = "sucessfully logged out, redirected back to the home page";
				return getHome(request, message);
			}
			else
			{
				String message = "An error has occured";
				return new ModelAndView("user/logout", "message", message); 
			}
    	}
    	else{
    		//user is not logged in, they do not need to logout again
			String message = "Our apologies you do not need to logout";
			return getHome(request, message);    		
    	}
    }     
    
    /**
     * Register
     * @throws MongoException 
     * @throws UnknownHostException 
    */
    @RequestMapping("/register")
    public ModelAndView registerDisplay(
    		HttpServletRequest request, 
    		HttpServletResponse response,
    		@RequestParam(value="username", required=false) String username,
    		@RequestParam(value="emailaddress", required=false) String emailaddress,
    		@RequestParam(value="confirmemailaddress", required=false) String confirmemailaddress,
    		@RequestParam(value="password", required=false) String password,
    		@RequestParam(value="confirmpassword", required=false) String confirmpassword,
    		@RequestParam(value="gender", required=false) String gender,
    		@RequestParam(value="birthyear", required=false) String birthyear,
    		@RequestParam(value="birthmonth", required=false) String birthmonth,
    		@RequestParam(value="birthday", required=false) String birthday,
    		@RequestParam(value="ethnicity", required=false) String ethnicity,
    		@RequestParam(value="country", required=false) String country,
    		@RequestParam(value="submit", required=false) String submit
    		) throws UnknownHostException, MongoException 
    {
    	HttpSession session = serlvetService(request);
    	String inSession = PersonController.inSession(session);
    	ServiceSerlvet.appendSesssion(request);
    	
    	System.out.println("inSession: "+inSession);
 


    	Map<String,String> countryList = CommonUtils.getCountries();
    	String countriesCommonKey = "GBR"; //United Kingdom
    	
    	Map<String,String> genderList = CommonUtils.getGender();
    	String genderCommonKey = "0"; //Male	    	


    	Map<String,String> ethnicityList = CommonUtils.getEthnicity();
    	String ethnicityCommonKey = "0"; //Caucasian
    	
    	request.setAttribute("countryCommonKey", countriesCommonKey);
    	request.setAttribute("countryList", countryList);	    	

    	request.setAttribute("genderCommonKey", genderCommonKey);
    	request.setAttribute("genderList", genderList);		
    	
    	request.setAttribute("ethnicityCommonKey", ethnicityCommonKey);
    	request.setAttribute("ethnicityList", ethnicityList);
		    		
		    	
    	
    	if(submit!=null){  
    		
	    	System.out.println("username"+username);
	    	System.out.println("emailaddress"+emailaddress);    	
	    	System.out.println("confirmemailaddress"+confirmemailaddress);
	    	System.out.println("password"+password);
	    	System.out.println("confirmpassword"+confirmpassword);
	    	System.out.println("gender"+gender);

	    	System.out.println("birthyear"+birthyear);
	    	System.out.println("birthmonth"+birthmonth);
	    	System.out.println("birthday"+birthday);
	    	System.out.println("ethnicity"+ethnicity);    	
	    	System.out.println("country"+country);
	    	
	    	
	    	request.setAttribute("username", username);
	    	request.setAttribute("emailaddress", emailaddress);
	    	request.setAttribute("confirmemailaddress", confirmemailaddress);
	    	request.setAttribute("password", password);
	    	request.setAttribute("confirmpassword", confirmpassword);
	    	request.setAttribute("gender", gender);
	    	request.setAttribute("birthyear", birthyear);
	    	request.setAttribute("birthmonth", birthmonth);
	    	request.setAttribute("birthday", birthday);
	    	request.setAttribute("ethnicity", ethnicity);
	    	request.setAttribute("country", country);
	    	
	    	/*_Store*/
	
	    	List<DBObject> registerResponse = PersonController.registerUser(
				username, emailaddress, password, confirmpassword, gender, birthyear, birthmonth, birthday, ethnicity, country
		    );
		        		    
		    //BasicDBObject recentuser = PersonController.getUniqueUser(Lastid.toString());
	    	System.out.println("registerResponse"+registerResponse);
	    	
		    //Auto Login
		    if(registerResponse.get(0).get("response") == "OK"){		    
		    	List<DBObject> dataresponse = PersonController.loginUser(emailaddress, password, session);
		    	
		    	if(dataresponse.get(0).get("response") == "OK"){
		    		String sessionVariable = "loggedid";
		    		setSessionVar(session, sessionVariable, (String) dataresponse.get(0).get("userId"));
					String message = "You have successfully registered and logged in, enjoy the site.";
					return getHome(request, message);
		    	}		    
		    }
	    	else{
	    		//the guest can register
	    		String message = "An error occured";
	    		return new ModelAndView("user/register", "message", message);    		
	    	}
    	}

    	
    	//if the user has logged into the session
    	if(inSession != null){
    		//they will need to logout in order to re-register a new account
    		String message = "You have been automatically logged out";
    		PersonController.logoutUser(inSession, session);
    		return new ModelAndView("user/register", "message", message);     		
    	}
    	else{
    		//the guest can register
    		String message = "Dear Guest please register";
    		return new ModelAndView("user/register", "message", message);    		
    	}
    }   
    
    
    /*
     * Member List
     * Displays All Users
    */
    @RequestMapping("/members")
    public ModelAndView memberList(
	    		HttpServletRequest request, 
	    		HttpServletResponse response
    		) throws UnknownHostException, MongoException 
    {
    	ServiceSerlvet.appendSesssion(request);
    	//get search ALL users
    	BasicDBObject searchQuery = new BasicDBObject();
    	List<DBObject> dataresponse = PersonController.searchUsers(searchQuery);    	
    	
    	return new ModelAndView("memberlist", "response", dataresponse);
    }    
    
    
    /*
     * Search List
     * /search/?gender=m&race=asian
    */
    @RequestMapping(method=RequestMethod.GET, value={"/search","/search/{query}"})
    public ModelAndView searchResults(
    		HttpServletRequest request, 
    		HttpServletResponse response,
    		@RequestParam(value="query", required=false) String query
    	) 
    {
    	ServiceSerlvet.appendSesssion(request);
    	String message = "search view for "+query;
		return new ModelAndView("searchview", "message", message);
    }    
       
    
    
    /*
     * User
    */
    @RequestMapping(method=RequestMethod.GET, value={"/profile","/profile/{id}"})
    public ModelAndView profileDisplay(
    		HttpServletRequest request, 
    		HttpServletResponse response,
    		@RequestParam(value="id", required=false) String id
    ) throws UnknownHostException, MongoException {
    	ServiceSerlvet.appendSesssion(request);
    	//get search ALL users
    	BasicDBObject searchQuery = new BasicDBObject();
    		searchQuery.put("_id", new ObjectId(id));
    	List<DBObject> searchResponse = PersonController.searchUsers(searchQuery);    	
    	
    	//System.out.println("response from search user method: "+searchResponse);
    	
		return new ModelAndView("profileview", "people", searchResponse);
    }
    
    
 
    
    /*
     * Edit User
    */
    /**
     * Register
     * @throws MongoException 
     * @throws UnknownHostException 
    */
    @RequestMapping("/edit")
    public ModelAndView editDisplay(
    		HttpServletRequest request, 
    		HttpServletResponse response,
    		
    		//account
    		@RequestParam(value="username", required=false) String username,
    		@RequestParam(value="emailaddress", required=false) String emailaddress,
    		@RequestParam(value="confirmemailaddress", required=false) String confirmemailaddress,
    		@RequestParam(value="password", required=false) String password,
    		@RequestParam(value="confirmpassword", required=false) String confirmpassword,
    		@RequestParam(value="gender", required=false) String gender,
    		@RequestParam(value="birthyear", required=false) String birthyear,
    		@RequestParam(value="birthmonth", required=false) String birthmonth,
    		@RequestParam(value="birthday", required=false) String birthday,
    		@RequestParam(value="ethnicity", required=false) String ethnicity,
    		@RequestParam(value="country", required=false) String country,
    		@RequestParam(value="submitaccount", required=false) String submitaccount,
    		
    		//basics
    		@RequestParam(value="seeking", required=false) String seeking,
    		@RequestParam(value="submitbasics", required=false) String submitbasics
    		) throws UnknownHostException, MongoException 
    {
    	HttpSession session = serlvetService(request);
    	String inSession = PersonController.inSession(session);
    	ServiceSerlvet.appendSesssion(request);
    	System.out.println("inSession: "+inSession);
    	System.out.println("edit");
    	List<DBObject> person = null;
 

    	Map<String,String> countryList = CommonUtils.getCountries();
    	String countriesCommonKey = "GBR"; //United Kingdom
    	
    	Map<String,String> genderList = CommonUtils.getGender();
    	String genderCommonKey = "0"; //Male
    	

    	Map<String,String> ethnicityList = CommonUtils.getEthnicity();
    	String ethnicityCommonKey = "0"; //Caucasian
    	
    	request.setAttribute("countryCommonKey", countriesCommonKey);
    	request.setAttribute("countryList", countryList);	    	

    	request.setAttribute("genderCommonKey", genderCommonKey);
    	
    	request.setAttribute("genderList", genderList);		
    	
    	request.setAttribute("ethnicityCommonKey", ethnicityCommonKey);
    	request.setAttribute("ethnicityList", ethnicityList);    	
    	
    	//basics
    	String seekingGenderCommonKey = "1"; //Female
    	request.setAttribute("seekingGenderCommonKey", seekingGenderCommonKey);
    	
    	if(inSession != null){
    		
    		//edit account details
	    	if(submitaccount!=null){
	    		
	    		System.out.println("submitaccount"+submitaccount);
		    	System.out.println("username"+username);
		    	System.out.println("emailaddress"+emailaddress);    	
		    	System.out.println("confirmemailaddress"+confirmemailaddress);
		    	System.out.println("password"+password);
		    	System.out.println("confirmpassword"+confirmpassword);
		    	System.out.println("gender"+gender);
		    	System.out.println("birthyear"+birthyear);
		    	System.out.println("birthmonth"+birthmonth);
		    	System.out.println("birthday"+birthday);
		    	System.out.println("ethnicity"+ethnicity);    	
		    	System.out.println("country"+country);    	
		    	
		    	/*_Store*/
		    	
		    	List<DBObject> editResponse = PersonController.editUser(
		    			inSession, username, emailaddress, password, gender, birthyear, birthmonth, birthday, ethnicity, country
			    );
			    
			    System.out.println("response from edit user method: "+editResponse);
		    	   		    
			    //BasicDBObject recentuser = PersonController.getUniqueUser(Lastid.toString());
	    	}
	    	
	    	
	    	if(submitbasics!=null){
	    		System.out.println("submitbasics"+submitbasics);
	    		
	    		System.out.println("seeking"+seeking);
	    		
	    		
		    	List<DBObject> editBasicsResponse = PersonController.editUserBasic(
		    			inSession, seeking
			    );
		    	System.out.println("response from edit basics user method: "+editBasicsResponse);
	    	}
	    	
	    	
	    	
    		//get user
    		person = PersonController.getUniqueUser(inSession);    		
    		System.out.println(person.get(0).get("user"));
	    	return new ModelAndView("user/edit", "response", person.get(0).get("user"));
    	}
    	else
    	{
    		//need to be logged in to edit your page   
			String message = "back to home";
			return getHome(request, message);
    	}
	    
		//String message = "edit";
		
    }      
    
    /*
     * Delete User
    */
    @RequestMapping(method=RequestMethod.GET, value={"/delete","/delete/{id}"})
    public ModelAndView deleteDisplay(
    		HttpServletRequest request, 
    		HttpServletResponse response,
    		@RequestParam(value="id", required=false) String id
    ) throws UnknownHostException, MongoException {
    	ServiceSerlvet.appendSesssion(request);
    	BasicDBObject deleteResponse = PersonController.deleteUser(id);    	
    	
    	System.out.println("response from user to delete method: "+deleteResponse);
    	
		return new ModelAndView("user/delete", "people", deleteResponse);
    }    
    	
    /*
     * Messages
    */
    @RequestMapping(method=RequestMethod.GET, value={"/messages","/messages/{mode}"})
    public ModelAndView userDisplay(
    		HttpServletRequest request, 
    		HttpServletResponse response,
    		@RequestParam(value="mode", required=false) String mode) {
    	ServiceSerlvet.appendSesssion(request);
    	//check if user is logged in.
    	
    	//if user is logged in, they can send and read their own messages
    	
    	//if user is not logged in - they are redirected to login page    	
    	
    	String message = "the message system";    	
    	String viewPage = "messagesview";
   
		 if(mode != null){   	
		    	if(mode.equalsIgnoreCase("compose")){
					//if mode is register
					message = "compose message";
					//viewPage = "userRegister";	
		    	}
		    	
		    	if(mode.equalsIgnoreCase("sent")){
					//if mode is edit
					message = "read sent messages";
					//viewPage = "userEdit";
		    	}
		    	    	
		    	if(mode.equalsIgnoreCase("read")){
					//if mode is login
					message = "read received messages";
					//viewPage = "userLogin";
		    	}
		 }	

    	
    	/*
    	    /inbox/compose
    	    /inbox/received
    	    /inbox/sent
    	    messenging system
    	    - allow users to send and recieve messages
    	*/
    				
		return new ModelAndView(viewPage, "message", message);
    }
    


    
    /*
     * Gallery methods
    */
    
    @RequestMapping(method=RequestMethod.GET, value={"/gallery"})
    public ModelAndView galleryDisplay(
    		HttpServletRequest request, 
    		HttpServletResponse response,
    		@RequestParam(value="mode", required=false) String mode
    	) {
    	ServiceSerlvet.appendSesssion(request);
    	String message = "search view for "+mode;
		
		
		List<DBObject> images = GalleryController.getGallery();
		System.out.println(images);
		
		String viewPage = "gallery/galleryview";
		
		return new ModelAndView(viewPage, "images", images);
    }    
  


    
}