package net.oldcounty.model;

import java.util.Map;

public class Trivia {
	Object uid;
	Map<String,Integer> triviaResults;
	String chartType;

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
	public Map<String, Integer> getTriviaResults() {
		return triviaResults;
	}
	public void setTriviaResults(Map<String,Integer> triviaResults) {
		this.triviaResults = triviaResults;
	}
}