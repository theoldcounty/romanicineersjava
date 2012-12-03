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
	
}
