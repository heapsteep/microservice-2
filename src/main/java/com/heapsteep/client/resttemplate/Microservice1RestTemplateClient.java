package com.heapsteep.client.resttemplate;

import com.heapsteep.model.GreetingResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Microservice1RestTemplateClient {

	private final RestTemplate restTemplate;
	private final String baseUrl;

	public Microservice1RestTemplateClient(RestTemplateBuilder restTemplateBuilder,
	                                       @Value("${microservice1.base-url}") String baseUrl) {
		this.restTemplate = restTemplateBuilder.build();
		this.baseUrl = baseUrl;
	}

	public GreetingResponse getGreeting(String name) {
		return restTemplate.getForObject(baseUrl + "/api/greetings/{name}", GreetingResponse.class, name);
	}
}