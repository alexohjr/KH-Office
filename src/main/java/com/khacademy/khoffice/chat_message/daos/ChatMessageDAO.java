package com.khacademy.khoffice.chat_message.daos;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.khacademy.khoffice.chat_message.models.ChatMessageDTO;

public class ChatMessageDAO extends SqlSessionDaoSupport {

	public List<ChatMessageDTO> getMessageByCwindowNo(String string, int cwindowNo){
		List<ChatMessageDTO> chatMessageDTOList = getSqlSession().selectList(string, cwindowNo);
		return chatMessageDTOList;
	}
	
	public int addChatMessageDTO(String string, ChatMessageDTO chatMessageDTO) {
		return getSqlSession().insert(string, chatMessageDTO);
	}
	
	public ChatMessageDTO getMessageByCmessageNo(String string, ChatMessageDTO chatMessageDTO) {
		return getSqlSession().selectOne(string, chatMessageDTO);
	}
}
