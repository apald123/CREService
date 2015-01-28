package com.cisco.cre.service.impl;


import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.drools.KnowledgeBase;
import org.drools.builder.ResourceType;
import org.drools.conf.EventProcessingOption;
import org.drools.runtime.StatelessKnowledgeSession;
import org.springframework.stereotype.Service;

import com.cisco.cre.bean.NeighborItem;
import com.cisco.cre.drools.facts.RecommendationFact;
import com.cisco.cre.drools.util.DroolsResource;
import com.cisco.cre.drools.util.DroolsUtil;
import com.cisco.cre.drools.util.ResourcePathType;
import com.cisco.cre.drools.util.TrackingAgendaEventListener;
import com.cisco.cre.drools.util.TrackingWorkingMemoryEventListener;
import com.cisco.cre.service.RecommendationRulesService;
import com.cisco.cre.util.LogUtil;

@Service("recommendationRulesService")
public class RecommendationRuleServiceImpl implements
		RecommendationRulesService {
	
    private KnowledgeBase kbase;
       
    public RecommendationRuleServiceImpl() {
        this.kbase = DroolsUtil.createKnowledgeBase(
                new DroolsResource[]{ 
                        new DroolsResource("rules/Recommendations.drl", 
                                ResourcePathType.CLASSPATH, 
                                ResourceType.DRL)
                }, 
                EventProcessingOption.CLOUD);      
        
    }

	@Override
	public Set<NeighborItem> filterRecommendations(Set<String> cartItemSet, Set<NeighborItem> nbrItemSet) {
		
		// get the cutoff date - 3 months back
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, 3);		
		Date cutoffDate = cal.getTime();
		
		// this will updated by the rules and will be returned
		Set<NeighborItem> filteredSet = new HashSet<NeighborItem>(nbrItemSet);

		StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
		ksession.setGlobal("filteredSet", filteredSet);
			    
	    TrackingAgendaEventListener agendaEventListener = 
	            new TrackingAgendaEventListener();
	    TrackingWorkingMemoryEventListener workingMemoryEventListener = 
	            new TrackingWorkingMemoryEventListener();
	    ksession.addEventListener(agendaEventListener);
	    ksession.addEventListener(workingMemoryEventListener);

	    RecommendationFact rcmdFact = new RecommendationFact();
	    rcmdFact.setCartItemSet(cartItemSet);
	    rcmdFact.setCutoffDate(cutoffDate);

		// static rules
		for (NeighborItem item : nbrItemSet) {

			//LogUtil.debug(this,  item.toString());
		    rcmdFact.setNeighborItem(item);
			ksession.execute(rcmdFact);

			/*
			// only GOODS are recommended
			if (!item.getItemType().equals("MAINT")) {
				continue;
			}
			
			// item shouldn't be getting eoled in next 3 months
			if (item.getItemEOLDate().before(cutoffDate)) {
				continue;
			}
			
			// less than 60% 
			if (item.getScore() < 0.6) {
				continue;
			}
					
			// skip the cart items			
			if (cartItemSet.contains(item.getName())) {
				continue;
			}
			
			// outherwise add the item to the filtered list
			filteredList.add(item);
			*/
		}		

		
		ksession.removeEventListener(agendaEventListener);
		ksession.removeEventListener(workingMemoryEventListener);

		return filteredSet;
	}

}
