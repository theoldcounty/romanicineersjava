/*
*
* Author: Rob Shan Lone
* Copyright (c) 2012 The Old County Limited.
*
* All rights reserved.
*/

package net.oldcounty.controller;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.MissingResourceException;

import org.bson.types.ObjectId;

import com.mongo.app.MongoApp;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoException;

import java.util.Locale;

public class CommonUtils{


	public static boolean isOlderThan18(String dob){
		//__Prepare result
		Boolean result = true;

		int yearDOB = Integer.parseInt(dob.substring(0, 4));
		int monthDOB = Integer.parseInt(dob.substring(4, 6));
		int dayDOB = Integer.parseInt(dob.substring(6, 8));

		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		java.util.Date date = new java.util.Date();
		int thisYear = Integer.parseInt(dateFormat.format(date));

		dateFormat = new SimpleDateFormat("MM");
		date = new java.util.Date();
		int thisMonth = Integer.parseInt(dateFormat.format(date));

		dateFormat = new SimpleDateFormat("dd");
		date = new java.util.Date();
		int thisDay = Integer.parseInt(dateFormat.format(date));

		int age = thisYear - yearDOB;

		if(thisMonth < monthDOB){
			age = age - 1;
		}

		if(thisMonth == monthDOB && thisDay < dayDOB){
			age = age - 1;
		}


		if(age <18){
			result = false;
		}

		return result;
	}

	public static boolean isValidDate(String dt){
		//__Prepare result
		Boolean result = true;

		 String dateformat = "yyyyMMdd";

         try{
             SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
             sdf.setLenient(false);
             sdf.parse(dt);
         }
         catch (ParseException e) {
             result = false;
         }
         catch (IllegalArgumentException e) {
             result = false;
         }

		return result;
	}

	public static Boolean isEmailUnique(String emailaddress) throws UnknownHostException, MongoException {
		//__Prepare result
		Boolean result = true;

		//_getCollection
		DBCollection collection = MongoApp.getCollection("myCollection");

	    // search query
	    BasicDBObject searchQuery = new BasicDBObject();
	    	searchQuery.put("emailaddress", emailaddress);

	    DBCursor cursor = collection.find(searchQuery);

	    if(cursor.count() >0){
	    	result = false;
	    }

		return result;
	}

	public static boolean isEmpty(String variable) {
		//__Prepare result
		Boolean result = true;

		if(variable != null)
		{
			result = false;
		}
		return result;
	}

	public static boolean doPasswordsMatch(String password, String confirmpassword) {
		//__Prepare result
		Boolean result = true;

		if(!password.equals(confirmpassword))
		{
			result = false;
		}
		return result;
	}

	public static boolean checkLoggedIn(String objId) throws UnknownHostException, MongoException {
		//__Prepare result
		Boolean result = false;

		//_getCollection
		DBCollection collection = MongoApp.getCollection("myCollection");

	    // search query
	    BasicDBObject searchQuery = new BasicDBObject();
	    	searchQuery.put("_id", new ObjectId(objId));
	    	searchQuery.put("isloggedon", 1);

	    DBCursor cursor = collection.find(searchQuery);

	    System.out.println(cursor.count());

	    if(cursor.count() >0){
	    	result = true;
	    }

		return result;
	}


	public static Map<String, String> getEthnicity() {
		Map<String,String> ethnicity = new LinkedHashMap<String,String>();
			ethnicity.put("0","Caucasian");
			ethnicity.put("1","Asian");
			ethnicity.put("2","Black");
			ethnicity.put("3","Indian");
			ethnicity.put("4","Hispanic");
			ethnicity.put("5","Middle Eastern");
			ethnicity.put("6","Native American");
			ethnicity.put("7","Mixed Race");
			ethnicity.put("8","Other Ethnicity");

		return ethnicity;
	}

	public static Map<String, String> getCountries() {
		Map<String,String> country = new LinkedHashMap<String,String>();

	    Locale[] locales = Locale.getAvailableLocales();
	    
	    for (Locale locale : locales) {
			try {
				String iso = locale.getISO3Country();
				String name = locale.getDisplayCountry();
				if (!"".equals(iso) && !"".equals(name)) {
					country.put(iso, name);
				}
			}
			catch (MissingResourceException e){
				//do nothing
			}
	    }

		return country;
	}

	public static Map<String, String> getGender() {
		Map<String,String> gender = new LinkedHashMap<String,String>();
			gender.put("0","Male");
			gender.put("1","Female");

		return gender;
	}


}
