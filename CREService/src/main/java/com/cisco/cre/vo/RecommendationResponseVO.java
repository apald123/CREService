package com.cisco.cre.vo;

import java.util.List;

public class RecommendationResponseVO {

	List coOccuranceList;
	List similarityList;
	List featuredList;
	
	public RecommendationResponseVO() {
		
	}
	
	public List getCoOccuranceList() {
		return coOccuranceList;
	}
	public void setCoOccuranceList(List coOccuranceList) {
		this.coOccuranceList = coOccuranceList;
	}
	public List getSimilarityList() {
		return similarityList;
	}
	public void setSimilarityList(List similarityList) {
		this.similarityList = similarityList;
	}
	public List getFeaturedList() {
		return featuredList;
	}
	public void setFeaturedList(List featuredList) {
		this.featuredList = featuredList;
	}
	
	
}
