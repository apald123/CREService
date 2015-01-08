package com.cisco.cre.service;

import com.cisco.cre.vo.RecommendationRequestVO;
import com.cisco.cre.vo.RecommendationResponseVO;


public interface RecommendationService {

	public RecommendationResponseVO getRecommendations(RecommendationRequestVO request) throws Exception;
}
