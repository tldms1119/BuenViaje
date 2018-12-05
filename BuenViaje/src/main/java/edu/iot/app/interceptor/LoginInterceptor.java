package edu.iot.app.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class LoginInterceptor extends BaseInterceptor {
	@Override
	public String check(HttpServletRequest request) {
		if(getUser(request) == null) {
			return "로그인이 필요한 서비스 입니다";
		} else {
			return null;
		}
	}
}
