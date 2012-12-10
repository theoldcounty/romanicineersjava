/*
*
* Author: Rob Shan Lone
* Copyright (c) 2012 The Old County Limited.
*
* All rights reserved.
*/

package com.mongo.app;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
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
	public static void deleteCollectionEntry(BasicDBObject obj, String collectionName) throws UnknownHostException, MongoException{
		DBCollection collection = getCollection(collectionName);
		collection.remove(obj);
	}

	/**
	 * empty database
	 **/
	public static void cleanCollection() throws UnknownHostException, MongoException{
	    //empty object clears ALL entries
	    deleteCollectionEntry(new BasicDBObject(), "myCollection");
	}

	/**
	 * get collection
	 * @throws MongoException
	 * @throws UnknownHostException
	 **/
	public static DBCollection getCollection(String collectionName) throws UnknownHostException, MongoException {
		// if database doesn't exists, mongoDB will create it
	    DB db = getDatabase();

	    //String collectionName = "myCollection";

	    // Get collection from MongoDB, database named "mydb"
	    // if collection doesn't exists, mongoDB will create it
	    DBCollection collection = db.getCollection(collectionName);

	    return collection;
	}

	
	public static List<DBObject> searchCollections(
			BasicDBObject searchQuery,
			String collectionName
		) throws UnknownHostException, MongoException{

		//__Prepare result
		List<DBObject> results = new ArrayList<DBObject>();

		//_getCollection
		DBCollection collection = MongoApp.getCollection(collectionName);

	    DBCursor cursor = collection.find(searchQuery);

        // loop over the cursor and display the result
    	while (cursor.hasNext()) {
	    	results.add(cursor.next());
	    }

	    return results;
	}	
	/*
    public static void main(String[] args) {

		try {
			getCollection("myCollection");
			//cleanCollection();
		} catch (UnknownHostException e) {
		    e.printStackTrace();
		} catch (MongoException e) {
		    e.printStackTrace();
		}
    }*/
}
