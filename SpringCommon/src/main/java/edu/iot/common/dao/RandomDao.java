package edu.iot.common.dao;

import java.util.List;

public interface RandomDao<M> {
	List<M> random(int num) throws Exception;
}
