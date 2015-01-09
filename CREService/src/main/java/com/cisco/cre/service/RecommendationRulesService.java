package com.cisco.cre.service;

import java.util.List;
import java.util.Set;

import com.cisco.cre.bean.Item;
import com.cisco.cre.bean.NeighborItem;

public interface RecommendationRulesService {
	
	public List<NeighborItem>  filterRecommendations(Set<String> cartItemSet, List<NeighborItem> nbrItemList);

}
