package edu.iot.app.controller;

import java.io.File;
import java.util.ArrayList;
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

import edu.iot.common.exception.PasswordMissmatchException;
import edu.iot.common.model.AttachmentB;
import edu.iot.common.model.Search;
import edu.iot.common.model.Travel;
import edu.iot.common.service.TravelReplyService;
import edu.iot.common.service.TravelService;
import edu.iot.common.util.ImageUtil;

@Controller
@RequestMapping("/travel")
public class TravelController {

	@Autowired
	TravelService service;

	@Autowired
	ServletContext context;
	
	@Autowired
	TravelReplyService replyService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "orderBy", defaultValue = "travel_id") String orderBy, Model model) throws Exception {
		model.addAllAttributes(service.getPage(page, orderBy));
		model.addAttribute("orderBy", orderBy);
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public void createForm(Travel travel) {

	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createSubmit(@Valid Travel travel, BindingResult result, MultipartHttpServletRequest request)
			throws Exception {
		if (result.hasErrors()) {
			return "travel/create";
		}

		List<AttachmentB> list = upload(request);
		travel.setAttachList(list);

		service.create(travel);
		return "redirect:list";
	}

	@RequestMapping(value = "/view/{travelId}", method = RequestMethod.GET)
	public String view(@PathVariable long travelId, Model model) throws Exception {
		service.increaseReadCnt(travelId);
		Travel travel = service.findById(travelId);
		model.addAttribute("travel", travel);
		model.addAttribute("replies", replyService.getList(travelId));
		return "travel/view";
	}

	// 단일 파일 업로드 처리
	AttachmentB upload(MultipartFile file) throws Exception {
		String fname = file.getOriginalFilename();
		long timestamp = System.currentTimeMillis();
		String location = timestamp + "_" + fname;
		File dest = new File("c:/temp/buenViaje/" + location);
		file.transferTo(dest);
		ImageUtil.saveImage(dest);
		// boardId 는 board가 insert 된 이후에 attachment에 setBoardId해준다
		return new AttachmentB(fname, location);
	}

	// 다중 업로드 파일 처리
	// 업로드된 정보를 가지는 Attachment의 리스트 생성/반환
	private List<AttachmentB> upload(MultipartHttpServletRequest request) throws Exception {
		List<AttachmentB> list = new ArrayList<>();

		List<MultipartFile> files = request.getFiles("files");
		for (MultipartFile file : files) {
			if (!file.isEmpty()) { // 아무것도 첨부하지 않았을 때 검사
				AttachmentB attachment = upload(file);
				list.add(attachment);
			}
		}

		return list;
	}

	@RequestMapping(value = "/download/{attachmentId}", method = RequestMethod.GET)
	public String download(@PathVariable long attachmentId, Model model) throws Exception {
		AttachmentB attachment = service.getAttachment(attachmentId);
		String path = "c:/temp/buenViaje/" + attachment.getLocation();
		String fileName = attachment.getFileName();
		String type = context.getMimeType(path);
		// log.info(type); 확장자, 파일 타입 알려줌
		model.addAttribute("path", path);
		model.addAttribute("fileName", fileName);
		model.addAttribute("type", type);

		return "download";
	}

	@RequestMapping(value = "/edit/{travelId}", method = RequestMethod.GET)
	public String editForm(@PathVariable long travelId, Model model) throws Exception {
		Travel travel = service.findById(travelId);
		model.addAttribute("travel", travel);
		return "travel/edit";
	}

	@RequestMapping(value = "/edit/{travelId}", method = RequestMethod.POST)
	public String editSubmit(@Valid Travel travel, BindingResult result, @RequestParam("page") int page,
			MultipartHttpServletRequest request) throws Exception {
		if (result.hasErrors())
			return "travel/edit";

		List<AttachmentB> list = upload(request);
		travel.setAttachList(list);

		try {
			service.update(travel);
		} catch (PasswordMissmatchException e) {
			result.reject("updateFail", e.getMessage());
			travel.setAttachList(service.getAttachList(travel.getTravelId()));
			return "travel/edit";
		}
		return "redirect:/travel/view/" + travel.getTravelId() + "?page=" + page;
	}
	
	// view에서 첨부파일 보여주기
	@RequestMapping(value="/attachment/{attachmentId}", method=RequestMethod.GET)
	public String image(@PathVariable long attachmentId, 
							Model model) throws Exception {
		AttachmentB attachment = service.getAttachment(attachmentId);
		String path = "c:/temp/buenViaje/image/";
		
		path += attachment.getLocation();
		
		// 파일의 Mimetype 문자열(.jpg, .png 등등의 타입을 리턴해주는거 같음)
		String type = context.getMimeType(path);
		model.addAttribute("path", path);
		model.addAttribute("type", type);
		return "fileView";
	}

	@RequestMapping(value = "/delete_attachment", method = RequestMethod.GET, 
							produces = "text/plain; charset=utf8")
	@ResponseBody
	public String deleteAttachment(@RequestParam(value = "travelId") long travelId,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "attachmentId") long attachmentId) {
		try {
			service.deleteAttachment(attachmentId, travelId, password);
			return "ok";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET, 
							produces = "text/plain; charset=utf8")
	@ResponseBody
	public String delete(Travel travel) {
		try {
			service.delete(travel);
			return "ok";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	/*@RequestMapping("/search")
	public void search(Search search, 
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "orderBy", defaultValue = "travel_id") 
			String orderBy, Model model, HttpServletRequest request) throws Exception{
		search.setOrderBy(orderBy);
		model.addAttribute("keyword", request.getParameter("keyword"));
		model.addAttribute("search", search);
		model.addAllAttributes(service.search(search, page));
	}*/
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public void search(Search search, 
			@RequestParam(value = "page", defaultValue = "1") int page,
			Model model,  HttpServletRequest request) throws Exception{
		String keyword = search.getKeyword();
		String secondKeyword = search.getSecondKeyword();
		if(secondKeyword != null) {
			model.addAttribute("secondKeyword", secondKeyword.substring(1, secondKeyword.length()-1));
		}
		model.addAttribute("search", search);
		model.addAttribute("keyword", keyword.substring(1, keyword.length()-1));
		model.addAllAttributes(service.search(search, page));
	}

}
