package com.khacademy.khoffice.commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Controller 
public class NotAllowedMemberException extends HandlerInterceptorAdapter {
	
	
	@Override
	public boolean preHandle( 
		HttpServletRequest request, 
		HttpServletResponse response,   
		Object handler){
		try	{ 
			// 일반 사원 세션 있을 경우 
			if(request.getSession().getAttribute("session_memberNo")  != null){
				System.out.println("사원 계정으로 로그인"); 
				// 접근권한 없음 페이지로 redirect
				response.sendRedirect(request.getContextPath() + "/exception/not_allowed_member");
				return false;  
			}    
			System.out.println("세션값은 " + request.getSession().getAttribute("session_memberNo"));
		}catch(Exception e){
			e.printStackTrace();
		} // 어드민 세션 없으면 정상적으로 컨트롤러 호출
		return true;
	}  
	 
	@RequestMapping(value="/exception/not_allowed_member")
	public String memberCheckInterceptor() {
		return "exception/not_allowed_member";
	}
 
}
