package net.oldcounty.controller;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.bson.types.ObjectId;

import com.mongo.app.MongoApp;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

public class PersonController extends ServiceSerlvet {

	/*test main method - delete when final*/
    public static void main(String[] args) throws UnknownHostException, MongoException {
    }
    
	public static String inSession(HttpSession session){		
		String sessionVariable = "loggedid";
		String Val = getSessionVar(session, sessionVariable);
		
		return Val;
	}    
	    
    
	/**
	 * Login User
	 * @param id
	 * @throws MongoException 
	 * @throws UnknownHostException 
	 **/
	public static List<DBObject> loginUser(String emailaddress, String password, HttpSession session) throws UnknownHostException, MongoException{
		//__Prepare response
		List<DBObject> response = new ArrayList<DBObject>();
		
		Boolean isValidInputs = true;
		
		BasicDBObject results = new BasicDBObject();
		//__check if these required values are empty
		if(
			CommonUtils.isEmpty(emailaddress) ||
			CommonUtils.isEmpty(password)
		){
			isValidInputs = false;
			results.put("error", "At least one required field was blank");
		}		
		
		if(isValidInputs){		
			//_getCollection
			DBCollection collection = MongoApp.getCollection();
			
		    // search query
		    BasicDBObject searchQuery = new BasicDBObject();
		    	searchQuery.put("emailaddress", emailaddress);
		    	searchQuery.put("password", password);
		    
		    DBCursor cursor = collection.find(searchQuery);
		    
		    //get time
		    Date now = new Date();
			BasicDBObject time = new BasicDBObject("ts", now);
			
			 //get object changes with new values
		    BasicDBObject change = new BasicDBObject();
				change.put("lastloggedon", time);
				change.put("isloggedon", 1);
			
			//place the changes into one set object	
			BasicDBObject changes = new BasicDBObject();
				changes.put("$set", change);
	
				//if the search finds a result update and log them in
				
				if(cursor.count() != 0){
					String objId = null;
			    	while (cursor.hasNext()) {
			    		objId = cursor.next().get("_id").toString();
				    }				
					collection.update(new BasicDBObject().append("_id", new ObjectId(objId)), changes);
				
					results.put("response", "OK");
					results.put("userId", objId);
					results.put("description", "User has been found and is now logged in");
					
					String sessionVariable = "loggedid";
		    		setSessionVar(session, sessionVariable, (String) objId);					
					
				}
				else{
					results.put("response", "FAIL");
					results.put("description", "No user found");
				}
		}
		else
		{
			results.put("response", "FAIL");
			results.put("description", "Null field error");		
		}
		
		response.add(results);
		return response;
	}
	
	
	/**
	 * Register User
	 * @param id
	 * @throws MongoException 
	 * @throws UnknownHostException 
	 **/
	public static List<DBObject> registerUser(
			String username,
			String emailaddress,
			String password,
			String confirmpassword,
			String gender,
			String birthyear,
			String birthmonth,
			String birthday,
			String ethnicity,
			String country
				) throws UnknownHostException, MongoException
		{
		System.out.println("running usercontrol register user");
		
		//__Prepare response
		List<DBObject> response = new ArrayList<DBObject>();

			//_getCollection
			DBCollection collection = MongoApp.getCollection();
			
			Date now = new Date();
			BasicDBObject time = new BasicDBObject("ts", now);			

		    BasicDBObject results = new BasicDBObject();
			
		    Boolean isValidInputs = true;

		    //_validate inputs

			//__check if email already exists in the database
			if(!CommonUtils.isEmailUnique(emailaddress)){
				isValidInputs = false;
				results.put("error", "Email address was not unique");
			}

			//__check if passwords match
			if(!CommonUtils.doPasswordsMatch(password, confirmpassword)){
				isValidInputs = false;
				results.put("error", "Passwords do not match");
			}				
			
			//__check if these required values are empty
			if(
				CommonUtils.isEmpty(username) ||
				CommonUtils.isEmpty(emailaddress) ||
				CommonUtils.isEmpty(password)
			){
				isValidInputs = false;
				results.put("error", "At least one required field was blank");
			}

			if(
				!CommonUtils.isValidDate(birthyear+birthmonth+birthday)
			){
				isValidInputs = false;
				results.put("error", "The birthdate is not valid");
			}
			

			if(
				!CommonUtils.isOlderThan18(birthyear+birthmonth+birthday)
			){
				isValidInputs = false;
				results.put("error", "You need to be over 18 to access this site");
			}			
			
		    if(isValidInputs){
			    // create a document to store attributes
			    BasicDBObject document = new BasicDBObject();
			    
			    document.put("username", username);
			    document.put("emailaddress", emailaddress);
			    document.put("password", password);			    
			    document.put("gender", gender);	
			    document.put("birthdate", birthyear+birthmonth+birthday);	
			    document.put("ethnicity", ethnicity);	
			    document.put("country", country);			    
			    
			    document.put("registeredon", time);
			    document.put("lastupdated", time);
			    document.put("lastloggedon", "");
			    document.put("isloggedon", 0);
			    
			    // save it into collection named "myCollection"
			    collection.insert(document);
			    ObjectId Lastid = (ObjectId)document.get( "_id" );
		    
		    	document.put("uniqueid", Lastid);

				results.put("response", "OK");
				results.put("description", "User has been registered");		    	
		    	results.put("user", document);
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
	 * Get User
	 * @param id
	 * @throws MongoException 
	 * @throws UnknownHostException 
	 **/
	public static List<DBObject> getUniqueUser(String objId) throws UnknownHostException, MongoException{
		System.out.println("running usercontrol get unique user details");

		//__Prepare response
		List<DBObject> response = new ArrayList<DBObject>();
		
		BasicDBObject results = new BasicDBObject();
		
	    // search query
	    BasicDBObject searchQuery = new BasicDBObject();
	    	searchQuery.put("_id", new ObjectId(objId));
	    
	    List<DBObject> uniqueUser = searchUsers(searchQuery);
	    
			if(uniqueUser.size()>0 ){
				results.put("response", "OK");
				results.put("description", "The specific user has been found");		    	
				results.put("user", uniqueUser);
			}
			else
			{
				results.put("response", "FAIL");
				results.put("description", "Failed to specific user, could have been deleted");	    	
			}	    
			response.add(results);
	    
		return response;
	}	
	
	
	/**
	 * Get User List
	 * @param id
	 * @throws MongoException 
	 * @throws UnknownHostException 
	 **/
	public static List<DBObject> searchUsers(BasicDBObject searchQuery) throws UnknownHostException, MongoException{
		System.out.println("running usercontrol get user");
		//__Prepare result
		List<DBObject> results = new ArrayList<DBObject>();
		
		//_getCollection
		DBCollection collection = MongoApp.getCollection();
		
	    DBCursor cursor = collection.find(searchQuery);
		    
        // loop over the cursor and display the result
    	while (cursor.hasNext()) {
	    	results.add(cursor.next());
	    }    		    
	    return results;
	}	
	
	/**
	 * Delete User
	 * @param id
	 * @throws MongoException 
	 * @throws UnknownHostException 
	 **/	
	public static BasicDBObject deleteUser(String objId) throws UnknownHostException, MongoException{

		//__Prepare result
		BasicDBObject results = new BasicDBObject();
		
	    BasicDBObject obj = new BasicDBObject();
	    	obj.put("_id", new ObjectId(objId));

			//_getCollection
			MongoApp.deleteCollectionEntry(obj);

			results.put("response", "OK");
			results.put("description", "User has been deleted");

		return results;
	}

	
	/**
	 * Logout User
	 * @param id
	 * @throws MongoException 
	 * @throws UnknownHostException 
	 **/
	public static List<DBObject> logoutUser(String objId, HttpSession session) throws UnknownHostException, MongoException {
			System.out.println("logout controller");		
			//__Prepare response
			List<DBObject> response = new ArrayList<DBObject>();

			Boolean isLoggedIn = false;
			
			BasicDBObject results = new BasicDBObject();
			//__check if these required values are empty
			if(CommonUtils.checkLoggedIn(objId)){
				isLoggedIn = true;
				results.put("userId", objId);
			}		
			
			if(isLoggedIn){		
				//_getCollection
				DBCollection collection = MongoApp.getCollection();
			    
			    //get time
			    Date now = new Date();
				BasicDBObject time = new BasicDBObject("ts", now);
				
				 //get object changes with new values
			    BasicDBObject change = new BasicDBObject();
					change.put("lastloggedon", time);
					change.put("isloggedon", 0);
				
				//place the changes into one set object	
				BasicDBObject changes = new BasicDBObject();
					changes.put("$set", change);
		
					//if the search finds a result update and log them in
					
						collection.update(new BasicDBObject().append("_id", new ObjectId(objId)), changes);
					
						results.put("response", "OK");
						results.put("description", "User has logged out");
						System.out.println("logout");
			    		//unset session
			    		String sessionVariable = "loggedid";
			    		removeSessionVar(session, sessionVariable);  						
						
			}
			else
			{
				results.put("response", "FAIL");
				results.put("description", "No record of the user being logged in");		
			}
			
			response.add(results);
			return response;
		}

	public static List<DBObject> editUser(
			String id,
			String username,
			String emailaddress,
			String password,
			String gender,
			String birthyear,
			String birthmonth,
			String birthday,
			String ethnicity,
			String country
				) throws UnknownHostException, MongoException
		{
		System.out.println("running usercontrol edit  user");
		
		//__Prepare response
		List<DBObject> response = new ArrayList<DBObject>();

			//_getCollection
			DBCollection collection = MongoApp.getCollection();
			
			//Time
			Date now = new Date();
			BasicDBObject time = new BasicDBObject("ts", now);			

		    BasicDBObject results = new BasicDBObject();
			
		    Boolean isValidInputs = true;

		    //_validate inputs

			//__check if email already exists in the database
			
		    /*
		    if(!CommonUtils.isEmailUnique(emailaddress)){
				isValidInputs = false;
				results.put("error", "Email address was not unique");
			}
			*/
		    
			//__check if these required values are empty
			if(
				CommonUtils.isEmpty(username) ||
				CommonUtils.isEmpty(emailaddress) ||
				CommonUtils.isEmpty(password)
			){
				isValidInputs = false;
				results.put("error", "At least one required field was blank");
			}
			
			if(
				!CommonUtils.isValidDate(birthyear+birthmonth+birthday)
			){
				isValidInputs = false;
				results.put("error", "The birthdate is not valid");
			}
			

			if(
				!CommonUtils.isOlderThan18(birthyear+birthmonth+birthday)
			){
				isValidInputs = false;
				results.put("error", "You need to be over 18 to access this site");
			}
			
		    
		    if(isValidInputs){
		    
		    	System.out.println("valid inputs");
				 //get object changes with new values
			    BasicDBObject change = new BasicDBObject();
					change.put("lastupdated", time);
					change.put("username", username);
					change.put("emailaddress", emailaddress);
					change.put("password", password);			    
					change.put("gender", gender);	;
					change.put("birthdate", birthyear+birthmonth+birthday);
					change.put("ethnicity", ethnicity);	
					change.put("country", country);							
				
				//place the changes into one set object	
				BasicDBObject changes = new BasicDBObject();
					changes.put("$set", change);

				
				collection.update(new BasicDBObject().append("_id", new ObjectId(id)), changes);			    
			    
			    
				results.put("response", "OK");
				results.put("description", "User has been edited");
		    }
		    else
		    {
				results.put("response", "FAIL");
				results.put("description", "Failed to edit user");	    	
		    }
		    
		    response.add(results);
		return response;
	}
	
	
	
	
	
	
	
	
	
	
	public static List<DBObject> editUserBasic(
			String id,
			String seeking
				) throws UnknownHostException, MongoException
		{
		System.out.println("running usercontrol edit user basic");
		
		//__Prepare response
		List<DBObject> response = new ArrayList<DBObject>();

			//_getCollection
			DBCollection collection = MongoApp.getCollection();

			//Time
			Date now = new Date();
			BasicDBObject time = new BasicDBObject("ts", now);
			
		    BasicDBObject results = new BasicDBObject();
			
		    Boolean isValidInputs = true;
		    
			//__check if these required values are empty
			if(
				CommonUtils.isEmpty(seeking)
			){
				isValidInputs = false;
				results.put("error", "At least one required field was blank");
			}
		    
		    if(isValidInputs){
		    
		    	System.out.println("valid inputs");
				 //get object changes with new values
			    BasicDBObject change = new BasicDBObject();
					change.put("lastupdated", time);
					change.put("seeking", seeking);					
				
				//place the changes into one set object	
				BasicDBObject changes = new BasicDBObject();
					changes.put("$set", change);

				
				collection.update(new BasicDBObject().append("_id", new ObjectId(id)), changes);			    
			    
			    
				results.put("response", "OK");
				results.put("description", "User has been edited basic");
		    }
		    else
		    {
				results.put("response", "FAIL");
				results.put("description", "Failed to edit user basic");	    	
		    }
		    
		    response.add(results);
		return response;
	}	
	
	
	
	
	
}