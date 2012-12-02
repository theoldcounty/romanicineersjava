package net.oldcounty.manager;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
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
				List<DBObject> response = new ArrayList<DBObject>();

					//_getCollection
					DBCollection collection = null;
					try {
						collection = MongoApp.getCollection("myCollection");
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MongoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					Date now = new Date();
					BasicDBObject time = new BasicDBObject("ts", now);

				    BasicDBObject results = new BasicDBObject();

				    Boolean isValidInputs = true;

				    //_validate inputs

					//__check if email already exists in the database
					try {
						if(!CommonUtils.isEmailUnique(person.getEmailaddress())){
							isValidInputs = false;
							results.put("error", "Email address was not unique");
						}
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MongoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					//__check if passwords match
					if(!CommonUtils.doPasswordsMatch(person.getPassword(), person.getConfirmpassword())){
						isValidInputs = false;
						results.put("error", "Passwords do not match");
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

				    if(isValidInputs){
					    // create a document to store attributes
					    BasicDBObject document = new BasicDBObject();


					    document.put("realname", person.getRealname());
					    document.put("username", person.getUsername());
					    document.put("emailaddress", person.getEmailaddress());
					    document.put("password", person.getPassword());

					    document.put("whichscreenname", person.getWhichscreenname());

					    document.put("about", person.getAbout());

					    document.put("birthdate", person.getBirthyear()+person.getBirthmonth()+person.getBirthday());

					    document.put("country", person.getCountry());

					    document.put("gender", person.getGender());
					    document.put("ethnicity", person.getEthnicity());
					    document.put("kindofrelationship", person.getKindofrelationship());
					    document.put("bodytype", person.getBodytype());
					    document.put("haircolor", person.getHaircolor());
					    document.put("eyecolor", person.getEyecolor());
					    document.put("children", person.getChildren());
					    document.put("education", person.getEducation());
					    document.put("occupation", person.getOccupation());

					    document.put("latitude", person.getLatitude());
					    document.put("longitude", person.getLongitude());

					    document.put("languages", person.getLanguages());

					    document.put("goal1", person.getGoal1());
					    document.put("goal2", person.getGoal2());
					    document.put("goal3", person.getGoal3());

					    document.put("registeredon", time);
					    document.put("lastupdated", time);
					    document.put("lastloggedon", "");
					    document.put("isloggedon", 0);

					    // save it into collection named "myCollection"
					    collection.insert(document);
					    ObjectId lastid = (ObjectId)document.get( "_id" );


					    /*check file*/
					   // MultipartFile multipartFile = fileUpload.getFile();
					    //System.out.println("file "+file+"<br>");



					    /*add to new collection personality*/
					    List<DBObject> responseUserPersonality = null;
						try {
							responseUserPersonality = addUserPersonality(
									lastid,
									person.getPersonality()
							);
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (MongoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					    System.out.println("userPersonality "+responseUserPersonality+"<br>");
						/*add to new collection personality*/


					    /*add to new interests chart*/
					    Map<String,Integer> userInterests = new LinkedHashMap<String,Integer>();
				    	if(person.getInterests()!=null){
				    		int index = 0;
				    		for(String interest : person.getInterests())
					    	{
					    		userInterests.put(interest,person.getInterestknobs()[index]);
					    		index++;
					    	}
				    	}
					    List<DBObject> responseInterestsChart = null;
						try {
							responseInterestsChart = addUserPieChart(
								lastid,
								"interests",
								userInterests
							);
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (MongoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					    System.out.println("userInterests "+responseInterestsChart+"<br>");
					    /*add to new interests chart*/





					    /*add to new seeking chart*/
					    Map<String,Integer> userSeeking = new LinkedHashMap<String,Integer>();
				    	if(person.getSeekings()!=null){
				    		int index = 0;
				    		for(String seeking : person.getSeekings())
					    	{
				    			userSeeking.put(seeking,person.getSeekingknobs()[index]);
					    		index++;
					    	}
				    	}
					    List<DBObject> responseSeekingChart = null;
						try {
							responseSeekingChart = addUserPieChart(
								lastid,
								"seeking",
								userSeeking
							);
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (MongoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					    System.out.println("userSeeking "+responseSeekingChart+"<br>");
					    /*add to new seeking chart*/




					    /*add to new visiting chart*/
					    Map<String,Integer> userVisiting = new LinkedHashMap<String,Integer>();
				    	if(person.getVisitings()!=null){
				    		int index = 0;
				    		for(String visiting : person.getVisitings())
					    	{
				    			userVisiting.put(visiting,person.getVisitingknobs()[index]);
					    		index++;
					    	}
				    	}
					    List<DBObject> responseVisitingChart = null;
						try {
							responseVisitingChart = addUserBubbleChart(
								lastid,
								"visiting",
								userVisiting
							);
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (MongoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					    System.out.println("userVisiting "+responseVisitingChart+"<br>");
					    /*add to new visiting chart*/


				    	document.put("uniqueid", lastid);

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
