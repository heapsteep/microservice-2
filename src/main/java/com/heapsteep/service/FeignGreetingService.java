package com.heapsteep.service;

import com.heapsteep.client.feign.Microservice1FeignClient;
import com.heapsteep.model.GreetingResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FeignGreetingService {

	private static final Logger log = LoggerFactory.getLogger(FeignGreetingService.class);

	private final Microservice1FeignClient feignClient;

	public FeignGreetingService(Microservice1FeignClient feignClient) {
		this.feignClient = feignClient;
	}

	@CircuitBreaker(name = "CircuitBreaker1", fallbackMethod = "getGreetingFallback")
	public GreetingResponse getGreeting(String name) {
		return feignClient.getGreeting(name);
	}

	public GreetingResponse getGreetingFallback(String name, Throwable cause) {
		log.warn("Circuit breaker fallback triggered for name={}: {}", name, cause.toString());
		return new GreetingResponse(name,
				"Fallback response from microservice-2 (microservice-1 unavailable via Feign)");
	}

}
