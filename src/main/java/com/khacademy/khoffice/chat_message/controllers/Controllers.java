package com.khacademy.khoffice.chat_message.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.khacademy.khoffice.chat_message.models.ChatMessageDTO;
import com.khacademy.khoffice.chat_message.services.ChatMessageService;
import com.khacademy.khoffice.chat_window.models.ChatWindowHeadDTO;
import com.khacademy.khoffice.chat_window.models.ChatWindowMemberJoinDTO;

@Controller("chatMessageController")
@RequestMapping("/chat_message")
public class Controllers {
	private ChatMessageService chatMessageService;

	@Autowired
	@Required
	public void setChatMessageService(ChatMessageService chatMessageService) {
		this.chatMessageService = chatMessageService;
	}
	
	@RequestMapping(value="/{cwindowNo}", method=RequestMethod.GET)
	public String getList(
			@PathVariable int cwindowNo,
			ModelMap medel
	) {
		
		// 채팅방 정보 세팅
		ChatWindowHeadDTO chatWindowHeadDTO = chatMessageService.getChatWindowHeadDTO(cwindowNo);
		
		// 채팅 메시지 리스트 세팅
		List<ChatMessageDTO> chatMessageDTOList = chatMessageService.getChatMessageDTOList(cwindowNo);

		// 채팅 멤버 리스트 세팅
		List<ChatWindowMemberJoinDTO> chatWindowMemberJoinDTOList = chatMessageService.getChatWindowMemberDTOList(cwindowNo);
		
		medel.addAttribute("chatWindowHeadDTO", chatWindowHeadDTO);
		medel.addAttribute("chatMessageDTOList", chatMessageDTOList);
		medel.addAttribute("chatWindowMemberJoinDTOList", chatWindowMemberJoinDTOList);
		
		return "tiles/chat/chat_window";
	}
	
	
	
	
}











