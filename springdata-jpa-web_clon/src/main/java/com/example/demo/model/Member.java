package com.example.demo.model;

import java.util.Date;

import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Persistable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

@Entity
@Table(name="TBL_MEMBER")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member implements Persistable<String> {
	@Transient
	@Builder.Default
	private boolean isNew = false;	// false(update), true(create)
	
	@Id
	private String userid;		// Id : Primary Key
	
	@Column(name = "USERPW")
	private String passwd;
	
	@Column(name="USERNAME")
	private String name;
	private String email;
	private Date regdate;
	private Date udtdate;
	
	@Override
	public @Nullable String getId() {
		return this.userid;
	}
	@Override
	public boolean isNew() {
		return this.isNew;
	}

}
