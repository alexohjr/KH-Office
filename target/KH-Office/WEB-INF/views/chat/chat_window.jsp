<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<input type="hidden" id="cwindow_no" value="${chatWindowHeadDTO.cwindowNo }" />
<input type="hidden" id="session_id" value="${session_memberNo }" />
<div class="cwindow_left_area">
	<div class="header_area">
		<span class="thumb_path">
			<c:if test="${chatWindowHeadDTO.cwindowMemberCount <= 2 }">
				<!-- 2인 이하의 채팅방인 경우 -->
				<img src="${pageContext.request.contextPath }/${chatWindowHeadDTO.thumbPath }" alt="${chatWindowHeadDTO.cwindowName } 채팅방 썸네일" />
			</c:if>
			<c:if test="${chatWindowHeadDTO.cwindowMemberCount > 2 }">
				<!-- 3인 이상의 채팅방인 경우 -->
				<span class="number_profile">${chatWindowHeadDTO.cwindowMemberCount }</span>
			</c:if>
		</span>
		
		 <span>${chatWindowHeadDTO.cwindowName }</span>
	</div>
	<div class="message_area">
		<c:if test="${chatMessageDTOList != null }">
			<c:forEach var="message" items="${chatMessageDTOList }">
				<p 
					data-cmessage-no="${message.cmessageNo }" 
					data-member-no="${message.memberNo }"
					<c:if test="${session_memberNo == message.memberNo}">class="my_message"</c:if>
					<c:if test="${session_memberNo != message.memberNo}">class="other_message"</c:if>
				>
				
				<%-- 
				<c:if test="${session_memberNo != message.memberNo}">
					<span class="thumb_path"><img src="${pageContext.request.contextPath }/${message.thumbPath }" alt="${message.position } ${message.name } 님의 프로필 이미지" /></span>
					<span class="position_name">${message.position } ${message.name }</span>
				</c:if>
					<span class="message">${message.message }</span>
					<span class="time">${message.time }</span>
				 --%>
				
				<c:if test="${session_memberNo != message.memberNo}">
					<span class="thumb_path"><img src="${pageContext.request.contextPath }/${message.thumbPath }" alt="${message.position } ${message.name } 님의 프로필 이미지" /></span>
					<span class="position_name">${message.position } ${message.name }</span>
				</c:if>
				<c:if test="${session_memberNo == message.memberNo}">
					<span class="time">${message.time }</span>
				</c:if>
				
					<span class="message">${message.message }</span>
					
				<c:if test="${session_memberNo != message.memberNo}">
					<span class="time">${message.time }</span>
				</c:if>
				
				</p>
				
			</c:forEach>
		</c:if>
	</div>
	<div class="send_area">
		<textarea name="message" id="message" placeholder="메시지를 입력해주세요."></textarea>
		<button type="button">전송</button>
	</div>
</div>
<div class="cwindow_right_area">
	<div class="exit_btn_area">
		<button type="button">채팅방 퇴장하기</button>
	</div>
	<div class="member_info_area">
	
		<p><span>참여인원 : </span><span>${chatWindowHeadDTO.cwindowMemberCount }명</span></p>
		
		<ul>
			<c:if test="${chatWindowMemberJoinDTOList != null }">
				<c:forEach var="member" items="${chatWindowMemberJoinDTOList }">
					<li data-member-no="${member.memberNo }">
						<span class="thumb_path">
							<img src="${pageContext.request.contextPath }/${member.thumbPath }" alt="${member.departmentName } ${member.position } ${member.name } 님의 프로필 이미지" />
						</span>
						<span>${member.departmentName }</span>
						<span>${member.position }</span>
						<span>${member.name }</span>
					</li>		
				</c:forEach>
			</c:if>
		</ul>
		
	</div>
	
	<form id="add_chat_member">
		<div class="added_input_area"></div>
	</form>
	
	<div class="member_add_area">
		<tiles:insertDefinition name="department_selectbox"></tiles:insertDefinition>
		<button type="button">선택한 상대  초대</button>
	</div>
</div>
