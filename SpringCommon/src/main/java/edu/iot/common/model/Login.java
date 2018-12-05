package edu.iot.common.model;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class Login {
	@NotEmpty(message="사용자 ID는 필수 항목 입니다")
	private String userId;
	
	@NotEmpty(message="비밀 번호는 필수 항목입니다")
	private String password;
	
	private String url;
	
	private String reason;
}
