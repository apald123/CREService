package com.cisco.cre.vo;

import java.util.Set;

import com.cisco.cre.bean.NeighborItem;

public class RecommendationResponseVO {

	private Set<NeighborItem> coOccuranceSet;
	private Set<NeighborItem> similaritySet;
	private Set<NeighborItem> featuredSet;
	
	public RecommendationResponseVO() {
		
	}

	public Set<NeighborItem> getCoOccuranceSet() {
		return coOccuranceSet;
	}


	public void setCoOccuranceSet(Set<NeighborItem> coOccuranceSet) {
		this.coOccuranceSet = coOccuranceSet;
	}


	public Set<NeighborItem> getSimilaritySet() {
		return similaritySet;
	}


	public void setSimilaritySet(Set<NeighborItem> similaritySet) {
		this.similaritySet = similaritySet;
	}


	public Set<NeighborItem> getFeaturedSet() {
		return featuredSet;
	}


	public void setFeaturedSet(Set<NeighborItem> featuredSet) {
		this.featuredSet = featuredSet;
	}


	@Override
	public String toString() {
		return "RecommendationResponseVO [coOccuranceSet=" + coOccuranceSet
				+ ", similaritySet=" + similaritySet + ", featuredSet="
				+ featuredSet + "]";
	}
	
	
}
