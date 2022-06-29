package com.khacademy.khoffice.chat_window.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.khacademy.khoffice.chat_window.services.ChatWindowService;

@Controller("chatWindowFormController")
@RequestMapping("/chat_window")
public class FormController {
private ChatWindowService chatWindowService;
	
	@Autowired
	@Required
	public void setChatWindowService(ChatWindowService chatWindowService) {
		this.chatWindowService = chatWindowService;
	}

	// 채팅창 생성 페이지 가져오기
	@RequestMapping(value="/form",method=RequestMethod.GET)
	public String getAddForm(
	) {
		return "tiles/chat/add_form";
	}
}
