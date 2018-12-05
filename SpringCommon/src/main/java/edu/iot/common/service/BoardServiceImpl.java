package edu.iot.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iot.common.dao.AttachmentDao;
import edu.iot.common.dao.BoardDao;
import edu.iot.common.exception.PasswordMissmatchException;
import edu.iot.common.model.Attachment;
import edu.iot.common.model.Board;
import edu.iot.common.model.Pagination;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardDao dao;
	
	@Autowired
	AttachmentDao attachmentDao;

	@Override
	public Map<String, Object> getPage(int page) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		int total = dao.count();
		Pagination pagination = new Pagination(page, total);
		Map<String, Object> pageMap = pagination.getPageMap();
		
		map.put("pagination", pagination);
		map.put("list", dao.getPage(pageMap));
		return map;
	}
	
	@Transactional
	@Override
	public void create(Board board) throws Exception {
		dao.insert(board); 		// 먼저 insert 호출 해줘야 seq에 의해 board_id가 할당됨
		
		for(Attachment attachment: board.getAttachList()) {
			//attachment.setAttachmentId(1);
			attachment.setBoardId(board.getBoardId());		// foreign key 설정
			attachmentDao.insert(attachment);
		}
		
	}

	@Override
	public Board findById(long boardId) throws Exception {
		Board board = dao.findById(boardId);
		board.setAttachList(attachmentDao.getList(boardId));
		return board;
	}

	@Override
	public void increaseReadCnt(long boardId) throws Exception {
		dao.increaseReadCnt(boardId);
	}

	@Override
	public Attachment getAttachment(long attachmentId) throws Exception {
		return attachmentDao.findById(attachmentId);
	}

	@Override
	public void update(Board board) throws Exception {
		int result = dao.update(board);
		if(result == 0) {
			throw new PasswordMissmatchException();
		}
		
		for(Attachment attachment : board.getAttachList()) {
			attachment.setBoardId(board.getBoardId());
			attachmentDao.insert(attachment);
		}
	}

	@Override
	public List<Attachment> getAttachList(long boardId) throws Exception {
		return attachmentDao.getList(boardId);
	}

	@Override
	public void deleteAttachment(long attachmentId, long boardId, String password) 
											throws Exception {
		// 게시판의 비밀번호 일치 여부 확인
		// 일치하지 않으면 PasswordMissmatchException 예외 발생
		Board board = dao.findById(boardId);
		
		if(!password.equals(board.getPassword())) {
			throw new PasswordMissmatchException();
		}
		
		// 일치하면 첨부파일 삭제
		attachmentDao.delete(attachmentId);		
	}

	@Override
	public void delete(Board board) throws Exception {
		Board b = dao.findById(board.getBoardId());
		
		if(!b.getPassword().equals(board.getPassword())) {
			throw new PasswordMissmatchException();
		}
		attachmentDao.deleteByBoardId(board.getBoardId());
		dao.delete(board.getBoardId());
	}
}
