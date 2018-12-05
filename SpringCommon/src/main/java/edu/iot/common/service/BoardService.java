package edu.iot.common.service;

import java.util.List;
import java.util.Map;

import edu.iot.common.model.Attachment;
import edu.iot.common.model.Board;

public interface BoardService {
	
	Map<String, Object> getPage(int page) throws Exception;
	
	public void create(Board board) throws Exception;
	
	public Board findById(long boardId) throws Exception;
	
	public void increaseReadCnt(long boardId) throws Exception;
	
	public Attachment getAttachment(long attachmentId) throws Exception;
	
	public void update(Board board) throws Exception;
	
	public List<Attachment> getAttachList(long boardId) throws Exception;
	
	public void deleteAttachment(long attachmentId, long boardId, String password) throws Exception;
	
	public void delete(Board board) throws Exception;
	
}
