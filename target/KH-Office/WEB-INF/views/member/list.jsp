<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="mainWrapper">
	<nav>
		<c:if test="${session_adminID != null }">
			<form action="member/form" id="member_register">
				<input type="submit" name="member_register_btn" id="member_register_btn" value="사원 등록">
			</form>
		</c:if> 
		<a href="${pageContext.request.contextPath}/member" class="navBtn">사내 주소록</a>
		<c:if test="${session_adminID == null }">
			<a href="${pageContext.request.contextPath}/address" class="navBtn">개인 주소록</a>
		</c:if>
	</nav>
	
	<section>
	<c:if test="${session_adminID == null }"> 
		<c:if test="${isBookmark == 0}">
		
			<form action="member?page_num=${pageNum}&is_bookmark=${isBookmark}" id="bookmark_form">
				 <input type="hidden" name="is_bookmark" id="is_bookmark" value="1"> 
				<input type="submit" id="bookmark_sort" value="즐겨찾기 보기">
			</form>
		</c:if>
		<c:if test="${isBookmark == 1}">
			<form action="member?page_num=${pageNum}&keyword=${keyword}&is_bookmark=${isBookmark}" id="list_form">
				<input type="hidden" name="is_bookmark" id="is_bookmark" value="0"> 
				<input type="submit" id="list_sort" value="전체보기">
			</form>
		</c:if>
	</c:if>
	<form action="member?page_num=${pageNum}&keyword=${keyword}&is_bookmark=${isBookmark}" class="search_area">
		<input type="text" name="keyword" id="keyword" placeholder="이름을 입력하세요">
		<input type="submit" value="검색" class="search_btn">
	</form>
	
	<ul id="ulTable">
		<li>
			<ul id="column_title">
				<li>&nbsp;</li>
				<li>사진</li>
				<li>이름</li>
				<li>부서</li>
				<li>직위</li>
				<li>회사이메일</li>
				<li>내선번호</li>
			</ul>
		</li>
		<li>
			<c:forEach var="member" items="${list}">
				<ul class="memberContents" data-member-no="${member.member_no }" data-page-num="${pageNum}" data-keyword="${keyword}" data-is-bookmark="${isBookmark}">
					<c:if test="${session_adminID == null && session_memberNo != 0}"> 
						<c:if test="${member.is_bookmark == 0}">
							<li> <img src="${pageContext.request.contextPath }/resources/icon/non_bookmark.png" class="bookmark_btn"/></li>
						</c:if>
						<c:if test="${member.is_bookmark == 1}">
							<li> <img src="${pageContext.request.contextPath }/resources/icon/bookmark.png" class="bookmark_btn on"/></li>
						</c:if>
					</c:if>
					<c:if test="${session_adminID!=null}">
						<li>&nbsp;</li>
					</c:if>
					<li class="clickArea"> 
						<img src="${member.thumb_path}" class="thumbImg"/>
					</li>
					<li class="clickArea"> ${member.mname}</li>
					<li> ${member.dname}</li>
					<li> ${member.position}</li>
					<li> ${member.email}</li>
					<li> ${member.tel}</li>
					
				</ul>
			</c:forEach> 
		</li>
	</ul>

	<div id="pagingWrapper" data-page-num="${pageNum}">
		<c:if test="${count > 0 }">
			<c:if test="${startPage > 5}">
				<a href = "${pageContext.request.contextPath}/member?page_num=${startPage - 5}&keyword=${keyword }&is_bookmark=${is_bookmark}" id="prev" class="paging"><span>&lt;</span></a>
			</c:if>
				
			<c:forEach var="i" begin="${startPage}" end= "${endPage}">
				<a href = "${pageContext.request.contextPath}/member?page_num=${i}&keyword=${keyword }&is_bookmark=${is_bookmark}" class="paging"><span class="pagingNum">${i}</span></a>
			</c:forEach>
				
			<c:if test="${endPage < pageCount}">
				<a href = "${pageContext.request.contextPath}/member?page_num=${startPage + 5}&keyword=${keyword }&is_bookmark=${is_bookmark}" id="next" class="paging"><span>&gt;</span></a>
			</c:if>
		</c:if>
	</div>
	</section>
</div>
