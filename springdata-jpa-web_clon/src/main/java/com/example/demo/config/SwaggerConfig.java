package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
 * [ref] http://localhost:8081/swagger-ui/index.html
 */

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
					.components(new Components()
										.addSecuritySchemes("bearer-key", new SecurityScheme()
										.type(SecurityScheme.Type.HTTP)
										.scheme("bearer")
										.bearerFormat("JWT")))
					.addSecurityItem(new SecurityRequirement().addList("bearer-key"))
					.info(apiInfo());
	}

	private Info apiInfo() {
		return new Info().title("Spring Boot API Test")
						.description("Springdoc Swagger API 문서")
						.version("1.0.0");
	}
}
