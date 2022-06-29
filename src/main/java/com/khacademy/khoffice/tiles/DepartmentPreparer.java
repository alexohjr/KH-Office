package com.khacademy.khoffice.tiles;

import java.util.List;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import com.khacademy.khoffice.department.models.DepartmentDTO;
import com.khacademy.khoffice.department.services.DepartmentService;

@Component
public class DepartmentPreparer implements ViewPreparer {
	DepartmentService departmentService;
	
	@Autowired
	@Required
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		List<DepartmentDTO> departmentDTOList = null;
		departmentDTOList = departmentService.getDepartmentDTOList();
		attributeContext.putAttribute("departmentDTOList", new Attribute(departmentDTOList), true);
	}
}
