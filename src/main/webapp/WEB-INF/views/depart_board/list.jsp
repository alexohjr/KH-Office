<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


			



<div id="mainWrapper">

	<div id="contentTable">
		<span id="contentTitle">부서게시판</span><span id="contentDepartLine">|</span>	 <span id="contentDepartName"> ${depart_name}</span>			
	</div>

	

	<ul>
		<form id="boardForm" name="boardForm" action="${pageContext.request.contextPath}/depart_board">
			<!-- 게시판 제목 -->
			<c:if test="${count == 0}">
				<li>
					<ul>
						<li>게시판에 저장된 글이 없습니다.</li>
					</ul>
				</li>
			</c:if>

			<!-- 게시판 목록  -->

			<c:if test="${count > 0}">
				<ul id="ulTable">
					<li>
						<ul>
							<li>No</li>
							<li>제 목</li>
							<li>작성자</li>
							<li>작성일</li>
							<li>조회수</li>
						</ul>
					</li>
					<!-- 게시물이 출력될 영역 -->
					<li><c:forEach var="board" items="${list}">
							<ul>
								<li><c:out value="${number }"/>
          							 <c:set var="number" value="${number-1 }"/></li>
								<li class="left"><a href="depart_board/${board.dboard_no}?pageNum=${pageNum}&search_type=${search_type}&keyword=${keyword}" class="linkclick">${board.title}</a></li>
								<li>${board.name}${board.position}</li>
								<li><fmt:formatDate value="${board.reg_date}" pattern="yyyy-MM-dd" /></li>
								<li>${board.view_count}</li>
							</ul>
						</c:forEach></li>
				</ul>
			</c:if>



			<!-- 게시판 페이징 영역 -->
			<li>
				<div id="pageCountBtn">
					<c:if test="${count > 0 }">
						<c:set var="pageCount"
							value="${count / pageSize + (count % pageSize == 0 ? 0 : 1 )}" />
						<c:set var="pageBlock" value="${5 }" />
						<!-- 페이징 넘버  -->
						<fmt:parseNumber var="result" value="${currentPage / 5}"
							integerOnly="true" />
						<c:set var="startPage" value="${result * 5 + 1}" />
						<c:set var="endPage" value="${startPage + pageBlock-1}" />
						<!-- 보여질 페이징 넘버 -->
						<c:if test="${endPage > pageCount}">
							<c:set var="endPage" value="${pageCount}" />
						</c:if>

						<c:if test="${empty keyword }">
							<c:if test="${startPage > 5}">
								<a href="${pageContext.request.contextPath}/depart_board?pageNum=${startPage - 5}"
									class="pg_prev">[이전]</a>
							</c:if>

							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<a
									href="${pageContext.request.contextPath}/depart_board?pageNum=${i}"
									class="pg_page">${i}</a>
							</c:forEach>

							<c:if test="${endPage < pageCount}">
								<a
									href="${pageContext.request.contextPath}/depart_board?pageNum=${startPage + 5}"
									class="pg_next">[다음]</a>
							</c:if>
						</c:if>

						<c:if test="${!empty keyword }">
							<c:if test="${startPage > 5}">
								<a
									href="${pageContext.request.contextPath}/depart_board?pageNum=${startPage - 5}?pageNum=${pageNum}&search_type=${search_type}&keyword=${keyword}"
									class="pg_prev">[이전]</a>
							</c:if>

							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<a
									href="${pageContext.request.contextPath}/depart_board?pageNum=${i}?pageNum=${pageNum}&search_type=${search_type}&keyword=${keyword}"
									class="pg_page">${i}</a>
							</c:forEach>

							<c:if test="${endPage < pageCount}">
								<a
									href="${pageContext.request.contextPath}/depart_board?pageNum=${startPage + 5}?pageNum=${pageNum}&search_type=${search_type}&keyword=${keyword}"
									class="pg_next">[다음]</a>
							</c:if>
						</c:if>
					</c:if>
				</div>
			</li>
			
					<!-- 글쓰기 -->
		
			<div id='addButton'>
				<input type="button" class="inputBtnImg" value="글쓰기"
					OnClick="javascript:location.href='depart_board/form'">
			</div>


	<!-- 		<div class="board-box-line"></div> -->



			<!-- 검색 폼 영역 -->
			<div id='liSearchOption'>
				<select name="search_type" id="searchtypeBoxBtn">
					<option value="0">제목</option>
					<option value="1">내용</option>
					<option value="2">작성자</option>
				</select>
					<input type="text" name="keyword" class="keywordsubmit" />
					<input type="submit" value="검색" class="buttonSubmut" />
			</div>

		</form>



	</ul>
</div>
