package com.heapsteep.client.webclient;

import com.heapsteep.model.GreetingResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class Microservice1WebClient {

	private final WebClient webClient;

	/*public Microservice1WebClient(WebClient.Builder webClientBuilder,
	                              @Value("${microservice1.base-url}") String baseUrl) {
		this.webClient = webClientBuilder.baseUrl(baseUrl).build();
	}*/

	public Microservice1WebClient(@LoadBalanced WebClient.Builder webClientBuilder,
	                              @Value("${microservice-1.service-id}") String serviceId) {
		this.webClient = webClientBuilder.baseUrl("http://" + serviceId).build();
	}

	public GreetingResponse getGreeting(String name) {
		return webClient.get()
				.uri("/api/greetings/{name}", name)
				.retrieve()
				.bodyToMono(GreetingResponse.class)
				.block();
	}
}