package edu.iot.common.dao;

import java.util.List;
import java.util.Map;

import edu.iot.common.dao.CrudDao;
import edu.iot.common.model.Member;
import edu.iot.common.model.Search;

public interface MemberDao extends CrudDao<Member, String> {
	
	int changePassword(Map<String, String> map) throws Exception;
	
	int updateByAdmin(Member member) throws Exception;
	
	List<Member> search(Search search) throws Exception;
	
}
