package com.khacademy.khoffice.chat_message.services;

import java.io.IOException;
import java.util.List;

import com.khacademy.khoffice.chat_message.models.ChatMessageDTO;
import com.khacademy.khoffice.chat_window.models.ChatWindowHeadDTO;
import com.khacademy.khoffice.chat_window.models.ChatWindowMemberJoinDTO;

public interface ChatMessageService {
	
	public ChatWindowHeadDTO getChatWindowHeadDTO(int cwindowNo);
	public List<ChatMessageDTO> getChatMessageDTOList(int cwindowNo);
	public List<ChatWindowMemberJoinDTO> getChatWindowMemberDTOList(int cwindowNo);
	public ChatMessageDTO addChatMessageDTO(ChatMessageDTO chatMessageDTO) throws IOException;
	
}




