<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<input type="hidden" id="session_id" value="${session_memberNo }" />

<h2>채팅방 생성</h2>
<form id="create_chat_form">
	<div class="chat_window_name">
		<label for="name">채팅방 이름</label>
		<input type="text" name="name" id="name" placeholder="재밌는 업무챗힝~" maxlength="30" required />
	</div>
	<div class="chat_member_select">
		<p>채팅상대 선택</p>
		<tiles:insertDefinition name="department_selectbox"></tiles:insertDefinition>
	</div>
	
	<div class="added_member_area">
		<span>사원을 클릭하면 이곳에 추가됩니다.</span>
	</div>
	
	<div class="added_member_area_message"></div>
	
	<div class="added_input_area"></div>
	
	<div class="btn_area">
		<button type="button" id="create_chat_window_btn">생성</button>
		<button type="button" id="create_chat_cancel_btn">취소</button>
	</div>
</form>