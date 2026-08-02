package com.example.demo.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
	private String userid;
	private String passwd;
	private String name;
	private String email;
	private Date regdate;
	private Date uptdate;

}
