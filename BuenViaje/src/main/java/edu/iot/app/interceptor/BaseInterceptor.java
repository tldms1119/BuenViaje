package edu.iot.app.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import edu.iot.common.model.Member;
import edu.iot.common.model.UserLevel;

public abstract class BaseInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	ServletContext context;
	
	public Member getUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (Member) session.getAttribute("USER");
	}

	public boolean isAdmin(HttpServletRequest request) {
		Member member = getUser(request);
		return member.getUserLevel() == UserLevel.ADMIN;
	}
	
	public void saveUrl(String reason,
						HttpServletRequest request,
						HttpServletResponse response) {
		String url = request.getRequestURI()
							.substring(context.getContextPath().length());
		String query = request.getQueryString();
		if(query != null) {
			url = url + "?" + query;
		}
		
		FlashMap flashMap = new FlashMap(); 
		flashMap.put("url", url); 
		flashMap.put("reason", reason);
		FlashMapManager flashMapManager = 
					RequestContextUtils.getFlashMapManager(request); 
		flashMapManager.saveOutputFlashMap(flashMap, request, response);
	}
	
	public abstract String check(HttpServletRequest request);
	
	// template pattern
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
						Object handler) throws Exception {
		String reason = check(request);
		if (reason != null) {
			saveUrl(reason, request, response);
			response.sendRedirect(context.getContextPath() + "/account/login");
			return false;
		}
		return super.preHandle(request, response, handler);
	}
}
