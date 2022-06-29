package com.khacademy.khoffice.admin.daos;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.khacademy.khoffice.admin.models.AdminDTO;

public class AdminDAO extends SqlSessionDaoSupport{
	public String doLogin(String string, AdminDTO adminDTO) {
		// mapper 사용 로직 작성
		String x = null;
		x =  getSqlSession().selectOne(string, adminDTO);
		return x;
	}
} 
