package com.khacademy.khoffice.admin.services;

import javax.servlet.http.HttpServletRequest;

import com.khacademy.khoffice.admin.models.AdminDTO;

public interface AdminService {
	boolean authenticate(AdminDTO adminDTO,HttpServletRequest request);
}
