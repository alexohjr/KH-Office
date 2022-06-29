package com.khacademy.khoffice.department.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.khacademy.khoffice.department.daos.DepartmentDAO;
import com.khacademy.khoffice.department.models.DepartmentDTO;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	DepartmentDAO departmentDAO ;
	
	@Autowired
	@Required
	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}

	@Override
	public List<DepartmentDTO> getDepartmentDTOList() {
		List<DepartmentDTO> departmentDTOList = null;
		departmentDTOList = departmentDAO.getDepartmentDTOList("department.selectAll");
		return departmentDTOList;
	}
	
}
