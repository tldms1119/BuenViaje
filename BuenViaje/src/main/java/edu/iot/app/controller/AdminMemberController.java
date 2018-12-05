package edu.iot.app.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.iot.common.sec.SHA256Util;
import edu.iot.common.util.Util;
import edu.iot.common.model.Member;
import edu.iot.common.model.Search;
import edu.iot.common.model.UserLevel;
import edu.iot.common.service.MemberService;

@Controller
@RequestMapping("/admin/member")
public class AdminMemberController {

	@Autowired
	MemberService service;
	
	
	@RequestMapping("/list")
	public void list(
			@RequestParam(value="page", defaultValue="1") int page,
			Model model) throws Exception {
		model.addAttribute("today", Util.getToday());
		model.addAllAttributes(service.getPage(page));
	}
	
	@RequestMapping(value="/view/{userId}", method=RequestMethod.GET)
	public String view(@PathVariable String userId, Model model) throws Exception {
		Member member = service.findById(userId);
		model.addAttribute("member", member);
		return "admin/member/view";
	}
	
	@RequestMapping(value="/edit/{userId}", method=RequestMethod.GET)
	public String editForm(@PathVariable String userId, Model model) throws Exception{
		Member member = service.findById(userId);
		model.addAttribute("userLevels", UserLevel.values());
		model.addAttribute("member", member);
		return "admin/member/edit";
	}
	
	@RequestMapping(value="/edit/{userId}", method=RequestMethod.POST)
	public String editSubmit(
						@Valid Member member, BindingResult result, HttpSession session,
						@RequestParam(value="page", defaultValue="1") int page,
						Model model) throws Exception{
		Member admin = (Member) session.getAttribute("USER");
		String pw = SHA256Util.getEncrypt(member.getPassword(), admin.getSalt());
		
		if(result.hasErrors()) {
			model.addAttribute("userLevels", UserLevel.values());
			return "admin/member/edit";	// forwarding 이니까 url은 유지됨!
		}
		
		if(!admin.getPassword().equals(pw)) {
			model.addAttribute("userLevels", UserLevel.values());
			result.reject("fail", "Wrong Password :(");	// 전역 에러 메시지
			// model.addAttribute("error", "Wrong Password :(");
			return "admin/member/edit";	// forwarding 이니까 url은 유지됨!
		}
	
		service.updateByAdmin(member);
		return "redirect:/admin/member/view/" + member.getUserId() + "?page=" + page;
	}
	
	@RequestMapping(value="/delete/{userId}", method=RequestMethod.GET)
	public String delete(@PathVariable String userId, 
					@RequestParam(value="page", defaultValue="1") int page) throws Exception{
		service.delete(userId);
		return "redirect:/admin/member/list";
	}
	
	@RequestMapping("/search")
	public void search(Search search, Model model) throws Exception{
		model.addAttribute("today", Util.getToday());
		model.addAttribute("list", service.search(search));
	}
}
