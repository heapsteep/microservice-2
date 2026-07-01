package com.heapsteep.client.httpinterface;

import com.heapsteep.model.GreetingResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/api/greetings")
public interface Microservice1HttpInterfaceClient {

	@GetExchange("/{name}")
	GreetingResponse getGreeting(@PathVariable String name);

}