package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.model.Employee;
import com.example.demo.service.TestOneService;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(TestOneController.class)
//@ExtendWith(MockitoExtension.class)
public class TestOneControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private TestOneService testOneService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void returnStringWithResponseEntity() throws Exception {
		String output = "Hello World!";
		
		mockMvc.perform(get("/api/v9/test/get"))
				.andExpect(status().isOk())
				.andExpect(content().string(output))
				.andDo(print())
				;
	}
	
	@Test
	public void returnStringFromService() throws Exception {
		String output = "Hello World!";
		
		// 지정한 메서드가 호출되는 상황을 포착함. 그 상황에서 진짜로 실행하지 않고, 미리 준비한 output 데이터를 결과값으로 돌려줌
		when(testOneService.giveMeString()).thenReturn(output);
		
		mockMvc.perform(get("/api/v9/test/service"))
				.andExpect(status().isOk())
				.andExpect(content().string(output))
				.andDo(print())
				;
	}
	
	@Test
	public void saveData() throws Exception {
		String input = "123";
		String output = String.format("%s - Data Saved", input);
		
		when(testOneService.saveData(anyString())).thenReturn(output);
		
		mockMvc.perform(post("/api/v9/test/save")
						.param("data", input))
				.andExpect(status().isOk())
				.andExpect(content().string(output));
		
	}
	
	@Test
	public void saveEmployee() throws Exception {
		Employee input = new Employee(null, "John", 28);
		String requestBody = objectMapper.writeValueAsString(input);
		
		Employee savedEmployeeOutput = new Employee(UUID.randomUUID().toString(), "John", 28);
		
		//when(testOneService.saveEmployee(any(Employee.class))).thenReturn(savedEmployeeOutput);
		
		mockMvc.perform(post("/api/v9/test/save-employee")
						.content(requestBody)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value(savedEmployeeOutput.id()))
				.andExpect(jsonPath("$.name").value(savedEmployeeOutput.name()))
				.andExpect(jsonPath("$.age").isNumber())
				;		
		
		System.out.println(objectMapper.writeValueAsString(savedEmployeeOutput));
		
	}
	
	@Test
	public void readData() throws Exception {
		MockMultipartFile input = new MockMultipartFile("file",
											"hello.txt",
											MediaType.TEXT_PLAIN_VALUE,
											"Hey".getBytes());
		
		mockMvc.perform(multipart("/api/v9/test/read-data")
						.file(input)
						.contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(status().isOk())
				.andExpect(content().string("hello.txt"));
	}
	
	

}
