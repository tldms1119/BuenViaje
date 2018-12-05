package edu.iot.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.iot.common.dao.TravelReplyDao;
import edu.iot.common.exception.PasswordMissmatchException;
import edu.iot.common.model.TravelReply;

@Service
public class TravelReplyServiceImpl implements TravelReplyService {

	@Autowired
	TravelReplyDao dao;
	
	@Override
	public List<TravelReply> getList(long travelId) throws Exception {
		return dao.getList(travelId);
	}

	@Override
	public TravelReply findById(long replyId) throws Exception {
		return dao.findById(replyId);
	}

	@Override
	public TravelReply create(TravelReply reply) throws Exception {
		dao.insert(reply);
		return dao.findById(reply.getReplyId());
	}

	@Override
	public TravelReply update(TravelReply reply) throws Exception {
		dao.update(reply);
		if(dao.update(reply) == 0)
			throw new PasswordMissmatchException();
		return dao.findById(reply.getReplyId());
	}

	@Override
	public void delete(TravelReply reply) throws Exception {
		if(dao.delete(reply) == 0)
			throw new PasswordMissmatchException();
	}

}
