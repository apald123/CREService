package com.cisco.cre.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.cisco.cre.bean.IndexItem;
import com.cisco.cre.dao.ElasticsearchDAO;
import com.cisco.cre.dao.RecommendationDAO;
import com.cisco.cre.util.LogUtil;
import com.cisco.cre.vo.RecommendationRequestVO;

@Repository("recommendationDAO")
public class RecommendationDAOImpl implements RecommendationDAO {
	
	private ElasticsearchDAO elasticsearchDAO;
	
	@Autowired
	public RecommendationDAOImpl(ElasticsearchDAO esDAO) {
		this.elasticsearchDAO = esDAO;		
	}

	@Override
	public List<IndexItem>  getRecommendationItemList(RecommendationRequestVO rcmdReq) throws Exception {
		
		//LogUtil.debug(this, "------------ BEGIN ElasticsearchServiceImpl.getCoOccuranceList()-----------");
		
		// use "unknown" as fallback if customer segment is not available 
		String esType = rcmdReq.getCustSegment();
		if (StringUtils.hasLength(esType)) {
			esType = esType.toLowerCase();
		} else {
			esType = "unknown";
		}
			
		List<IndexItem> itemList = elasticsearchDAO.getListByIdsFilter(esType, rcmdReq.getItems(), 
				IndexItem.class);
				
		LogUtil.info(this, "ItemList="+itemList.toString());
		//LogUtil.debug(this, "------------ END ElasticsearchServiceImpl.getCoOccuranceList()-----------");
		
	
		return itemList;
	}
	
}

