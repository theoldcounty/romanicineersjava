package net.oldcounty.model;

import java.util.Map;

public class Interests {
	String name; //name of the chart, e.g. hobbies, sports, favourite foods
	String userId; //the user id associated with this chart
	Map<String,Integer> interestData;
	
	//set name of the chart
	public void setName(String name){
		this.name = name;		
	}	
	//get name of the chart
	public String getName() {
		return name;
	}

	//set associated user id object
	public void setUserId(String userId) {
		this.userId = userId;
	}
	//get associated user id object
	public String getUserId() {
		return userId;
	}
	
	//set associated interest data
	public void setResults(Map<String,Integer> interestData){
		this.interestData = interestData;
	}	
	//get associated interest data
	public Map<String, Integer> getResults(){
		return interestData;
	}
}