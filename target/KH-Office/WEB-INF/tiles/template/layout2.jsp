<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- <tiles:getAsString name = "어트리뷰트이름" ignore = "true/false"/>
<tiles:insertAttribute name = "어트리뷰트이름" flush = "플러쉬여부" ignore = "에러무시여부"/> --%>

<tiles:importAttribute name="favicon"/>
<tiles:importAttribute name="commonCss"/>
<tiles:importAttribute name="prefixfreeCss"/>
<tiles:importAttribute name="headerCss"/>
<tiles:importAttribute name="css"/>

<tiles:importAttribute name="jQueryJs"/>
<tiles:importAttribute name="commonJs"/>
<tiles:importAttribute name="utilsJs"/>
<tiles:importAttribute name="js"/>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>
	<!-- favicon 설정 -->
	<link rel="shortcut icon" href="resources/icon/<c:url value="${favicon }" />" >
	<!-- 사이트 공통 css -->
	<link rel = "stylesheet" type = "text/css" href ="commons/css/<c:url value="${commonCss }" />.css">
	<!-- 부트스트랩 css -->
	<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->
	<!-- 부트스트랩 테마 css -->
	<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"> -->
	<!-- prefix free 플러그인 -->
	<link rel="stylesheet" type = "text/css" href ="commons/css/<c:url value="${prefixfreeCss }" />.css">
	<!-- 헤더 영역 디자인 -->
	<link rel="stylesheet" type = "text/css" href="commons/css/<c:url value="${headerCss }" />.css">
	<!-- 이 페이지 전용 css -->
	<link rel="stylesheet" type = "text/css" href="commons/css/<c:url value="${css }"/>.css">
 	
	<!-- 제이쿼리 로드 -->
	<script src="commons/js/<c:url value="${jQueryJs }" />.js"></script>
	<!-- 부트스트랩 자바스크립트 -->
	<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script> -->
	<!-- 사이트 공통 js -->
	<script src="commons/js/<c:url value="${commonJs }" />.js"></script>
	<!-- 사이트 공통 유틸리티 js -->
	<script src="commons/js/<c:url value="${utilsJs }" />.js"></script>
	<!-- 이 페이지 전용 js -->
	<script src="commons/js/<c:url value="${js}"/>.js"></script>
</head>
<body>
	<!--[if lt IE 10]>
        <p class="alert alert-warning">
            Warning: You are using an unsupported version of Internet Explorer. We recommend using Internet Explorer
            10+. If you are a Windows XP user you'll need to download an alternative browsers such as FireFox, Chrome,
            Opera, or Safari. 
        </p>
    <![endif]-->
    
	<!-- header -->
	<tiles:insertAttribute name="header" />
	<!-- end header  -->
	
	<!-- body -->
	<div id="wrap">
		<tiles:insertAttribute name="body" />
	</div>
	<!-- end body -->
</body>
</html>