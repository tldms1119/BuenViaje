package edu.iot.common.dao;

import java.util.List;
import java.util.Map;

public interface CrudDao<M, K> {
	
	public List<M> getList() throws Exception;
	M findById(K k) throws Exception;
	public int insert(M m) throws Exception;
	public int update(M m) throws Exception;
	public int delete(K k) throws Exception;
	public int count() throws Exception;
	public List<M> random(int num) throws Exception;
	List<M> getPage(Map map) throws Exception;
}
