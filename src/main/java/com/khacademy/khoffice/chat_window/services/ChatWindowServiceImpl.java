package com.khacademy.khoffice.chat_window.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.khacademy.khoffice.chat_member.daos.ChatMemberDAO;
import com.khacademy.khoffice.chat_window.daos.ChatWindowDAO;
import com.khacademy.khoffice.chat_window.models.ChatWindowDTO;
import com.khacademy.khoffice.chat_window.models.ChatWindowMemberDTO;
import com.khacademy.khoffice.chat_window.models.Pager;

@Service
public class ChatWindowServiceImpl implements ChatWindowService {
	private ChatWindowDAO chatWindowDAO;
	private ChatMemberDAO chatMemberDAO;
	
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

	@Override
	public int addChatWindowMember(ChatWindowMemberDTO chatWindowMemberDTO) throws IOException {
		// 1. 먼저 채팅방을 생성 0
		// 2. 채팅방 생성 확인되면 채팅방에 채팅멤버 삽입 0
		// 3. 채팅방에 치팅멤버도 삽입되면 채팅방 번호 조회 0
		// 4. 채팅방 번호 리턴 0
		
		// 채팅방 생성
		int insertedRows = chatWindowDAO.addChatWindow("chat_window.insertOne" , chatWindowMemberDTO);
		if(insertedRows != 1) {
			// 삽입에 실패한 경우
			throw new IOException("채팅방 생성 실패");
		}
		
		int cwindowNo = chatWindowMemberDTO.getCwindow_no();
		chatWindowMemberDTO.setCwindow_no(cwindowNo);
		insertedRows = chatMemberDAO.addChatMember("chat_member.insertAll", chatWindowMemberDTO);
		if(insertedRows == 0) {
			// 삽입에 실패한 경우
			throw new IOException("채팅방 멤버 할당 실패");
		}
		
		return chatWindowMemberDTO.getCwindow_no();
	}

	@Override
	public Map<String, Object> getChatWindowDTOList(int pageNum, int sessionId) {
		
		// 페이징 계산
		int pageSize = 10; // 한 페이지에 보여줄 글 수
		int pagerSize = 5; // 페이저 갯수
		
		int postCount = 0;
		int pageCount = 0;
		int startPager = 0;
		int endPager = 0;
		// String queryString = ""; // 페이징시 넘겨줄 쿼리스트링
		
		int start = (pageNum - 1) * pageSize + 1; // 페이저 시작 수
		int end = pageNum * pageSize; // 페이저 끝 수
		
		Pager pager = new Pager();
		pager.setStart(start);
		pager.setEnd(end);
		pager.setSessionId(sessionId);
		
		List<ChatWindowDTO> chatWindowDTOList = chatWindowDAO.getChatWindowDTOList("chat_window.selectByStartEnd", pager); // 가져온 글 목록
		
		postCount = chatWindowDAO.getChatWindowCount("chat_window.selectCount", pager); // 전체 글 갯수
		
		if (postCount > 0) { // 글이 있는 경우에
			pageCount = postCount / pageSize + (postCount % pageSize == 0 ? 0 : 1);

			int tmp = (pageNum % pagerSize) == 0 ? (pageNum / pagerSize) - 1 : (pageNum / pagerSize);
			startPager = (tmp * pagerSize + 1);
			endPager = startPager + pagerSize - 1;

			if (endPager > pageCount) {
				endPager = pageCount;
			}
		}
		
		// queryString = "page_num=" + pageNum;
		
		pager.setPostCount(postCount);
		pager.setPageCount(pageCount);
		pager.setStartPager(startPager);
		pager.setEndPager(endPager);
		pager.setPagerSize(pagerSize);
		pager.setPageSize(pageSize);
		// pager.setQueryString(queryString);
		pager.setPageNum(pageNum);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("chatWindowDTOList", chatWindowDTOList);
		map.put("pager", pager);
		
		return map;
	}
	
}
