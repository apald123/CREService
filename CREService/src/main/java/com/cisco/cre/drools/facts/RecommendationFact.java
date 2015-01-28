package com.cisco.cre.drools.facts;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.cisco.cre.bean.NeighborItem;

public class RecommendationFact {
	
	private NeighborItem neighborItem;
	private Set cartItemSet;
	private Date cutoffDate;
	
	public RecommendationFact() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Set getCartItemSet() {
		return cartItemSet;
	}
	public void setCartItemSet(Set cartItemSet) {
		this.cartItemSet = cartItemSet;
	}
	public Date getCutoffDate() {
		return cutoffDate;
	}
	public void setCutoffDate(Date cutoffDate) {
		this.cutoffDate = cutoffDate;
	}
	public NeighborItem getNeighborItem() {
		return neighborItem;
	}
	public void setNeighborItem(NeighborItem neighborItem) {
		this.neighborItem = neighborItem;
	}

}
