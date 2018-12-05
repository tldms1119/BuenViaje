package edu.iot.common.dao;

import java.util.List;

import edu.iot.common.model.GalleryB;
import edu.iot.common.model.GalleryImageB;
import edu.iot.common.model.Search;

public interface GalleryBDao 
				extends CrudDao<GalleryB, Long> {
	
	void addImage(GalleryImageB image) throws Exception;
	
	List<GalleryImageB> getImages(long galleryId) throws Exception;
	
	GalleryImageB getImage(long imageId) throws Exception;

	void increaseReadCnt(long galleryId) throws Exception;

	List<GalleryB> search(Search search) throws Exception;
}