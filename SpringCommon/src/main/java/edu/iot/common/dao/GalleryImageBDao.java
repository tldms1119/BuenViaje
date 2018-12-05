package edu.iot.common.dao;

import java.util.Map;

public interface GalleryImageBDao {
	
	void delete(long imageId) throws Exception;
	
	void deleteByGalleryId(long galleryId) throws Exception;
	
	void updateByGalleryId(Map<String, Object> map)  throws Exception;
}
