package com.cisco.cre.bean;

import java.util.Set;

public class NeighborItemFamily {
	
	private String productFamily;
	private Set<NeighborItem> itemSet;
	
	public NeighborItemFamily() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getProductFamily() {
		return productFamily;
	}
	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}
	public Set<NeighborItem> getItemSet() {
		return itemSet;
	}
	public void setItemSet(Set<NeighborItem> itemSet) {
		this.itemSet = itemSet;
	}
	
	@Override
	public String toString() {
		return "NeighborItemFamily [productFamily=" + productFamily
				+ ", itemSet=" + itemSet + "]";
	}
		
}
