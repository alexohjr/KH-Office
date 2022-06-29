package com.khacademy.khoffice.commons;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.khacademy.khoffice.chat_member.models.ChatMemberDTO;
import com.khacademy.khoffice.chat_member.services.ChatMemberService;
import com.khacademy.khoffice.chat_message.models.ChatMessageDTO;
import com.khacademy.khoffice.chat_message.services.ChatMessageService;
import com.khacademy.khoffice.member.models.MemberDTO;
import com.khacademy.khoffice.member.services.MemberService;

@ServerEndpoint("/eho")
public class EchoHandler extends TextWebSocketHandler {
	private MemberService memberService;
	private ChatMessageService chatMessageService;
	private ChatMemberService chatMemberService;
	
	@Autowired
	@Required
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Autowired
	@Required
	public void setChatMessageService(ChatMessageService chatMessageService) {
		this.chatMessageService = chatMessageService;
	}
	
	@Autowired
	@Required
	public void setChatMemberService(ChatMemberService chatMemberService) {
		this.chatMemberService = chatMemberService;
	}

	//	private List<WebSocketSession> webSocketSessionList = new ArrayList<WebSocketSession>();
	private static Map<String, Map<String, WebSocketSession>> cwinodwMap = new ConcurrentHashMap<String, Map<String, WebSocketSession>>();
	
	@Override
	@RequestMapping(value="/{cwindowNo}/{memberNo}")
	public void afterConnectionEstablished(
		WebSocketSession session
	) throws Exception {
		super.afterConnectionEstablished(session);
		
//		HttpHeaders headers = session.getHandshakeHeaders();
		
		/*for(String key : headers.keySet()) {
			Object sess = headers.get(key);
            System.out.println( String.format("키 : %s, 값 : %s", key, sess) );
           // sess.sendMessage(new TextMessage(session.getPrincipal().getName()));
		}*/

		// 토크나이저로 방번호, 접속자 사번 알아오기
		Map<String, String> map = getCwindowNoMemberNoMap(session.getUri().getPath());

		System.out.println("memberNo : " + map.get("memberNo"));
		System.out.println("cwindowNo : " + map.get("cwindowNo"));
		
		for(String str : cwinodwMap.keySet()) {
        	System.out.println("존재하는 방의 cwindowNo : " + str);
        }
		
		// 맵에 넣어두기
		if(cwinodwMap.containsKey(map.get("cwindowNo"))) {
			// 이미 방이 존재한다면
			// 해당 방에 나를 추가하고
			System.out.println(map.get("memberNo") + " 를 " + map.get("cwindowNo") + " 번 방에 추가했습니다.");
			Map<String, WebSocketSession> tmpMap = cwinodwMap.get(map.get("cwindowNo"));
			tmpMap.put(map.get("memberNo"), session);
			cwinodwMap.put(map.get("cwindowNo"), tmpMap);
		} else {
			// 존재하지 않는다면
			// 새로 만들면서 나를 추가
			Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<String, WebSocketSession>();
			System.out.println(map.get("memberNo") + " 를 추가하며 " + map.get("cwindowNo") + " 번 방을 새로 생성했습니다.");
			sessionMap.put(map.get("memberNo"), session);
			cwinodwMap .put(map.get("cwindowNo"), sessionMap);
		}
		
		// 직급, 이름 가져오기
		MemberDTO memberDTO = memberService.getPositionNameByMemberNo(Integer.parseInt(map.get("memberNo")));
		
		// 모두에게 입장메시지 보내기
		Map<String, WebSocketSession> targetMap = cwinodwMap.get(map.get("cwindowNo"));
		
        for(String key : targetMap.keySet()){
        	WebSocketSession sess = targetMap.get(key);
        	JSONObject jso = new JSONObject();
        	jso.put("memberNo", memberDTO.getMember_no());
        	jso.put("position", memberDTO.getPosition());
        	jso.put("name", memberDTO.getName());
        	jso.put("isEnterMessage", true);
        	sess.sendMessage(new TextMessage(jso.toString()));
        }
        
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		
		System.out.println("요청 uri : " + session.getUri().getPath());
		System.out.println("아이디 : " + session.getId()); // 식별자
		System.out.println("본문 : " + message.getPayload()); // 본문
		
		// 토크나이저로 방번호, 접속자 사번 알아오기
		Map<String, String> map = getCwindowNoMemberNoMap(session.getUri().getPath());
		
		// 메시지 삽입하기
		ChatMessageDTO chatMessageDTO = new ChatMessageDTO();
		chatMessageDTO.setMessage(message.getPayload());
		chatMessageDTO.setCwindowNo(Integer.parseInt(map.get("cwindowNo")));
		chatMessageDTO.setMemberNo(Integer.parseInt(map.get("memberNo")));
		
		chatMessageDTO = chatMessageService.addChatMessageDTO(chatMessageDTO); // 메시지가 담긴 객체
		
		// 모두에게 메시지 보내기
		Map<String, WebSocketSession> targetMap = cwinodwMap.get(map.get("cwindowNo"));

		for (String key : targetMap.keySet()) {
			WebSocketSession sess = targetMap.get(key);
			JSONObject jso = new JSONObject(chatMessageDTO);
			sess.sendMessage(new TextMessage(jso.toString()));
		}

	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		
		// 토크나이저로 방번호, 접속자 사번 알아오기
		Map<String, String> map = getCwindowNoMemberNoMap(session.getUri().getPath());
		
		System.out.println(map.get("memberNo") + " 번 사용자 "+  map.get("cwindowNo") + " 방에서 " + " 연결 끊김");
		System.out.println("상태값 : " + status);
		
		// 방에서 나간 사람이 완전히 나갔다면(DB에서 삭제했다면)
		// 방에서 나갔다는 메시지 출력
		ChatMemberDTO chatMemberDTO = new ChatMemberDTO();
		chatMemberDTO.setCwindowNo(Integer.parseInt(map.get("cwindowNo")));
		chatMemberDTO.setMemberNo(Integer.parseInt(map.get("memberNo")));
		MemberDTO memberDTO = chatMemberService.getMemberByCwindowNoMemberNo(chatMemberDTO);
		
		System.out.println("memberDTO : " + memberDTO);
		if(memberDTO == null) {
			// 채팅방에서 나간 사람이라면
			// 모두에게 퇴장메시지 보내기
			
			// 직급, 이름 가져오기
			memberDTO = memberService.getPositionNameByMemberNo(Integer.parseInt(map.get("memberNo")));
			System.out.println("memberDTO.getName : " + memberDTO.getName());
			
			Map<String, WebSocketSession> targetMap = cwinodwMap.get(map.get("cwindowNo"));
			System.out.println("targetMap.size() : " + targetMap.size());
			
			for(String key : targetMap.keySet()){
				System.out.println("받는사람 사번 : " + key);
	        	WebSocketSession sess = targetMap.get(key);
	        	JSONObject jso = new JSONObject();
	        	jso.put("memberNo", memberDTO.getMember_no());
	        	jso.put("position", memberDTO.getPosition());
	        	jso.put("name", memberDTO.getName());
	        	jso.put("isExitMessage", true);
	        	sess.sendMessage(new TextMessage(jso.toString()));
	        }
			
			/*
			for(String str : cwinodwMap.keySet()) {
				System.out.println("존재하는 방의 cwindowNo : " + str);
				if(str.equals(map.get("cwindowNo"))) {
					for(String str2 : cwinodwMap.get(str).keySet()) {
						System.out.println("딸린 방에 있는 사원 : " + str2);
						
						WebSocketSession sess = cwinodwMap.get(str).get(str2);
						JSONObject jso = new JSONObject();
			        	jso.put("memberNo", memberDTO.getMember_no());
			        	jso.put("position", memberDTO.getPosition());
			        	jso.put("name", memberDTO.getName());
			        	jso.put("isExitMessage", true);
			        	sess.sendMessage(new TextMessage(jso.toString()));
						
					}
				}
	        }
			*/
		}
		
		/*
		// 만약 방에 사람이 없다면 방 지워버리기
		int remainMemberCount = cwinodwMap.get(map.get("cwindowNo")).size();
		System.out.println("remainMemberCount : " + remainMemberCount);
		
		if(remainMemberCount == 1) {
			cwinodwMap.remove(map.get("cwindowNo"));
		} else if(remainMemberCount >= 2) {
			// Map에서 해당 방번호의 해당 사번을 삭제하기
			cwinodwMap.get(map.get("cwindowNo")).remove(map.get("memberNo"));
		}
		
		for(String str : cwinodwMap.keySet()) {
        	System.out.println("존재하는 방의 cwindowNo : " + str);
        }
        */
		
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		log(session.getId() + " 익셉션 발생: " + exception.getMessage());
	}

	private void log(String logmsg) {
		System.out.println(new Date() + " : " + logmsg);
	}
	
	private Map<String, String> getCwindowNoMemberNoMap(String uri){
		// /KH-Office/echo/38/10
		
		Map<String, String> map = new HashMap<String, String>();
		
		StringTokenizer tokenizer = new StringTokenizer(uri, "/");
		
		while (tokenizer.hasMoreTokens()) {
			String tmp = tokenizer.nextToken();
			
			if(tokenizer.countTokens() == 1) {
				map.put("cwindowNo", tmp);
			}
			if(tokenizer.countTokens() == 0) {
				map.put("memberNo", tmp);
			}
		}

		return map;
	}
	
}
