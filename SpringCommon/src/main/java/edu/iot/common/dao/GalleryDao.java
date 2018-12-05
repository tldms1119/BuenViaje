package edu.iot.common.dao;

import java.util.List;

import edu.iot.common.model.Gallery;
import edu.iot.common.model.GalleryImage;

public interface GalleryDao extends CrudDao<Gallery, Long> {
	
	void addImage(GalleryImage image) throws Exception;
	
	List<GalleryImage> getImages(long galleryId) throws Exception;
	
	GalleryImage getImage(long imageId) throws Exception;
	
	void updateCount(long galleryId) throws Exception;
}
