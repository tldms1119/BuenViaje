package edu.iot.common.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AttachmentB {
	private long attachmentId;
	private long travelId;			// 게시글 ID
	private String fileName;		// 원본 파일명
	private String location;		// 서버에서 파일명
	private Date regDate;			// 등록일
	
	public AttachmentB(String fileName, String location) {
		this.fileName = fileName;
		this.location = location;
	}
}
