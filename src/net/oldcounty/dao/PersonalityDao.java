package net.oldcounty.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import net.oldcounty.model.Personality;

import com.mongo.app.MongoApp;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

/**
 * PersonalityDao
 * @param personality
 * Used to set and get personality sliders per user
 **/
public class PersonalityDao {

	public static List<DBObject> addUserPersonality(Personality personality){
		
		List<DBObject> response = new ArrayList<DBObject>();
		    	
		//_getCollection
		DBCollection collection = null;
		try {
			collection = MongoApp.getCollection("userPersonality");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		
	    BasicDBObject document = new BasicDBObject();
	    BasicDBObject personaltraits = new BasicDBObject();

	    /*add to new collection personality*/
			String[] personalTypes ={"confidence","reasoning","emotion","daring","attachment","sensitivity","comedy"};
			Integer j = 0;
			for(Integer traits : personality.getPersonality())
	    	{
	    		System.out.println("traits "+traits);
	    		personaltraits.put(personalTypes[j], traits);
	    		j++;
	    	}

		    document.put("_id", personality.getUid());
		    document.put("personality", personaltraits);
		/*add to new collection personality*/

	    // save it into collection named "userPersonality"
	    collection.insert(document);	    
    	
		return response;
	}
	
	
	/**
	 * Get UserPersonality
	 * @param id
	 * @throws MongoException
	 * @throws UnknownHostException
	 **/
	public static List<DBObject> getUserPersonality(Personality personality){
		System.out.println("get personality");

		//__Prepare response
		List<DBObject> response = new ArrayList<DBObject>();

		BasicDBObject results = new BasicDBObject();

	    // search query
	    BasicDBObject searchQuery = new BasicDBObject();
	    	searchQuery.put("_id", personality.getUid());

	    List<DBObject> uniquePersonality = null;
		try {
			uniquePersonality = MongoApp.searchCollections(searchQuery, "userPersonality");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}

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
	
}