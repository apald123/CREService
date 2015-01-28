package com.cisco.cre.service;

import java.util.List;
import java.util.Set;

import com.cisco.cre.bean.IndexItem;
import com.cisco.cre.bean.NeighborItem;

public interface RecommendationRulesService {
	
	public Set<NeighborItem>  filterRecommendations(Set<String> cartItemSet, Set<NeighborItem> nbrItemSet);

}
