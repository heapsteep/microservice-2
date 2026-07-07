package com.heapsteep.controller;

import com.heapsteep.client.httpinterface.Microservice1HttpInterfaceClient;
import com.heapsteep.client.resttemplate.Microservice1RestTemplateClient;
import com.heapsteep.client.webclient.Microservice1WebClient;
import com.heapsteep.model.GreetingResponse;
import com.heapsteep.service.FeignGreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clients")
public class GreetingClientController {

	private static final Logger log = LoggerFactory.getLogger(GreetingClientController.class);

	private final Microservice1RestTemplateClient restTemplateClient;
	private final Microservice1WebClient webClient;
	private final FeignGreetingService feignGreetingService;
	private final Microservice1HttpInterfaceClient httpInterfaceClient;

	public GreetingClientController(Microservice1RestTemplateClient restTemplateClient,
	                                Microservice1WebClient webClient, FeignGreetingService feignGreetingService,
	                                Microservice1HttpInterfaceClient httpInterfaceClient) {
		this.restTemplateClient = restTemplateClient;
		this.webClient = webClient;
		this.feignGreetingService = feignGreetingService;
		this.httpInterfaceClient = httpInterfaceClient;
	}

	@GetMapping("/rest-template/{name}")
	public GreetingResponse getGreetingViaRestTemplate(@PathVariable String name) {
		log.info("Calling microservice-1 via RestTemplate for name={}", name);
		return restTemplateClient.getGreeting(name);
	}

	@GetMapping("/webclient/{name}")
	public GreetingResponse getGreetingViaWebClient(@PathVariable String name) {
		log.info("Calling microservice-1 via WebClient for name={}", name);
		return webClient.getGreeting(name);
	}

	@GetMapping("/feign/{name}")
	public GreetingResponse getGreetingViaFeign(@PathVariable String name) {
		log.info("Calling microservice-1 via Feign for name={}", name);
		return feignGreetingService.getGreeting(name);
	}

	@GetMapping("/http-interface/{name}")
	public GreetingResponse getGreetingViaHttpInterface(@PathVariable String name) {
		log.info("Calling microservice-1 via HTTP Interface for name={}", name);
		return httpInterfaceClient.getGreeting(name);
	}
}