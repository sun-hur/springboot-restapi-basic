package com.example.demo.model;

import java.util.Date;

import org.springframework.data.domain.Persistable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Table(name = "VIP_MEMBER")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member implements Persistable<Long> {
	@Transient
	@Builder.Default
	private boolean isNew = false;	// true(insert), false(update)
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@Column(name = "DISPLAY_NAME")
	private String name;
	private String email;
	private Integer age;
	private Date birthdate;
	
	@Override
	public boolean isNew() {
		return this.isNew;
	}

}
