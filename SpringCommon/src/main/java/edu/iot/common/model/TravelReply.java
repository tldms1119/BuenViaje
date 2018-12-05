package edu.iot.common.model;

import java.util.Date;

import lombok.Data;

@Data
public class TravelReply {
	private int replyId;		// 댓글 번호
	private int travelId;		// 글 그룹(게시글 ID)
	private String password;
	private int level;			// 댓글 수준
	private int parent;			// 상위 글 번호
	private String writer;		// 작성자 ID
	private String content;		// 내용
	private int deleted;		// 삭제 여부
	private Date regDate;
	private Date updateDate;
}
