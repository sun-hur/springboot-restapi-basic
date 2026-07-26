package com.example.hkms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.hkms.service.CompanyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;

@RestController
@RequestMapping("/")
@Tag(name="예제 API", description="Swagger 테스트용 API endpoint 설정입니다.")
public class WelcomeController {
	
	@Operation(summary="환영메시지", description="환영합니다.")
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to my Springboot REST-API Home!!!";
	}

	@Operation(summary="안녕메시지", description="좋은아침입니다.")
	@GetMapping("/greeting/{name}")
	public ResponseEntity<String> greeting(@PathVariable String name) {
		return ResponseEntity.ok(String.format("Good morning, %s", name));
	}

}
