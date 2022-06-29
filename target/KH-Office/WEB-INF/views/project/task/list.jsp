<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h2>파일저장소</h2>

<!-- 검색폼 -->
<form method="get" action="${pageContext.request.contextPath }/task" class="search_form">
	<select name="type">
		
		<option value="project_name" <c:if test="${pager.type eq 'roject_name' }">selected</c:if>>프로젝트명</option>
		<option value="task_name" <c:if test="${pager.type eq 'task_name' }">selected</c:if>>태스크명</option>
		
	</select>
	
	<input type="text" name="keyword" id="keyword" value="${pager.keyword }"/>
	<input type="submit" value="검색" />
</form>
	
<c:if test="${projectTaskFileDTOList != null && projectTaskFileDTOList.size() != 0}">
	<!-- 목록영역 -->
	<div class="task_list_area">
		<ul>
			<li>
				<span class="project_name_label">프로젝트명</span>
				<span class="project_status_label">프로젝트상태</span>
				<span class="task_name_label">태스크명</span>
				<span class="task_status_label">태스크상태</span>
				<span class="deadline_label">태스크마감일</span>
				<span class="file_path_label">첨부파일경로</span>
			</li>
			<c:forEach var="task" items="${projectTaskFileDTOList }">
				<li>
					<span class="project_name" data-project-no="${task.project_no }">${task.project_name }</span>
						<c:if test="${task.project_status == '0' }">
							<span class="project_status status_green">
								진행중
							</span>
						</c:if>
						<c:if test="${task.project_status == '1'}">
							<span class="project_status status_silver">
								완료
							</span>
						</c:if>
						<c:if test="${task.project_status == '2'}">
							<span class="project_status status_black">
								보류
							</span> 
						</c:if>
						<c:if test="${task.project_status == '3'}">
							<span class="project_status status_red">
								중단
							</span>
						</c:if>
					
					<span class="task_name" data-project-no="${task.project_no }" data-task-no="${task.task_no }">${task.task_name }</span>
					
						<c:if test="${task.task_status == '0'}">
							<span class="task_status status_black">
								대기
							</span>
						</c:if>
						<c:if test="${task.task_status == '1'}">
							<span class="task_status status_green">
								진행중
							</span> 
						</c:if>
						<c:if test="${task.task_status == '2'}">
							<span class="task_status status_silver">
								완료
							</span>
						</c:if>
						
					
					<span class="deadline">${task.deadline }</span>
					<span class="file_path"><a href="commons/download/task/${task.task_no }"><img src="resources/icon/file_download.png" alt="파일 다운로드" /></a></span>
				</li>
			</c:forEach>
		</ul>
	</div>
			
	<!-- 페이저 영역 -->
	<div class="pager_area">
	    <c:if test="${pager.postCount > 0}">
	        <c:if test="${pager.startPager > pager.pagerSize}">
	            <a href="${pageContext.request.contextPath}/task?${pager.queryString }&page_num=${pager.startPager - 1}"
	                class="pager_left pager_direction"><img src="${pageContext.request.contextPath}/resources/icon/pager_left.png"
	                    alt="이전 버튼" /></a>
	        </c:if>
	
	        <c:forEach var="i" begin="${pager.startPager}" end="${pager.endPager}">
	            <c:if test="${pager.page_num == i}">
	                <a href="${pageContext.request.contextPath}/task?${pagerqueryString }page_num=${i}" class="curr_page">${i}</a>
	            </c:if>
	            <c:if test="${pager.page_num != i}">
	                <a href="${pageContext.request.contextPath}/task?${pager.queryString }page_num=${i}">${i}</a>
	            </c:if>
	        </c:forEach>
	
	        <c:if test="${pager.endPager < pager.pageCount}">
	            <a href="${pageContext.request.contextPath}/task?${pager.queryString }page_num=${pager.endPager + 1}"
	                class="pager_right pager_direction"><img src="${pageContext.request.contextPath}/resources/icon/pager_right.png"
	                    alt="다음 버튼" /></a>
	        </c:if>
	    </c:if>
	</div>
	<!--// 페이저 영역 -->
</c:if>
		
<c:if test="${projectTaskFileDTOList == null || projectTaskFileDTOList.size() == 0}">
	<div class="post_not_exist">태스크가 존재하지 않습니다.</div>
</c:if>
		
	
