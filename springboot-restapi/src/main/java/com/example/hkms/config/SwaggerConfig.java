package com.example.hkms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.components(new Components())
				.info(apiInfo());
	}
	
	private Info apiInfo() {
		Contact contact = new Contact();
		contact.name("홍길동").email("kdhong@email.com");
		
		return new Info()
				.title("SpringDoc Test Example Swagger")
				.description("Springdoc을 사용한 Swagger UI 문서입니다.")
				.contact(contact)
				.version("1.0.0");
	}

}
