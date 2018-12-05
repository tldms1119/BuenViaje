package edu.iot.common.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import edu.iot.common.model.Member;
import edu.iot.common.service.MemberService;

public class MemberServiceTest extends BaseServiceTest<MemberService> {
	
	@Test
	public void testGetPage() throws Exception {
		Map<String, Object> map = service.getPage(1);
		
		assert map.containsKey("pagination") :
										"Pagination 객체가 없습니다";
		
		assert map.containsKey("list") : "List 객체가 없습니다";
	}
	
	@Test
	public void testFindById() throws Exception {
		Member member = service.findById("admin");
		
		assert member != null : "해당되는 id 가 없습니다";
	}
	
	@Test
	public void testDelete() throws Exception {
		int result = service.delete("test");
		
		assert result == 1 : "삭제에 실패했습니다";
	}
	
}
