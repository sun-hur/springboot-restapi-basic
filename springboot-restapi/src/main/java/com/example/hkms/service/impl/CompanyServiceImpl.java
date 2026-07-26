package com.example.hkms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hkms.model.mapper.CompanyMapper;
import com.example.hkms.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	CompanyMapper companyMapper;
	
	@Override
	public List<Map<String, Object>> selectAllList() throws Exception {
		return companyMapper.selectAllList();
	}

}
