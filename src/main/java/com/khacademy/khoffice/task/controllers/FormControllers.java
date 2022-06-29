package com.khacademy.khoffice.task.controllers;

import java.util.ArrayList;
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
import com.khacademy.khoffice.project.service.ProjectService;
import com.khacademy.khoffice.task.models.TaskDTO;
import com.khacademy.khoffice.task.services.TaskService;

@Controller
@RequestMapping("/task")
public class FormControllers {

	private TaskService taskService;
	private ProjectService projectService;

	@Autowired
	@Required
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	@Autowired
	@Required
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	// 태스크 생성 페이지 가져오기
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String getAddFrom(HttpServletRequest request, HttpSession session, ModelMap model) {
		int project_no = new Integer(request.getParameter("project_no"));
//		int loginMember = 1;
		int loginMember = (int) session.getAttribute("session_memberNo");
		List<MemberDTO> memberList = new ArrayList<>();

		memberList = taskService.getProjectMember(project_no);
		model.addAttribute("projectMember", memberList);
		model.addAttribute("project_no", project_no);
		model.addAttribute("loginMember", loginMember);
		return "tiles/project/task/add_form";
	}

	// 태스크 수정 페이지 가져오기
	@RequestMapping(value = "/form/{taskNo}", method = RequestMethod.GET)
	public String getEditForm(@PathVariable("taskNo") int task_no, ModelMap model, HttpServletRequest request,
			HttpSession session) {
		List<MemberDTO> projectMemberList = new ArrayList<>();
		int project_no = new Integer(request.getParameter("project_no"));
//		int member_no = 1;
		int member_no = (int) session.getAttribute("session_memberNo");

		TaskDTO taskDTO = taskService.getTaskDetail(task_no);

		List<MemberDTO> memberList = projectService.getMemberListByTaskNo(task_no);
		taskDTO.setMemberList(memberList);
		// 프로젝트 전체 멤버가져오기
		projectMemberList = taskService.getProjectMember(project_no);
		int projectStatus = projectService.checkProjectStatus(project_no);

		// 원본 파일명으로 만들기
		if (taskDTO.getFile_path() != null) {
			String fileName = taskDTO.getFile_path();
			int index = fileName.indexOf("_");
			String downloadFileName = fileName.substring(index + 1);
			taskDTO.setFile_path(downloadFileName);
			System.out.println("fileName fileName :: " + fileName);
		}

		model.addAttribute("taskDTO", taskDTO);
		model.addAttribute("projectMemberList", projectMemberList);
		model.addAttribute("project_no", project_no);
		model.addAttribute("loginMember", member_no);
		model.addAttribute("projectStatus", projectStatus);

		return "tiles/project/task/edit_form";
	}
}
