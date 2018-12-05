package edu.iot.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iot.common.dao.AttachmentBDao;
import edu.iot.common.dao.TravelDao;
import edu.iot.common.exception.PasswordMissmatchException;
import edu.iot.common.model.AttachmentB;
import edu.iot.common.model.Pagination;
import edu.iot.common.model.Search;
import edu.iot.common.model.Travel;

@Service
public class TravelServiceImpl implements TravelService {
	
	@Autowired
	TravelDao dao;
	
	@Autowired
	AttachmentBDao attachmentBDao;

	@Override
	public Map<String, Object> getPage(int page, String orderBy) throws Exception {
		Map<String, Object> map = new HashMap<>();

		int total = dao.count();
		Pagination pagination = new Pagination(page, total);
		Map<String, Object> pageMap = pagination.getPageMap();
		pageMap.put("orderBy", orderBy);
		
		map.put("pagination", pagination);
		map.put("list", dao.getPage(pageMap));
		
		return map;
	}
	
	@Transactional
	@Override
	public void create(Travel travel) throws Exception {
		int result = dao.insert(travel);
		if(result == 1) {
			throw new RuntimeException("test");
			//thorw new Exception("test"); 얘는 안됨
		}
		
		for(AttachmentB attachmentB: travel.getAttachList()) {
			//attachmentB.setAttachmentId(1);
			attachmentB.setTravelId(travel.getTravelId());		// foreign key 설정
			attachmentBDao.insert(attachmentB);
		}
	}

	@Override
	public void increaseReadCnt(long travelId) throws Exception {
		dao.increaseReadCnt(travelId);
	}

	@Override
	public Travel findById(long travelId) throws Exception {
		Travel travel = dao.findById(travelId);
		travel.setAttachList(attachmentBDao.getList(travelId));
		return travel;
	}

	@Override
	public AttachmentB getAttachment(long attachmentId) throws Exception {
		return attachmentBDao.findById(attachmentId);
	}

	@Override
	public void update(Travel travel) throws Exception {
		int result = dao.update(travel);
		if(result == 0) {
			throw new PasswordMissmatchException();
		}
		
		for(AttachmentB attachmentB: travel.getAttachList()) {
			attachmentB.setTravelId(travel.getTravelId());		// foreign key 설정
			attachmentBDao.insert(attachmentB);
		}
	}

	@Override
	public List<AttachmentB> getAttachList(long travelId) throws Exception {
		return attachmentBDao.getList(travelId);
	}

	@Override
	public void deleteAttachment(long attachmentId, long travelId, String password) throws Exception {
		Travel travel = dao.findById(travelId);
		
		if(!password.equals(travel.getPassword())) {
			throw new PasswordMissmatchException();
		}
		
		attachmentBDao.delete(attachmentId);
	}

	@Override
	public void delete(Travel travel) throws Exception {
		Travel t = dao.findById(travel.getTravelId());
		
		if(!t.getPassword().equals(travel.getPassword())) {
			throw new PasswordMissmatchException();
		}
		
		attachmentBDao.deleteByTravelId(travel.getTravelId());
		dao.delete(travel.getTravelId());
	}

	@Override
	public Map<String, Object> search(Search search, int page) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		Pagination pagination = new Pagination(page, dao.search(search).size());
		
		map.put("list", dao.search(search));
		map.put("pagination", pagination);
		
		return map;
	}

}
