package net.oldcounty.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import net.oldcounty.model.Personality;

import com.mongo.app.MongoApp;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

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
	
}
