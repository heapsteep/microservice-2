package com.heapsteep.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.heapsteep.bean.Microservice2;
import com.heapsteep.proxy.MyProxy;

@RestController
public class Microservice2Controller {
	@Autowired
	private Environment environment;
	
	@Autowired
	MyProxy proxy;
	
	@GetMapping("microservice-2/from/{from}/to/{to}/quantity/{quantity}")
	public Microservice2 calculate(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		HashMap uriVariables=new HashMap();
		uriVariables.put("from",from);
		uriVariables.put("to",to);
		
		ResponseEntity<Microservice2> responseEntity=new RestTemplate().getForEntity("http://localhost:8000/microservice-1/from/USD/to/INR", Microservice2.class, uriVariables);
		Microservice2 microservice2 = responseEntity.getBody();
		
		return new Microservice2(microservice2.getId(), from, to, microservice2.getConversionMultiple(),microservice2.getEnvironment(), quantity, quantity.multiply(microservice2.getConversionMultiple())  ); 
	}
	
	@GetMapping("microservice-2-feign/from/{from}/to/{to}/quantity/{quantity}")
	public Microservice2 calculateFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		Microservice2 microservice2 = proxy.getValue(from, to);
		
		return new Microservice2(microservice2.getId(), from, to, microservice2.getConversionMultiple(),microservice2.getEnvironment(), quantity, quantity.multiply(microservice2.getConversionMultiple())  );		
	}

}
