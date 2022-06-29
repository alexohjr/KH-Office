package com.khacademy.khoffice.notice_board.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.khacademy.khoffice.notice_board.models.NoticeBoardDTO;
import com.khacademy.khoffice.notice_board.services.NoticeBoardService;


@Controller("notice_boardForm")
@RequestMapping("/notice_board")
public class FormController {
	
	private NoticeBoardService nbservice;

	@Autowired
	@Required
	public void setNbService(NoticeBoardService nbservice) {
		this.nbservice = nbservice;
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String getAddForm(HttpSession session, ModelMap modelMap) {
		 String adminId = (String) session.getAttribute("adminId");
		 modelMap.addAttribute("adminId", adminId);
		
		return "tiles/notice_board/add_form";
	}
	
	@RequestMapping(value="/form/{nboard_no}", method = RequestMethod.GET)
	public String getEditForm(@PathVariable("nboard_no") int nboard_no,ModelMap modelMap, HttpSession session) throws Exception {
		 String adminId = (String) session.getAttribute("adminId");
		 modelMap.addAttribute("adminId", adminId);
		 
		NoticeBoardDTO dto = nbservice.boardDetail(nboard_no);
		
		modelMap.addAttribute("board", dto);
		return "tiles/notice_board/edit_form";
	}
	
}
