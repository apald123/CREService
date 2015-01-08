package com.cisco.cre.vo;

import java.util.List;

/*
 * "RecommendationRequest":{ 
      "custSegment":"ENT",
      "userId":"apaldhik",
      "items":[ 
         "A123",
         "B123",
         "C123"
      ],
      "coOccuranceFlag":"Y",
      "similarityFlag":"Y",
      "featuredFlag":"Y"
   }
 */

public class RecommendationRequestVO {
	
	String userId;
	String custSegment;
	String coOccuranceFlag;
	String similarityFlag;
	String featuredFlag;
	//List<String> items;
	String[] items;
	
	public RecommendationRequestVO() {
		
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCustSegment() {
		return custSegment;
	}
	public void setCustSegment(String custSegment) {
		this.custSegment = custSegment;
	}
	public String getCoOccuranceFlag() {
		return coOccuranceFlag;
	}
	public void setCoOccuranceFlag(String coOccuranceFlag) {
		this.coOccuranceFlag = coOccuranceFlag;
	}
	public String getSimilarityFlag() {
		return similarityFlag;
	}
	public void setSimilarityFlag(String similarityFlag) {
		this.similarityFlag = similarityFlag;
	}
	public String getFeaturedFlag() {
		return featuredFlag;
	}
	public void setFeaturedFlag(String featuredFlag) {
		this.featuredFlag = featuredFlag;
	}
	public String[] getItems() {
		return items;
	}
	public void setItems(String[] items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "RecommendationRequest [userId=" + userId + ", custSegment="
				+ custSegment + ", coOccuranceFlag=" + coOccuranceFlag
				+ ", similarityFlag=" + similarityFlag + ", featuredFlag="
				+ featuredFlag + ", items=" + items + "]";
	}
		
	
}
