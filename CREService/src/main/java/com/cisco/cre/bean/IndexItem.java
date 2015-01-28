package com.cisco.cre.bean;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IndexItem extends Item {
	
	//private Set<NeighborItemFamily> alsoBoughtSet;
	private Set<NeighborItem> coOccurances;
	private Set<NeighborItem> similarities;

	public IndexItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Set<NeighborItem> getCoOccurances() {
		return coOccurances;
	}

	@JsonProperty("_alsoBought")
	public void setCoOccurances(Set<NeighborItem> coOccurances) {
		this.coOccurances = coOccurances;
	}

	
	public Set<NeighborItem> getSimilarities() {
		return similarities;
	}

	public void setSimilarities(Set<NeighborItem> similarities) {
		this.similarities = similarities;
	}

	@Override
	public String toString() {
		return "IndexItem [coOccurances=" + coOccurances + ", similarities="
				+ similarities + ", partNumber=" + partNumber
				+ ", description=" + description + ", itemType=" + itemType
				+ ", productType=" + productType + ", productFamily="
				+ productFamily + ", serviceSKU=" + serviceSKU
				+ ", eolAnnounceDate=" + eolAnnounceDate + ", bu=" + bu
				+ ", eolSaleDate=" + eolSaleDate + ", lastSupportDate="
				+ lastSupportDate + ", leadTime=" + leadTime + ", techGroup="
				+ techGroup + ", productSubGroup=" + productSubGroup + "]";
	}
			
	
} 
