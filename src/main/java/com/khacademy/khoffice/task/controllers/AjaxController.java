package com.khacademy.khoffice.task.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khacademy.khoffice.project.service.ProjectService;
import com.khacademy.khoffice.task.models.TaskDTO;
import com.khacademy.khoffice.task.services.TaskService;

@Controller("taskAjaxController")
@RequestMapping("/task")
public class AjaxController {

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
	
	// 프로젝트 상세에서 태스크 상태 변경
	@RequestMapping(value="/ajax/{taskNo}", method=RequestMethod.POST,  produces="text/plain;charset=utf-8")
	@ResponseBody
	public String update(HttpServletResponse response, @PathVariable("taskNo") int task_no, @RequestParam("status") int status, @RequestParam("project_no") int project_no, @RequestParam("projectStatus") String projectStatus) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("task_no", task_no);
		map.put("status", status);
		taskService.updateTaskStatus(map);
		
		List<TaskDTO> taskList = projectService.getTaskList(project_no);
		
		// 태스크 개수
		int taskCount = taskList.size();
		
		// 프로젝트 진행률
		double total = 0;
		
		for(int i=0; i<taskList.size(); i++) {
			String taskStatus = taskList.get(i).getStatus();
			double statusNum = Double.parseDouble(taskStatus);
						
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
		
		String pstatus;
		
		if(projectProgress == 100) {
			pstatus = "1";
		} else {
			pstatus = "0";
		}
		
		if (!projectStatus.equals("w") && !projectStatus.equals("s")) {
			map.put("status", pstatus);
			map.put("project_no", project_no);
			projectService.updateProjectStatus(map);
		} 
		
		projectProgress = Math.round(projectProgress*10)/10.0;
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("task_no", new Integer(task_no));
		jsonObject.put("projectStatus", projectStatus);
		jsonObject.put("status", new Integer(status));
		jsonObject.put("projectProgress", projectProgress);
		return jsonObject.toString();
	}
}
