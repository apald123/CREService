package com.cisco.cre.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.cisco.cre.bean.Item;
import com.cisco.cre.bean.NeighborItem;
import com.cisco.cre.service.FeaturedItemsService;

public class FeaturedItemsServiceImpl implements FeaturedItemsService {
	
	class BundleItemInfo {
		
		private String bundleName ;
		private Map<String, Integer> itemCountMap ;
		
		public String getBundleName() {
			return bundleName;
		}
		public void setBundleName(String bundleName) {
			this.bundleName = bundleName;
		}
		public Map<String, Integer> getItemCountMap() {
			return itemCountMap;
		}
		public void setItemCountMap(Map<String, Integer> itemCountMap) {
			this.itemCountMap = itemCountMap;
		}						
	}

	@Override
	public Set<NeighborItem> getFeaturedItemSet(Set<Item> cartItemSet) {

		// create the map of cart items to counts
		Map<String, Integer> cartItemCountMap = new HashMap<String, Integer>();
		for (Item item : cartItemSet) {
			if (!cartItemCountMap.containsKey(item.getPartNumber())) {
				cartItemCountMap.put(item.getPartNumber(), new Integer(1));
			} else {
				Integer count = cartItemCountMap.get(item.getPartNumber());
				cartItemCountMap.put(item.getPartNumber(), count+1);
			}
		}
		
		// implement the jaccard similarity metric algorithm
		Set<BundleItemInfo> bundleItemInfoSet = null;
		
		for (BundleItemInfo bundleItemInfo : bundleItemInfoSet) {

			String bundleName = bundleItemInfo.getBundleName();
			double jaccardIndex = getJaccardIndex(cartItemCountMap, bundleItemInfo);
		}
		
		return null;
	}
	
	/*
	 * Returns the Jaccard Similarity coefficient
	 */
	private double getJaccardIndex(Map<String, Integer> cartItemCountMap, BundleItemInfo bundleItemInfo) {
		
		Map<String, Integer> bndlCountMap = bundleItemInfo.getItemCountMap();
		
		// get the minimum count from cart vs. bundle
		Map<String, Integer> minCountMap = new HashMap<String, Integer>();
		
		for (String itemName : cartItemCountMap.keySet()) {
			if (bndlCountMap.containsKey(itemName)) {
				
				Integer minCount = Math.min(cartItemCountMap.get(itemName), bndlCountMap.get(itemName));				
				minCountMap.put(itemName, minCount);
			}
		}
		
		// build the numerator for the jaccard similarity coefficient
		Integer numerator = 0;
		for (String itemName : minCountMap.keySet()) {
			numerator += minCountMap.get(itemName);
		}
		
		// build the denominator
		Integer denominator = cartItemCountMap.size() + bundleItemInfo.getItemCountMap().size();
		
		double jaccardIndex = numerator / denominator;

		return jaccardIndex;
	}
	

}
