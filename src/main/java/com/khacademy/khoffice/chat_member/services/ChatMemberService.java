package com.khacademy.khoffice.chat_member.services;

import java.io.IOException;

import com.khacademy.khoffice.chat_member.models.ChatMemberDTO;
import com.khacademy.khoffice.chat_window.models.ChatWindowMemberDTO;
import com.khacademy.khoffice.member.models.MemberDTO;

public interface ChatMemberService {
	public boolean exitChatWindow(ChatMemberDTO chatMemberDTO) throws IOException;
	public boolean addChatWindowMemberDTO(ChatWindowMemberDTO chatWindowMemberDTO);
	public MemberDTO getMemberByCwindowNoMemberNo(ChatMemberDTO chatMemberDTO);
}
