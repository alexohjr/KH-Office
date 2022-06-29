package com.khacademy.khoffice.chat_window.controllers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.khacademy.khoffice.chat_window.services.ChatWindowService;

@Controller("chatWindowController")
@RequestMapping("/chat_window")
public class Controllers {
	private ChatWindowService chatWindowService;
	
	@Autowired
	@Required
	public void setChatWindowService(ChatWindowService chatWindowService) {
		this.chatWindowService = chatWindowService;
	}

	// 채팅창목록 가져오기
	@RequestMapping(method=RequestMethod.GET)
	public String getList(
			HttpSession session, 
			@RequestParam(value="page_num",defaultValue="1") int pageNum,
			ModelMap model
	) {
		int sessionId = (int)session.getAttribute("session_memberNo");
		
		// 서비스 호출하여 목록 가져오기
		Map<String, Object> map = chatWindowService.getChatWindowDTOList(pageNum, sessionId);
		model.addAllAttributes(map);
		
		return "tiles/chat/list";
	}
	
}
