package edu.iot.app.controller;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import edu.iot.common.model.GalleryB;
import edu.iot.common.model.GalleryImageB;
import edu.iot.common.model.Search;
import edu.iot.common.service.GalleryBService;
import edu.iot.common.util.ImageUtil;
import edu.iot.common.util.Util;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/gallery")
public class GalleryBController {
	
	@Autowired
	GalleryBService service;
	
	@Autowired
	ServletContext context;
	
	@RequestMapping("/list")
	public void list(
				@RequestParam(value="page", defaultValue="1") int page,
				@RequestParam(value="orderBy", defaultValue="gallery_id") String orderBy,
				Model model) throws Exception {
		model.addAttribute("orderBy", orderBy);
		model.addAllAttributes(service.getPage(page, orderBy));
		model.addAttribute("today", Util.getToday());
	}
	
	@RequestMapping("/card")
	public void card(
				@RequestParam(value="page", defaultValue="1") int page,
				@RequestParam(value="orderBy", defaultValue="gallery_id") String orderBy,
				Model model) throws Exception {
		
		model.addAllAttributes(service.getPage(page, orderBy));
		model.addAttribute("today", Util.getToday());
	}
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public void createForm(GalleryB gallery) throws Exception {
		
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String createSubmit(@Valid GalleryB gallery,
				BindingResult result, Model model) throws Exception {
		if(result.hasErrors()) {
			return "gallery/create";
		}
		
		service.create(gallery);
		log.info(gallery.toString());
		
		return "redirect:/gallery/update/" + gallery.getGalleryId(); //어디로 이동할 것인가
	}
	
	@RequestMapping(value="/update/{galleryId}", method=RequestMethod.GET)
	public String updateForm(
				@PathVariable long galleryId, Model model) throws Exception {
		
		model.addAttribute("galleryB", service.findById(galleryId));

		return "gallery/update";
	}
	
	@RequestMapping(value="/update/add_images", method=RequestMethod.POST)
	public String addImagesSubmit(GalleryImageB image,
				MultipartHttpServletRequest request) throws Exception {
		
		System.out.println(image.getGalleryId());
		
		List<MultipartFile> fileList = request.getFiles("files");
		
		service.updateByGalleryId(image.getGalleryId(), image.getSource());
		
		for(MultipartFile imageFile : fileList) {
			if(!imageFile.isEmpty()) {
				uploadImage(image, imageFile);
				service.addImage(image);
			}
		}
		return "redirect:/gallery/view/" + image.getGalleryId();
	}
	
	private void uploadImage(GalleryImageB image,
					MultipartFile imageFile) throws Exception {
		
		// 원본 이미지 파일명
		String fname = imageFile.getOriginalFilename();
		// 서버에서의 이미지 파일명
		long timestamp = System.currentTimeMillis();
		String serverFileName = timestamp + "_" + fname;
		
		// 업로드 파일 재배치
		File dest = new File("c:/temp/upload/" + serverFileName);
		imageFile.transferTo(dest); // 원본파일이 결정된 후
		
		// thumbnail 이미지 생성
		ImageUtil.saveThumb(dest); //원본을 주고 썸네일을 만들어 냄
		
		// GalleryImage 정보 설정
		image.setFileName(fname);
		image.setServerFileName(serverFileName);
	}
	
	
	@RequestMapping(value="/view/{galleryId}", method=RequestMethod.GET)
	public String view(
				@PathVariable long galleryId,
				@RequestParam(value="image", defaultValue="1") int page,
				Model model) throws Exception {
					
		GalleryB gallery = service.findById(galleryId);
		model.addAttribute("gallery", gallery);
		model.addAttribute("page", page);
		return "gallery/view";
				
	}
				

	@RequestMapping(value="/image/{imageId}", method=RequestMethod.GET)
	public String image(
						@PathVariable long imageId,
						Model model) throws Exception {
					
		GalleryImageB image = service.getImage(imageId);
		String path = "C:/temp/upload/";
		
		path += image.getServerFileName();
		
		String type = context.getMimeType(path);
		model.addAttribute("path", path);
		model.addAttribute("type", type);
		
		return "fileView"; 
	}
	
	@RequestMapping(value="/edit/{galleryId}", method=RequestMethod.GET)
	public String editForm(
				@PathVariable long galleryId, Model model) throws Exception {
		
		model.addAttribute("galleryB", service.findById(galleryId));

		return "gallery/edit";
	}
	
	@RequestMapping(value="/edit/{galleryId}", method=RequestMethod.POST)
	public String editSubmit(
				@PathVariable long galleryId,
				@Valid GalleryB gallery,
				BindingResult result) throws Exception {
		if(result.hasErrors()) {

			return "gallery/edit";
		}
		service.update(gallery);
		
		return "redirect:/gallery/edit2/" + gallery.getGalleryId();
	}
	
	@RequestMapping(value="/edit2/{galleryId}", method=RequestMethod.GET)
	public String edit2Form(
				@PathVariable long galleryId, Model model) throws Exception {
		
		model.addAttribute("galleryB", service.findById(galleryId));

		return "gallery/edit2";
	}

	@RequestMapping(value="/download/{imageId}", method=RequestMethod.GET)
	public String download(@PathVariable long imageId,
							Model model) throws Exception {
		
		GalleryImageB image = service.getImage(imageId);
		String path = "C:/temp/upload/" + image.getServerFileName();
		String fileName = image.getFileName();
		String type = context.getMimeType(path);
		model.addAttribute("path", path);
		model.addAttribute("fileName", fileName);
		model.addAttribute("type", type);
				
		return "download";		
	}
	
	@RequestMapping(value = "/delete_image", method = RequestMethod.GET, 
					produces = "text/plain; charset=utf8")
	@ResponseBody
	public String deleteImage(@RequestParam(value = "galleryId") long galleryId,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "imageId") long imageId) {
		try {
			service.deleteImage(galleryId, password, imageId);
			return "ok";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/delete", method=RequestMethod.GET,
					produces = "text/plain; charset=utf8")
	public String delete(GalleryB gallery) {
		try {
			service.delete(gallery);
			return "ok";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@RequestMapping(value="/search")
	public void search(Search search, 
			@RequestParam(value = "page", defaultValue = "1") int page,
			Model model, HttpServletRequest request) throws Exception{
		String keyword = search.getKeyword();
		
		model.addAttribute("search", search);
		model.addAttribute("keyword", keyword.substring(1, keyword.length()-1));
		model.addAllAttributes(service.search(search, page));	
	}
}
