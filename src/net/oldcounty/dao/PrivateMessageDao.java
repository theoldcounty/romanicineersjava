//package net.oldcounty.dao;
//privateMessageDao.java


/*
 * 
 * insert private message into db
 * 
 * 
 * privateMessageCollection
 * 
 * receipientUserId
 * senderUserId
 * subject
 * message
 * date
 * 
 * get message from db
 * 
 */






package net.oldcounty.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import org.bson.types.ObjectId;

import net.oldcounty.controller.SimpleEmailService;
import net.oldcounty.model.PrivateMessage;

import com.mongo.app.MongoApp;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

/**
 * PrivateMessageDao
 * @param interest
 * Used to set and get interest pie charts per user
 **/
public class PrivateMessageDao {

	static String collectionName = "privateMessageCollection";
	
	/**
	 * Add User Interests
	 * @param interest
	 * @throws MongoException
	 * @throws UnknownHostException
	 **/
	public static List<DBObject> sendPrivateMessage(PrivateMessage privatemessage){
		
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

		Date now = new Date();
		BasicDBObject time = new BasicDBObject("ts", now);		
		
	    BasicDBObject document = new BasicDBObject();

	    	document.put("senderUid", privatemessage.getSenderUserId());
	    	document.put("recepientUid", privatemessage.getRecepientUserId());
	    	document.put("subject", privatemessage.getSubject());
	    	document.put("message", privatemessage.getMessage());
	    	document.put("date", time);	    	
	    	

	    	collection.insert(document);
	    
	    ObjectId lastid = (ObjectId)document.get("_id");

	    
	    //if recepient has email notifcation set enabled - send them an email now.
	    /*
	     * 		
	     * 		inform the recepient they have a private message and need to login to check it out/reply to it.
				String email = person.getEmailaddress();
	        	
			    BasicDBObject data = new BasicDBObject();
	        	 	data.put("emailAddress", email);
	        	 	data.put("userName", "Robbys");
	        	 	data.put("password", password);	
	        	 	
		    	//__ send email notification
		    	SimpleEmailService.generateEmail("privateMessageNotificationTemplate", data);
		    	
		    	List<DBObject> latestUser = PersonDao.setForgotPassword(person);
	     * */
	    
	    
		BasicDBObject results = new BasicDBObject();
		
		results.put("response", "OK");
		results.put("lastId", lastid);
		response.add(results);	
    	
		return response;
	}

	public static List<DBObject> getInboxPrivateMessage(PrivateMessage privatemessage){
		// search query
	    BasicDBObject searchQuery = new BasicDBObject();
	    	searchQuery.put("recepientUid", privatemessage.getRecepientUserId());
	    
	    BasicDBObject excludeFields = new BasicDBObject();
	    return getPrivateMessage(privatemessage, searchQuery, excludeFields);
	}
	
	public static List<DBObject> getSentPrivateMessage(PrivateMessage privatemessage){
		// search query
	    BasicDBObject searchQuery = new BasicDBObject();
	    	searchQuery.put("senderUid", privatemessage.getSenderUserId());	    	
	    
	    BasicDBObject excludeFields = new BasicDBObject();
	    return getPrivateMessage(privatemessage, searchQuery, excludeFields);
	}
	
	/**
	 * Get Private Message
	 * @param interest
	 * @throws MongoException
	 * @throws UnknownHostException
	 **/
	public static List<DBObject> getPrivateMessage(PrivateMessage privatemessage, BasicDBObject searchQuery, BasicDBObject excludeFields){
		
		//__Prepare response
		List<DBObject> response = new ArrayList<DBObject>();		
		BasicDBObject results = new BasicDBObject();
		
		//BasicDBObject searchQuery = new BasicDBObject();
	    
	    List<DBObject> uniquePrivateMessages = null;
		try {
			uniquePrivateMessages = MongoApp.searchCollections(searchQuery, excludeFields, collectionName);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		
		if(uniquePrivateMessages.size()>0 ){			
			results.put("response", "OK");
			results.put("description", "The specific set of messages has been found");
			results.put("results", uniquePrivateMessages);
	    }
		else{
			results.put("response", "FAIL");
			results.put("description", "Failed to get messages list");
		}
		response.add(results);
		
		return response;
	}
}