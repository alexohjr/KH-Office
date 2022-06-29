package com.khacademy.khoffice.depart_comment.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.khacademy.khoffice.depart_comment.models.DepartCommentDTO;
import com.khacademy.khoffice.depart_comment.services.DepartCommentService;

@Controller("depart_commentDc")
@RequestMapping("/depart_comment")
public class Controllers {
	private DepartCommentService dcmService;
	private Object request;

	@Autowired
	@Required
	public void setDcmService(DepartCommentService dcmService) {
		this.dcmService = dcmService;
	}

	// 댓글 등록
	@RequestMapping(method = RequestMethod.POST)
	public String add(DepartCommentDTO departCommentDTO, HttpServletRequest request,HttpSession session) {
		int member_no = (int)session.getAttribute("session_memberNo");
		System.out.println("댓글 등록 메서드");
		
		departCommentDTO.setMember_no(member_no);
		dcmService.dcommentInsert(departCommentDTO);
		
		int dboard_no = new Integer(request.getParameter("dboard_no"));

		return "redirect:/depart_board/" + dboard_no;
	} 

	// 댓글 수정/삭제
	@RequestMapping(value = "/{dcomment_no}", method = RequestMethod.POST)
	public String update(@PathVariable("dcomment_no") int dcomment_no, DepartCommentDTO departCommentDTO,HttpServletRequest request,HttpSession session) {
		int member_no = (int)session.getAttribute("session_memberNo");
		System.out.println("댓글 수정 메서드");
		departCommentDTO.setMember_no(member_no);
		/*dcmService.dcommentInsert(departCommentDTO);*/
		int dboard_no = new Integer(request.getParameter("dboard_no"));
		
		String type = request.getParameter("type");

		if (type == "edit" || type.equals("edit")) {
			departCommentDTO.setDcomment_no(dcomment_no);
			dcmService.dcommentUpdate(departCommentDTO);
		} else if (type == "delete" || type.equals("delete")) {
			dcmService.dcommentDelete(dcomment_no);
		}

		return "redirect:/depart_board/" + dboard_no;
	}

}