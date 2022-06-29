package com.khacademy.khoffice.chat_window.daos;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.khacademy.khoffice.chat_member.models.ChatMemberDTO;
import com.khacademy.khoffice.chat_window.models.ChatWindowDTO;
import com.khacademy.khoffice.chat_window.models.ChatWindowHeadDTO;
import com.khacademy.khoffice.chat_window.models.ChatWindowMemberDTO;
import com.khacademy.khoffice.chat_window.models.Pager;

public class ChatWindowDAO extends SqlSessionDaoSupport {
	
	public int addChatWindow(String string, ChatWindowMemberDTO chatWindowMemberDTO){
		return getSqlSession().insert(string, chatWindowMemberDTO);
	}
	
	public ChatWindowHeadDTO getNameNoByNo(String string, int cwindowNo) {
		return getSqlSession().selectOne(string, cwindowNo);
	}
	
	public void deleteChatWindowIfMemberZero(String string, ChatMemberDTO chatMemberDTO) {
		getSqlSession().selectOne(string, chatMemberDTO);
	}
	
	public void deleteChatWindowIfMessageZedo(String string, ChatMemberDTO chatMemberDTO) {
		getSqlSession().selectOne(string, chatMemberDTO);
	}
	
	public List<ChatWindowDTO> getChatWindowDTOList(String string, Pager pager){
		return getSqlSession().selectList(string, pager);
	}

	public int getChatWindowCount(String string, Pager pager) {
		return getSqlSession().selectOne(string, pager);
	}
}
