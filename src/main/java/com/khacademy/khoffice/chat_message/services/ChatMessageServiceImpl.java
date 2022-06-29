package com.khacademy.khoffice.chat_message.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.khacademy.khoffice.chat_member.daos.ChatMemberDAO;
import com.khacademy.khoffice.chat_message.daos.ChatMessageDAO;
import com.khacademy.khoffice.chat_message.models.ChatMessageDTO;
import com.khacademy.khoffice.chat_window.daos.ChatWindowDAO;
import com.khacademy.khoffice.chat_window.models.ChatWindowHeadDTO;
import com.khacademy.khoffice.chat_window.models.ChatWindowMemberJoinDTO;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {
	private ChatWindowDAO chatWindowDAO;
	private ChatMemberDAO chatMemberDAO;
	private ChatMessageDAO chatMessageDAO;
	
	@Autowired
	@Required
	public void setChatWindowDAO(ChatWindowDAO chatWindowDAO) {
		this.chatWindowDAO = chatWindowDAO;
	}

	@Autowired
	@Required
	public void setChatMemberDAO(ChatMemberDAO chatMemberDAO) {
		this.chatMemberDAO = chatMemberDAO;
	}

	@Autowired
	@Required
	public void setChatMessageDAO(ChatMessageDAO chatMessageDAO) {
		this.chatMessageDAO = chatMessageDAO;
	}

	@Override
	public ChatWindowHeadDTO getChatWindowHeadDTO(int cwindowNo) {
		ChatWindowHeadDTO chatWindowHeadDTO = new ChatWindowHeadDTO();
		
		// 채팅방 이름, 채팅방 번호 세팅
		ChatWindowHeadDTO result = chatWindowDAO.getNameNoByNo("chat_window.selectNameNoByCwindowNo", cwindowNo);
		chatWindowHeadDTO.setCwindowName(result.getCwindowName());
		chatWindowHeadDTO.setCwindowNo(result.getCwindowNo());
		
		// 채팅방 인원 수 세팅
		result = chatMemberDAO.getCountByCwindowNo("chat_member.selectCountByCwindowNo", cwindowNo);
		chatWindowHeadDTO.setCwindowMemberCount(result.getCwindowMemberCount());
		
		// 1:1 채팅방인 경우 출력하기 위한 마지막 채팅보낸 사원 썸네일 가져오기
		if(chatWindowHeadDTO.getCwindowMemberCount() == 2) {
			result = chatMemberDAO.getMemberThumPath("chat_member.selectThumbPathByCwindowNo", cwindowNo);
			chatWindowHeadDTO.setThumbPath(result.getThumbPath());
		}
		System.out.println(chatWindowHeadDTO.getThumbPath());
		return chatWindowHeadDTO;
	}

	@Override
	public List<ChatMessageDTO> getChatMessageDTOList(int cwindowNo) {
		List<ChatMessageDTO> chatMessageDTOList = chatMessageDAO.getMessageByCwindowNo("chat_message.selectByCwinodwNo", cwindowNo);
		return chatMessageDTOList;
	}

	@Override
	public List<ChatWindowMemberJoinDTO> getChatWindowMemberDTOList(int cwindowNo) {
		List<ChatWindowMemberJoinDTO> chatWindowMemberDTOList = chatMemberDAO.getMemberByCwindowNo("chat_member.selectByCwindowNo", cwindowNo);
		return chatWindowMemberDTOList;
	}

	@Override
	public ChatMessageDTO addChatMessageDTO(ChatMessageDTO chatMessageDTO) throws IOException {
		int insertedRows = 0;
		insertedRows = chatMessageDAO.addChatMessageDTO("chat_message.insertOne", chatMessageDTO);
		if(insertedRows == 0) {
			// 삽입에 실패한 경우
			throw new IOException("채팅 메시지 삽입 실패");
		}
		// 삽입한 메시지 조회하기
		return chatMessageDAO.getMessageByCmessageNo("chat_message.selectByCmessageNo", chatMessageDTO);
	}
	
}
