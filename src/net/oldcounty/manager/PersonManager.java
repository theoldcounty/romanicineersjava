package net.oldcounty.manager;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.oldcounty.controller.CommonUtils;
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

public class PersonManager {

	PersonDao personDao;
	
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
	
	


	public void setPersonDao(PersonDao personDao){
		this.personDao = personDao;
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
}
