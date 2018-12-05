package edu.iot.common.service;

import java.util.List;

import edu.iot.common.model.TravelReply;

public interface TravelReplyService {
	
	List<TravelReply> getList(long travelId) throws Exception;

	TravelReply findById(long replyId) throws Exception;

	TravelReply create(TravelReply reply) throws Exception;

	TravelReply update(TravelReply reply) throws Exception;

	void delete(TravelReply reply) throws Exception;

}
