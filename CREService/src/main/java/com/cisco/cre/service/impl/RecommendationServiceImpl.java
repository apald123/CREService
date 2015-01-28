package com.cisco.cre.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.cre.bean.IndexItem;
import com.cisco.cre.bean.NeighborItem;
import com.cisco.cre.dao.RecommendationDAO;
import com.cisco.cre.service.RecommendationRulesService;
import com.cisco.cre.service.RecommendationService;
import com.cisco.cre.vo.RecommendationRequestVO;
import com.cisco.cre.vo.RecommendationResponseVO;

@Service("recommendationService")
public class RecommendationServiceImpl implements RecommendationService {

	private RecommendationDAO recommendationDAO;
	private RecommendationRulesService rcmdRulesService;
	
	@Autowired
	public RecommendationServiceImpl(RecommendationDAO recommendationDAO,
			RecommendationRulesService rcmdRulesService) {

		this.recommendationDAO = recommendationDAO;
		this.rcmdRulesService = rcmdRulesService;
	}

	
	@Override
	public RecommendationResponseVO getRecommendations(RecommendationRequestVO rcmdReq) 
			throws Exception {
		
		//LogUtil.print(this, "------------ BEGIN RecommendationServiceImpl.getRecommendations()-----------");
			
		List<IndexItem> itemList = recommendationDAO.getRecommendationItemList(rcmdReq);
		
		// create a set for comparisons purpose
		Set<String> cartItemSet = new HashSet<String>(rcmdReq.getItems());
		
		Set<NeighborItem> coOccSet = new HashSet<NeighborItem>();
		if (rcmdReq.getCoOccuranceFlag().equals("Y")) {
			
			// extract the co-occurrences from the Item
			for (IndexItem item : itemList) {
				if (item.getCoOccurances() != null)
					coOccSet.addAll(item.getCoOccurances());
			}	
			
			// apply rules to reduce the noise
			coOccSet = rcmdRulesService.filterRecommendations(cartItemSet, coOccSet);
		}

		Set<NeighborItem> simlrSet = new HashSet<NeighborItem>();
		if (rcmdReq.getSimilarityFlag().equals("Y")) {

			// extract the similarities from the Item
			for (IndexItem item : itemList) {
				if (item.getSimilarities() != null)
					simlrSet.addAll(item.getSimilarities());
			}

			// apply rules
			//rcmdRulesService.filterRecommendations(simlrList);
		}

		// build the response object
		RecommendationResponseVO rcmdResp = new RecommendationResponseVO();
		rcmdResp.setCoOccuranceSet(coOccSet);
		rcmdResp.setSimilaritySet(simlrSet);
		
		if (rcmdReq.getFeaturedFlag().equals("Y")) {
			//featuredItemsService.
		}
		//LogUtil.print(this, "------------ END RecommendationServiceImpl.getRecommendations()-----------");
		
		return rcmdResp; 
	}
}
