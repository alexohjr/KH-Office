package com.khacademy.khoffice.project_comment.controllers;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.khacademy.khoffice.project_comment.models.ProjectCommentDTO;
import com.khacademy.khoffice.project_comment.service.ProjectCommentService;

@Controller("projectCommentControllers")
@RequestMapping("/project_comment")
public class Controllers {
	
	private ProjectCommentService projectCommentService;
	
	@Autowired
	@Required
	public void setProjectCommentService(ProjectCommentService projectCommentService) {
		this.projectCommentService = projectCommentService;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	// 프로젝트 댓글 추가
	@RequestMapping(method=RequestMethod.POST)
	public String add(ProjectCommentDTO pcommentDTO, HttpServletRequest request) {
		int project_no = new Integer(request.getParameter("project_no"));
		projectCommentService.addProjectComment(pcommentDTO);
		return "redirect:/project/"+project_no;
	}
	
	// 프로젝트 댓글 수정/삭제
	@RequestMapping(value="/{pcommentNo}", method=RequestMethod.POST)
	public String update(@PathVariable("pcommentNo") int pcomment_no, ProjectCommentDTO pcommentDTO, HttpServletRequest request, HttpSession session) {
		//int member_no = 1;
		int member_no = (int) session.getAttribute("session_memberNo");
		int project_no = new Integer(request.getParameter("project_no"));
		
		String type = request.getParameter("type");
		
		if (type=="edit" || type.equals("edit")) {
			pcommentDTO.setPcomment_no(pcomment_no);
			projectCommentService.updateProjectComment(pcommentDTO);
		} else if (type=="delete" || type.equals("delete")) {
			projectCommentService.deleteProjectComment(pcomment_no);
		}
		
		return "redirect:/project/"+project_no;
	}
	
}
