package edu.iot.common.dao;

import java.util.List;

import edu.iot.common.model.TourSpot;

public interface TourSpotDao {

	void insert(TourSpot spot) throws Exception;
	
	List<TourSpot> getList(long routeId) throws Exception;
	
	TourSpot findById(long tourSpotId) throws Exception;
	
	void delete(long tourSpotId) throws Exception;
	
	void deleteByRouteId(long routeId) throws Exception;
	
}
