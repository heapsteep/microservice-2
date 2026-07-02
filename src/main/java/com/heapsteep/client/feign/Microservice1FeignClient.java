package com.heapsteep.client.feign;

import com.heapsteep.model.GreetingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${microservice-1.service-id}")
public interface Microservice1FeignClient {

	@GetMapping("/api/greetings/{name}")
	GreetingResponse getGreeting(@PathVariable("name") String name);

}