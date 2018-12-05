package edu.iot.app.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class AdminInterceptor extends BaseInterceptor {
	@Override
	public String check(HttpServletRequest request) {
		if(!isAdmin(request)) {
			return "관리자 권한이 필요한 서비스 입니다";
		} else {
			return null;
		}
	}
}
