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
				LogUtil.debug(this, result);

				//LogUtil.debug(this, result);		
				
		        //convert json string to object
		        ObjectMapper objectMapper = new ObjectMapper();   
		        //final DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		        //objectMapper.setDateFormat(df);
		        //objectMapper.setSerializationInclusion(Include.NON_NULL); 
		        //objectMapper.setSerializationInclusion(Include.NON_EMPTY);
		        
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
            "_index": "items",
            "_type": "com",
            "_id": "UCSC-C240-M3S2",
            "_score": 1,
            "_source": {
               "name": "UCSC-C240-M3S2",
               "description": "UCS FLEXPOD EXPRESS",
               "itemType": "BUNDLE",
               "productType": "MULTIPROD",
               "productFamily": "UCSX",
               "_AlsoBought": [
                  {
                     "productFamily": "PF1",
                     "itemSet": [
                        {
                           "name": "CON-OSP-UCS3048F",
                           "description": "ONSITE 24X7X4 Nexus 3048 for UCS Smart Play",
                           "itemType": "MAINT",
                           "productType": "GOODS",
                           "productFamily": "PF1",
                           "itemEOLDate": "2013/1/1",
                           "score": 1
                        },
                        {
                           "name": "Test Item 1",
                           "description": "Service Item - skip",
                           "itemType": "MAINT",
                           "productType": "SERVICE",
                           "productFamily": "PF1",
                           "itemEOLDate": "2013/10/1",
                           "score": 1
                        }
                     ]
                  },
                  {
                     "productFamily": "PF2",
                     "itemSet": [
                        {
                           "name": "Test Item 2",
                           "description": "EOL Item - skip",
                           "itemType": "MAINT",
                           "productType": "GOODS",
                           "productFamily": "PF2",
                           "itemEOLDate": "2013/10/1",
                           "score": 1
                        },
                        {
                           "name": "Test Item 3",
                           "description": "Score < 60% - skip",
                           "itemType": "MAINT",
                           "productType": "GOODS",
                           "productFamily": "PF2",
                           "itemEOLDate": "2013/10/1",
                           "score": 0.3
                        },
                        {
                           "name": "Test Item 4",
                           "description": "Fetch",
                           "itemType": "MAINT",
                           "productType": "GOODS",
                           "productFamily": "PF2",
                           "itemEOLDate": "2013/10/1",
                           "score": 0.65
                        },
                        {
                           "name": "Test Item 5",
                           "description": "Fetch",
                           "itemType": "MAINT",
                           "productType": "GOODS",
                           "productFamily": "PF2",
                           "itemEOLDate": "2013/10/1",
                           "score": 0.8
                        }
                     ]
                  }
               ],
               "similarities": []
            }
         } 
 
 
 
POST /items/com/UCSC-C240-M3S2
{    
        "name": "UCSC-C240-M3S2",
        "description" : "UCS FLEXPOD EXPRESS",
        "itemType": "BUNDLE",
        "productType":"MULTIPROD",
        "productFamily":"UCSX",
        "_AlsoBought": [
            {    
                "name": "CON-OSP-UCS3048F",
                "description" : "ONSITE 24X7X4 Nexus 3048 for UCS Smart Play",
                "itemType": "MAINT",
                "productType": "GOODS",
                "productFamily": "SERVICE",
                "itemEOLDate" : "2013/1/1",
                "score": 1.0
            },
		{	
		    "name": "Test Item 1",
		    "description" : "Service Item - skip",
		    "itemType": "MAINT",
		    "productType": "SERVICE",
		    "productFamily": "PF1",
		    "itemEOLDate" : "2013/10/1",
		    "score": 1.0
		},
		{	
		    "name": "Test Item 2",
		    "description" : "EOL Item - skip",
		    "itemType": "MAINT",
		    "productType": "GOODS",
		    "productFamily": "PF2",
		    "itemEOLDate" : "2013/10/1",
		    "score": 1.0
		},
		{	
		    "name": "Test Item 3",
		    "description" : "Score < 60% - skip",
		    "itemType": "MAINT",
		    "productType": "GOODS",
		    "productFamily": "PF3",
		    "itemEOLDate" : "2013/10/1",
		    "score": 0.3
		},
		{	
		    "name": "Test Item 4",
		    "description" : "Fetch",
		    "itemType": "MAINT",
		    "productType": "GOODS",
		    "productFamily": "PF4",
		    "itemEOLDate" : "2013/10/1",
		    "score": 0.65
		},
		{	
		    "name": "Test Item 5",
		    "description" : "Fetch",
		    "itemType": "MAINT",
		    "productType": "GOODS",
		    "productFamily": "PF5",
		    "itemEOLDate" : "2013/10/1",
		    "score": 0.8
		}
        ],	
        "similarities": [
        ]	
}
*/