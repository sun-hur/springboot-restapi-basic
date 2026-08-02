package com.example.demo.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Table("VIP_MEMBER")
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member implements Persistable<Long> {
	@Transient
	@Builder.Default
	private boolean isNew = false;	// true(insert), false(update)
	
	@Id
	private Long id;
	
	//@Column("DISPLAY_NAME")
	private String name;
	
	private String email;
	private Integer age;
	private Date birthdate;
	
	@Override
	public boolean isNew() {
		return this.isNew;
	}
}
