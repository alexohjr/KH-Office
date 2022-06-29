package com.khacademy.khoffice.anony_comment.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.khacademy.khoffice.anony_comment.models.AnonyCommentDTO;
import com.khacademy.khoffice.anony_comment.services.AnonyCommentService;


@Controller("anony_commentAc")
@RequestMapping("/anony_comment")
public class Controllers {
	private AnonyCommentService acmService;

	@Autowired
	@Required	
	public void setAcmService(AnonyCommentService acmService) {
		this.acmService = acmService;
	}

	// 댓글 등록
	@RequestMapping(method = RequestMethod.POST)
	public String add(AnonyCommentDTO anonyCommentDTO, HttpServletRequest request) {
		int aboard_no = new Integer(request.getParameter("aboard_no"));
		acmService.acommentInsert(anonyCommentDTO);
			
		return "redirect:/anony_board/" + aboard_no;
	}
	
}