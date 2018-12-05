package edu.iot.common.model;

import lombok.Data;

@Data
public class Search {
	private String category;
	private String keyword;
	private String secondCategory = null;
	private String secondKeyword = null;
	//private String orderBy;
	
	public void setKeyword(String keyword) {
		if(keyword.startsWith("%")) {
			this.keyword = keyword;
		} else {
			this.keyword = "%" + keyword + "%";
		}
	}
	
	public void setSecondKeyword(String keyword) {
		this.secondKeyword = "%" + keyword + "%";
	}
	
}
