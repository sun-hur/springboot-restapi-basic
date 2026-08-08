package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Employee;
import com.example.demo.service.TestOneService;

@RestController
@RequestMapping("/api/v9/test")
public class TestOneController {
	private final TestOneService testOneService;
	
	public TestOneController(TestOneService testOneService) {
		this.testOneService = testOneService;
	}
	
	@GetMapping("/get")
	public ResponseEntity<String> returnStringWithResponseEntity() {
		return ResponseEntity.ok("Hello World!");
	}
	
	@GetMapping("/service")
	public ResponseEntity<String> returnStringFromService() {
		String valueFromService = testOneService.giveMeString();
		return ResponseEntity.ok(valueFromService);
	}
	
	@PostMapping("/save")
	public ResponseEntity<String> saveData(@RequestParam String data) {
		return ResponseEntity.ok(testOneService.saveData(data));
	}
	
	@PostMapping("/save-employee")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<>(testOneService.saveEmployee(employee), HttpStatus.CREATED);
	}
	
	@PostMapping("/read-data")
	public ResponseEntity<String> readData(@RequestParam("file") MultipartFile file) {
		return ResponseEntity.ok(file.getOriginalFilename());
		
	}

}
