package com.cisco.cre.dao;

import java.util.List;

import com.cisco.cre.bean.IndexItem;
import com.cisco.cre.vo.RecommendationRequestVO;
import com.cisco.cre.vo.RecommendationResponseVO;

public interface RecommendationDAO extends GenericDAO {

	public List<IndexItem> getRecommendationItemList(RecommendationRequestVO rcmdReq) throws Exception ;
	
}
