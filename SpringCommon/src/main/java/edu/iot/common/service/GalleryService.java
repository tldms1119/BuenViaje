package edu.iot.common.service;

import java.util.Map;

import edu.iot.common.model.Gallery;
import edu.iot.common.model.GalleryImage;

public interface GalleryService {

	Map<String, Object> getPage(int page, String orderBy) throws Exception;
	
	void create(Gallery gallery) throws Exception;
	
	Gallery findById(long galleryId) throws Exception;
	
	void update(Gallery gallery) throws Exception;
	
	void addImage(GalleryImage image) throws Exception;
	
	GalleryImage getImage(long imageId) throws Exception;
	
	void updateCount(long galleryId) throws Exception;
	
}
