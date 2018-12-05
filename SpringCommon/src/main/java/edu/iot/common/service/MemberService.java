package edu.iot.common.service;

import java.util.List;
import java.util.Map;

import edu.iot.common.model.Avata;
import edu.iot.common.model.Member;
import edu.iot.common.model.Search;

public interface MemberService {
	Member login(String id, String pw) throws Exception;
	
	void join(Member member) throws Exception;
	
	String idcheck(String id) throws Exception;
	
	Map<String, Object> getPage(int page) throws Exception;
	
	List<Member> search(Search search) throws Exception;
	
	Member findById(String userId) throws Exception;
	
	int delete(String userId) throws Exception;
	
	int update(Member member, Member sMember) throws Exception;
	
	int changePassword(Map<String, Object> map) throws Exception;
	
	int updateByAdmin(Member member) throws Exception;
	
	// 아바타 처리
	byte[] getAvata(String userId) throws Exception;
	
	void insertAvata(Avata avata) throws Exception;
	
	void updateAvata(Avata avata) throws Exception;
	
	void deleteAvata(String userId) throws Exception;
	
}
