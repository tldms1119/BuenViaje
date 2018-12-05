package edu.iot.common.model;

import java.util.Date;

import lombok.Data;

@Data
public class GalleryImageB {
	private long imageId;
	private String fileName;
	private String serverFileName;
	private String source;
	private long galleryId;
	private Date regDate;
	private Date updateDate;
}
