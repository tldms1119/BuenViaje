package edu.iot.common.service;

import java.util.List;
import java.util.Map;

import edu.iot.common.model.AttachmentB;
import edu.iot.common.model.Search;
import edu.iot.common.model.Travel;

public interface TravelService {

	public Map<String, Object> getPage(int page, String orderBy) throws Exception;
	
	public void create(Travel travel) throws Exception;
	
	public void increaseReadCnt(long travelId) throws Exception;
	
	public Travel findById(long travelId) throws Exception;
	
	public AttachmentB getAttachment(long attachmentId) throws Exception;
	
	public void update(Travel travel) throws Exception;
	
	public List<AttachmentB> getAttachList(long travelId) throws Exception;
	
	public void deleteAttachment(long attachmentId, long travelId, String password) throws Exception;
	
	public void delete(Travel travel) throws Exception;
	
	public Map<String, Object> search(Search search, int page) throws Exception;
	
}
