package com.khacademy.khoffice.member.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("LogoutController")
@RequestMapping("/member")
public class LogoutController {
	
		//로그아웃
		@RequestMapping(value="/logout", method=RequestMethod.GET)
		public void doLogout(HttpServletRequest request,HttpServletResponse response) throws IOException {
			request.getSession().removeAttribute("session_memberNo");
			request.getSession().removeAttribute("session_adminID");
			
			response.sendRedirect(request.getContextPath() + "/login.jsp");	
		}
}
