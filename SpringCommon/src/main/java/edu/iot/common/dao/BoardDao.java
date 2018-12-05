package edu.iot.common.dao;

import edu.iot.common.model.Board;

// BoardDao 내에 첨부파일 가져오는 걸 합쳐도 되고, AttachmentDao 따로 생성해서 분리해도 됨
// Gallery 때는 합쳤고, Attachment는 분리했으니 참고!
public interface BoardDao extends CrudDao<Board, Long>{
	
	void increaseReadCnt(long boardId) throws Exception;

}
