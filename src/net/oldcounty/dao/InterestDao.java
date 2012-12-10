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

	public static List<DBObject> addUserPieChart(Interests interest){
		
		List<DBObject> response = new ArrayList<DBObject>();
		    	
		//_getCollection
		DBCollection collection = null;
		try {
			collection = MongoApp.getCollection("userPieCharts");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		
	    BasicDBObject document = new BasicDBObject();
	    BasicDBObject dataResults = new BasicDBObject();

        	if(interest.getDataResults()!=null){
	    		for (Entry<String, Integer> entry : interest.getDataResults().entrySet()) {
	    			dataResults.put(entry.getKey(), entry.getValue());
	    		}
	    	}
	    	document.put("uid", interest.getUid());
	    	document.put("chartType", interest.getChartType());
	    	document.put("dataResults", dataResults);

	    // save it into collection named "interest"
	    collection.insert(document);
	    ObjectId lastid = (ObjectId)document.get( "_id" );
		
		BasicDBObject results = new BasicDBObject();
		
		results.put("response", "OK");
		results.put("lastId", lastid);
		response.add(results);	    
	    
    	document.put("uniqueid", lastid);    	
    	
		return response;
	}
	
	
	/**
	 * Get UserInterests
	 * @param id
	 * @throws MongoException
	 * @throws UnknownHostException
	 **/
	public static List<DBObject> getUserPieChart(Interests interest){
		System.out.println("get interests");

		//__Prepare response
		List<DBObject> response = new ArrayList<DBObject>();

		BasicDBObject results = new BasicDBObject();

	    // search query
	    BasicDBObject searchQuery = new BasicDBObject();
	    	searchQuery.put("uid", interest.getUid());
	    	searchQuery.put("chartType", interest.getChartType());

	    List<DBObject> uniqueInterests = null;
		try {
			uniqueInterests = MongoApp.searchCollections(searchQuery, "userPieCharts");
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