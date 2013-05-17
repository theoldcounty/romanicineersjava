package net.oldcounty.model;

import java.util.Map;

public class Interests {
	String name; //name of the chart, e.g. hobbies, sports, favourite foods
	String userId; //the user id associated with this chart
	String cid;
	
	Map<String,Integer> interestData;

	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}	
	public void setName(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}	
	public String getUserId() {
		return userId;
	}	
	public void setResults(Map<String,Integer> interestData){
		this.interestData = interestData;
	}
	public Map<String, Integer> getResults(){
		return interestData;
	}
}