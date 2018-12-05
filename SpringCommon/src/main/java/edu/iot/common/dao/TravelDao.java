package edu.iot.common.dao;

import java.util.List;

import edu.iot.common.model.Search;
import edu.iot.common.model.Travel;

public interface TravelDao extends CrudDao<Travel, Long> {
	
	void increaseReadCnt(long travelId) throws Exception;
	
	List<Travel> search(Search search) throws Exception;
}
