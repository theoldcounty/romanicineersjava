package net.oldcounty.model;

import java.util.Map;

public class Gallery {
	Object uid;
	Map<String,Integer> galleryResults;

	public Object getUid() {
		return uid;
	}
	public void setUid(Object uid) {
		this.uid = uid;
	}
	public Map<String, Integer> getGalleryResults() {
		return galleryResults;
	}
	public void setGalleryResults(Map<String,Integer> galleryResults) {
		this.galleryResults = galleryResults;
	}
}