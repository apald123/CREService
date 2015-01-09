package com.cisco.cre.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.cisco.cre.dao.ElasticsearchDAO;
import com.cisco.cre.ds.ElasticsearchDS;
import com.cisco.cre.util.LogUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository("elasticsearchDAO")
public class ElasticsearchDAOImpl implements ElasticsearchDAO {
	
	private ElasticsearchDS elasticsearchDS;
	
	@Autowired
	public ElasticsearchDAOImpl(ElasticsearchDS elasticDS) {
		this.elasticsearchDS = elasticDS;		
	}

	/*
	 * Returns the search response given a type and filter
	 */
	@Override
	public <T> List<T> getListByIdsFilter(String type, List<String> idList, Class<T> objClass) 
			throws Exception {

		Client client = null;
		LogUtil.debug(this, "--------------------- BEGIN ----------------------");
		try {
			
			client = elasticsearchDS.getClient();
			
			String[] ids = new String[idList.size()];
			ids = idList.toArray(ids);
			FilterBuilder filter = FilterBuilders.idsFilter().addIds(ids);
			SearchRequestBuilder request = client.prepareSearch(elasticsearchDS.getIndex())
					.setPostFilter(filter);
			
			// add type only if specified
			if (StringUtils.hasLength(type)) {	
				request.setTypes(type);
			}
			
			//LogUtil.debug(this, request.toString());
			    
			        //.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
			        //.setQuery(QueryBuilders.matchQuery("state", "AZ"))             // Query
			        //.setPostFilter(FilterBuilders.rangeFilter("age").from(12).to(18))   // Filter
			        //.setPostFilter(FilterBuilders.idsFilter().addIds(ids));
			        //.setFrom(0).setSize(60).setExplain(true);
			
			ListenableActionFuture<SearchResponse> future = request.execute();
			SearchResponse response = future.actionGet();
	
			SearchHits hits = response.getHits();
			SearchHit[] results = hits.getHits();
			
			LogUtil.debug(this, "# Resutls="+results.length);
			List<T> itemList = new ArrayList<T>();
			
			for (SearchHit hit : results) {
				LogUtil.debug(this, "------------------------------");

				//Map<String,Object> result = hit.getSource();
				
				String result = hit.getSourceAsString();

				//LogUtil.debug(this, result);		
				
		        //convert json string to object
		        ObjectMapper objectMapper = new ObjectMapper();   
		        //final DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		        //objectMapper.setDateFormat(df);
		        
		        T item = objectMapper.readValue(result, objClass);

		        LogUtil.debug(this, item.toString());
		        
		        itemList.add(item);
		        		        
			}		

			LogUtil.debug(this, "--------------------- END ----------------------");
			return itemList;

		} catch(Exception ex) {
			
			throw ex;
			
		} 
	}

	@Override
	public String toString() {
		return "ElasticsearchDAOImpl [elasticsearchDS=" + elasticsearchDS + "]";
	}
		

	/*
	 * Returns the search response given a type and filter
	 */
	
	/*
	@Override	
	public SearchResponse launchSearch(String type, FilterBuilder filter) throws Exception {

		try {
			
			Client client = elasticDS.getClient();
			
			SearchRequestBuilder request = client.prepareSearch(index)
					.setPostFilter(filter);
			
			// add type only if specified
			if (StringUtils.hasLength(type)) {
				request.setTypes(type);
			}
			    
			        //.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
			        //.setQuery(QueryBuilders.matchQuery("state", "AZ"))             // Query
			        //.setPostFilter(FilterBuilders.rangeFilter("age").from(12).to(18))   // Filter
			        //.setPostFilter(FilterBuilders.idsFilter().addIds(ids));
			        //.setFrom(0).setSize(60).setExplain(true);
			
			ListenableActionFuture<SearchResponse> future = request.execute();
			SearchResponse response = future.actionGet();
	
			return response;

		} catch(Exception ex) {
			throw ex;
		}
	}
	*/

}

/*
{
"name": "ASA-5000",
"description" : "ASA 5500 Adaptive Security Appliance",
"type": "Security Appliance",
"neighbors": [
    {
        "name": "IPS6000",
        "description" : "Intrusion Prevention",
        "type": "Software",
        "score": 0.85,
        "last_copurchased_on": "01-Nov-2014"
    },
    {
        "name": "CSM4",
        "description": "Cisco Security Manager",
        "type": "Software",
        "score": 0.65,
        "last_copurchased_on": "01-Nov-2014"
    }
]
}
*/