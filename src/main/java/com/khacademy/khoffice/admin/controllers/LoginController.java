package com.khacademy.khoffice.admin.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.khacademy.khoffice.admin.models.AdminDTO;
import com.khacademy.khoffice.admin.services.AdminService;

@Controller("adminLoginController")
@RequestMapping("/admin")
public class LoginController {
	private AdminService adminService;
	
	@Autowired
	@Required
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	// 어드민로그인
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String doLogin(AdminDTO adminDTO,HttpServletRequest request) {
			// 서비스 호출하여 DB 조회 및 모델과 뷰 세팅후 뷰 던지기 로직 구현
			boolean dologin = false;
			dologin = adminService.authenticate(adminDTO,request);
			if(dologin == true) {
				return "redirect:/member";
			}
			else{
				return "tiles/exception/loginfail";
			}
		}
	}
