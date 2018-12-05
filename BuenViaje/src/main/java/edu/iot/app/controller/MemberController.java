package edu.iot.app.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.iot.common.model.Avata;
import edu.iot.common.model.Member;
import edu.iot.common.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	MemberService service;

	@RequestMapping(value = "/avata/{userId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getAvata(@PathVariable String userId) throws Exception {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(service.getAvata(userId), headers, HttpStatus.OK);
	}
	
	/*@ResponseBody
	@RequestMapping(value = "/avata/{userId}", method = RequestMethod.GET,
					produces = "image/png")
	public byte[] getAvata(@PathVariable String userId) throws Exception {
		
		return service.getAvata(userId);
	}*/
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public void view() throws Exception {
		
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public void editForm(HttpSession session, Model model) throws Exception {
		model.addAttribute("member", session.getAttribute("USER"));
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String editSubmit(@Valid Member member, BindingResult result,
							@RequestParam("avata") MultipartFile mFile,
							HttpSession session) throws Exception {
		if(result.hasErrors()) return "member/edit";
		
		try {
			Member sMember = (Member) session.getAttribute("USER");
			service.update(member, sMember);
		} catch(Exception e) {
			result.reject("updateFail", "Wrong password :(");
			return "member/edit";
		}
		
		if(mFile != null && !mFile.isEmpty()) {
			service.updateAvata(new Avata(member.getUserId(), mFile.getBytes()));
		}
		
		session.setAttribute("USER", service.findById(member.getUserId()));
		return "redirect:/member/view";
	}
}
