package com.cisco.cre.bean;

import java.util.List;

public class Item extends GenericItem {
	
	private List<NeighborItem> coOccurances;
	private List<NeighborItem> similarities;

	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<NeighborItem> getCoOccurances() {
		return coOccurances;
	}

	public void setCoOccurances(List<NeighborItem> coOccurances) {
		this.coOccurances = coOccurances;
	}

	
	public List<NeighborItem> getSimilarities() {
		return similarities;
	}

	public void setSimilarities(List<NeighborItem> similarities) {
		this.similarities = similarities;
	}

	@Override
	public String toString() {
		return "Item [coOccurances=" + coOccurances + ", similarities="
				+ similarities + ", name=" + name + ", description="
				+ description + ", itemType=" + itemType + ", productType="
				+ productType + ", productFamily=" + productFamily + "]";
	}
			
	
} 
