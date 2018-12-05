package edu.iot.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.iot.common.dao.AvataDao;
import edu.iot.common.dao.MemberDao;
import edu.iot.common.model.Avata;
import edu.iot.common.model.Member;
import edu.iot.common.model.Pagination;
import edu.iot.common.model.Search;
import edu.iot.common.exception.LoginFailException;
import edu.iot.common.exception.PasswordMissmatchException;
import edu.iot.common.sec.SHA256Util;
import edu.iot.common.util.ImageUtil;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDao dao;
	
	@Autowired
	AvataDao avataDao;
	
	byte[] anonyAvata;
	
	@PostConstruct			// 생성 이후 모든 주입 객체들까지 다 준비되었을 때 실행해달라는 @
	public void setup() {
		Avata avata;
		try {
			avata = avataDao.findById("anonymous");
			anonyAvata = avata.getImage();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Member login(String id, String pw) throws Exception {
		Member user = dao.findById(id);
		if(user != null) {
			String result = SHA256Util.getEncrypt(pw, user.getSalt());
			if(result.equals(user.getPassword())) {
				return user;
			} else {
				throw new LoginFailException("비밀번호가 일치하지 않습니다");
			}
		} else {
			throw new LoginFailException("존재하지 않는 ID 입니다");
		}			
	}

	@Override
	public void join(Member member) throws Exception {
		String salt = SHA256Util.generateSalt();
		String encPw = SHA256Util.getEncrypt(member.getPassword(), salt);
		
		member.setSalt(salt);
		member.setPassword(encPw);
		
		dao.insert(member);
	}

	@Override
	public String idcheck(String id) throws Exception {
		Member member = dao.findById(id);
		if(member == null) {
			return "ok";
		}
		return "fail";
	}

	/*@Override
	public Map<String, Object> getPage(int page) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		int total = dao.count();
		int totalPage = (int) Math.ceil(total/10.);
		
		int start = (page-1)*10 + 1;
		int end = start + 9;
		Map<String, Integer> pageMap = new HashMap<>();
		pageMap.put("start", start);
		pageMap.put("end", end);
		
		List<Member> list = dao.getPage(pageMap);
		
		map.put("page", page);
		map.put("total", total);
		map.put("totalPage", totalPage);
		map.put("list", list);
		
		return map;
	}*/
	
	@Override
	public Map<String, Object> getPage(int page) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		int total = dao.count();
		Pagination pagination = new Pagination(page, total);
		map.put("pagination", pagination);
		map.put("list", dao.getPage(pagination.getPageMap()));
		
		return map;
	}

	@Override
	public Member findById(String userId) throws Exception {
		
		Member member = dao.findById(userId);
		
		return member;
	}

	@Override
	public int delete(String userId) throws Exception {
		return dao.delete(userId);
	}

	@Override
	public int update(Member member, Member sMember) throws Exception {
		String password = SHA256Util.getEncrypt(member.getPassword(), sMember.getSalt());

		member.setPassword(password);
		
		if(dao.update(member) == 0) {
			throw new PasswordMissmatchException();
		}
		
		return 1;
	}

	@Override
	public int changePassword(Map<String, Object> map) throws Exception {
		Map<String, String> daoMap = new HashMap<>();
		Member member = (Member) map.get("member");
	
		String oldPassword = SHA256Util.getEncrypt((String)map.get("oldPassword"), member.getSalt());
		String newPassword = SHA256Util.getEncrypt((String)map.get("newPassword"), member.getSalt());
		
		daoMap.put("oldPassword", oldPassword);
		daoMap.put("newPassword", newPassword);
		daoMap.put("userId", member.getUserId());
		
		return dao.changePassword(daoMap);
	}

	@Override
	public int updateByAdmin(Member member) throws Exception {
		return dao.updateByAdmin(member);
	}

	@Override
	public List<Member> search(Search search) throws Exception {
		return dao.search(search);
	}

	@Override
	public byte[] getAvata(String userId) throws Exception {
		Avata avata = avataDao.findById(userId);
		// 아바타 이미지가 없는 경우
		if(avata == null) {
			// 익명 사용자 미리 하나 등록 해둠
			//avata = avataDao.findById("anonymous");
			return anonyAvata;
		}
		return avata.getImage();
	}

	@Override
	public void insertAvata(Avata avata) throws Exception {
		avata.setImage(ImageUtil.makeThumb(avata.getImage()));
		avataDao.insert(avata);
	}

	@Override
	public void updateAvata(Avata avata) throws Exception {
		avata.setImage(ImageUtil.makeThumb(avata.getImage()));
		
		if(avataDao.update(avata) == 0) {		// 존재하지 않는 경우(아바타 처음 등록인 경우)
			avataDao.insert(avata);
		}
	}

	@Override
	public void deleteAvata(String userId) throws Exception {
		avataDao.delete(userId);
	}

	
}
