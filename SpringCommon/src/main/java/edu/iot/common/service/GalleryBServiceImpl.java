package edu.iot.common.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.iot.common.dao.GalleryBDao;
import edu.iot.common.dao.GalleryImageBDao;
import edu.iot.common.exception.PasswordMissmatchException;
import edu.iot.common.model.GalleryB;
import edu.iot.common.model.GalleryImageB;
import edu.iot.common.model.Pagination;
import edu.iot.common.model.Search;

@Service
public class GalleryBServiceImpl implements GalleryBService {
	
	@Autowired
	GalleryBDao dao;
	
	@Autowired
	GalleryImageBDao imageDao;
	

	@Override
	public Map<String, Object> getPage(int page, String orderBy) throws Exception {
		
		Map<String, Object> map = new HashMap<>();
		
		int total = dao.count();
		Pagination pagination = new Pagination(page, total);
		
		
		Map<String, Object> pageMap = pagination.getPageMap();
		pageMap.put("orderBy", orderBy);
		map.put("pagination", pagination);
		map.put("list", dao.getPage(pageMap));
		map.put("orderBy", orderBy);
				
		return map;
	}

	@Override
	public void create(GalleryB gallery) throws Exception {
		dao.insert(gallery);
		
		/*for(GalleryImageB image: gallery.getList()) {
			image.setGalleryId(gallery.getGalleryId());
			dao.addImage(image);
		}*/
				
	}

	@Override
	public GalleryB findById(long galleryId) throws Exception {
		dao.increaseReadCnt(galleryId);
		GalleryB gallery = dao.findById(galleryId);
		return gallery;
	}
	
	@Override
	public void update(GalleryB gallery) throws Exception {
		dao.update(gallery);
		
	}

	@Override
	public void addImage(GalleryImageB image) throws Exception {
		dao.addImage(image);
		
	}

	@Override
	public GalleryImageB getImage(long imageId) throws Exception {
		return dao.getImage(imageId);
	}

	@Override
	public void deleteImage(long galleryId, String password, long imageId) throws Exception {
		
		GalleryB gallery = dao.findById(galleryId);
		if(!password.equals(gallery.getPassword())) {
			throw new PasswordMissmatchException();
		}
		imageDao.delete(imageId);
		
	}
	
	@Override
	public void delete(GalleryB gallery) throws Exception {
		GalleryB g = dao.findById(gallery.getGalleryId());
		if(!g.getPassword().equals(gallery.getPassword())) {
			throw new PasswordMissmatchException();
		}
		
		imageDao.deleteByGalleryId(gallery.getGalleryId());
		dao.delete(gallery.getGalleryId());
	}

	@Override
	public void updateByGalleryId(long galleryId, String source) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		map.put("galleryId", galleryId);
		map.put("source", source);
		
		imageDao.updateByGalleryId(map);
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
