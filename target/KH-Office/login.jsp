<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<title>KH 그룹웨어 로그인</title>  
<!-- favicon 설정 -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/icon/favicon.ico" >
<!-- prefix free 플러그인 -->
<link rel="stylesheet" type = "text/css" href ="${pageContext.request.contextPath}/commons/css/prefixfree.css">
<!-- 사이트 공통 css -->
<link rel = "stylesheet" type = "text/css" href ="${pageContext.request.contextPath}/commons/css/common.css">

<link rel="stylesheet" href="${pageContext.request.contextPath }/commons/css/login.css" />
<!-- 제이쿼리 로드 -->
<script src="${pageContext.request.contextPath }/commons/js/jquery-3.3.1.min.js"></script>
<!-- transit 라이브러리 -->
<script src="${pageContext.request.contextPath }/commons/js/transit-0.9.12.min.js"></script>
<!-- swal 라이브러리 -->
<script src="${pageContext.request.contextPath }/commons/js/sweetalert.min.js"></script>
<!-- nicescroll 라이브러리 -->
<script src="${pageContext.request.contextPath }/commons/js/jquery.nicescroll.min.js"></script>
<!-- 사이트 공통 유틸리티 js -->
<script src="${pageContext.request.contextPath}/commons/js/utils.js"></script> 
<!-- 사이트 공통 js -->
<script src="${pageContext.request.contextPath}/commons/js/common.js"></script>
<script src="${pageContext.request.contextPath}/commons/js/login.js"></script>
<body>    
	<div id="wrap"> 
	  
		<div id="login_wrap">
			<div class="title_area">  
				<h2>LOGIN</h2>
			</div>
			
			<div class="tab_area">
				<ul>
					<li class="member_btn on">임직원</li>
					<li class="admin_btn">관리자</li>
				</ul>
			</div>
			
			<div class="input_area">
			
				<div class="member_input">
					<form action="member/login" method="post" id="member_form">
						<input type="email" name="email" id="email" placeholder="이메일" />
						<input type="password" name="password" id="member_pw" placeholder="비밀번호" />
						<input type="submit" value="LOGIN">
					</form>
				</div>
				
				<div class="admin_input">
					<form action="admin/login" method="post" id="admin_form">
						<input type="text" name="admin_id" id="id" placeholder="아이디" />
						<input type="password" name="password" id="admin_pw" placeholder="비밀번호" />
						<input type="submit" value="LOGIN">
					</form>
				</div>
				
			</div>
			
			<div class="info_area">
				* 직원 아이디 발급을 위해서는 관리자에게 문의하세요.
			</div>
		</div>
		
	</div>
</body>
</html>







