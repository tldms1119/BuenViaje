package edu.iot.app.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
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
import edu.iot.common.model.Attachment;
import edu.iot.common.model.Board;
import edu.iot.common.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService service;

	@Autowired
	ServletContext context;

	@RequestMapping("/list")
	public void list(@RequestParam(value = "page", defaultValue = "1") int page, Model model) throws Exception {
		// model.addAttribute("today", Util.getToday());
		model.addAllAttributes(service.getPage(page));
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public void createForm(Board board) {

	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createSubmit(@Valid Board board, BindingResult result, MultipartHttpServletRequest request)
			throws Exception {
		if (result.hasErrors()) {
			return "board/create";
		}

		List<Attachment> list = upload(request);
		board.setAttachList(list);

		service.create(board);
		return "redirect:list";
	}

	// 단일 파일 업로드 처리
	Attachment upload(MultipartFile file) throws Exception {
		String fname = file.getOriginalFilename();
		long timestamp = System.currentTimeMillis();
		String location = timestamp + "_" + fname;
		File dest = new File("c:/temp/upload/" + location);
		file.transferTo(dest);
		// boardId 는 board가 insert 된 이후에 attachment에 setBoardId해준다
		return new Attachment(fname, location);
	}

	// 다중 업로드 파일 처리
	// 업로드된 정보를 가지는 Attachment의 리스트 생성/반환
	private List<Attachment> upload(MultipartHttpServletRequest request) throws Exception {
		List<Attachment> list = new ArrayList<>();

		List<MultipartFile> files = request.getFiles("files");
		for (MultipartFile file : files) {
			if(!file.isEmpty()) {					// 아무것도 첨부하지 않았을 때 검사
				Attachment attachment = upload(file);
				list.add(attachment);
			}
		}

		return list;
	}

	@RequestMapping(value = "/view/{boardId}", method = RequestMethod.GET)
	public String view(@PathVariable long boardId,
									Model model) throws Exception {
		service.increaseReadCnt(boardId);
		Board board = service.findById(boardId);
		model.addAttribute("board", board);
		return "board/view";
	}

	@RequestMapping(value = "/download/{attachmentId}", method = RequestMethod.GET)
	public String download(@PathVariable long attachmentId,

			Model model) throws Exception {
		Attachment attachment = service.getAttachment(attachmentId);
		String path = "c:/temp/upload/" + attachment.getLocation();
		String fileName = attachment.getFileName();
		String type = context.getMimeType(path);
		// log.info(type); 확장자, 파일 타입 알려줌
		model.addAttribute("path", path);
		model.addAttribute("fileName", fileName);
		model.addAttribute("type", type);

		return "download";
	}

	@RequestMapping(value = "/edit/{boardId}", method = RequestMethod.GET)
	public String editForm(@PathVariable long boardId, 
										Model model) throws Exception {
		Board board = service.findById(boardId);
		model.addAttribute("board", board);
		return "board/edit";
	}

	@RequestMapping(value="/edit/{boardId}", method=RequestMethod.POST) 
	public String editSubmit(@Valid Board board, BindingResult result, 
			@RequestParam("page") int page,
			MultipartHttpServletRequest request) throws Exception { 
		if(result.hasErrors()) return "board/edit"; 
		
		List<Attachment> list = upload(request); 
		board.setAttachList(list); 
		
		try {
			service.update(board);
		} catch(PasswordMissmatchException e) {
			result.reject("updateFail", e.getMessage());
			board.setAttachList(service.getAttachList(board.getBoardId()));
			return "board/edit";
		}
		return "redirect:/board/view/" + board.getBoardId() + "?page=" + page;
	}
	
	// return 타입을 json 문자열로 변환해서 반환해줌(String이 아닌 다른 객체라도)
	@RequestMapping(value="/delete_attachment", method=RequestMethod.GET,
					produces="text/plain; charset=utf8")
	@ResponseBody
	public String deleteAttachment(@RequestParam(value="boardId") long boardId,
								@RequestParam(value="password") String password,
								@RequestParam(value="attachmentId") long attachmentId){
		try {
			service.deleteAttachment(attachmentId, boardId, password);
			return "ok";
		} catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET,
					produces="text/plain; charset=utf8")
	@ResponseBody
	public String delete(Board board){
		try {
			service.delete(board);
			return "ok";
		} catch(Exception e) {
			return e.getMessage();
		}
	}
	
	
	
}
