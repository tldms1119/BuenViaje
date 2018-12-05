package edu.iot.common.dao;

import java.util.List;

import edu.iot.common.model.AttachmentB;

public interface AttachmentBDao {
	
	void insert(AttachmentB attachment) throws Exception;
	
	List<AttachmentB> getList(long travelId) throws Exception;
	
	AttachmentB findById(long attachmentId) throws Exception;
	
	void delete(long attachmentId) throws Exception;
	
	void deleteByTravelId(long travelId) throws Exception;
	
}
