package com.khacademy.khoffice.member.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.khacademy.khoffice.member.models.MemberDTO;
import com.khacademy.khoffice.member.services.MemberService;

@Controller("memberLoginController")
@RequestMapping("/member")
public class LoginController {
	private MemberService memberService;
	
	@Autowired
	@Required
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	// 멤버로그인
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String doLogin(HttpServletRequest request,MemberDTO memberDTO){
		// 서비스 호출하여 DB 조회 및 모델과 뷰 세팅후 뷰 던지기 로직 구현
			boolean dologin = false;
			
			dologin = memberService.authenticate(memberDTO,request);
				if(dologin == true) { //반환결과가 true면 로그인 성공
					return "redirect:/dashboard";
					//return "tiles/member/list";
				}
				else{ 
					return "tiles/exception/loginfail";
				}
	}
}
