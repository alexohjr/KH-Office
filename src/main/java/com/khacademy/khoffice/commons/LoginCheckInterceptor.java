package com.khacademy.khoffice.commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class LoginCheckInterceptor extends HandlerInterceptorAdapter  {

	@Override
	public boolean preHandle(
		HttpServletRequest request, 
		HttpServletResponse response, 
		Object handler){
		try	{
			// session_세션값이 널일경우 
			if(request.getSession().getAttribute("session_memberNo") == null && request.getSession().getAttribute("session_adminID")  == null){
				System.out.println("세션값이 없음");
				//로그인페이지로 redirect
				response.sendRedirect("login.jsp");
				return false; 
			} 
			System.out.println("세션값은 " + request.getSession().getAttribute("session_memberNo"));
		}catch(Exception e){
			e.printStackTrace();
		} // 널이 아니면 정상적으로 컨트롤러 호출 
		return true;
	}

	
	/*@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 요청주소가 /event/list.do 이면서
		// 이벤트 종료시각보다 현재시각이 뒤면
		if(checkEvent(request) && checkEventExpiration()) {
			displayEventExpirationPage(request, response); // 이벤트 종료페이지 호출
			return false; // 다음의 인터셉터를 발생시키지 않기 위해 false 반환
		}
		return true;
	}
	
	private boolean checkEvent(HttpServletRequest request) {
		return request.getRequestURI().equals(request.getContextPath() + "/event/list.do");
	}
	
	private boolean checkEventExpiration() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2019,9,9);
		Date now = new Date();
		return now.after(calendar.getTime()); // 현재시각이 이벤트 종료시각보다 크면(뒤면) true 반환
	}
	
	private void displayEventExpirationPage(
		HttpServletRequest request,
		HttpServletResponse response
	) throws IOException {
		response.sendRedirect("eventExpired.do");
	}*/
}
