package com.example.hkms.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.hkms.service.CompanyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/company")
@Tag(name="회사 API", description="Swagger 회사 API endpoint 설정입니다.")
public class CompanyController {
	private final static Logger logger = LoggerFactory.getLogger(CompanyController.class);
	
	@Resource
	private CompanyService companyService;
	
	@Operation(summary="회사-인사정보", description="회사-인사정보입니다.")
	@GetMapping("/list")
	public ModelAndView allListView(Map<String, Object> map) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		List<Map<String, Object>> allList = companyService.selectAllList();
		
		mav.addObject("AllList", allList);
		mav.setViewName("list");
		
		return mav;
	}
	
	
	@Operation(summary="회사-인사정보(json)", description="회사-인사정보입니다.")
	@GetMapping("/listjson")
	public List<Map<String, Object>> allListJson(Map<String, Object> map) throws Exception {
		List<Map<String, Object>> allList = companyService.selectAllList();
		
		// Convert object to json
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(allList);
		logger.debug(jsonInString);
		
		return allList;
	}

}
