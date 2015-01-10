package com.cisco.cre.service.impl;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.cisco.cre.bean.Item;
import com.cisco.cre.bean.NeighborItem;
import com.cisco.cre.service.RecommendationRulesService;

@Service("recommendationRulesService")
public class RecommendationRuleServiceImpl implements
		RecommendationRulesService {

	@Override
	public List<NeighborItem> filterRecommendations(Set<String> cartItemSet, List<NeighborItem> nbrItemList) {
		
		List<NeighborItem> filteredList = new ArrayList<NeighborItem>();
		
		// static rules
		for (NeighborItem item : nbrItemList) {
			
			// only GOODS are recommended
			if (!item.getItemType().equals("MAINT")) {
				continue;
			}
			
			// get the cutoff date - 3 months back
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.MONTH, 3);		
			Date cutoffDate = cal.getTime();
			
			// item shouldn't be getting eoled in next 3 months
			if (item.getItemEOLDate().before(cutoffDate)) {
				continue;
			}
			
			// less than 60% 
			if (item.getScore() < 0.6) {
				continue;
			}
					
			// skip the cart items			
			if (cartItemSet.contains(item.getName())) {
				continue;
			}
			
			// outherwise add the item to the filtered list
			filteredList.add(item);
		}		
		
		return filteredList;
	}

}
