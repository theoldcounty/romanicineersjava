package net.oldcounty.model;

import java.util.Map;

public class Interests {
	Object uid;
	String chartType;
	Map<String,Integer> dataResults;

	public Object getUid() {
		return uid;
	}
	public void setUid(Object uid) {
		this.uid = uid;
	}
	public String getChartType() {
		return chartType;
	}
	public void setChartType(String chartType) {
		this.chartType = chartType;
	}	
	public Map<String, Integer> getDataResults() {
		return dataResults;
	}
	public void setDataResults(Map<String,Integer> dataResults) {
		this.dataResults = dataResults;
	}
}