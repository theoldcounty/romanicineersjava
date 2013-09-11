package net.oldcounty.controller;


import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.oldcounty.dao.ImageDao;
import net.oldcounty.dao.PersonDao;

import org.bson.BasicBSONObject;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoException;


@Controller
public class PrivateMessageController {
	
	public static List<DBObject> enrichMessages(
			List<DBObject> messages,
			String relevantUser
		) throws UnknownHostException, MongoException {
		

		Object messageArray = messages.get(0).get("results");
		
		if(messageArray != null){
			@SuppressWarnings("unchecked")
			Iterator<DBObject> it = ((List<DBObject>) messageArray).iterator();
			while(it.hasNext())
			{
			    Object obj = it.next();
			    //Do something with obj
			    	
			    	String userId = null;	
				    if(relevantUser == "sender"){
				    	 userId = (String) ((BasicBSONObject) obj).get("recepientUid").toString();
					}
				    else{
				    	 userId = (String) ((BasicBSONObject) obj).get("senderUid").toString();
					}
			    	
				    System.out.println("userId: "+ userId);
				    
				    //_get images to associate with user
					List<DBObject> imgResults = ImageDao.getUsersImages(userId);
					((BasicBSONObject) obj).put("UserImg", imgResults);
					
					//_get user details
					BasicDBObject searchQuery = new BasicDBObject();
						searchQuery.put("_id", new ObjectId(userId));
					
					BasicDBObject excludeFields = new BasicDBObject();
						excludeFields.put("username", 1);
						excludeFields.put("realname", 1);
						excludeFields.put("whichscreenname", 1);
						excludeFields.put("_id", 0);
					
					List<DBObject> personObj =PersonDao.getUniqueUser(searchQuery, excludeFields);
					((BasicBSONObject) obj).put("userDetails", personObj.get(0).get("user"));
					
			    System.out.println("obj"+obj);
			}		
		}
		
		return messages;
	}
}