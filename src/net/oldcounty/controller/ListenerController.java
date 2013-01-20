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
    	person.setPersonality(personality);
    	
    	
    	//_ register the user into the database
    	List<DBObject> registerResponse = personManager.registerUser(person);
    	
    	if(registerResponse.get(0).get("response") == "OK"){
    			Object userId = registerResponse.get(0).get("lastId"); //get actual user id
    			//"5koiopewr03oewrew";
    			System.out.println("using UID "+userId);
    			
		    	/*new interest code*/
			    /*add to new interests chart*/
			    Map<String,Integer> interestData = new LinkedHashMap<String,Integer>();
		    	if(interests!=null){
		    		int index = 0;
		    		for(String interest : interests)
			    	{
		    			interestData.put(interest,interestknobs[index]);
			    		index++;
			    	}
		    	}
		    	
		    	Interests interest = new Interests();
			    	interest.setUserId(userId);
			    	interest.setName("interests");
			    	interest.setResults(interestData);
			    	
		    	List<DBObject> interestResponse = InterestDao.addInterest(interest);
		    	if(interestResponse.get(0).get("response") == "OK"){
		    		System.out.println("interests added b "+ userId);
		    		//check if the chart has been added successfully.
		    	}
		    	
		    	/*new interest code*/
    	}
    	//last id by registered user - dummy user id

    	Map<String,String> countryList = CommonUtils.getCountries();
    	String countriesCommonKey = "GBR"; //United Kingdom

    	Map<String,String> ethnicityList = CommonUtils.getEthnicity();
    	String ethnicityCommonKey = "0"; //Caucasian

    	request.setAttribute("countryCommonKey", countriesCommonKey);
    	request.setAttribute("countryList", countryList);

    	request.setAttribute("ethnicityCommonKey", ethnicityCommonKey);
    	request.setAttribute("ethnicityList", ethnicityList);
    	
    	Boolean inSession = null;
       	//if the user has logged into the session
    	if(inSession != null){
    		//they will need to logout in order to re-register a new account
    		String message = "You have been automatically logged out";
    		//PersonController.logoutUser(inSession, session);
    		return new ModelAndView("jsp/user/register", "message", message);
    	}
    	else{
    		//the guest can register
    		String message = "Dear Guest please register";
    		return new ModelAndView("jsp/user/register", "message", message);
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
    	List<DBObject> dataresponse = PersonDao.searchUsers(searchQuery, "myCollection");

    	request.setAttribute("page", "members");
    	return new ModelAndView("jsp/memberlist", "response", dataresponse);
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
    	List<DBObject> searchResponse = PersonDao.searchUsers(searchQuery, "myCollection");

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
     * Api
    */
    @RequestMapping(method=RequestMethod.GET, value={"/api","/api/{servicerequest}/{id}/{chartname}"})
    public ModelAndView apiService(
    		HttpServletRequest request,
    		@RequestParam(value="id", required=false) String id,
    		@RequestParam(value="servicerequest", required=false) String servicerequest,
    		@RequestParam(value="chartname", required=false) String chartname
    ) throws UnknownHostException, MongoException {
    	//ServiceSerlvet.appendSesssion(request);
       	
  	
    	List<DBObject> json = null;
    	
    	if(servicerequest.equals("getPersonality")){
    		//request personality data    	
    		//id
    		json = PersonDao.getPersonality(id);
    	}
    	else if(servicerequest.equals("getInterests")){
			//request interest data 
    		//chartname, visiting, interests
    		//id
		    	Interests interest = new Interests();
			    	interest.setUserId(id);
			    	interest.setName("interests");
    		json = InterestDao.getInterest(interest);
		}
    	
		return new ModelAndView("jsp/json/response", "json", json);
    }    
}