package edu.iot.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import edu.iot.common.model.TravelReply;

public interface TravelReplyDao {

	@Select("select count(*) from travelreply where travel_id=#{travelId}")
	int count(long travelId) throws Exception;

	@Select("select * from travelreply where reply_id=#{replyId} ")
	TravelReply findById(long replyId) throws Exception;

	@Select({ "select travel_id, reply_id, level, parent, writer, content, deleted, update_date", "from travelreply",
			"where travel_id = #{travelId}", "start with parent = 0", "connect by parent = prior reply_id ",
			"order siblings by reply_id desc" })
	List<TravelReply> getList(long travelId) throws Exception;

	@Insert({ "insert into travelreply(reply_id, travel_id, password, parent, writer, content)",
			"values(travelreply_seq.nextval, #{travelId}, #{password}, #{parent}, #{writer}, #{content})" })
	@Options(useGeneratedKeys = true, keyColumn = "reply_id", keyProperty = "replyId")
	int insert(TravelReply reply) throws Exception;

	@Update({ "update travelreply set", " content = #{content}, update_date = sysdate",
			"where reply_id=#{replyId} and password = #{password}" })
	int update(TravelReply reply) throws Exception;

	@Delete({ "update travelreply set", " content = '삭제된 글', deleted = 1, update_date = sysdate",
			"where reply_id=#{replyId} and password = #{password}" })
	int delete(TravelReply reply) throws Exception;

}
