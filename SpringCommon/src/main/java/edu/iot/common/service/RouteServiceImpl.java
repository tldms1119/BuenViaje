package edu.iot.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.iot.common.dao.RouteDao;
import edu.iot.common.dao.TourSpotDao;
import edu.iot.common.exception.PasswordMissmatchException;
import edu.iot.common.model.Pagination;
import edu.iot.common.model.Route;
import edu.iot.common.model.Search;
import edu.iot.common.model.TourSpot;

@Service
public class RouteServiceImpl implements RouteService {
	
	@Autowired
	RouteDao dao;
	
	@Autowired
	TourSpotDao spotDao;

	@Override
	public Map<String, Object> getPage(int page, String orderBy) throws Exception {
		Map<String, Object> map = new HashMap<>();

		int total = dao.count();
		Pagination pagination = new Pagination(page, total);
		Map<String, Object> pageMap = pagination.getPageMap();
		pageMap.put("orderBy", orderBy);
		
		map.put("pagination", pagination);
		map.put("list", dao.getPage(pageMap));
		
		return map;
	}

	@Override
	public void create(Route route) throws Exception {
		dao.insert(route);							// 먼저 route 생성하고 routeId 생성
		
		for(TourSpot spot: route.getList()) {
			spot.setRouteId(route.getRouteId());	// 생성한 routeId를 외래키로 setting
			spotDao.insert(spot);
		}
	}

	@Override
	public void increaseReadCnt(long routeId) throws Exception {
		dao.increaseReadCnt(routeId);
	}

	@Override
	public Route findById(long routeId) throws Exception {
		Route route = dao.findById(routeId);
		route.setList(spotDao.getList(routeId));
		return route;
	}

	@Override
	public void update(Route route) throws Exception {
		int result = dao.update(route);
		if(result == 0) {
			throw new PasswordMissmatchException();
		}
		
		// 기존의 routeId를 가지고 있던 tourSpot 삭제
		for(TourSpot spot: spotDao.getList(route.getRouteId())) {
			spotDao.delete(spot.getTourSpotId());
		}
		
		// 새로 수정된 tourSpot 등록
		for(TourSpot spot: route.getList()) {
			spot.setRouteId(route.getRouteId());	// 생성한 routeId를 외래키로 setting
			spotDao.insert(spot);
		}
	}

	@Override
	public void delete(Route route) throws Exception {
		// route Id로 연결된 TourSpot 먼저 삭제
		spotDao.deleteByRouteId(route.getRouteId());
		
		// Route 삭제
		dao.delete(route.getRouteId());
	}

	@Override
	public Map<String, Object> search(Search search, int page) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		Pagination pagination = new Pagination(page, dao.search(search).size());
		
		map.put("list", dao.search(search));
		map.put("pagination", pagination);
		
		return map;
	}

}
