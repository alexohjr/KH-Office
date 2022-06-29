package com.khacademy.khoffice.chat_member.daos;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.khacademy.khoffice.chat_member.models.ChatMemberDTO;
import com.khacademy.khoffice.chat_window.models.ChatWindowHeadDTO;
import com.khacademy.khoffice.chat_window.models.ChatWindowMemberDTO;
import com.khacademy.khoffice.chat_window.models.ChatWindowMemberJoinDTO;
import com.khacademy.khoffice.member.models.MemberDTO;

public class ChatMemberDAO extends SqlSessionDaoSupport {
	
	public int addChatMember(String string, ChatWindowMemberDTO chatWindowMemberDTO){
		return getSqlSession().insert(string, chatWindowMemberDTO);
	}
	
	public ChatWindowHeadDTO getCountByCwindowNo(String string, int cwindowNo) {
		return getSqlSession().selectOne(string, cwindowNo);
	}
	
	public ChatWindowHeadDTO getMemberThumPath(String string, int cwindowNo) {
		return getSqlSession().selectOne(string, cwindowNo);
	}
	
	public List<ChatWindowMemberJoinDTO> getMemberByCwindowNo(String string, int cwindowNo) {
		return getSqlSession().selectList(string, cwindowNo);
	}
	
	public int exitChatWindow(String string, ChatMemberDTO chatMemberDTO) {
		return getSqlSession().delete(string, chatMemberDTO);
	}
	
	
	public MemberDTO getCountChatMemberByCwindowNoMemberNo(String string, ChatMemberDTO chatMemberDTO) {
		return getSqlSession().selectOne(string, chatMemberDTO);
	}
	
}
