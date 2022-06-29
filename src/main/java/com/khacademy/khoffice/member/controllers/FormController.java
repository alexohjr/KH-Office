package com.khacademy.khoffice.member.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.khacademy.khoffice.department.models.DepartmentDTO;
import com.khacademy.khoffice.department.services.DepartmentService;
import com.khacademy.khoffice.member.models.MemberDetailDTO;
import com.khacademy.khoffice.member.services.MemberService;

@Controller("memberFormController")
@RequestMapping("/member")
public class FormController {
	private DepartmentService departmentService;
	private MemberService memberService;
	
	@Autowired
	@Required
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	@Autowired
	@Required
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping(value="/form", method=RequestMethod.GET)
	public String getAddForm(ModelMap model) {
		List<DepartmentDTO> list = null;
		list = departmentService.getDepartmentDTOList();
		model.addAttribute("list", list);
		return "tiles/member/add_form";
	}
	
	@RequestMapping(value="/form/{memberNo}", method=RequestMethod.GET)
	public String getEditForm(
			@PathVariable("memberNo") int member_no,
			@RequestParam(defaultValue="edit")String type,
			@RequestParam(defaultValue="1")String page_num,
			@RequestParam(defaultValue="")String keyword,
			@RequestParam(defaultValue="0")int is_bookmark,
			ModelMap model
			) {
		String result = null;
		
		if(type.equals("change_pw")) {     //사원 개인비밀번호 변경폼으로 이동하는 부분
			result = "tiles/member/change_pw";
		} else if(type.equals("edit")) {     //어드민 사원수정폼으로 이동하는 부분
			List<DepartmentDTO> deptList = null; 
			deptList = departmentService.getDepartmentDTOList(); //폼에 보여질 부서명을 DB에서 가져옴
			
			List<MemberDetailDTO> detailList = null;
			detailList = memberService.getMemberDetail(member_no);

			model.addAttribute("deptList", deptList);
			model.addAttribute("detailList",detailList);
			result = "tiles/member/edit_form";
		}
		model.addAttribute("memberNo", member_no);
		model.addAttribute("pageNum", page_num);
		model.addAttribute("keyword", keyword);
		model.addAttribute("isBookmark", is_bookmark);
		return result;
	}
	
	
}
