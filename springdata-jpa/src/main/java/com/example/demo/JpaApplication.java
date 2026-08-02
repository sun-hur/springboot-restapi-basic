package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JpaApplication implements ApplicationRunner {
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// 초기 데이터 입력
		long count = memberRepository.count();
		if (count <= 0) dataInitializer();
		log.info(" 멤버수: {} - ref.멤버 없을때만 추가합니다.", count);
		
		// 데이터 삭제
//		memberRepository.deleteById(1L);
		
		// 데이터 조회 - MemberRepository 에 추가된 메서드 조회
		var member = memberRepository.findByEmail("hongbanjang@korea.com");
		log.info("회원 = {}", member);
		
		
		/*******[참고] .findAll(example) 의 Example 객체는 JpaRepository 만 가능함. CrudRepository 에서는 사용 불가함.   ***/
		// Dynamic Queries Using Example Object
		//Member probe = new Member();
		//probe.setName("이순신");
		//probe.setAge(35);		// 이름과 나이만 조건 적용
		var probe = Member.builder().name("이순신").age(35).build();	// 이름과 나이만 조건 적용
		
		Example<Member> example = Example.of(probe);
		
		var result = memberRepository.findAll(example);
		log.info("회원 = {}", result);
		
	}
	
	/**
	 * 초기 데이터 입력 => ./config/DataInitializer.java 로 전환함
	 */
	private void dataInitializer() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse("2023-09-11 10:22:03");
		
		var member1 = Member.builder()
							.isNew(true)	// true(insert), false(update)
							.id(3L)
							.name("홍길순")
							.email("hongkilsun@korea.com")
							.age(20)
							.birthdate(date)
							.build();
		
		memberRepository.save(member1);
		
		var member2 = Member.builder()
				.isNew(true)	// true(insert), false(update)
				.id(4L)
				.name("홍반장")
				.email("hongbanjang@korea.com")
				.age(23)
				.birthdate(date)
				.build();
		
		memberRepository.save(member2);
		
		var members = memberRepository.findAll();
		log.info("회원목록 = {}", members);
	}

}
