<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<label id="titleLabel">사원상세정보</label>
<div id="main_wrapper">
<form action="${pageContext.request.contextPath}/member/${memberNo}?type=delete&page_num=${pageNum}&keyword=${keyword}&is_bookmark=${isBookmark }" method="post" id="detail_form" enctype="multipart/form-data">
	<c:forEach var="list" items="${list }">
		<div id="infoTitle">
			<span>
				<img src="${pageContext.request.contextPath}/${list.thumb_path}" class="profile_img">
			</span>	
			<div class="name">
				<span>${list.department_name}</span>
				<span>${list.name}</span>
				<span>${list.position}</span>
			</div>
		</div>
		<ul id="detailInfo">  
			<li>
				<span class="title">사내메일</span>
				<span class="contents">${list.email}</span> 
			</li>
			<li>
				<span class="title">내선번호 </span>
				<span class="contents">${list.tel}</span>
			</li>
			<li>
				<span class="title">사번</span>
				<span class="contents">${list.member_no}</span>
			</li>
			<li>
				<span class="title">휴대폰 번호</span>
				<span class="contents">${list.phone}</span>
			</li>
			<li>
				<span class="title">자택주소</span> 
				<span class="contents">${list.main_address} ${list.detail_address} (${list.zipcode})</span>
			</li>
			<li>
				<span class="title">총 연차</span>
				 <span class="contents">${list.total_leave}</span>
			</li>
			<li>
				<span class="title">남은 연차</span>
				 <span class="contents">${list.remainder_leave}</span>
			</li>
			<li>
				<span class="title">입사일</span> 
				<span class="contents">${list.hire_date}</span>
			</li>
			<li>
				<span class="title">채용 구분</span> 
				<span class="contents">${list.hire_type}</span>
			</li>
			<li>
				<span class="title">직원 구분</span> 
				<span class="contents">${list.emp_type}</span>
			</li>
			<li>
				<span class="title">생년월일</span> 
				<span class="contents">${list.birthday}</span>
			</li>
			<li>
				<span class="title">성별</span>
				 <span class="contents">${list.gender}</span>
			</li>
			<li>
				<c:if test="${list.marriage eq'0'}">
					<span class="title">결혼 여부</span>
					<span class="contents"> 미혼</span>
				</c:if>
				<c:if test="${list.marriage eq '1'}">
					<span class="title">결혼 여부</span> 
					<span class="contents">기혼</span>
				</c:if>
			</li>
			<li>
				<c:if test="${list.disabled eq '0'}">
					<span class="title">장애 여부</span>
					<span class="contents">장애</span>
				</c:if>
				<c:if test="${list.disabled eq '1'}">
					<span class="title">장애 여부 </span>
					<span class="contents">비장애</span>
				</c:if>
			</li>
		</ul>
		<div id="btn_area" data-member-no="${list.member_no }" data-page-num="${pageNum}" data-keyword="${keyword}" data-is-bookmark="${isBookmark}">
			<c:if test="${session_adminID != null }"> <!-- 어드민계정 로그인 한 경우 -->
				<input type="button" value="수정" id="edit_form_btn" class="admin_btn">
				<input type="submit" value="삭제" id="delete_form_btn" class="admin_btn" data-name="${list.name}">
				<span data-page-num="${pageNum}" data-keyword="${keyword}" data-is-bookmark="${isBookmark}"><input type="button" value="목록으로" id="list_btn" class="admin_btn"></span>
			</c:if>
		</div>
		<c:if test="${session_memberNo != null && session_memberNo != 0}">  <!-- 사원계정 로그인 한 경우 -->
			<input type="button" value="비밀번호 수정" id="password_edit_form_btn" data-member-no="${list.member_no }">
		</c:if>
			
	</c:forEach>
</form>
</div>
