package com.khacademy.khoffice.chat_member.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.khacademy.khoffice.chat_member.daos.ChatMemberDAO;
import com.khacademy.khoffice.chat_member.models.ChatMemberDTO;
import com.khacademy.khoffice.chat_window.daos.ChatWindowDAO;
import com.khacademy.khoffice.chat_window.models.ChatWindowMemberDTO;
import com.khacademy.khoffice.member.models.MemberDTO;

@Service
public class ChatMemberServiceImpl implements ChatMemberService {
	private ChatMemberDAO chatMemberDAO;
	private ChatWindowDAO chatWindowDAO;
	
	@Autowired
	@Required
	public void setChatMemberDAO(ChatMemberDAO chatMemberDAO) {
		this.chatMemberDAO = chatMemberDAO;
	}
	
	@Autowired
	@Required
	public void setChatWindowDAO(ChatWindowDAO chatWindowDAO) {
		this.chatWindowDAO = chatWindowDAO;
	}

	@Override
	public boolean exitChatWindow(ChatMemberDTO chatMemberDTO) throws IOException {
		boolean result = true;
		// 한명 채팅창 나가기
		int deletedRows = chatMemberDAO.exitChatWindow("chat_member.deleteOne", chatMemberDTO);
		
		if(deletedRows == 0) {
			result = false;
			throw new IOException("채팅 멤버 삭제 실패");
		}
		
		// 현재 방 인원 DB에서 조회하여
		// 0인 경우 방 및 해당 방에 대한 메시지 모두 삭제
		// 1인 이상인 경우 아무것도 안하는 프로시저 호출
		chatWindowDAO.deleteChatWindowIfMemberZero("chat_window.deleteIfMemberZero", chatMemberDTO);
		chatWindowDAO.deleteChatWindowIfMessageZedo("chat_window.deleteIfMessageZero", chatMemberDTO);
		
		return result;
	}

	@Override
	public boolean addChatWindowMemberDTO(ChatWindowMemberDTO chatWindowMemberDTO) {
		boolean result = false;
		int insertedRows = chatMemberDAO.addChatMember("chat_member.insertAll", chatWindowMemberDTO);
		if(insertedRows > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public MemberDTO getMemberByCwindowNoMemberNo(ChatMemberDTO chatMemberDTO) {
		return chatMemberDAO.getCountChatMemberByCwindowNoMemberNo("chat_member.selectMemberByCwindowNoMemberNo", chatMemberDTO);
	}
	
}
