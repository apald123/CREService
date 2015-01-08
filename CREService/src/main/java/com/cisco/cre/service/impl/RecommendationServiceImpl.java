package com.cisco.cre.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.cre.bean.Item;
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
			
		List<Item> itemList = recommendationDAO.getRecommendationItemList(rcmdReq);

		List<NeighborItem> coOccList = new ArrayList<NeighborItem>();
		if (rcmdReq.getCoOccuranceFlag().equals("Y")) {
			
			// extract the co-occurrences from the Item
			for (Item item : itemList) {
				if (item.getCoOccurances() != null)
					coOccList.addAll(item.getCoOccurances());
			}	
			
			//rcmdRulesService.filterRecommendations(coOccList);
		}

		List<NeighborItem> simlrList = new ArrayList<NeighborItem>();
		if (rcmdReq.getSimilarityFlag().equals("Y")) {

			// extract the similarities from the Item
			for (Item item : itemList) {
				if (item.getSimilarities() != null)
					simlrList.addAll(item.getSimilarities());
			}
			
			//rcmdRulesService.filterRecommendations(simlrList);
		}

		// build the response object
		RecommendationResponseVO rcmdResp = new RecommendationResponseVO();
		rcmdResp.setCoOccuranceList(coOccList);
		rcmdResp.setSimilarityList(simlrList);
		
		//LogUtil.print(this, "------------ END RecommendationServiceImpl.getRecommendations()-----------");
		
		return rcmdResp; 
	}
}
