package com.cisco.cre.dao;

import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.FilterBuilder;

public interface ElasticsearchDAO {

	public <T> List<T> getListByIdsFilter(String type, String[] ids, Class<T> objClass) throws Exception;
}
