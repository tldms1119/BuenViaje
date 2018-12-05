package edu.iot.common.dao;

import java.util.List;

import edu.iot.common.model.Route;
import edu.iot.common.model.Search;

public interface RouteDao extends CrudDao<Route, Long> {

	void increaseReadCnt(long routeId) throws Exception;
	
	List<Route> search(Search search) throws Exception;
	
}
