package edu.iot.common.model;

import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
	private int			seq;
	@NotEmpty(message="필수 항목 입니다")
	@Length(min=4, message="4글자 이상이어야 합니다")
	private String 		userId;
	@NotEmpty(message="필수 항목 입니다")
	private String 		name;
	@NotEmpty(message="필수 항목 입니다")
	private String 		password;
	private String 		salt;
	private UserLevel 	userLevel;
	private String 		phoneNumber;
	private String 		nickName;
	@Email
	@NotEmpty(message="필수 항목 입니다")
	private String 		email;
	private String 		address;
	private Date 		regDate;
	private Date 		updateDate;
}
