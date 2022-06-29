<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="contents">
	<img src="${pageContext.request.contextPath }/resources/icon/x.png" id="iconimg">
	<p>
	회원정보가 일치하지 않아 변경에 실패했습니다.
	<p>
	다시 시도해주세요.
</div>
<input type="button" onclick="javascript:history.go(-1)" id="backbtn" value="돌아가기">