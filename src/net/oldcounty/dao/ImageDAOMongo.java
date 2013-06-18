package net.oldcounty.dao;

import java.net.UnknownHostException;
import java.util.Collection;

import net.oldcounty.model.UserImage;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class ImageDAOMongo{
	MongoClient mongoClient;
	private DB db;
	private static ImageDAOMongo instance=null;
	
	public static ImageDAOMongo getInstance(){
		if(instance==null){
			instance=new ImageDAOMongo();
		}
		return instance;
	}
	
	private ImageDAOMongo() {
		try {
			mongoClient = new MongoClient("localhost");
			db = mongoClient.getDB("mydb");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public UserImage getUserImage(String id) {
		DBCollection coll = db.getCollection("images");
		BasicDBObject query = new BasicDBObject();
		DBObject searchById = new BasicDBObject("_id", new ObjectId(id));
		DBObject dbObject = coll.findOne(searchById);				
		UserImage userImage=new UserImage();
		userImage.setId(id);
		userImage.setName( (String) dbObject.get("name"));
		
		
		userImage.setFormat( (String) dbObject.get("format"));
		userImage.setImage( (byte[]) dbObject.get("image"));
		System.out.println("file name:"+userImage.getName());
		coll.findOne();
		return userImage;
	}

	
	public void saveUserImage(UserImage userImage) {
		DBCollection coll = db.getCollection("images");
		BasicDBObject doc = new BasicDBObject("name", userImage.getName())
				.append("format", userImage.getFormat())
				.append("userid", userImage.getUserId())
				.append("image", userImage.getImage());
		coll.insert(doc);
		coll.save(doc);
	}

}
