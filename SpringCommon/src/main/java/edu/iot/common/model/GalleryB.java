package edu.iot.common.model;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class GalleryB {
	private long galleryId;
	@NotEmpty(message="필수 항목입니다.")
	private String title;
	@NotEmpty(message="필수 항목입니다.")
	private String password;
	@NotEmpty(message="필수 항목입니다.")
	private String country;
	@NotEmpty(message="필수 항목입니다.")
	private String owner;
	private String description;
	private int readCnt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateDate;
	private List<GalleryImageB> list;
	
	public String getSummary() {
		if(description.length() < 34) {
			return description + "<br><br>";
		} else {
			String summary = description.substring(0, 31) + "...";
			return summary;
		}
	}
}
