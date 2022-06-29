package com.khacademy.khoffice.member.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khacademy.khoffice.member.models.MemberDTO;
import com.khacademy.khoffice.member.services.MemberService;

@Controller("memberAjaxController")
@RequestMapping("/member")
public class AjaxController {
	private MemberService memberService;
	
	@Autowired
	@Required
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	// 해당 부서에 속한 멤버 리스트 구하기
	@RequestMapping(value="/ajax",method=RequestMethod.GET, produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getList(
		HttpServletResponse response,
		@RequestParam("departmentNo") String departmentNo
	) {
		response.setContentType("text/html;charset=utf-8");
		List<MemberDTO> memberDTOList =  memberService.getMemberListByDepartmentNo(Integer.parseInt(departmentNo));
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", memberDTOList);
		return jsonObject.toString();
	}
	
	// 즐겨찾기
	@RequestMapping(value="/ajax/{memberNo}",method=RequestMethod.POST, produces="text/plain;charset=utf-8")
	@ResponseBody
	public String update(
		HttpServletRequest request,
		HttpServletResponse response,
		@PathVariable("memberNo") int bMemberNo,
		@RequestParam(value="page_num",defaultValue="1")String pageNum,
		@RequestParam(defaultValue="")String keyword,
		@RequestParam("is_bookmark") int isBookmark
	) {
		
		response.setContentType("text/html;charset=utf-8");
		
		//세션에 저장된 값 받아오기
		HttpSession session = request.getSession();	
		//String string_memberNo = (String)session.getAttribute("session_memberNo");
		int memberNo = (int) session.getAttribute("session_memberNo");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_no", memberNo);
		map.put("bmember_no",bMemberNo);
		boolean result = false;
		
		if(isBookmark == 0) { 
			result =  memberService.insertBookmark(map);
		} else if(isBookmark == 1) {
			result =  memberService.deleteBookmark(map);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", result);
		
		return jsonObject.toString();
	}
	
	//이메일 중복체크하기
	@RequestMapping(value = "/ajax/email/check" , method = RequestMethod.POST)
	public @ResponseBody String doCheck(
			@ModelAttribute("memberDTO") MemberDTO memberDTO
			) throws Exception{
	    int result = memberService.emailCheck(memberDTO.getEmail());
	    return String.valueOf(result);
	}
	
	
	
}