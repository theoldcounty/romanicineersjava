package net.oldcounty.manager;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.oldcounty.controller.CommonUtils;
import net.oldcounty.dao.PersonDao;
import net.oldcounty.model.Person;

import org.bson.types.ObjectId;

import com.mongo.app.MongoApp;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

public class PersonManager {

	PersonDao personDao;
	
	public List<DBObject> registerUser(Person person){
		//__Prepare response
		
				System.out.println("register user using person control");    			
				List<DBObject> response = new ArrayList<DBObject>();

				    BasicDBObject results = new BasicDBObject();
				    Boolean isValidInputs = true;

				    //_validate inputs
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
				    	results.put("error", "Email address was empty");				    	
				    }
				    
					//__check if passwords match
				    if(!CommonUtils.isEmpty(person.getPassword()) && !CommonUtils.isEmpty(person.getConfirmpassword())){
					    if(!CommonUtils.doPasswordsMatch(person.getPassword(), person.getConfirmpassword())){
							isValidInputs = false;
							results.put("error", "Passwords do not match");
						}
				    }
				    else{
				    	results.put("error", "One or both of the passwords were blank");
				    }

					//__check if these required values are empty
					if(
						CommonUtils.isEmpty(person.getUsername()) ||
						CommonUtils.isEmpty(person.getEmailaddress()) ||
						CommonUtils.isEmpty(person.getPassword())
					){
						isValidInputs = false;
						results.put("error", "At least one required field was blank");
					}

					if(
						!CommonUtils.isEmpty(person.getBirthyear()) && 
						!CommonUtils.isEmpty(person.getBirthmonth()) && 
						!CommonUtils.isEmpty(person.getBirthday())
					){
						if(
							!CommonUtils.isValidDate(person.getBirthyear()+person.getBirthmonth()+person.getBirthday())
						){
							isValidInputs = false;
							results.put("error", "The birthdate is not valid");
						}
						
						if(
								!CommonUtils.isOlderThan18(person.getBirthyear()+person.getBirthmonth()+person.getBirthday())
						){
							isValidInputs = false;
							results.put("error", "You need to be over 18 to access this site");
						}						
					}
					else{
						results.put("error", "The birthdate is incomplete");
					}

				    if(isValidInputs){				    	
				    	List<DBObject> latestUser = PersonDao.registerUser(person);
				    	
						results.put("response", "OK");
						results.put("description", "User has been registered");
				    	results.put("user", latestUser);
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
	 * Add User Personality
	 * @param id
	 * @throws MongoException
	 * @throws UnknownHostException
	 **/
	public static List<DBObject> addUserPersonality(
					ObjectId lastid,
				    Integer[] personality
				) throws UnknownHostException, MongoException
		{

		//__Prepare response
		List<DBObject> response = new ArrayList<DBObject>();

			//_getCollection
			DBCollection collection = MongoApp.getCollection("userPersonality");

			BasicDBObject results = new BasicDBObject();
		    Boolean isValidInputs = true;

		    if(isValidInputs){
			    // create a document to store attributes
			    BasicDBObject document = new BasicDBObject();
			    BasicDBObject personaltraits = new BasicDBObject();

			    /*add to new collection personality*/
					String[] personalTypes ={"confidence","reasoning","emotion","daring","attachment","sensitivity","comedy"};
					Integer j = 0;
					for(Integer traits : personality)
			    	{
			    		System.out.println("traits "+traits);
			    		personaltraits.put(personalTypes[j], traits);
			    		j++;
			    	}

				    document.put("_id", lastid);
				    document.put("personality", personaltraits);
				/*add to new collection personality*/

			    // save it into collection named "userPersonality"
			    collection.insert(document);

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
	 * Add User Personality
	 * @param id
	 * @throws MongoException
	 * @throws UnknownHostException
	 **/
	public static List<DBObject> addUserPieChart(
				ObjectId lastid,
				String chartType,
				Map<String,Integer> interests
			) throws UnknownHostException, MongoException
		{
			System.out.println("add a CHART");

			//__Prepare response
			List<DBObject> response = new ArrayList<DBObject>();


			//_getCollection
			DBCollection collection = MongoApp.getCollection("userPieCharts");

			BasicDBObject results = new BasicDBObject();

		    Boolean isValidInputs = true;

		    if(isValidInputs){
			    // create a document to store attributes
			    BasicDBObject document = new BasicDBObject();
			    BasicDBObject dataResults = new BasicDBObject();

		        	if(interests!=null){
			    		for (Entry<String, Integer> entry : interests.entrySet()) {
			    			dataResults.put(entry.getKey(), entry.getValue());
			    		}
			    	}
			    	document.put("uid", lastid);
			    	document.put("chartType", chartType);
			    	document.put("dataResults", dataResults);

			    // save it into collection
			    collection.insert(document);

				results.put("response", "OK");
				results.put("description", "Interests have been recorded");
		    	results.put("user", document);
		    }
		    else
		    {
				results.put("response", "FAIL");
				results.put("description", "Failed record Interests");
		    }

		    response.add(results);

		return response;
	}
	
	/**
	 * Add User Bubble
	 * @param id
	 * @throws MongoException
	 * @throws UnknownHostException
	 **/
	public static List<DBObject> addUserBubbleChart(
				ObjectId lastid,
				String chartType,
				Map<String,Integer> interests
			) throws UnknownHostException, MongoException
		{
			System.out.println("add a bubble CHART");

			//__Prepare response
			List<DBObject> response = new ArrayList<DBObject>();


			//_getCollection
			DBCollection collection = MongoApp.getCollection("userBubbleCharts");


			BasicDBObject results = new BasicDBObject();

		    Boolean isValidInputs = true;

		    if(isValidInputs){
			    // create a document to store attributes
			    BasicDBObject document = new BasicDBObject();
			    BasicDBObject dataResults = new BasicDBObject();

		        	if(interests!=null){
			    		for (Entry<String, Integer> entry : interests.entrySet()) {
			    			dataResults.put(entry.getKey(), entry.getValue());
			    		}
			    	}
			    	document.put("uid", lastid);
			    	document.put("chartType", chartType);
			    	document.put("dataResults", dataResults);

			    // save it into collection
			    collection.insert(document);

				results.put("response", "OK");
				results.put("description", "Interests have been recorded");
		    	results.put("user", document);
		    }
		    else
		    {
				results.put("response", "FAIL");
				results.put("description", "Failed record Interests");
		    }

		    response.add(results);

		return response;
	}

	
	public void setPersonDao(PersonDao personDao){
		this.personDao = personDao;
	}
}
