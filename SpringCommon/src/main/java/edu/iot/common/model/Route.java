package edu.iot.common.model;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class Route {
	 private long routeId;
	 @NotEmpty(message="필수 항목입니다")
	 private String writer;
	 @NotEmpty(message="필수 항목입니다")
	 private String title;
	 @NotEmpty(message="필수 항목입니다")
	 private String password;
	 @NotEmpty(message="필수 항목입니다")
	 private String country;
	 @NotEmpty(message="필수 항목입니다")
	 private String city;
	 private String description;
	 private int readCnt;
	 private List<TourSpot> list;
	 private Date regDate;
	 private Date updateDate;
}
