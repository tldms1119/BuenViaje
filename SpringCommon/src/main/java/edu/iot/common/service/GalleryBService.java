package edu.iot.common.service;

import java.util.Map;

import edu.iot.common.model.GalleryB;
import edu.iot.common.model.GalleryImageB;
import edu.iot.common.model.Search;

public interface GalleryBService {
	
	Map<String, Object> getPage(int page, String orderBy) throws Exception;
	
	void create(GalleryB gallery) throws Exception;
	
	GalleryB findById(long galleryId) throws Exception;
	
	void update(GalleryB gallery) throws Exception;

	void addImage(GalleryImageB image) throws Exception;
	
	GalleryImageB getImage(long imageId) throws Exception;
	
	public void deleteImage(long galleryId, String password, long imageId) throws Exception;
	
	void delete(GalleryB gallery) throws Exception;
	
	void updateByGalleryId(long galleryId, String source) throws Exception;
	
	public Map<String, Object> search(Search search, int page) throws Exception;

}
