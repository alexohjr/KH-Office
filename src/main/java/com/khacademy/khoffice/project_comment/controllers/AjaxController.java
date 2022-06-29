package com.khacademy.khoffice.project_comment.controllers;

import java.util.ArrayList;
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

import com.khacademy.khoffice.project_comment.models.ProjectCommentDTO;
import com.khacademy.khoffice.project_comment.service.ProjectCommentService;

@Controller("projectCommentAjaxController")
@RequestMapping("/project_comment")
public class AjaxController {

	private ProjectCommentService projectCommentService;

	@Autowired
	@Required
	public void setProjectCommentService(ProjectCommentService projectCommentService) {
		this.projectCommentService = projectCommentService;
	}
	
	// 프로젝트 상세에서 태스크 상태 변경
	@RequestMapping(value="/ajax/{viewNum}", method=RequestMethod.GET,  produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getList(HttpServletResponse response, @PathVariable("viewNum") int viewNum, @RequestParam("project_no") int project_no) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		List<ProjectCommentDTO> projectCommentList = new ArrayList<ProjectCommentDTO>();
		
		int pageSize = 5; // 한페이지의 글의 개수
		int currentPage = viewNum;
		int startRow = (currentPage - 1) * pageSize + 1; // 한 페이지의 시작글 번호
		int endRow = currentPage * pageSize; // 한 페이지의 마지막 글번호
		
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("project_no", project_no);
		
		projectCommentList = projectCommentService.getProjectCommentList(map);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("viewNum", viewNum+1);
		jsonObject.put("projectCommentList", projectCommentList);
		return jsonObject.toString();
	}
}
