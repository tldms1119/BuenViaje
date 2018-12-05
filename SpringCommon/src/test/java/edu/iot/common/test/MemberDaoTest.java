package edu.iot.common.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import edu.iot.common.dao.MemberDao;
import edu.iot.common.model.Member;

public class MemberDaoTest extends BaseDaoTest<MemberDao>{

	@Test
	public void testCount() throws Exception {
		int count = dao.count();
		
		assert count > 0 : "count의 값은 0 이상이어야 합니다";
	}
	
	@Test 
	public void testGetPage() throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("start", 1);
		map.put("end", 10);
		List<Member> list = dao.getPage(map);
		
		assert list.size() > 0 : "getPage()의 길이가 0 이상이어야 합니다";
	}
	
/*	@Test
	public void testInsert() throws Exception {
		Member member = new Member();
		member.builder().name("test11")
						.email("aa@gmail.com")
						.password("aaa")
						.build();
		
		int result = dao.insert(member);
		assert result < 1 : "유효성 검사에 실패했습니다";
	}*/
	
}
