package com.cisco.cre.service;

import java.util.List;

import com.cisco.cre.bean.Item;
import com.cisco.cre.bean.NeighborItem;

public interface RecommendationRulesService {
	
	public void  filterRecommendations(List<NeighborItem> itemList);

}
