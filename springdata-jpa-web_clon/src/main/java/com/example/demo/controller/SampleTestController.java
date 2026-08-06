package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MemberDTO;
import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v9/sampletest")
@Tag(name = "SampleTest API", description = "SampleTest API 목록입니다.")
@Slf4j
public class SampleTestController {
	@Autowired
	private MemberRepository _memberRepository;
	
	@Autowired
	private ModelMapper _modelMapper;
	
	
	@GetMapping
	@Tag(name = "SampleTest Member List")
	@Operation(summary = "SampleTest Member 목록", description = "SmapleTest Member 목록을 조회")
	public List<Member> getAll() {
		return _memberRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Tag(name = "SampleTest add")
	@Operation(summary = "SampleTest Add", description = "SampleTest Add 데이터 저장")
	public Member add(@RequestBody MemberDTO dto) {
		Member member = _modelMapper.map(dto, Member.class);
		log.info("입력 파람 = {}", member);
		return _memberRepository.saveAndFlush(member);
	}
	
	@PostMapping("/one")
	@Tag(name= "SampleTest Member one")
	@Operation(summary = "SampleTest Member one", description = "SampleTest Member one 조회")
	public MemberDTO getOne(@RequestBody MemberDTO dto) {
		Member member = _modelMapper.map(dto, Member.class);
		
		log.info("memberDTO = {}", dto);
		log.info("member = {}", member);
		
		String userid = member.getUserid();
		
		Optional<Member> result = _memberRepository.findById(userid);
		
		MemberDTO one = _modelMapper.map(result, MemberDTO.class);
		return one;
	}
	
	@PostMapping("/one2")
	@Tag(name= "SampleTest Member one2")
	@Operation(summary = "SampleTest Member one2", description = "SampleTest Member one2 조회")
	public MemberDTO getOne2(@RequestBody MemberDTO dto) {
		Member person = _modelMapper.map(dto, Member.class);
		
		// Dynamic Queries Using Example Object
		Member probe = Member.builder().userid(person.getUserid()).build();
		Example<Member> example = Example.of(probe);
		
		var result = _memberRepository.findAll(example);
		Member member = result.get(0);
		
		MemberDTO one = _modelMapper.map(member, MemberDTO.class);
		
		return one;
	}
	
}
