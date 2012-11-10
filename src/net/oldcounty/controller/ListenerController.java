package net.oldcounty.controller;
 
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bson.BasicBSONObject;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	@RequestMapping("/*")
	public ModelAndView getHome2(
    		HttpServletRequest request, 
    		HttpServletResponse response,
			String message
	)
	{
    	HttpSession session = serlvetService(request);
    	String inSession = PersonController.inSession(session);
    	ServiceSerlvet.appendSesssion(request);
    	
    	System.out.println("Session inSession " + inSession);	 
    	request.setAttribute("page", "home");
    	
		String messages = "test home";
		return new ModelAndView("../../index", "message", messages);	
	}
	
	/*
	@RequestMapping("/index")
	public ModelAndView getHome(
    		HttpServletRequest request, 
    		HttpServletResponse response,
			String message
	)
	{
    	HttpSession session = serlvetService(request);
    	String inSession = PersonController.inSession(session);
    	ServiceSerlvet.appendSesssion(request);
    	
    	System.out.println("Session inSession " + inSession);	 
    	request.setAttribute("page", "home");
    	
		String messages = "test home";
		return new ModelAndView("../../index", "message", messages);	
	}
	*/
	
	
	
	
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
					return getHome2(request, response, message);
				}				
			}
			String message = "login";
			return new ModelAndView("user/login", "message", message);			
		}
		else
		{	
			String message = "Already logged in";
			return getHome2(request, response, message);	
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
				return getHome2(request, response, message);
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
			return getHome2(request, response, message);    		
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
    		@RequestParam(value="realname", required=false) String realname,
    		@RequestParam(value="username", required=false) String username,
    		@RequestParam(value="emailaddress", required=false) String emailaddress,
    		@RequestParam(value="confirmemailaddress", required=false) String confirmemailaddress,
    		@RequestParam(value="password", required=false) String password,
    		@RequestParam(value="confirmpassword", required=false) String confirmpassword,
    		
    		@RequestParam(value="birthyear", required=false) String birthyear,
    		@RequestParam(value="birthmonth", required=false) String birthmonth,
    		@RequestParam(value="birthday", required=false) String birthday,
    		
    		@RequestParam(value="country", required=false) String country,
    		@RequestParam(value="whichscreenname", required=false) String whichscreenname,
    		@RequestParam(value="about", required=false) String about,
    		@RequestParam(value="submit", required=false) String submit,
    		
    		@RequestParam(value="gender", required=false) String gender,
    		@RequestParam(value="ethnicity", required=false) String ethnicity,
    		@RequestParam(value="kindofrelationship", required=false) String kindofrelationship,
    		@RequestParam(value="bodytype", required=false) String bodytype,
    		@RequestParam(value="haircolor", required=false) String haircolor,
    		@RequestParam(value="eyecolor", required=false) String eyecolor,
    		@RequestParam(value="children", required=false) String children,
    		@RequestParam(value="education", required=false) String education,
    		@RequestParam(value="occupation", required=false) String occupation,    		
    		@RequestParam(value="languages", required=false) String[] languages,
    		
    		@RequestParam(value="interests", required=false) String[] interests,
    		@RequestParam(value="interestknobs", required=false) Integer[] interestknobs,
    		@RequestParam(value="personality", required=false) Integer[] personality,
    		
    		//@RequestParam(value="lookingfor", required=false) String lookingfor,    		
    		
    		@RequestParam(value="submitted", required=false) String submitted    		
    		) throws UnknownHostException, MongoException 
    {
    	HttpSession session = serlvetService(request);
    	String inSession = PersonController.inSession(session);
    	ServiceSerlvet.appendSesssion(request);
    	
    	System.out.println("inSession: "+inSession);

    	Map<String,String> countryList = CommonUtils.getCountries();
    	String countriesCommonKey = "GBR"; //United Kingdom
    	
    	Map<String,String> ethnicityList = CommonUtils.getEthnicity();
    	String ethnicityCommonKey = "0"; //Caucasian
    	
    	
    	request.setAttribute("countryCommonKey", countriesCommonKey);
    	request.setAttribute("countryList", countryList);	    	
    	
    	request.setAttribute("ethnicityCommonKey", ethnicityCommonKey);
    	request.setAttribute("ethnicityList", ethnicityList);
		    	
    	System.out.println("submitted: "+submitted);
    	if(submitted !=null){  
    		
    		System.out.println("realname "+realname);
	    	System.out.println("username "+username);
	    	System.out.println("emailaddress "+emailaddress);    	
	    	System.out.println("confirmemailaddress "+confirmemailaddress);
	    	System.out.println("password "+password);
	    	System.out.println("confirmpassword "+confirmpassword);
	    	System.out.println("gender "+gender);

	    	System.out.println("birthyear "+birthyear);
	    	System.out.println("birthmonth "+birthmonth);
	    	System.out.println("birthday "+birthday);
	    	System.out.println("ethnicity "+ethnicity);    	
	    	System.out.println("country "+country);
	    	
	    	if(languages!=null){
		    	for(String language : languages)
		    	{
		    		System.out.println("language "+language);
		    	}
	    	}
	    	
	    	//languages  	
	    	
	    	/*
	    	request.setAttribute("username", username);
	    	request.setAttribute("emailaddress", emailaddress);
	    	request.setAttribute("confirmemailaddress", confirmemailaddress);
	    	request.setAttribute("password", password);
	    	request.setAttribute("confirmpassword", confirmpassword);
	    	
	    	request.setAttribute("birthyear", birthyear);
	    	request.setAttribute("birthmonth", birthmonth);
	    	request.setAttribute("birthday", birthday);
	    	request.setAttribute("about", about);	    	
	    	request.setAttribute("country", country);

	    	request.setAttribute("gender", gender);
	    	request.setAttribute("ethnicity", ethnicity);
	    	request.setAttribute("kindofrelationship", kindofrelationship);
	    	request.setAttribute("bodytype", bodytype);	    	
	    	request.setAttribute("haircolor", haircolor);
	    	request.setAttribute("eyecolor", eyecolor);
	    	request.setAttribute("children", children);
	    	request.setAttribute("education", education);
	    	request.setAttribute("occupation", occupation);*/
	    	//request.setAttribute("languages", languages);
	    	
	    	
    		
	    	
	    	/*_Store*/
	
	    	List<DBObject> registerResponse = PersonController.registerUser(
    			realname,
    			username,
    			emailaddress,
    			confirmemailaddress,
    			password,
    			confirmpassword,
    			whichscreenname,    			
    			birthyear,
    			birthmonth,
    			birthday,
    			about,
    			country,
				gender,
				ethnicity,
				kindofrelationship,
				bodytype,    	
				haircolor,
				eyecolor,
				children,
				education,
				occupation,
				languages,
				interests,
				interestknobs,
				personality
	    	);
		        		    
		    //BasicDBObject recentuser = PersonController.getUniqueUser(Lastid.toString());
	    	System.out.println("registerResponse"+registerResponse);
	    	
		    //Auto Login
		    if(registerResponse.get(0).get("response") == "OK"){		    
		    	List<DBObject> dataresponse = PersonController.loginUser(emailaddress, password, session);
		    	
		    	if(dataresponse.get(0).get("response") == "OK"){
		    		String sessionVariable = "loggedid";
		    		setSessionVar(session, sessionVariable, (String) dataresponse.get(0).get("userId"));
					//String jsonResponse = "You have successfully registered and logged in, enjoy the site.";
					//return getHome(request, response);
					ServiceSerlvet.appendSesssion(request);
					return new ModelAndView("json/response", "jsonResponse", dataresponse);   
		    	}		    
		    }
	    	else{
	    		//the guest can register
	    		//String message = "An error occured";
	    		//return new ModelAndView("user/register", "message", message);
	    		return new ModelAndView("json/response", "jsonResponse", registerResponse); 
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
    	
    	request.setAttribute("page", "members");
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
    	request.setAttribute("page", "search");
    	
		return new ModelAndView("searchview", "message", message);
    }    
       
    
    
    /*
     * User
    */
    @RequestMapping(method=RequestMethod.GET, value={"/user","/user/{id}"})
    public ModelAndView profileDisplay(
    		HttpServletRequest request, 
    		HttpServletResponse response,
    		//@PathVariable(value="id") String id
    		@RequestParam(value="id", required=false) String id
    ) throws UnknownHostException, MongoException {
    	ServiceSerlvet.appendSesssion(request);
    	
    	request.setAttribute("page", "user");
    	
    	//get search ALL users
    	BasicDBObject searchQuery = new BasicDBObject();
    		searchQuery.put("_id", new ObjectId(id));
    	List<DBObject> searchResponse = PersonController.searchUsers(searchQuery);    	
    
    	//append actual age to the returned user object.
    	DBObject newInformation = new BasicDBObject();
    	
    	String birthdate = (String) searchResponse.get(0).get("birthdate");
    
    	Integer ageInYears = PersonController.getAge(birthdate);
    	newInformation.put("ageInYears", ageInYears);    	
    	
    	searchResponse.add(newInformation);
    	
    	
    	System.out.println("response from search user method: "+searchResponse);
 
    	Map<String,String> countryList = CommonUtils.getCountries();    	
    	Map<String,String> genderList = CommonUtils.getGender();  	
    	Map<String,String> ethnicityList = CommonUtils.getEthnicity();
    	
    	
    	request.setAttribute("countryList", countryList);    	
    	request.setAttribute("genderList", genderList);	
    	request.setAttribute("ethnicityList", ethnicityList);    	  	   	
    	
    	ServiceSerlvet.appendSesssion(request);
    	
		return new ModelAndView("user", "people", searchResponse);
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
			return getHome2(request, response, message);
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