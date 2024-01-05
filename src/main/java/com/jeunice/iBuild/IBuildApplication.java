package com.jeunice.iBuild;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition
@SpringBootApplication
public class IBuildApplication {

	public static void main(String[] args) {
		SpringApplication.run(IBuildApplication.class, args);
	}

}
