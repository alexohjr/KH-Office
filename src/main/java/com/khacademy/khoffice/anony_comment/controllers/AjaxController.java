package com.khacademy.khoffice.anony_comment.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khacademy.khoffice.anony_board.models.AnonyBoardDTO;
import com.khacademy.khoffice.anony_board.services.AnonyBoardService;
import com.khacademy.khoffice.anony_comment.models.AnonyCommentDTO;
import com.khacademy.khoffice.anony_comment.services.AnonyCommentService;


@Controller("anony_boardAjax")
@RequestMapping("/anony_comment")
public class AjaxController {
	private AnonyCommentService acmService;

	@Autowired
	@Required	
	public void setAcmService(AnonyCommentService acmService) {
		this.acmService = acmService;
	}

	// 부서게시판 댓글 더 불러오기
	@RequestMapping(value = "/ajax/{viewNum}", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getList(HttpServletResponse response, @PathVariable("viewNum") int viewNum, @RequestParam("aboard_no") int aboard_no) {
		
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		

		int pageSize = 5; // 한페이지의 글의 개수
		int currentPage = viewNum;
		int startRow = (currentPage - 1) * pageSize + 1; // 한 페이지의 시작글 번호
		int endRow = currentPage * pageSize; // 한 페이지의 마지막 글번호

		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("aboard_no", aboard_no);

		List<AnonyCommentDTO> commentlist = acmService.acommentList(map);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("viewNum", viewNum + 1);
		jsonObject.put("commentlist", commentlist);
		return jsonObject.toString();
	}

}