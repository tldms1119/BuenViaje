package edu.iot.app.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.iot.common.exception.LoginFailException;
import edu.iot.common.model.Avata;
import edu.iot.common.model.Login;
//import edu.iot.controller_test.model.Code;
import edu.iot.common.model.Member;
import edu.iot.common.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	MemberService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginForm(Login login, 
							@ModelAttribute("url") String url,
							@ModelAttribute("reason") String reason) {
		login.setUrl(url);
		login.setReason(reason);
		//return "account/login"; 			// 뷰 이름 자동 지정
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginSubmit(Login login, 
							HttpSession session, Model model) throws Exception {
		try {
			Member member = service.login(login.getUserId(), login.getPassword());
			// 성공
			session.setAttribute("USER", member);
			
			String url = login.getUrl();
			if(url != null && !url.isEmpty()) return "redirect:"+ url;
			return "redirect:/";
		} catch (LoginFailException e){
			// 실패
			model.addAttribute("error", e.getMessage());
			return "account/login";
		}
		
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}	
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void joinForm(Member member) {		
		// form: 로 바꿔주면서 null exception 나지 않게 하기 위해 비어있는 멤버 객체 전달
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinSubmit(@Valid Member member, BindingResult result,
							/*@RequestParam("avata") MultipartFile mFile,*/
							RedirectAttributes ra, Model model) throws Exception {
		
		log.info(member.toString());
		if(result.hasErrors()) {			// 유효성 검사 결과가 실패이면
			result.reject("validation check failed", "유효성 검사 실패");
			return "account/join";			// 내용을 담은 커맨드 객체와 바인딩 결과 객체도 같이 넘어감
		}
		
		// 유효성 검사가 통과면
		service.join(member);
		
		/*// 아바타 저장
		if(mFile != null && !mFile.isEmpty()) {
			service.insertAvata(new Avata(member.getUserId(), mFile.getBytes()));
		}*/
				
		ra.addFlashAttribute("member", member);		// 딱 한번 유효하게 플래시 메모리에 담기는 애(세션에 저장 후 request에 담아줌)
		return "redirect:/account/join_success";
	}
	
	//id 중복 체크
	//json 자동 변환- gson 의존성 추가
	@RequestMapping(value="/idcheck", method=RequestMethod.GET)
	@ResponseBody	// 리턴 값을 view 이름으로 보는게 아니라 그냥 응답으로 본다는 뜻
	public String checkUserId(
			@RequestParam("userId") String userId) throws Exception {
		return service.idcheck(userId);
	}
	
	@RequestMapping(value="/join_success", method=RequestMethod.GET)
	public void checkUserId() throws Exception {
		
	}
}
