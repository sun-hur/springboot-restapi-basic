package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MemberDTO;
import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/members")
@Tag(name = "Member API", description = "Member API 목록입니다.")
public class MemberController {
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/")
	@Tag(name = "Member List")
	@Operation(summary = "Member 목록", description = "Member 목록을 조회")
	public String memberList(Model model) {
		List<Member> list = memberRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
		model.addAttribute("list", list);
		return "member/list";
	}
	
	@GetMapping("write")
	public String write() {
		return "member/write";
	}
	
	/**
	 * [참고] Entity 와 DTO
	 * 
	 * entity: 모델객체(도메인객체)로서 테이블과 일대일 관계로 매칭되어 처리됨
	 * dto : 자료 교환(화면에서 컨트롤러로 데이터를 보낼때 dto에 담아서 보냄)
	 * 
	 *     화면 ---------------> 컨트롤러 -------------------> 모델 (DB) 
	 *               dto                 entity(table모델)
	 *                |                        |
	 *                ------> modelMapper ----->
	 *                    
	 * modelMapper: dto와 entity 간에 쉽게 자료를 전달할 수 있게 해주는 매핑(dto와 entity 간에 데이터를 호환해주는 역할)
	 * 
	 */
	@PostMapping("insert")
	public String insert(@ModelAttribute MemberDTO dto) {
		Member member = modelMapper.map(dto, Member.class);
		member.setRegdate(new Date());
		memberRepository.save(member);
		return "member/insert";
	}
	
	@PostMapping("view")
	public String view(@RequestParam(name = "userid") String userid, Model model) {
		Optional<Member> result = memberRepository.findById(userid);
		Member member = result.get();
		model.addAttribute("dto", member);		
		return "member/detail";
	}
	
	@PostMapping("update")
	public String update(@ModelAttribute MemberDTO dto, Model model) {
		Optional<Member> result = memberRepository.findByUseridAndPasswd(dto.getUserid(), dto.getPasswd());
		if(result.isPresent()) {
			Member member = modelMapper.map(dto, Member.class);
			member.setRegdate(result.get().getRegdate());
			memberRepository.save(member);
			return "redirect:/";
		} else {
			model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
			Optional<Member> m = memberRepository.findById(dto.getUserid());
			Member member = m.get();
			model.addAttribute("dto", member);			
			return "member/detail";
		}
	}
	
	@PostMapping("delete")
	public String delete(@RequestParam(name = "userid") String userid, @RequestParam(name = "passwd") String passwd, Model model) {
		Optional<Member> result = memberRepository.findByUseridAndPasswd(userid, passwd);
		if(result.isPresent()) {
			memberRepository.deleteById(userid);
			return "redirect:/";
		} else {
			model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
			Optional<Member> m = memberRepository.findById(userid);
			Member member = m.get();
			model.addAttribute("dto", member);			
			return "member/detail";
		}
	}
	
	
	
	

}
