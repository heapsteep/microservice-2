package com.heapsteep;

import com.heapsteep.client.httpinterface.Microservice1HttpInterfaceClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.service.registry.ImportHttpServices;

@SpringBootApplication
@EnableFeignClients
@ImportHttpServices(group = "microservice-1", types = Microservice1HttpInterfaceClient.class)
public class Microservice2Application {

	public static void main(String[] args) {
		SpringApplication.run(Microservice2Application.class, args);
	}

}
