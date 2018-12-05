package edu.iot.common.service;

import java.util.List;
import java.util.Map;

import edu.iot.common.model.Route;
import edu.iot.common.model.Search;

public interface RouteService {
	
	public Map<String, Object> getPage(int page, String orderBy) throws Exception;
	
	public void create(Route route) throws Exception;
	
	public void increaseReadCnt(long routeId) throws Exception;
	
	public Route findById(long routeId) throws Exception;
	
	public void update(Route route) throws Exception;
	
	public void delete(Route route) throws Exception;
	
	public Map<String, Object> search(Search search, int page) throws Exception;
	
}
