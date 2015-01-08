package com.cisco.cre.controller;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cisco.cre.service.RecommendationService;
import com.cisco.cre.util.LogUtil;
import com.cisco.cre.vo.RecommendationRequestVO;
import com.cisco.cre.vo.RecommendationResponseVO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/*
import dw.spring3.rest.util.AtomUtil;
*/


@Controller
public class RecommendationController {
	
	private RecommendationService rcmdService;

	@Autowired
	public RecommendationController(RecommendationService service) {
		this.rcmdService = service;
	}
	
	@RequestMapping(value="/recommendations", method=RequestMethod.POST)
	public @ResponseBody RecommendationResponseVO getRecommendations(@RequestBody RecommendationRequestVO recoReq) 
			throws Exception {
		
		LogUtil.debug(this, "RecommendationRequest="+recoReq.toString());
		
		RecommendationResponseVO recoResp = rcmdService.getRecommendations(recoReq);
		
		/*
		 * rcmdRulesService.executeRules(recoResp);
		 */
		
		LogUtil.debug(this, "Recommendation Response="+recoResp.toString());
		return recoResp;
	}

	@RequestMapping(value="/recommendations1", method=RequestMethod.POST)
	public @ResponseBody RecommendationResponseVO getRecommendations(@RequestBody String recoReqStr) 
			throws JsonParseException, JsonMappingException, IOException, Exception {
		
		LogUtil.debug(this, "RecommendationRequest="+recoReqStr);

        //convert json string to object
        ObjectMapper objectMapper = new ObjectMapper();     

        RecommendationRequestVO recoReq = objectMapper.readValue(recoReqStr, RecommendationRequestVO.class);         
        //System.out.println("Recommendation Request\n"+recoReq);

        StringWriter recoRespStr = new StringWriter();
        RecommendationResponseVO recoResp = rcmdService.getRecommendations(recoReq);
        objectMapper.writeValue(recoRespStr, recoResp);
        		
        LogUtil.debug(this, "Recommendation Response="+recoRespStr.toString());

		return recoResp;
	}
	
	@RequestMapping(value = "/recommendations2", method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<String> handle(HttpEntity<String> requestEntity) 
			throws JsonParseException, JsonMappingException, IOException, Exception {
		
		String requestHeader = requestEntity.getHeaders().getFirst("MyRequestHeader");
		//byte[] requestBody = requestEntity.getBody();		
		String request = requestEntity.getBody();
		System.out.println("Request="+request);
		//JsonReader reader = Json.createReader(new StringReader(request));
		//JsonObject jobj = reader.readObject();
		//System.out.println("JSON Object="+jobj);
		
		//JsonArray jarr = jobj.getJsonArray("phoneNumbers");
		//System.out.println("JSON Array="+jarr);
		
        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();
         
        //convert json string to object
        RecommendationRequestVO recoReq = objectMapper.readValue(request, RecommendationRequestVO.class);         
        System.out.println("Recommendation Request\n"+recoReq);

        StringWriter recoRespStr = new StringWriter();
        RecommendationResponseVO recoResp = rcmdService.getRecommendations(recoReq);
        objectMapper.writeValue(recoRespStr, recoResp);
        		
        System.out.println("Recommendation Response="+recoRespStr.toString());
        		
		// do something with request header and body
        /*
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		*/
		return new ResponseEntity<String>(recoRespStr.toString(), null, HttpStatus.CREATED);
	}

}

/*
Input :

{ 
      "custSegment":"com",
      "userId":"apaldhik",
      "items":[ 
         "CSM4",
         "IPS6000"
      ],
      "coOccuranceFlag":"Y",
      "similarityFlag":"Y",
      "featuredFlag":"Y"
}

ES Data Model ={

        "name": "ASA-5000",
        "description" : "ASA 5500 Adaptive Security Appliance",
        "type": "Security Appliance",
        "coOccurances": [
            {
                "name": "IPS6000",
                "description" : "Intrusion Prevention",
                "type": "Software",
                "score": 0.85,
                "lastCopurchasedOn": "01-Nov-2014" //2014-11-01
            },
            {
                "name": "CSM4",
                "description": "Cisco Security Manager",
                "type": "Software",
                "score": 0.65,
                "lastCopurchasedOn": "01-Nov-2014"
            }
        ],
        "similarity": [
            {
                "name": "IPS6000",
                "description" : "Intrusion Prevention",
                "type": "Software",
                "score": 0.85,
                "lastCopurchasedOn": "01-Nov-2014"
            }
        ]
}

Output :



@RequestMapping(method=RequestMethod.GET, value="/emp/{id}", headers="Accept=application/xml, application/json")
public @ResponseBody Employee getEmp(@PathVariable String id, HttpServletRequest req) {
	Employee e = employeeDS.get(Long.parseLong(id));
	System.out.println("HttpRequest="+req.getParameter("data"));
	return e;
}

@RequestMapping(method=RequestMethod.GET, value="/emps", headers="Accept=text/html")
public @ResponseBody String getAllEmpHtml() {
	List<Employee> employees = employeeDS.getAll();
	StringWriter sw = new StringWriter();
	ObjectMapper om = new ObjectMapper();
	try {
		om.writeValue(sw, employees);
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	System.out.println(sw.toString());
	return sw.toString();
}

@RequestMapping(method=RequestMethod.GET, value="/emps", headers="Accept=application/xml, application/json")
public @ResponseBody EmployeeList getAllEmp() {
	List<Employee> employees = employeeDS.getAll();
	EmployeeList list = new EmployeeList(employees);
	return list;
}

*/
