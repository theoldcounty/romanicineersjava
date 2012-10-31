package com.mongo.app;

import java.net.UnknownHostException;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

/**
 * Java MongoDB Example
 * 
 */
public class MongoApp {
		

	/**
	 * get database
	 * @throws MongoException 
	 * @throws UnknownHostException 
	 **/	
	public static DB getDatabase() throws UnknownHostException, MongoException{
		//String host = "46.38.190.133";
		//Integer port = 29017;

		String host = "127.0.0.1";
		Integer port = 27017;
		
	    // connect to mongoDB
	    Mongo mongo = new Mongo(host, port);

	    // if database doesn't exists, mongoDB will create it
	    String dbName = "mydb";
	    DB db = mongo.getDB(dbName);

	    return db;
	}	
	

	
	
	/**
	 * delete collection entry
	 **/
	public static void deleteCollectionEntry(BasicDBObject obj) throws UnknownHostException, MongoException{
		DBCollection collection = getCollection();
		collection.remove(obj);
	}
	
	/**
	 * empty database
	 **/
	public static void cleanCollection() throws UnknownHostException, MongoException{
	    //empty object clears ALL entries	
	    deleteCollectionEntry(new BasicDBObject());
	}

	/**
	 * get collection
	 * @throws MongoException 
	 * @throws UnknownHostException 
	 **/	
	public static DBCollection getCollection() throws UnknownHostException, MongoException {
		// if database doesn't exists, mongoDB will create it
	    DB db = getDatabase();

	    String collectionName = "myCollection";
	    
	    // Get collection from MongoDB, database named "mydb"
	    // if collection doesn't exists, mongoDB will create it
	    DBCollection collection = db.getCollection(collectionName);
	    
	    return collection;
	}
	
	
    public static void main(String[] args) {

		try {
			getCollection();
			//cleanCollection();
		} catch (UnknownHostException e) {
		    e.printStackTrace();
		} catch (MongoException e) {
		    e.printStackTrace();
		}
    }
}