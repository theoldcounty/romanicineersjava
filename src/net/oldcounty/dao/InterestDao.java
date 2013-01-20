package net.oldcounty.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.bson.types.ObjectId;

import net.oldcounty.model.Interests;

import com.mongo.app.MongoApp;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

/**
 * InterestDao
 * @param interest
 * Used to set and get interest pie charts per user
 **/
public class InterestDao {

	static String collectionName = "interestCollection";
	
	public static List<DBObject> addInterest(Interests interest){
		
		List<DBObject> response = new ArrayList<DBObject>();
		    	
		//_getCollection
		DBCollection collection = null;
		try {
			collection = MongoApp.getCollection(collectionName);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		
	    BasicDBObject document = new BasicDBObject();
	    BasicDBObject dataResults = new BasicDBObject();

        	if(interest.getResults()!=null){
	    		for (Entry<String, Integer> entry : interest.getResults().entrySet()) {
	    			dataResults.put(entry.getKey(), entry.getValue());
	    		}
	    	}
	    	document.put("uid", interest.getUserId());
	    	document.put("chartType", interest.getName());
	    	document.put("dataResults", dataResults);

	    collection.insert(document);
	    ObjectId lastid = (ObjectId)document.get( "_id" );
	    
		BasicDBObject results = new BasicDBObject();
		
		results.put("response", "OK");
		results.put("lastId", lastid);
		response.add(results);	
    	
		return response;
	}
	
	
	/**
	 * Get UserInterests
	 * @param id
	 * @throws MongoException
	 * @throws UnknownHostException
	 **/
	public static List<DBObject> getInterest(Interests interest){
		
		//__Prepare response
		List<DBObject> response = new ArrayList<DBObject>();
		
		BasicDBObject results = new BasicDBObject();

	    // search query
	    BasicDBObject searchQuery = new BasicDBObject();
	    	//searchQuery.put("uid", interest.getUserId());
	    	searchQuery.put("chartType", interest.getName());
	    	
	    List<DBObject> uniqueInterests = null;
		try {
			uniqueInterests = MongoApp.searchCollections(searchQuery, collectionName);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		
		if(uniqueInterests.size()>0 ){
			results.put("response", "OK");
			results.put("description", "The specific user has been found");
			results.put("dataResults", uniqueInterests.get(0).get("dataResults"));
		}
		else
		{
			results.put("response", "FAIL");
			results.put("description", "Failed to get interest list");
		}
		response.add(results);

		return response;
	}


}