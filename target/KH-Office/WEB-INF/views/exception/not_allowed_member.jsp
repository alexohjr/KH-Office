<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div id="notAllowedAdminJsp">
<div id="warningDiv">
<img src="${pageContext.request.contextPath}/resources/icon/warning.png" id="errorIcon"></div>
<div id="warningTextDiv">관리자 외 접근이 불가능한 페이지입니다.</div>
</div>
<div id="warningPageBtnDiv">
<img src="${pageContext.request.contextPath}/resources/icon/go_back_icon.png" id="backIcon">
<img src="${pageContext.request.contextPath}/resources/icon/home_icon.png" id="homeIcon">
</div>

 