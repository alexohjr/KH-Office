package com.khacademy.khoffice.project.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.khacademy.khoffice.member.models.MemberDTO;
import com.khacademy.khoffice.project.models.ProjectDetailDTO;
import com.khacademy.khoffice.project.models.ProjectMemberDTO;
import com.khacademy.khoffice.project.service.ProjectService;

@Controller
@RequestMapping("/project")
public class FormController
{

	private ProjectService projectService;
	
	@Autowired
	@Required
	public void setMemberService(ProjectService projectService)
	{
		this.projectService = projectService;
	}
	
	// 프로젝트 추가 페이지 가져오기
	@RequestMapping(value="/form", method=RequestMethod.GET)
	public String getAddForm (HttpServletRequest request, HttpSession session, 
	ModelMap model
	) 
			throws Exception
	{
//		int member_no = 1;
		int member_no = (int) session.getAttribute("session_memberNo");
		
		MemberDTO memberDTO = projectService.getLeaderInfo(member_no);
		model.addAttribute("member", memberDTO);
		
		return "tiles/project/add_form";  
	}
	
	// 프로젝트 수정 페이지 가져오기
	@RequestMapping(value="/form/{projectNo}", method=RequestMethod.GET)
	public String getEditForm(@PathVariable("projectNo") int project_no, ModelMap model, HttpServletRequest request, HttpSession session) {
//		int member_no = 1; fdsajfdsak;fjdsk;
		int member_no = (int) session.getAttribute("session_memberNo");
		ProjectDetailDTO projectDetailDTO = null;
		MemberDTO leaderDTO = null;
		List<ProjectMemberDTO> projectMemberList = null;
		
		//------------------------------------------------
		// 프로젝트 상세 정보 가져오기
		//------------------------------------------------
		projectDetailDTO = projectService.getDetail(project_no);
		
		String projectStatus = projectDetailDTO.getStatus();
		
		if (projectStatus.equals("0"))
		{
			projectDetailDTO.setStatus("p");
		}
		else if (projectStatus.equals("1"))
		{
			projectDetailDTO.setStatus("c");
		}
		else if (projectStatus.equals("2"))
		{
			projectDetailDTO.setStatus("w");
		}
		else if (projectStatus.equals("3"))
		{
			projectDetailDTO.setStatus("s");
		}
		
		// 프로젝트 리더 정보 가져오기
		leaderDTO = projectService.getLeaderInfoDetail(project_no);
		
		// 프로젝트 팀원넘버 목록 가져오기
		projectMemberList = projectService.getProjectMembers(project_no);
		
		model.addAttribute("project", projectDetailDTO);
		model.addAttribute("leader", leaderDTO);
		model.addAttribute("projectMember", projectMemberList);
		model.addAttribute("loginMember", member_no);
		
		return "tiles/project/edit_form";
	}
}
