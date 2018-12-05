package edu.iot.common.service;

import java.util.List;

import edu.iot.common.model.Reply;

public interface ReplyService {

	List<Reply> getList(long boardId) throws Exception;

	Reply findById(long replyId) throws Exception;

	Reply create(Reply reply) throws Exception;

	Reply update(Reply reply) throws Exception;

	void delete(Reply reply) throws Exception;

}
