package net.oldcounty.manager;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import net.oldcounty.controller.CommonUtils;
import net.oldcounty.controller.SessionController;
import net.oldcounty.controller.SimpleEmailService;
import net.oldcounty.dao.PersonDao;
import net.oldcounty.model.Person;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

import java.security.SecureRandom;
import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

public class PersonManager {

	PersonDao personDao;

	/**
	 * setPersonDao
	 * set person Dao object
	 **/		
	public void setPersonDao(PersonDao personDao){
		this.personDao = personDao;
	}
	
	/**
	 * Generate new password
	 * */
	 public String generatePassword(){
		 SecureRandom random = new SecureRandom();		 
		 return new BigInteger(130, random).toString(32);
	 }
	 
	/**
	 * Validate the person, check if the passwords match, that the relevant fields are not blank
	 * */	 
	 private BasicDBObject validateUser(Person person){
		
		BasicDBObject response = new BasicDBObject();
	    Boolean isValidInputs = true;
	    BasicDBObject results = new BasicDBObject();
		
	    //_validate inputs
	    
		//__check if passwords match
	    if(!CommonUtils.isEmpty(person.getPassword()) && !CommonUtils.isEmpty(person.getConfirmpassword())){
		    if(!CommonUtils.doPasswordsMatch(person.getPassword(), person.getConfirmpassword())){
				isValidInputs = false;
				results.put("error", "Passwords do not match");
			}
	    }
	    else{
	    	isValidInputs = false;
	    	results.put("error", "One or both of the passwords were blank");
	    }

		//__check if these required values are empty
		if(
			CommonUtils.isEmpty(person.getUsername()) ||
			CommonUtils.isEmpty(person.getEmailaddress()) ||
			CommonUtils.isEmpty(person.getPassword()) ||
			CommonUtils.isEmpty(person.getBirthyear()) ||
			CommonUtils.isEmpty(person.getBirthmonth()) ||
			CommonUtils.isEmpty(person.getBirthday()) 
		){
			isValidInputs = false;
			results.put("error", "At least one required field was blank");
		}
		
		//__check if the date details are not empty
		if(
			!CommonUtils.isEmpty(person.getBirthyear()) && 
			!CommonUtils.isEmpty(person.getBirthmonth()) && 
			!CommonUtils.isEmpty(person.getBirthday())
		){

			//__validate date
			if(
				!CommonUtils.isValidDate(person.getBirthyear()+person.getBirthmonth()+person.getBirthday())
			){
				isValidInputs = false;
				results.put("error", "The birthdate is not valid");
			}else{
				//__validate if birth date is older than 18
				if(
					!CommonUtils.isOlderThan18(person.getBirthyear()+person.getBirthmonth()+person.getBirthday())
				){ 
					isValidInputs = false;
					results.put("error", "You need to be over 18 to access this site");
				}							
			}
		}
		else{
			isValidInputs = false;
			results.put("error", "The birthdate is incomplete");
		}
		
		//__check if email already exists in the database
	    if(!CommonUtils.isEmpty(person.getEmailaddress())){
			try {
				if(!CommonUtils.isEmailUnique(person.getEmailaddress())){
					isValidInputs = false;
					results.put("error", "Email address was not unique");
				}
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (MongoException e) {
				e.printStackTrace();
			}
	    }
	    else{
	    	isValidInputs = false;	
	    	results.put("error", "Email address was empty");				    	
	    }
	    
	    response.put("isValidInputs", isValidInputs);
	    response.put("results", results);
	    
	    return response;
	}
	 
	/**
	 * Register the person
	 * */	 
	public List<DBObject> registerUser(Person person){
			//__Prepare response					
			List<DBObject> response = new ArrayList<DBObject>();
		    BasicDBObject results = new BasicDBObject();
		    
			BasicDBObject valdidationResponse = validateUser(person);
				results = (BasicDBObject) valdidationResponse.get("results");
		    
		    if((Boolean) valdidationResponse.get("isValidInputs")){		    	
			    BasicDBObject data = new BasicDBObject();
					data.put("emailAddress", person.getEmailaddress());
					data.put("userName", person.getUsername());
					data.put("realName", person.getRealname());
					data.put("password", person.getPassword());	
					
				//__ send email notification
				SimpleEmailService.generateEmail("registerTemplate", data);		    	
		    	
		    	List<DBObject> latestUser = PersonDao.registerUser(person);
		    	
				results.put("response", "OK");
				results.put("description", "User has been registered");
		    	results.put("user", latestUser.get(0));
		    	results.put("lastId", latestUser.get(0).get("lastId"));
		    }
		    else
		    {
				results.put("response", "FAIL");
				results.put("description", "Failed to register user");
		    }

		    response.add(results);
		return response;
	}
	
	/**
	 * Edit the person
	 * */		
	public List<DBObject> editUser(Person person, String section){
			//__Prepare response		
			List<DBObject> response = new ArrayList<DBObject>();

		    BasicDBObject results = new BasicDBObject();
		    Boolean isValidInputs = true;

		    if(section.equals("account")){			    
			    BasicDBObject valdidationResponse = validateUser(person);
			    isValidInputs = (Boolean) valdidationResponse.get("isValidInputs");	
			    results = (BasicDBObject) valdidationResponse.get("results");		    	
		    }
		    
		    //_validate inputs 
		    if(isValidInputs){				    	
		    	List<DBObject> latestUser = PersonDao.editUser(person, section);
		    	
				results.put("response", "OK");
				results.put("description", "User has been registered");
		    	results.put("user", latestUser.get(0));
		    	results.put("lastId", latestUser.get(0).get("lastId"));
		    }
		    else
		    {
				results.put("response", "FAIL");
				results.put("description", "Failed to register user");
		    }

		    response.add(results);
		return response;
	}

	/**
	 * Send the person a new password
	 * */	
	@SuppressWarnings("unchecked")
	public List<DBObject> forgotPassword(String emailaddress) throws UnknownHostException, MongoException{
			//__Prepare response		
			List<DBObject> response = new ArrayList<DBObject>();

		    BasicDBObject results = new BasicDBObject();
		    Boolean isValidInputs = true;

		    //check if email exists
    		//_new person
        	Person person = new Person();
        	
        	if(!CommonUtils.isEmailUnique(emailaddress)){
        	    // search query
        	    BasicDBObject searchQuery = new BasicDBObject();
        	    	searchQuery.put("emailaddress", emailaddress);        		
        		
        	    List<DBObject> user = (List<DBObject>) PersonDao.getUniqueUser(searchQuery).get(0).get("user");
        	    	String userId = (String) user.get(0).get("_id").toString();      		
        		
            	person.setUid(userId);
            	person.setEmailaddress(emailaddress);
            	person.setPassword(generatePassword());//_generate random password 
            }
        	else{
        		isValidInputs = false;
        	}
		    
		    if(isValidInputs){		    	
			    String password = person.getPassword();
			    String email = person.getEmailaddress();
	        	
			    BasicDBObject data = new BasicDBObject();
	        	 	data.put("emailAddress", email);
	        	 	data.put("userName", "Robbys");
	        	 	data.put("password", password);	
	        	 	
		    	//__ send email notification
		    	SimpleEmailService.generateEmail("forgotPasswordTemplate", data);
		    	
		    	List<DBObject> latestUser = PersonDao.setForgotPassword(person);

				results.put("response", "OK");
				results.put("description", "User password reset");
		    	results.put("user", latestUser.get(0));
		    	results.put("lastId", latestUser.get(0).get("lastId"));
		    }
		    else
		    {
				results.put("response", "FAIL");
				results.put("description", "Failed to reset user password");
		    }

		    response.add(results);
		return response;
	}

	/**
	 * Log the person in 
	 * */		
	@SuppressWarnings("unchecked")
	public List<DBObject> login(String emailaddress, String password, HttpServletRequest request) throws UnknownHostException, MongoException{
			//__Prepare response		
			List<DBObject> response = new ArrayList<DBObject>();

		    BasicDBObject results = new BasicDBObject();

		    //check if email exists
    		//_new person
        	Person person = new Person();
        	
        	if(!CommonUtils.isEmailUnique(emailaddress)){		    	
        	    // search query
        	    BasicDBObject searchQuery = new BasicDBObject();
        	    	searchQuery.put("emailaddress", emailaddress);
        	    	searchQuery.put("password", password);
        		
        	    List<DBObject> user = (List<DBObject>) PersonDao.getUniqueUser(searchQuery).get(0).get("user");
        	    	String userId = (String) user.get(0).get("_id").toString();      		
        		
            	person.setUid(userId);
            	person.setEmailaddress(emailaddress);		    	
		    	
            	List<DBObject> latestUser = PersonDao.loginUser(person);
		    	
				results.put("response", "OK");
				results.put("description", "User Logged In");
		    	results.put("user", latestUser.get(0));
		    	results.put("lastId", latestUser.get(0).get("lastId"));
		    
		    	SessionController.logUser(user, request);//store user in session
        	}
		    else
		    {
				results.put("response", "FAIL");
				results.put("description", "Failed to login user");
		    }

		    response.add(results);
		return response;
	}	
	
	/**
	 * Log the person out
	 * */		
	public List<DBObject> logout(HttpServletRequest request) throws UnknownHostException, MongoException{
			//__Prepare response		
			List<DBObject> response = new ArrayList<DBObject>();
		    BasicDBObject results = new BasicDBObject();

    		//_new person
		    List<DBObject> oldLoggedUser = SessionController.getLoggedUser(request);		   	
		   		String userId = (String) oldLoggedUser.get(0).get("_id").toString();
		   	
		   	Person person = new Person();
		   		person.setUid(userId);
		   		
	        List<DBObject> latestUser = PersonDao.logoutUser(person);

		    results.put("response", "OK");
			results.put("description", "User Logged Out");
	    	results.put("lastId", latestUser.get(0).get("lastId"));
	    	results.put("oldLoggedUser", oldLoggedUser);
	    	
	    	SessionController.logOutUser(request);//remove from session		    	

	    	response.add(results);
		return response;
	}
}