package com.khacademy.khoffice.chat_member.controllers;

import java.io.IOException;

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

import com.khacademy.khoffice.chat_member.models.ChatMemberDTO;
import com.khacademy.khoffice.chat_member.services.ChatMemberService;

@Controller("chatMemberAjaxController")
@RequestMapping("/chat_member")
public class AjaxController {
	private ChatMemberService chatMemberService;
	
	@Autowired
	@Required
	public void setChatMemberService(ChatMemberService chatMemberService) {
		this.chatMemberService = chatMemberService;
	}

	// 채팅방 나가기
	@RequestMapping(value="/ajax/{cwindowNo}",method=RequestMethod.POST, produces="text/plain;charset=utf-8")
	@ResponseBody
	public String add(
		HttpServletResponse response, 
		HttpSession session, 
		@PathVariable int cwindowNo
	) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		
		int sessionId = (int)session.getAttribute("session_memberNo");
		
		// DTO 세팅
		ChatMemberDTO chatMemberDTO = new ChatMemberDTO();
		chatMemberDTO.setCwindowNo(cwindowNo);
		chatMemberDTO.setMemberNo(sessionId);
		
		// 서비스 호출하여 로직 실행
		boolean result = chatMemberService.exitChatWindow(chatMemberDTO);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", result);
		return jsonObject.toString();
	}
	
}
















