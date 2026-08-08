package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SampleTestControllerTests {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private MemberRepository memeberRepository;
	
	@BeforeEach
	public void setUp() throws Exception {
		Member member = Member.builder()
								.userid("3333")
								.passwd("pwd3333")
								.name("SunSinLee")
								.email("ss.lee@korea.com")
								.regdate(new Date())
								.build();
		memeberRepository.save(member);
	}
	
	@Test
	public void add() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("isNew", "true");
		params.add("userid", "4444");
		params.add("passwd", "pwd4444");
		params.add("name", "bogojang");
		params.add("email", "bogojang@korea.com");
		
		mockMvc.perform(post("/api/v9/sampletest").params(params))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.success").value(true))
					.andExpect(jsonPath("$.code").value(0))
					.andExpect(jsonPath("$.msg").exists())
					.andExpect(jsonPath("$.data").exists())
					;
		
		
	}
	
	
	
}
