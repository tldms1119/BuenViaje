package edu.iot.common.model;

import java.util.Date;

import lombok.*;

@Data
@NoArgsConstructor
public class Attachment {
	private long attachmentId;
	private long boardId;			// 게시글 ID
	private String fileName;		// 원본 파일명
	private String location;		// 서버에서 파일명
	private Date regDate;			// 등록일
	
	public Attachment(String fileName, String location) {
		this.fileName = fileName;
		this.location = location;
	}
}
