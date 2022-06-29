package com.khacademy.khoffice.chat_window.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khacademy.khoffice.chat_member.services.ChatMemberService;
import com.khacademy.khoffice.chat_window.models.ChatWindowMemberDTO;
import com.khacademy.khoffice.chat_window.services.ChatWindowService;

@Controller("chatWindowAjaxController")
@RequestMapping("/chat_window")
public class AjaxController {
	private ChatWindowService chatWindowService;
	private ChatMemberService chatMemberService;
	
	@Autowired
	@Required
	public void setChatWindowServicce(ChatWindowService chatWindowService) {
		this.chatWindowService = chatWindowService;
	}
	
	@Autowired
	@Required
	public void setChatMemberService(ChatMemberService chatMemberService) {
		this.chatMemberService = chatMemberService;
	}

	// 채팅창 만들기
	@RequestMapping(value="/ajax",method=RequestMethod.POST, produces="text/plain;charset=utf-8")
	@ResponseBody
	public String add(
		HttpServletResponse response, 
		HttpSession session, 
		ChatWindowMemberDTO chatWindowMemberDTO
	) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		// 새 창을 띄워 채팅방 열어야 하므로
		// insert 한 cwindow_no 반환해야 함
		
		// 나의 세션값 추가
		List<Integer> memberNoList = chatWindowMemberDTO.getMember_no();
		int sessionId = (int)session.getAttribute("session_memberNo");
		memberNoList.add(sessionId);
		chatWindowMemberDTO.setMember_no(memberNoList);
		
		 int cwindowNo = chatWindowService.addChatWindowMember(chatWindowMemberDTO);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", cwindowNo);
		return jsonObject.toString();
	}
	
	// 채팅인원 추가하기
	@RequestMapping(value="/ajax/{cwindowNo}",method=RequestMethod.POST, produces="text/plain;charset=utf-8")
	@ResponseBody
	public String addChatMember(
		HttpServletResponse response, 
		HttpSession session, 
		@PathVariable int cwindowNo, 
		ChatWindowMemberDTO chatWindowMemberDTO
	) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		
		// 먼저 cwindow_no 추가
		chatWindowMemberDTO.setCwindow_no(cwindowNo);

		// chatWindowMemberDTO.meber_no 리스트에 담긴 사원들을 cwindow_no 채팅방에 추가
		boolean result = chatMemberService.addChatWindowMemberDTO(chatWindowMemberDTO);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", result);
		return jsonObject.toString();
	}
	
}
