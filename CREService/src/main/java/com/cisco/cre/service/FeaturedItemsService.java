package com.cisco.cre.service;

import java.util.Set;

import com.cisco.cre.bean.Item;
import com.cisco.cre.bean.NeighborItem;

public interface FeaturedItemsService {

	public Set<NeighborItem> getFeaturedItemSet(Set<Item> cartItemSet);
}
