package com.heapsteep.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/api/config")
public class ConfigDemoController {

	@Value("${app.config-demo.company}")
	private String company;

	@Value("${app.config-demo.environment}")
	private String environment;

	@Value("${app.config-demo.client-note}")
	private String clientNote;

	@Value("${microservice-1.service-id}")
	private String microservice1ServiceId;

	@GetMapping
	public Map<String, String> getConfigValues() {
		Map<String, String> config = new LinkedHashMap<>();
		config.put("source", "Spring Cloud Config Client (microservice-2)");
		config.put("app.config-demo.company", company);
		config.put("app.config-demo.environment", environment);
		config.put("app.config-demo.client-note", clientNote);
		config.put("microservice-1.service-id", microservice1ServiceId);
		return config;
	}

}
