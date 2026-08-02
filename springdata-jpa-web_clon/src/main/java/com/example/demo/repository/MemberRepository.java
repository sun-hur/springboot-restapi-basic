package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Member;

/**
 * ORM (Object Relational Mapping)
 *   1) JPA (hibernate 이 발전된 형태임, 쿼리를 몰라도 간단한 코드 형태로 처리, 복잡한 쿼리에는 부적합) 
 *   2) Mybatis (복잡한 쿼리도 수행, 효율성 좋음)
 *
 * [ref] https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
 */

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	// Optional 은 select 시 데이터가 없는 경우 null 이 되는데 null point exception 을 방지해주는 기능
	Optional<Member> findByUseridAndPasswd(String userid, String passwd);
}
