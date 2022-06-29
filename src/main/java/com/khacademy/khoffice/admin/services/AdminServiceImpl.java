package com.khacademy.khoffice.admin.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.khacademy.khoffice.admin.daos.AdminDAO;
import com.khacademy.khoffice.admin.models.AdminDTO;


@Service
public class AdminServiceImpl implements AdminService {
	private AdminDAO adminDAO;
	
	@Autowired
	@Required
	public void setAdminDAO(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}
	
	@Override
	public boolean authenticate(AdminDTO adminDTO,HttpServletRequest request) {
		boolean result = false;

		try {
			String admin_id= null;
			admin_id = adminDAO.doLogin("admin.selectByEmailPassword", adminDTO);
			if(admin_id != null) {
				request.getSession().invalidate();
				request.getSession().setAttribute("session_adminID", admin_id);
				//request.getSession().setAttribute("session_memberNo", 0);    //memberNo 세션은 0으로 설정
				System.out.println("[AdminServiceImpl] Admin계정 로그인 완료. 세션 : " + request.getSession().getAttribute("session_adminID"));
				result = true;
			}else {
			 result = false;
			}
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	
}
