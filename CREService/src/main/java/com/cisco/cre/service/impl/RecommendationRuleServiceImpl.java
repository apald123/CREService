package com.cisco.cre.service.impl;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.cisco.cre.bean.Item;
import com.cisco.cre.bean.NeighborItem;
import com.cisco.cre.service.RecommendationRulesService;

public class RecommendationRuleServiceImpl implements
		RecommendationRulesService {

	@Override
	public void filterRecommendations(List<NeighborItem> itemList) {
		// static rules
		for (NeighborItem item : itemList) {
			
			// only GOODS are recommended
			if (!item.getProductType().equals("GOODS")) {
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
		}		
	}

}
