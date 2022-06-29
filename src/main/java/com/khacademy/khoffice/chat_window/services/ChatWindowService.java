package com.khacademy.khoffice.chat_window.services;

import java.io.IOException;
import java.util.Map;

import com.khacademy.khoffice.chat_window.models.ChatWindowMemberDTO;

public interface ChatWindowService {
	public int addChatWindowMember(ChatWindowMemberDTO chatWindowMemberDTO) throws IOException;
	public Map<String, Object> getChatWindowDTOList(int pageNum, int sessionId);
}
