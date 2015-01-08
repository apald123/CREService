package com.cisco.cre.ds;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ElasticsearchDS {
	
	private Client client = null;
	
	//private String host = "10.203.25.55";
	
	@Value( "${elasticsearchDS.host}" )
	private String host; // = "localhost";

	@Value( "${elasticsearchDS.index}" )
	private String index;

	
	public ElasticsearchDS() {
		
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Client getClient() {
		
		if (client == null) {
			connectESTransport();
		}
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	private void connectESTransport() {
		client = new TransportClient().addTransportAddress(new InetSocketTransportAddress(host, 9300))
				.addTransportAddress(new InetSocketTransportAddress(host, 9301))
				.addTransportAddress(new InetSocketTransportAddress(host, 9302));
	}
	
	public void exitESTransport() {
		client.close();
	}

	@Override
	public String toString() {
		return "ElasticsearchDS [client=" + client + ", host=" + host
				+ ", index=" + index + "]";
	}
		
	/*
	public void createIndex(Address addr) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(addr);
		
		IndexResponse resp = client.prepareIndex("employee", "address", addr.getName())
				.setSource(json)
				.execute()
				.actionGet();
				
		System.out.println("IndexResponse="+resp);
	}
	
	
	public void get(String index, String type, String id) {
		GetResponse response = client.prepareGet(index, type, id)
		        .execute()
		        .actionGet();
		
		Map<String, Object> source = response.getSource();
		 

		System.out.println("------------------------------");

		System.out.println("Index: " + response.getIndex());

		System.out.println("Type: " + response.getType());

		System.out.println("Id: " + response.getId());

		System.out.println("Version: " + response.getVersion());

		System.out.println(source);

		System.out.println("------------------------------");
	}
	
	public void search(String index, String type ) {
		SearchRequestBuilder request = client.prepareSearch(index)
		        .setTypes(type)
		        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		        .setQuery(QueryBuilders.matchQuery("state", "AZ"))             // Query
		        //.setPostFilter(FilterBuilders.rangeFilter("age").from(12).to(18))   // Filter
		        .setFrom(0).setSize(60).setExplain(true);
		
		ListenableActionFuture<SearchResponse> future = request.execute();
		SearchResponse response = future.actionGet();
		
		SearchHits hits = response.getHits();
		SearchHit[] results = hits.getHits();
		
		System.out.println("# Resutls="+results.length);
		
		for (SearchHit hit : results) {
			System.out.println("------------------------------");

			Map<String,Object> result = hit.getSource();   

			System.out.println(result);		
		}		
	}
	*/
}
