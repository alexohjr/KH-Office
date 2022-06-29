<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<span id="titleLabel">비밀번호 변경</span>
<form method="POST" action="${pageContext.request.contextPath}/member/${memberNo}?type=change_pw" id="password_area" enctype="multipart/form-data">
	<div id="passwordDiv">
		<div>
			<label class="passwordLabel">기존 비밀번호 : </label> <input type="password" name="beforePassword" class="password" id="password" >
		</div>
		<div>
			<label class="passwordLabel">새 비밀번호 : </label> <input type="password" name="newPassword" class="password" id="newPassword">
		</div>
		<div>
			<label class="passwordLabel">새 비밀번호 확인 : </label> <input type="password" name="newPassword2" class="password" id="newPassword2"  oninput="checkPassword()">
			<span id="check_message"></span>
		</div>
	</div>
	<div id="buttonDiv">
		<div>
		<input type="submit" value="변경" id="change_btn">
		<input type="button" value="취소" id="cancel_btn"  onclick="javascript:history.go(-1)" >
		</div>
	</div>
</form>