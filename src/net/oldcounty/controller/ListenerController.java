/*
*
* Author: Rob Shan Lone
* Copyright (c) 2012 The Old County Limited.
*
* All rights reserved.
*/

package net.oldcounty.controller;


import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.oldcounty.dao.InterestDao;
import net.oldcounty.dao.PersonDao;
import net.oldcounty.manager.PersonManager;
import net.oldcounty.model.Interests;
import net.oldcounty.model.Person;


import org.bson.BasicBSONObject;
import org.bson.types.ObjectId;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

@Controller
public class ListenerController{

	private PersonManager personManager;

    /**
     * Register
     * @return 
     * @throws MongoException
     * @throws UnknownHostException
    */
    @RequestMapping("/edit_chart")
    public ModelAndView editDisplay(
    		HttpServletRequest request,
    		@RequestParam(value="userId", required=false) String userId,
    		@RequestParam(value="interests", required=false) String[] interests,
    		@RequestParam(value="interestsknobs", required=false) Integer[] interestsknobs,
    		@RequestParam(value="submitted", required=false) String submitted
    		) throws UnknownHostException, MongoException
    {
    	
    	/*
    	int[] anArray;
        anArray = new int[10];
        anArray[0] = 10;
        anArray[1] = 20;
        anArray[2] = 30;
        anArray[3] = 40;
        anArray[4] = 50;
        
        String[] interests2 = {"Robots", "Pie", "Animal", "Love", "Cheese"};
        */
        
    	System.out.println("interests ::  "+ interests);
    	System.out.println("interestsknobs ::  "+ interestsknobs);
    	
		//String userId = "513f8a35d1efd3e0a2e673c1";//registerResponse.get(0).get("lastId").toString(); //get actual user id
		
    	/*_interests chart*/
	    Map<String,Integer> interestData = new LinkedHashMap<String,Integer>();
    	if(interests!=null){
    		int index = 0;
    		for(String interest : interests)
	    	{
    			interestData.put(interest,interestsknobs[index]);
	    		index++;
	    	}
    	}
    	
    	Interests interest = new Interests();
	    	interest.setUserId(userId);
	    	interest.setName("interests");
	    	interest.setResults(interestData);
	    	
	    System.out.println("interestData ::  "+ interestData);	
    	
    	if(submitted == null){
    		//__if not yet added a chart return html form	    	
			return new ModelAndView("jsp/user/edit_chart");   	
    	}else{
    		//_ register the user into the database and return a json response
        	return new ModelAndView("jsp/json/response", "json", InterestDao.addInterest(interest));  	
    	}       	
    }
    
    
    

    /**
     * Forgot Password
     * @return 
     * @throws MongoException
     * @throws UnknownHostException
    */
    @RequestMapping("/forgotpassword")
    public ModelAndView forgotPasswordDisplay(
	    		HttpServletRequest request,
	    		@RequestParam(value="emailaddress", required=false) String emailaddress,    		
	    		@RequestParam(value="submitted", required=false) String submitted
    		) throws UnknownHostException, MongoException
    {	
    	if(submitted == null){
    		//__if not yet loggedin return html form
	    	
			return new ModelAndView("jsp/user/forgotpassword");   	
    	}else{
    		//_ loggedin the user into the database and return a json response
    		String json = null;
        	return new ModelAndView("jsp/json/response", "json", json);  	
    	}
    }       
    
    
    /**
     * Login
     * @return 
     * @throws MongoException
     * @throws UnknownHostException
    */
    @RequestMapping("/login")
    public ModelAndView loginDisplay(
	    		HttpServletRequest request,
	    		@RequestParam(value="emailaddress", required=false) String emailaddress,
	    		@RequestParam(value="password", required=false) String password,	    		
	    		@RequestParam(value="submitted", required=false) String submitted
    		) throws UnknownHostException, MongoException
    {	
    	if(submitted == null){
    		//__if not yet loggedin return html form
	    	
	    	
			return new ModelAndView("jsp/user/login");   	
    	}else{
    		//_ loggedin the user into the database and return a json response
    		String json = null;
        	return new ModelAndView("jsp/json/response", "json", json);  	
    	}
    }   
          
    
    /**
     * Register
     * @return 
     * @throws MongoException
     * @throws UnknownHostException
    */
    @RequestMapping("/register")
    public ModelAndView registerDisplay(
    		HttpServletRequest request,
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

    		@RequestParam(value="seekings", required=false) String[] seekings,
    		@RequestParam(value="seekingknobs", required=false) Integer[] seekingknobs,

    		@RequestParam(value="visitings", required=false) String[] visitings,
    		@RequestParam(value="visitingknobs", required=false) Integer[] visitingknobs,

    		@RequestParam(value="personality", required=false) Integer[] personality,

    		@RequestParam(value="latitude", required=false) String latitude,
    		@RequestParam(value="longitude", required=false) String longitude,

    		@RequestParam(value="goal1", required=false) String goal1,
    		@RequestParam(value="goal2", required=false) String goal2,
    		@RequestParam(value="goal3", required=false) String goal3,

    		//@RequestParam(value="lookingfor", required=false) String lookingfor,

    		@RequestParam(value="file", required=false) MultipartFile[] file,

    		@RequestParam(value="submitted", required=false) String submitted
    		) throws UnknownHostException, MongoException
    {
    	//HttpSession session = serlvetService(request);
    	//String inSession = PersonController.inSession(session);
    	//ServiceSerlvet.appendSesssion(request);
    	
    	//_new person
    	Person person = new Person();

    	person.setRealname(realname);
    	person.setUsername(username);
    	person.setEmailaddress(emailaddress);
    	person.setConfirmemailaddress(confirmemailaddress);
    	person.setPassword(password);
    	person.setConfirmpassword(confirmpassword);
    	person.setWhichscreenname(whichscreenname);
    	person.setBirthyear(birthyear);
    	person.setBirthmonth(birthmonth);
    	person.setBirthday(birthday);
    	person.setAbout(about);
    	person.setCountry(country);
    	person.setGender(gender);
    	person.setEthnicity(ethnicity);
    	person.setKindofrelationship(kindofrelationship);
    	person.setBodytype(bodytype);
    	person.setHaircolor(haircolor);
    	person.setEyecolor(eyecolor);
    	person.setChildren(children);
    	person.setEducation(education);
    	person.setOccupation(occupation);
    	person.setLongitude(longitude);
    	person.setLatitude(latitude);
    	person.setLanguages(languages);
    	person.setInterests(interests);
    	person.setInterestknobs(interestknobs);
    	person.setSeekings(seekings);
    	person.setSeekingknobs(seekingknobs);
    	person.setVisitings(visitings);
    	person.setVisitingknobs(visitingknobs);
    	person.setGoal1(goal1);
    	person.setGoal2(goal2);
    	person.setGoal3(goal3);
    	
    	BasicDBObject personaltraits = new BasicDBObject();

    	if(personality != null){
		    /*add to new collection personality*/
			String[] personalTypes ={"confidence","reasoning","emotion","daring","attachment","sensitivity","comedy"};
			Integer j = 0;
			for(Integer traits : personality)
	    	{
	    		//System.out.println("traits "+traits);
	    		personaltraits.put(personalTypes[j], traits);
	    		j++;
	    	}	
    	}
    	person.setPersonality(personaltraits);
    	
    	if(submitted == null){
    		//__if not yet registered return html form
	    	Map<String,String> countryList = CommonUtils.getCountries();
	    	String countriesCommonKey = "GBR"; //United Kingdom
	
	    	Map<String,String> ethnicityList = CommonUtils.getEthnicity();
	    	String ethnicityCommonKey = "0"; //Caucasian
	
	    	request.setAttribute("countryCommonKey", countriesCommonKey);
	    	request.setAttribute("countryList", countryList);
	
	    	request.setAttribute("ethnicityCommonKey", ethnicityCommonKey);
	    	request.setAttribute("ethnicityList", ethnicityList);
	    	
			return new ModelAndView("jsp/user/register");   	
    	}else{
    		//_ register the user into the database and return a json response
        	return new ModelAndView("jsp/json/response", "json", personManager.registerUser(person));  	
    	}    	
    }


    public void setPersonManager(PersonManager personManager){
    	this.personManager = personManager;
    }
    
    /*
     * Member List
     * Displays All Users
    */
    @RequestMapping("/members")
    public ModelAndView memberList(
	    		HttpServletRequest request
    		) throws UnknownHostException, MongoException
    {
    	//ServiceSerlvet.appendSesssion(request);
    	//get search ALL users
    	BasicDBObject searchQuery = new BasicDBObject();
    	//skip 0
    	//limit 130
    	List<DBObject> dataresponse = PersonDao.searchUsers(searchQuery, 0, 130, "myCollection");
    	 System.out.println(dataresponse);
    	 
    	request.setAttribute("page", "members");
    	return new ModelAndView("jsp/memberlist", "response", dataresponse);
    }
    

    
    /*
     * Venue Form
    */
    @RequestMapping("/venueform")
    public ModelAndView venueForm(
	    		HttpServletRequest request
    		) throws UnknownHostException, MongoException
    {
    	
    	return new ModelAndView("jsp/venueform");
    }    
    
    
    
    /*
     * User
    */
    @RequestMapping(method=RequestMethod.GET, value={"/user","/user/{id}"})
    public ModelAndView profileDisplay(
    		HttpServletRequest request,
    		@RequestParam(value="id", required=false) String id
    ) throws UnknownHostException, MongoException {
    	//ServiceSerlvet.appendSesssion(request);

    	request.setAttribute("page", "user");

    	//get search ALL users
    	BasicDBObject searchQuery = new BasicDBObject();
    		searchQuery.put("_id", new ObjectId(id));
    	
    	//skip 0
    	//limit 1   		
    	List<DBObject> searchResponse = PersonDao.searchUsers(searchQuery, 0, 1, "myCollection");

    	//append actual age to the returned user object.
    	DBObject newInformation = new BasicDBObject();

    	String birthdate = (String) searchResponse.get(0).get("birthdate");

    	Integer ageInYears = PersonDao.getAge(birthdate);
    	newInformation.put("ageInYears", ageInYears);

    	/*
    	HashMap<Integer,Object> gallery = PersonController.getGallery(
    			id,
    			false
    	);

    	newInformation.put("gallery", gallery);

    	Integer countGallery = gallery.size();
    	newInformation.put("countGallery", countGallery);
		*/
    	
    	searchResponse.add(newInformation);

    	Map<String,String> countryList = CommonUtils.getCountries();
    	Map<String,String> genderList = CommonUtils.getGender();
    	Map<String,String> ethnicityList = CommonUtils.getEthnicity();

    	request.setAttribute("countryList", countryList);
    	request.setAttribute("genderList", genderList);
    	request.setAttribute("ethnicityList", ethnicityList);

    	//ServiceSerlvet.appendSesssion(request);

		return new ModelAndView("jsp/user", "people", searchResponse);
    }
    
    

    /*
     * Schedule a date
    */
    /*
     * getInterestJson
    */
    @RequestMapping("/scheduledate")
    public ModelAndView scheduleDateDisplay(
	    		HttpServletRequest request
    		) throws UnknownHostException, MongoException
    {
    	String json = null;
    	return new ModelAndView("jsp/scheduledate", "response", json);
    }
    
    /*
    @RequestMapping(method=RequestMethod.GET, value={"/scheduledate","/scheduledate/{id}"})
    public ModelAndView scheduleDateDisplay(
    		HttpServletRequest request,
    		@RequestParam(value="id", required=false) String id
    ) throws UnknownHostException, MongoException {
    	//ServiceSerlvet.appendSesssion(request);

    	request.setAttribute("page", "user");

    	//get search ALL users
    	BasicDBObject searchQuery = new BasicDBObject();
    		searchQuery.put("_id", new ObjectId(id));
    	
    	//skip 0
    	//limit 1   		
    	List<DBObject> searchResponse = PersonDao.searchUsers(searchQuery, 0, 1, "myCollection");

    	//append actual age to the returned user object.
    	DBObject newInformation = new BasicDBObject();

    	String birthdate = (String) searchResponse.get(0).get("birthdate");

    	Integer ageInYears = PersonDao.getAge(birthdate);
    	newInformation.put("ageInYears", ageInYears);
    	
    	searchResponse.add(newInformation);

    	Map<String,String> countryList = CommonUtils.getCountries();
    	Map<String,String> genderList = CommonUtils.getGender();
    	Map<String,String> ethnicityList = CommonUtils.getEthnicity();

    	request.setAttribute("countryList", countryList);
    	request.setAttribute("genderList", genderList);
    	request.setAttribute("ethnicityList", ethnicityList);

		return new ModelAndView("jsp/scheduledate", "people", searchResponse);
    }    
    */
    

    /*
     * Api
    */
    @RequestMapping(method=RequestMethod.GET, value={"/api","/api/{servicerequest}/{id}/{chartname}/{skips}/{limits}"})
    public ModelAndView apiService(
    		HttpServletRequest request,
    		@RequestParam(value="id", required=false) String id,
    		@RequestParam(value="servicerequest", required=false) String servicerequest,
    		@RequestParam(value="chartname", required=false) String chartname,
    		@RequestParam(value="skips", required=false) Integer skips,
    		@RequestParam(value="limits", required=false) Integer limits,
    		@RequestParam(value="bodytype", required=false) String bodytype,
    		@RequestParam(value="haircolor", required=false) String haircolor,
    		@RequestParam(value="eyecolor", required=false) String eyecolor,
    		@RequestParam(value="ethnicity", required=false) String ethnicity,
    		@RequestParam(value="gender", required=false) String gender
    ) throws UnknownHostException, MongoException {
    	//ServiceSerlvet.appendSesssion(request);
       	
  	
    	List<DBObject> json = null;
    	
    	if(servicerequest.equals("getMembers")){
    		//request personality data    	
    		//id
    		//Integer skips = 0;
    		//Integer limits = 20;
    		System.out.println(id);
    		BasicDBObject filter = new BasicDBObject();  		
    			//filter.put("isloggedon", "1");
    		
    			if(id != null){
    				filter.put("_id", new ObjectId(id));
    			}

    			if(bodytype != null){
    				filter.put("bodytype", bodytype);
    			}
    			if(haircolor != null){
    				filter.put("haircolor", haircolor);
    			}
    			if(eyecolor != null){
    				filter.put("eyecolor", eyecolor);
    			}    			
    			if(ethnicity != null){
    				filter.put("ethnicity", ethnicity);
    			}
    			if(gender != null){
    				filter.put("gender", gender);
    			}
    			
    		json = PersonDao.getMembers(skips, limits, filter);
    	}
    	else if(servicerequest.equals("getPersonality")){
    		//request personality data    	
    		//id
    		json = PersonDao.getPersonality(id);
    	}
    	else if(servicerequest.equals("getInterests")){
			//request interest data 
    		//chartname, visiting, interests
    		
    		System.out.println(id);
    		System.out.println(chartname);
    		//id
		    	Interests obj = new Interests();
			    	obj.setUserId(id);
			    	obj.setName(chartname);
    		json = InterestDao.getInterest(obj);
		}
    	
		return new ModelAndView("jsp/json/response", "json", json);
    }
    
    
    /*
     * getForgotPassword
    */
    @RequestMapping("/getForgotPassword")
    public ModelAndView getForgotPassword(
	    		HttpServletRequest request
    		) throws UnknownHostException, MongoException
    {
    	//_recover password or set a new one
    	
    	//_ email it out
    	
    	//MailMail myObject = new MailMail();	
    	//myObject.sendMail("robbsy", "bobby", "this is a test email", "here is the message");
    	/**
    	 * reference :: http://www.mkyong.com/spring/spring-sending-e-mail-via-gmail-smtp-server-with-mailsender/
    	 * http://static.springsource.org/spring/docs/3.0.x/reference/mail.html
    	 * http://www.codejava.net/frameworks/spring/sending-e-mail-with-spring-mvc
    	 * http://static.springsource.org/spring/docs/2.0.x/reference/mail.html
    	 * */
       	
    	
    	//_respond with json response to provide success/fail
    	
    	String json = null;
    	return new ModelAndView("jsp/json/interest", "response", json);
    }   
    
    
    
    
    
    /*
     * getInterestJson
    */
    @RequestMapping("/getInterestJson")
    public ModelAndView getInterestJson(
	    		HttpServletRequest request
    		) throws UnknownHostException, MongoException
    {
    	String json = null;
    	return new ModelAndView("jsp/json/interest", "response", json);
    }
    
    
    
    
    
    /*
     * getPlaceJson
    */
    @RequestMapping("/getPlaceJson")
    public ModelAndView getPlaceJson(
	    		HttpServletRequest request
    		) throws UnknownHostException, MongoException
    {
    	String json = null;
    	return new ModelAndView("jsp/json/places", "response", json);
    }    
    

    /*
     * getSeekingJson
    */
    @RequestMapping("/getSeekingJson")
    public ModelAndView getSeekingJson(
	    		HttpServletRequest request
    		) throws UnknownHostException, MongoException
    {
    	String json = null;
    	return new ModelAndView("jsp/json/seeking", "response", json);
    }    
            
    
}