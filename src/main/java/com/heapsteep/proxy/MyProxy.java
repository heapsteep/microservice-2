package com.heapsteep.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.heapsteep.bean.Microservice2;

@FeignClient(name="microservice-1", url="localhost:8000")
public interface MyProxy {
	@GetMapping("microservice-1/from/{from}/to/{to}")
	public Microservice2 getValue(@PathVariable String from, @PathVariable String to);
}
