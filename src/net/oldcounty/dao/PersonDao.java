package net.oldcounty.dao;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import net.oldcounty.model.Person;

import com.mongo.app.MongoApp;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

/**
 * PersonDao
 * @param person
 * Used to set and get a user
 **/
public class PersonDao {

	/*Register Methods*/	
	public static List<DBObject> registerUser(Person person){
		
		List<DBObject> response = new ArrayList<DBObject>();
		    	
		//_getCollection
		DBCollection collection = null;
		try {
			collection = MongoApp.getCollection("myCollection");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		
		Date now = new Date();
		BasicDBObject time = new BasicDBObject("ts", now);
		
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
	    document.put("personality", person.getPersonality());	    

	    document.put("registeredon", time);
	    document.put("lastupdated", time);
	    document.put("lastloggedon", "");
	    document.put("isloggedon", 0);

	    // save it into collection named "myCollection"
	    collection.insert(document);
	    ObjectId lastid = (ObjectId)document.get( "_id" );
		
		BasicDBObject results = new BasicDBObject();
		
		results.put("response", "OK");
		results.put("lastId", lastid);
		response.add(results);	    
	    
		return response;
	}
	
	/*Edit Methods*/	
	public static List<DBObject> editUser(Person person, String section){
		
		List<DBObject> response = new ArrayList<DBObject>();
		    	
		//_getCollection
		DBCollection collection = null;
		try {
			collection = MongoApp.getCollection("myCollection");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		
		Date now = new Date();
		BasicDBObject time = new BasicDBObject("ts", now);
		
	    // create a document to store attributes
	    BasicDBObject document = new BasicDBObject();

	    //_account
	    if(section.equals("account")){
		    document.put("realname", person.getRealname());
		    document.put("username", person.getUsername());
		    document.put("emailaddress", person.getEmailaddress());
		    document.put("password", person.getPassword());
		    document.put("whichscreenname", person.getWhichscreenname());
		    document.put("birthdate", person.getBirthyear()+person.getBirthmonth()+person.getBirthday());
	    }
	    
	    //_about
	    if(section.equals("about")){
	    	document.put("about", person.getAbout());
		    /*
		    document.put("goal1", person.getGoal1());
		    document.put("goal2", person.getGoal2());
		    document.put("goal3", person.getGoal3());
		    */
	    }
	    
	    //_personality
	    if(section.equals("personality")){
	    	document.put("personality", person.getPersonality());
	    }
	    
	    //_physical
	    if(section.equals("physical")){
	    	document.put("bodytype", person.getBodytype());
	    	document.put("haircolor", person.getHaircolor());
	    	document.put("eyecolor", person.getEyecolor());
	    	document.put("children", person.getChildren());
	    	document.put("languages", person.getLanguages());
	    	document.put("education", person.getEducation());
	    	document.put("occupation", person.getOccupation());
	    	document.put("ethnicity", person.getEthnicity());	    	
	    	document.put("country", person.getCountry());
	    	document.put("kindofrelationship", person.getKindofrelationship());
	    	document.put("languages", person.getLanguages());
	    	document.put("gender", person.getGender());		    	
		    document.put("latitude", person.getLatitude());
		    document.put("longitude", person.getLongitude());
	    }
	    
	    ObjectId objId = ObjectId.massageToObjectId(person.getUid());
	    BasicDBObject obj = new BasicDBObject();
    		obj.put("_id", objId);
    	
    		BasicDBObject set = new BasicDBObject();
    			set.put("$set", document);
    		
	    collection.update(obj, set);
	    
	    ObjectId lastid = (ObjectId)document.get( "_id" );
		
		BasicDBObject results = new BasicDBObject();
		
		results.put("response", "OK");
		results.put("lastId", lastid);
		response.add(results);	    
	    
		return response;
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
			MongoApp.deleteCollectionEntry(obj, "myCollection");

			results.put("response", "OK");
			results.put("description", "User has been deleted");

		return results;
	}
	
	/**
	 * Get User
	 * @param id
	 * @throws MongoException
	 * @throws UnknownHostException
	 **/
	public static List<DBObject> getUniqueUser(BasicDBObject searchQuery, BasicDBObject excludeFields) throws UnknownHostException, MongoException{
		//__Prepare response
		List<DBObject> response = new ArrayList<DBObject>();
		BasicDBObject results = new BasicDBObject();

	    List<DBObject> uniqueUser = MongoApp.searchCollections(searchQuery, excludeFields, "myCollection");

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
	 * Get User Age
	 * @param birthdate
	 **/
	public static Integer getAge(String birthdate) {

		int yearDOB = Integer.parseInt(birthdate.substring(0, 4));
		int monthDOB = Integer.parseInt(birthdate.substring(4, 6));
		int dayDOB = Integer.parseInt(birthdate.substring(6, 8));

		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		java.util.Date date = new java.util.Date();
		int thisYear = Integer.parseInt(dateFormat.format(date));

		dateFormat = new SimpleDateFormat("MM");
		date = new java.util.Date();
		int thisMonth = Integer.parseInt(dateFormat.format(date));

		dateFormat = new SimpleDateFormat("dd");
		date = new java.util.Date();
		int thisDay = Integer.parseInt(dateFormat.format(date));

		int age = thisYear - yearDOB;

		if(thisMonth < monthDOB){
			age = age - 1;
		}

		if(thisMonth == monthDOB && thisDay < dayDOB){
			age = age - 1;
		}

	    return age;
	}
	
	
	/**
	 * Get UserPersonality
	 * @param id
	 * @throws MongoException
	 * @throws UnknownHostException
	 **/
	public static List<DBObject> getPersonality(String objId) throws UnknownHostException, MongoException{
		//__Prepare response
		List<DBObject> response = new ArrayList<DBObject>();
		BasicDBObject results = new BasicDBObject();

	    // search query
	    BasicDBObject searchQuery = new BasicDBObject();
	    	searchQuery.put("_id", new ObjectId(objId));

    	//skip 0
    	//limit 1
	    List<DBObject> uniquePersonality = searchUsers(searchQuery, 0, 1, "myCollection");
		if(uniquePersonality.size()>0 ){
			results.put("response", "OK");
			results.put("description", "The specific user has been found");
			results.put("personality", uniquePersonality.get(0).get("personality"));
		}
		else
		{
			results.put("response", "FAIL");
			results.put("description", "Failed to get personality traits");
		}
		response.add(results);

		return response;
	}	
	
	/**
	 * Get Members
	 * @param id
	 * @throws MongoException
	 * @throws UnknownHostException
	 **/
	public static List<DBObject> getMembers(
			Integer skips,
			Integer limits,
			BasicDBObject filter
		) throws UnknownHostException, MongoException{
		
		//__Prepare response
		List<DBObject> response = new ArrayList<DBObject>();
		BasicDBObject results = new BasicDBObject();

	    // search query
		
	    //BasicDBObject searchQuery = new BasicDBObject();
	    	//filter online
	    	//filter ethnicity
	    	
    	//skip 0
    	//limit 1
	    List<DBObject> users = searchUsers(filter, skips, limits, "myCollection");
	    
	    //users.put("gallery",galleryResponse);
		if(users.size() > 0){
			results.put("response", "OK");
			results.put("description", "Found list of users");
			results.put("users", users);
		}
		else
		{
			results.put("response", "FAIL");
			results.put("description", "Failed to get a list of users");
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
	public static List<DBObject> searchUsers(
			BasicDBObject searchQuery,
			Integer skips,
			Integer limits,
			String collectionName
		) throws UnknownHostException, MongoException{

		//__Prepare result
		List<DBObject> results = new ArrayList<DBObject>();

		//_getCollection
		DBCollection collection = MongoApp.getCollection(collectionName);
		//1000-1100th elements
	    DBCursor cursor = collection.find(searchQuery).skip(skips).limit(limits);

        // loop over the cursor and display the result
    	while (cursor.hasNext()) {
	    	//results.add(cursor.next());
    		

    		DBObject personData = cursor.next();
    		String userId = personData.get("_id").toString();
    		
    		Object[] galleryResponse = ImageDao.getUsersImages(userId).toArray();
    		personData.put("gallery", galleryResponse);
    		
	    	results.add(personData); 		
	    }

	    return results;
	}

	public static List<DBObject> setForgotPassword(Person person) {
		
		List<DBObject> response = new ArrayList<DBObject>();
		    	
		//_getCollection
		DBCollection collection = null;
		try {
			collection = MongoApp.getCollection("myCollection");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		
		Date now = new Date();
		BasicDBObject time = new BasicDBObject("ts", now);
		
	    // create a document to store attributes
	    BasicDBObject document = new BasicDBObject();
	    	document.put("password", person.getPassword());
	    
	    ObjectId objId = ObjectId.massageToObjectId(person.getUid());
	    BasicDBObject obj = new BasicDBObject();
    		obj.put("_id", objId);
    	
    		BasicDBObject set = new BasicDBObject();
    			set.put("$set", document);
    		
	    collection.update(obj, set);
	    
	    ObjectId lastid = (ObjectId)document.get( "_id" );
		
		BasicDBObject results = new BasicDBObject();
		
		results.put("response", "OK");
		results.put("lastId", lastid);
		response.add(results);	    
	    
		return response;
	}

	/*Login Methods*/
	public static List<DBObject> loginUser(Person person) {
		
		List<DBObject> response = new ArrayList<DBObject>();
		    	
		//_getCollection
		DBCollection collection = null;
		try {
			collection = MongoApp.getCollection("myCollection");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		
		Date now = new Date();
		BasicDBObject time = new BasicDBObject("ts", now);
		
	    // create a document to store attributes
	    BasicDBObject document = new BasicDBObject();
		    document.put("lastloggedon", time);
		    document.put("isloggedon", 1);
	    	
	    ObjectId objId = ObjectId.massageToObjectId(person.getUid());
	    BasicDBObject obj = new BasicDBObject();
    		obj.put("_id", objId);
    	
    		BasicDBObject set = new BasicDBObject();
    			set.put("$set", document);
    		
	    collection.update(obj, set);
	    
		BasicDBObject results = new BasicDBObject();
			results.put("response", "OK");
			results.put("objId", person.getUid());
		
		response.add(results);	    
	    
		return response;
	}
	
	/*Logout Methods*/
	public static List<DBObject> logoutUser(Person person){
		
		List<DBObject> response = new ArrayList<DBObject>();
		    	
		//_getCollection
		DBCollection collection = null;
		try {
			collection = MongoApp.getCollection("myCollection");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		
		Date now = new Date();
		BasicDBObject time = new BasicDBObject("ts", now);
		
	    // create a document to store attributes
	    BasicDBObject document = new BasicDBObject();
		    document.put("lastloggedon", time);
		    document.put("isloggedon", 0);
	    	
	    ObjectId objId = ObjectId.massageToObjectId(person.getUid());
	    BasicDBObject obj = new BasicDBObject();
    		obj.put("_id", objId);
    	
    		BasicDBObject set = new BasicDBObject();
    			set.put("$set", document);
    		
	    collection.update(obj, set);
	    
		BasicDBObject results = new BasicDBObject();
			results.put("response", "OK");
			results.put("lastId", person.getUid());
		
		response.add(results);	    
	    
		return response;		
	}
	
	/*Ban Methods*/
	public static void banUser(Person person){
		
	}
}