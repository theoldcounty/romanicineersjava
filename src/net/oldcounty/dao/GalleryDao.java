package net.oldcounty.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import net.oldcounty.model.Gallery;

import com.mongo.app.MongoApp;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

/**
 * GalleryDao
 * @param gallery
 * Used to set and get images from the gallery
 **/
public class GalleryDao {

	public static List<DBObject> addGallery(Gallery gallery){
		
		List<DBObject> response = new ArrayList<DBObject>();
		    	
		//_getCollection
		DBCollection collection = null;
		try {
			collection = MongoApp.getCollection("userGallery");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		
	    BasicDBObject document = new BasicDBObject();

	    	document.put("uid", gallery.getUid());

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
	

	public static List<DBObject> getGallery(){

		//__Prepare response
		List<DBObject> images = new ArrayList<DBObject>();
		BasicDBObject results = new BasicDBObject();

		BasicDBObject result = new BasicDBObject();

			result.put("image1", "/images/Bannan.jpg");
			result.put("image2", "/images/Koala.jpg");
			result.put("image3", "/images/Monkey.jpg");

			results.put("images", result);
			images.add(results);

		return images;
	}	
	
}