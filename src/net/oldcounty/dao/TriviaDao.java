package net.oldcounty.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.bson.types.ObjectId;

import net.oldcounty.model.Trivia;

import com.mongo.app.MongoApp;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

/**
 * TriviaDao
 * @param trivia
 * Used to set and get the bubble charts per user
 **/
public class TriviaDao {

	public static List<DBObject> addBubbleChart(Trivia trivia){
		
		List<DBObject> response = new ArrayList<DBObject>();

		//_getCollection
		DBCollection collection = null;
		try {
			collection = MongoApp.getCollection("userBubbleCharts");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		
		BasicDBObject results = new BasicDBObject();

	    Boolean isValidInputs = true;

	    if(isValidInputs){
		    // create a document to store attributes
		    BasicDBObject document = new BasicDBObject();
		    BasicDBObject dataResults = new BasicDBObject();

	        	if(trivia!=null){
		    		for (Entry<String, Integer> entry : trivia.getTriviaResults().entrySet()) {
		    			dataResults.put(entry.getKey(), entry.getValue());
		    		}
		    	}
		    	document.put("uid", trivia.getUid());
		    	document.put("chartType", trivia.getChartType());
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
	 * Get User Bubble
	 * @param id
	 * @throws MongoException
	 * @throws UnknownHostException
	 **/
	public static List<DBObject> getUserBubbleChart(Trivia trivia){
		
		//__Prepare response
		List<DBObject> response = new ArrayList<DBObject>();

		BasicDBObject results = new BasicDBObject();

	    // search query
	    BasicDBObject searchQuery = new BasicDBObject();
	    	searchQuery.put("uid", trivia.getUid());
	    	searchQuery.put("chartType", trivia.getChartType());

	    List<DBObject> uniqueInterests = null;
		try {
			uniqueInterests = MongoApp.searchCollections(searchQuery, "userBubbleCharts");
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