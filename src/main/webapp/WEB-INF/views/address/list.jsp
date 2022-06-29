<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="mainWrapper">
	<nav>
		<a href="${pageContext.request.contextPath}/member" class="navBtn">사내 주소록</a>
		<a href="${pageContext.request.contextPath}/address" class="navBtn">개인 주소록</a>
	</nav>
	
	<section>
		<div id="add_area">
			<form action="address" id="add_form" method="POST">
				<ul>
					<li><label>이름</label><input type="text" name="name" id="name"></li>
					<li><label>휴대폰 번호</label><input type="text" name="phone" id="phone"></li>
					<li><label>이메일</label><input type="email" name="email" id="email"></li>
					<li><label>회사</label><input type="text" name="company" id="company"></li>
					<li><label>부서/직위</label><input type="text" name="dept_position" id="dept_position"></li>
				</ul>
				<input type="submit" id="add_btn" value="연락처 추가">
			</form>
		</div>
		<c:if test="${session_adminID == null}"> 
			<c:if test="${isBookmark == 0}">
				<form action="address?page_num=${pageNum}&is_bookmark=${isBookmark}" id="bookmark_form">
					 <input type="hidden" name="is_bookmark" id="is_bookmark" value="1"> 
					<input type="submit" id="bookmark_sort" value="즐겨찾기 보기">
				</form>
			</c:if>
			<c:if test="${isBookmark == 1}">
				<form action="address?page_num=${pageNum}&keyword=${keyword}&is_bookmark=${isBookmark}" id="list_form">
					<input type="hidden" name="is_bookmark" id="is_bookmark" value="0"> 
					<input type="submit" id="list_sort" value="전체보기">
				</form>
			</c:if>
		</c:if>
		<form action="address?page_num=${pageNum}&keyword=${keyword}&is_bookmark=${isBookmark}" class="search_area">
			<input type="text" name="keyword" id="keyword" placeholder="이름을 입력하세요">
			<input type="submit" value="검색" class="search_btn">
		</form>
		
		<ul id="ulTable">
			<li>
				<ul id="column_title">
					<li class="bookmark_label">&nbsp;</li>
					<li class="name_label">이름</li>
					<li class="company_label">회사</li>
					<li class="position_label">부서/직위</li>
					<li class="email_label">이메일</li>
					<li class="phone_label">휴대폰번호</li>
					<li class="btn_label">&nbsp;</li>
				</ul>
			</li>
			<c:forEach var="address" items="${list}">
			<li>
			<form action="address/${address.address_no }?type=delete&page_num=${pageNum}&keyword=${keyword}&is_bookmark=${isBookmark}" method="post" class="listform">
				<ul class="addressContents" data-address-no="${address.address_no }" data-page-num="${pageNum}" data-keyword="${keyword}" data-is-bookmark="${isBookmark}">
					<c:if test="${session_adminID == null && session_memberNo != 0}"> 
						<c:if test="${address.is_bookmark == 0}">
							<li> <img src="${pageContext.request.contextPath }/resources/icon/non_bookmark.png" class="bookmark_btn"/></li>
						</c:if>
						<c:if test="${address.is_bookmark == 1}">
							<li> <img src="${pageContext.request.contextPath }/resources/icon/bookmark.png" class="bookmark_btn on"/></li>
						</c:if>
					</c:if>
					<li> ${address.name}</li>
					<li> ${address.company}</li>
					<li> ${address.dept_position}</li>
					<li> ${address.email}</li>
					<li> ${address.phone}</li>
					<li>
						<input type="button" class="edit_btn" value="수정">
						<input type="submit" class="delete_btn"value="삭제">
					</li>
				</ul>
			</form>
			<form action="address/${address.address_no }?type=edit&page_num=${pageNum}&keyword=${keyword}&is_bookmark=${isBookmark}" method="post" class="inputform">
				<ul class="hiddenInput" data-address-no="${address.address_no }" data-page-num="${pageNum}" data-keyword="${keyword}" data-is-bookmark="${isBookmark}">
					<c:if test="${session_adminID == null && session_memberNo != 0}"> 
						<c:if test="${address.is_bookmark == 0}">
							<li> <img src="${pageContext.request.contextPath }/resources/icon/non_bookmark.png" class="bookmark_btn"/></li>
						</c:if>
						<c:if test="${address.is_bookmark == 1}">
							<li> <img src="${pageContext.request.contextPath }/resources/icon/bookmark.png" class="bookmark_btn on"/></li>
						</c:if>
					</c:if>
					<li><input type="text" name="edit_name" id="edit_name" value="${address.name}"> </li>
					<li><input type="text" name="edit_company" id="edit_company" value="${address.company}"></li>
					<li> <input type="text" name="edit_dept_position" id="edit_dept_position" value="${address.dept_position}"></li>
					<li><input type="email" name="edit_email" id="edit_email" value="${address.email}"></li>
					<li><input type="text" name="edit_phone" id="edit_phone" value="${address.phone}"></li>
					<li>
						<input type="submit" class="save_btn" value="저장">
						<input type="button" class="cancel_btn"value="취소">
					</li>
				</ul>
			</form>
			</li>
			</c:forEach>
		</ul>
	
		<div id="pagingWrapper">
			<c:if test="${count > 0 }">
				<c:if test="${startPage > 5}">
					<a href = "${pageContext.request.contextPath}/address?page_num=${startPage - 5}&keyword=${keyword }&is_bookmark=${is_bookmark}" class="paging">&lt;</a>
				</c:if>
					
				<c:forEach var="i" begin="${startPage}" end= "${endPage}">
					<a href = "${pageContext.request.contextPath}/address?page_num=${i}&keyword=${keyword }&is_bookmark=${is_bookmark}" class="paging">${i}</a>
				</c:forEach>
					
				<c:if test="${endPage < pageCount}">
					<a href = "${pageContext.request.contextPath}/address?page_num=${startPage + 5}&keyword=${keyword }&is_bookmark=${is_bookmark}" class="paging">&gt;</a>
				</c:if>
			</c:if>
		</div>
	</section>
</div>
