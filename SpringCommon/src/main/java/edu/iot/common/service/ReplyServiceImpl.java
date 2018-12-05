package edu.iot.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.iot.common.dao.ReplyDao;
import edu.iot.common.exception.PasswordMissmatchException;
import edu.iot.common.model.Reply;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	ReplyDao dao;

	@Override
	public List<Reply> getList(long boardId) throws Exception {
		return dao.getList(boardId);
	}

	@Override
	public Reply findById(long replyId) throws Exception {
		return dao.findById(replyId);
	}

	@Override
	public Reply create(Reply reply) throws Exception {
		dao.insert(reply);
		return dao.findById(reply.getReplyId());
	}

	@Override
	public Reply update(Reply reply) throws Exception {
		dao.update(reply);
		if(dao.update(reply) == 0) {
			throw new PasswordMissmatchException();
		}
		return dao.findById(reply.getReplyId());
	}

	@Override
	public void delete(Reply reply) throws Exception {
		if(dao.delete(reply) == 0)
			throw new PasswordMissmatchException();
	}

}
