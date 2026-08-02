package com.example.demo.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * (개발시만 실행됨) java -jar app.jar --spring.profiles.active=dev
 * (환경변수) SPRING_PROFILES_ACTIVE=dev
 * @Profile("dev")
 */

@Component
@RequiredArgsConstructor
@Profile("dev")
@Slf4j
public class DataInitializer implements ApplicationRunner {
	private final MemberRepository memberRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		//Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse("2021-12-02 11:03:09");
		
		var member1 = Member.builder()
							.isNew(false)	// true(insert), false(update)
							.id(1L)
							.name("홍길동")
							.email("hongkildong@korea.com")
							.age(30)
							.birthdate(date)
							.build();
		
		memberRepository.save(member1);
		
		var member2 = Member.builder()
							.isNew(false)	// true(insert), false(update)
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
