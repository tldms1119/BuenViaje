package edu.iot.common.model;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class Travel {
	long travelId;
	@NotEmpty(message="작성자는 필수 항목 입니다")
	String writer;
	@NotEmpty(message="제목은 필수 항목 입니다")
	String title;
	@NotEmpty(message="비밀번호는 필수 항목 입니다")
	String password;
	int readCnt;
	String content;
	List<AttachmentB> attachList;
	String country;
	Date regDate;
	Date updateDate;
}
