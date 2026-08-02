package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JdbcApplication implements ApplicationRunner {
	@Autowired
	private final MemberRepository memberRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// 초기 데이터 입력
		long count = memberRepository.count();
		if (count <= 0) dataInitializer();
		log.info(" 멤버수: {} - ref.멤버 없을때만 추가합니다.", count);
		
		// 데이터 조회 - MemberRepository 에 추가된 메서드 조회
		var members = memberRepository.findByName("홍길동");
		log.info("회원 = {}", members);
		
		members = memberRepository.findByEmail("leesunsin@korea.com");
		log.info("회원 = {}", members);
		
		members = memberRepository.findByAgeGreaterThan(30);
		log.info("회원 = {}", members);
		
		
		/*******[참고] .findAll(example) 의 Example 객체는 JpaRepository 만 가능함. CrudRepository 에서는 사용 불가함.
		// Dynamic Queries Using Example Object
		Member probe = new Member();
		probe.setName("이순신");
		probe.setAge(35);		// 이름과 나이만 조건 적용
		
		Example<Member> example = Example.of(probe);
		var result = memberRepository.findAll(example);
		log.info("회원 = {}", result);
		******/
	}
	
	
	/**
	 * 초기 데이터 입력 => ./config/DataInitializer.java 로 전환함
	 */
	private void dataInitializer() throws Exception {
		//Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse("2021-12-02 11:03:09");
		
		var member1 = Member.builder()
							.isNew(true)	// true(insert), false(update)
							.id(1L)
							.name("홍길동")
							.email("hongkildong@korea.com")
							.age(30)
							.birthdate(date)
							.build();
		
		memberRepository.save(member1);
		
		var member2 = Member.builder()
							.isNew(true)	// true(insert), false(update)
							.id(2L)
							.name("이순신")
							.email("leesunsin@korea.com")
							.age(35)
							.birthdate(date)
							.build();
		
		memberRepository.save(member2);
		
		// 데이터 전체 조회
		//List<Member> members = (List<Member>) memberRepository.findAll();
		var members = memberRepository.findAll();
		log.info("회원 = {}", members);
	}

}
