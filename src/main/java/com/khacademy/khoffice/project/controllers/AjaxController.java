package com.khacademy.khoffice.project.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khacademy.khoffice.member.models.MemberDTO;
import com.khacademy.khoffice.project.models.ProjectListDTO;
import com.khacademy.khoffice.project.service.ProjectService;
import com.khacademy.khoffice.task.models.TaskDTO;

@Controller("projectAjaxController")
@RequestMapping("/project")
public class AjaxController {
	private ProjectService projectService;
	
	@Autowired
	@Required
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	// 할당된 태스크 있는 지 여부 확인
	@RequestMapping(value="/ajax",method=RequestMethod.POST, produces="text/plain;charset=utf-8")
	@ResponseBody
	public String memberOnTask(HttpServletResponse response, @RequestParam("member_no") int member_no, @RequestParam("project_no") int project_no) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_no", member_no);
		map.put("project_no", project_no);
		int taskCount = projectService.memberOnTask(map);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("taskCount", new Integer(taskCount));
		return jsonObject.toString();
	}
	
	// 프로젝트 리스트 더 불러오기
	@RequestMapping(value="/ajax/{viewNum}", method=RequestMethod.GET, produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getList(HttpServletResponse response, @PathVariable("viewNum") int viewNum, HttpSession session) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		List<ProjectListDTO> projectList = new ArrayList<ProjectListDTO>();
		List<TaskDTO> taskList = null;
		MemberDTO memberDTO = null;
		
//		int member_no = 1;
		int member_no = (int) session.getAttribute("session_memberNo");
		
		int pageSize = 8; // 첫 페이지 리스트 개수
		int currentPage = viewNum; // 2
		int startRow = (currentPage - 1) * pageSize + 1; // 한 페이지의 시작글 번호 --9
		int endRow = currentPage * pageSize; // 한 페이지의 마지막 글번호 -- 16
		
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		
		
		map.put("member_no", member_no);

		projectList = projectService.getProjcetList(map);
		
		String projectStatus = "";
		
		for (int i=0; i < projectList.size(); i++) {
			// 프로젝트 상태 문자열로 바꾸기
			projectStatus = projectList.get(i).getStatus();
			if (projectStatus.equals("0")) {
				projectList.get(i).setStatus("p");
			} else if (projectStatus.equals("1")) {
				projectList.get(i).setStatus("c");
			} else if (projectStatus.equals("2")) {
				projectList.get(i).setStatus("w");
			} else if (projectStatus.equals("3")) {
				projectList.get(i).setStatus("s");
			}
			
			// 해당 project_no 값 가져오기
			int project_no = projectList.get(i).getProject_no();
			
			// 각 프로젝트의 멤버 수 구한 후 projectList에 값 넣어주기
			int memberCount = projectService.getMemberCount(project_no);
			projectList.get(i).setMemberCount(memberCount-1);
			
			// 각 프로젝트의 태스크 수 구한 후 projectList에 값 넣어주기
			int taskCount = projectService.getTaskCount(project_no);
			projectList.get(i).setTaskCount(taskCount);
			
			// 프로젝트 리더 정보 조회하기
			memberDTO = projectService.getLeaderInfoList(project_no); 
			
			// 프로젝트 리더 정보 list에 담기
			projectList.get(i).setLeaderName(memberDTO.getName());
			projectList.get(i).setLeaderPosition(memberDTO.getPosition());
			projectList.get(i).setLeaderThumb(memberDTO.getThumb_path()); 
			
			// 프로젝트 프로그레스 바
			taskList = projectService.getTaskList(project_no);
					
			// 태스크 개수
			taskCount = taskList.size();
			double taskProgress = 0;
			// 프로젝트 진행률
			double total = 0;
					
			for(int j=0; j<taskList.size(); j++) {
				String status = taskList.get(j).getStatus();
				double statusNum = Double.parseDouble(status);
							
				if(statusNum == 0) {
					statusNum = 0;   // 0
				} else if(statusNum == 1) {
					statusNum = 0.5; // 0.5
				} else if(statusNum == 2) {
					statusNum = 1; // 1
				}
				total += statusNum;
			}
			double projectProgress = total/taskCount * 100;
					
			long projectProgress2 = Math.round(projectProgress);
			
			projectList.get(i).setProgress(projectProgress2);
			
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("viewNum", viewNum+1);
		jsonObject.put("projectList", projectList);
		return jsonObject.toString();
	}
}