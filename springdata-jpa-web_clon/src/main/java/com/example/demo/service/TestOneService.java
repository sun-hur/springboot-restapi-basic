package com.example.demo.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;

@Service
public class TestOneService {
	
	public String giveMeString() {
		return "Hello World!";
	}
	
	public String saveData(String data) {
		return String.format("%s - Data Saved!", data);
	}
	
	public Employee saveEmployee(Employee employee) {
		return new Employee(
					UUID.randomUUID().toString(),
					employee.name(),
					employee.age()
				);
	}

}
