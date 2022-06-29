package com.khacademy.khoffice.department.daos;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.khacademy.khoffice.department.models.DepartmentDTO;

public class DepartmentDAO extends SqlSessionDaoSupport{
	public List<DepartmentDTO> getDepartmentDTOList(String string){
		List<DepartmentDTO> departmentDTOList = getSqlSession().selectList(string);
		return departmentDTOList;
	}
}