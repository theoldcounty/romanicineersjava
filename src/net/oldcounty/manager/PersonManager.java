package net.oldcounty.manager;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.oldcounty.controller.CommonUtils;
import net.oldcounty.controller.SimpleEmailService;
import net.oldcounty.dao.PersonDao;
import net.oldcounty.dao.InterestDao;
import net.oldcounty.model.Interests;
import net.oldcounty.model.Person;

import org.bson.BasicBSONObject;
import org.bson.types.ObjectId;

import com.mongo.app.MongoApp;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;


import java.security.SecureRandom;
import java.math.BigInteger;

public class PersonManager {

	PersonDao personDao;

	public void setPersonDao(PersonDao personDao){
		this.personDao = personDao;
	}
	
	 public String generatePassword(){
		 SecureRandom random = new SecureRandom();		 
		 return new BigInteger(130, random).toString(32);
	 }
	 
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
		
		
		if(
			!CommonUtils.isEmpty(person.getBirthyear()) && 
			!CommonUtils.isEmpty(person.getBirthmonth()) && 
			!CommonUtils.isEmpty(person.getBirthday())
		){
			System.out.println("checking birthday entry");   
			if(
				!CommonUtils.isValidDate(person.getBirthyear()+person.getBirthmonth()+person.getBirthday())
			){
				System.out.println("birthday is not valid"); 
				isValidInputs = false;
				results.put("error", "The birthdate is not valid");
			}else{
				if(
						!CommonUtils.isOlderThan18(person.getBirthyear()+person.getBirthmonth()+person.getBirthday())
				){
					System.out.println("need to be over 18"); 
					isValidInputs = false;
					results.put("error", "You need to be over 18 to access this site");
				}							
			}
		}
		else{
			System.out.println("birthday incomplete");
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
	
	 

	 
	public List<DBObject> registerUser(Person person){
		//__Prepare response
		
		System.out.println("register user using person control");    			
		List<DBObject> response = new ArrayList<DBObject>();

		    BasicDBObject results = new BasicDBObject();
		    Boolean isValidInputs = true;
		    
		    BasicDBObject valdidationResponse = validateUser(person);
		    isValidInputs = (Boolean) valdidationResponse.get("isValidInputs");	
		    results = (BasicDBObject) valdidationResponse.get("results");
		    
		    if(isValidInputs){
		    	
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
	
	
	public List<DBObject> editUser(Person person, String section){
		//__Prepare response
		
		System.out.println("edit user using person control<<<<<<<<<<< "+section);    			
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

				/*
				>>>>>>> reset password
				>>>>>>> recover password
				*/    		

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

		
	
	@SuppressWarnings("unchecked")
	public List<DBObject> login(String emailaddress, String password) throws UnknownHostException, MongoException{
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
        	    	searchQuery.put("password", password);
        		
        	    List<DBObject> user = (List<DBObject>) PersonDao.getUniqueUser(searchQuery).get(0).get("user");
        	    	String userId = (String) user.get(0).get("_id").toString();      		
        		
            	person.setUid(userId);
            	person.setEmailaddress(emailaddress);
            }
        	else{
        		isValidInputs = false;
        	}
		    
		    
		    if(isValidInputs){		    	
			    
		    	List<DBObject> latestUser = PersonDao.loginUser(person);

				results.put("response", "OK");
				results.put("description", "User Logged In");
		    	results.put("user", latestUser.get(0));
		    	results.put("lastId", latestUser.get(0).get("lastId"));
		    }
		    else
		    {
				results.put("response", "FAIL");
				results.put("description", "Failed to login user");
		    }

		    response.add(results);
		return response;
	}	
	
	
	
	@SuppressWarnings("unchecked")
	public List<DBObject> logout(Person person) throws UnknownHostException, MongoException{
		//__Prepare response
		
		List<DBObject> response = new ArrayList<DBObject>();

		    BasicDBObject results = new BasicDBObject();
		    Boolean isValidInputs = true;

    		//_new person
        	//Person person = new Person();        	
        	//person.setUid(userId);
        	
        	
		    if(isValidInputs){		    	
			    
		    	List<DBObject> latestUser = PersonDao.logoutUser(person);

				results.put("response", "OK");
				results.put("description", "User Logged Out");
		    	results.put("user", latestUser.get(0));
		    	results.put("lastId", latestUser.get(0).get("lastId"));
		    }
		    else
		    {
				results.put("response", "FAIL");
				results.put("description", "Failed to log user out");
		    }

		    response.add(results);
		return response;
	}	
}