package edu.iot.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.iot.common.dao.GalleryDao;
import edu.iot.common.model.Gallery;
import edu.iot.common.model.GalleryImage;
import edu.iot.common.model.Pagination;

@Service
public class GalleryServiceImpl implements GalleryService {

	@Autowired
	GalleryDao dao;
	
	@Override
	public Map<String, Object> getPage(int page, String orderBy) throws Exception {
		Map<String, Object> map = new HashMap<>();

		int total = dao.count();
		Pagination pagination = new Pagination(page, total);
		Map<String, Object> pageMap = pagination.getPageMap();
		pageMap.put("orderBy", orderBy);
		
		map.put("pagination", pagination);
		map.put("list", dao.getPage(pageMap));
		
		// 기존 getPage를 사용하는 경우(조인이 자신없는 사람!)
		/*List<Gallery> list = dao.getPage(pagination.getPageMap());
		for(Gallery g : list) {
			List<GalleryImage> imageList = dao.getImages(g.getGalleryId());
			g.setList(imageList);
		}
		map.put("list", list);*/
	
		return map;
	}

	@Override
	public void create(Gallery gallery) throws Exception {
		dao.insert(gallery);
	}
	
	@Override
	public Gallery findById(long galleryId) throws Exception {
		// join을 쓰지 않고 query를 2번 던져서 얻어낸 방법1
		/*Gallery gallery = dao.findById(galleryId);
		List<GalleryImage> list = dao.getImages(galleryId);
		gallery.setList(list);*/
		
		// join한 gallery_detail 뷰를 사용(resultMap도 사용)해서 query 한번으로 얻는 방법2
		return dao.findById(galleryId);
	}

	@Override
	public void update(Gallery gallery) throws Exception {
		dao.update(gallery);
	}

	@Override
	public void addImage(GalleryImage image) throws Exception {
		dao.addImage(image);
	}

	@Override
	public GalleryImage getImage(long imageId) throws Exception {
		return dao.getImage(imageId);
	}

	@Override
	public void updateCount(long galleryId) throws Exception {
		dao.updateCount(galleryId);
	}

}
