package com.khacademy.khoffice.depart_board.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.khacademy.khoffice.depart_board.models.DepartBoardDTO;
import com.khacademy.khoffice.depart_board.services.DepartBoardService;


@Controller("depart_boardForm")
@RequestMapping("/depart_board")
public class FormController {
	
	private DepartBoardService dbservice;

	@Autowired
	@Required
	public void setDbService(DepartBoardService dbservice) {
		this.dbservice = dbservice;
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String getAddForm(ModelMap model,HttpSession session) {
		int member_no = (int)session.getAttribute("session_memberNo");
		String depart_name=dbservice.getDepartmentName(member_no);
		model.addAttribute("depart_name", depart_name);
		return "tiles/depart_board/add_form";
	}
	
	@RequestMapping(value="/form/{dboard_no}", method = RequestMethod.GET)
	public String getEditForm(@PathVariable("dboard_no") int dboard_no,ModelMap model,HttpSession session) throws Exception {
		int member_no = (int)session.getAttribute("session_memberNo");
		DepartBoardDTO dto = dbservice.boardDetail(dboard_no);
		String depart_name=dbservice.getDepartmentName(member_no);
		model.addAttribute("board", dto);
		model.addAttribute("depart_name", depart_name);
		return "tiles/depart_board/edit_form";
	}
	
}
