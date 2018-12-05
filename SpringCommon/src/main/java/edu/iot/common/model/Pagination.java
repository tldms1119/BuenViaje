package edu.iot.common.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Pagination {
	public static final int PER_PAGE = 10;		// 1페이지당 데이터 건수
	public static final int PER_BLOCK = 10; 	// 1블럭 당 페이지 수
	
	private int total;			// 전체 데이터 건수
	private int page;			// 현재 페이지
	private int start;			// 현재 페이지 시작 번호
	private int end;			// 현재 페이지 끝 번호
	
	private int totalPage;		// 전체 페이지 수
	private int startPage;		// 현재 페이지 블럭에서 시작 페이지 번호
	private int endPage;		// 현재 페이지 블럭에서 마지막 페이지 번호
	
	private int currentBlock;	// 현재 페이지 블럭
	private int totalBlock;		// 전체 페이지 블럭
	private int prevBlockPage;	// 이전 페이지 블록 시작 페이지
	private int nextBlockPage;	// 다음 페이지 블록 시작 페이지
	
	public Pagination(int currentPage, int total) {
		page = currentPage;
		this.total = total;
		
		start = (page - 1)*PER_PAGE + 1;
		end = start + PER_PAGE - 1;
		totalPage = (int) Math.ceil((float)total/PER_PAGE);
		
		currentBlock = (int) Math.ceil((float)currentPage/PER_BLOCK);
		totalBlock = (int) Math.ceil((float)totalPage/PER_BLOCK);
		
		startPage = (currentBlock - 1)*PER_BLOCK + 1;
		endPage = startPage + PER_BLOCK - 1;
		
		if(endPage > totalPage) endPage = totalPage;	// 페이지가 딱 10 단위로 안 떨어질경우
		
		prevBlockPage = startPage - 10;
		nextBlockPage = endPage + 1;	
	}
	
	public Map<String, Object> getPageMap(){
		Map<String, Object> pageMap = new HashMap<>();
		pageMap.put("start", start);
		pageMap.put("end", end);
		return pageMap;
	}
}
