package net.oldcounty.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;


import net.oldcounty.model.UserImage;

import com.mongo.app.MongoApp;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

/**
 * ImageDao
 * @param image
 * Used to set and get images per user
 **/
public class ImageDao {

	static String collectionName = "imageCollection";
	private static ImageDao instance=null;
	
	public static ImageDao getInstance(){
		if(instance==null){
			instance=new ImageDao();
		}
		return instance;
	}	

	public UserImage getImage(String id) {
		//DBCollection coll = db.getCollection("images");
		
		//_getCollection
		DBCollection collection = null;
		try {
			collection = MongoApp.getCollection(collectionName);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}		
		
		//BasicDBObject query = new BasicDBObject();
		DBObject searchById = new BasicDBObject("_id", new ObjectId(id));
		DBObject dbObject = collection.findOne(searchById);				
		UserImage userImage=new UserImage();
		userImage.setId(id);
		userImage.setName( (String) dbObject.get("name"));
		userImage.setFormat( (String) dbObject.get("format"));
		userImage.setImage( (byte[]) dbObject.get("image"));
		
		//System.out.println("file name:"+userImage.getName());
		collection.findOne();
		return userImage;
	}

	

	public static List<DBObject> getUsersImages(String userId) {
		//DBCollection coll = db.getCollection("images");
		
		//_getCollection
		DBCollection collection = null;
		try {
			collection = MongoApp.getCollection(collectionName);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}		
		
		//__Prepare result
		List<DBObject> results = new ArrayList<DBObject>();
		
		DBObject searchById = new BasicDBObject("userId", userId);
		DBCursor cursor = collection.find(searchById);
		
        // loop over the cursor and display the result
    	while (cursor.hasNext()) {
    		
    		DBObject localCursor = cursor.next();
    		String galleryImageId = localCursor.get("_id").toString();
    		
    		BasicDBObject imgId = new BasicDBObject();
    			imgId.put("imgId",galleryImageId);
    		
	    	results.add(imgId);
	    }
    		
		return results;
	}
	
	
	
	
	public void saveUserImage(UserImage userImage) {
		//_getCollection
		DBCollection collection = null;
		try {
			collection = MongoApp.getCollection(collectionName);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		
		BasicDBObject document = new BasicDBObject("name", userImage.getName())
				.append("format", userImage.getFormat())
				.append("userId", userImage.getUserId())
				.append("image", userImage.getImage());
		collection.insert(document);
		collection.save(document);
	}

	public UserImage getUserImage(String imageId) {
		// TODO Auto-generated method stub
		
		return null;
	}
}