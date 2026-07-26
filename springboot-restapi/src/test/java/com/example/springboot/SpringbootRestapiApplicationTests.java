package com.example.springboot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.example.hkms.SpringbootRestapiApplication;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.MOCK)
@ContextConfiguration(classes=SpringbootRestapiApplication.class)
@AutoConfigureMockMvc
class SpringbootRestapiApplicationTests {
	
	@Autowired
	MockMvc mockMvc;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void welcome() throws Exception {
		mockMvc.perform(get("/welcome"))
				.andExpect(status().isOk())
//				.andExpect(content().string("Welcome to my Springboot REST-API Home!!!"))
				.andDo(print());
		
	}

}
