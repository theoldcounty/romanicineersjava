package net.oldcounty.controller;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class GalleryController{
	
	
	
	
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