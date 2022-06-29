<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<tiles:insertDefinition name="check_work_widget"></tiles:insertDefinition>

<div class="dashboard_address">
	<div class="div_title">
		<h3>개인주소록</h3>
	</div>
	<div class="plusBnt">
		<a href="${pageContext.request.contextPath}/address" class="navBtn">더보기</a>
	</div>
	<div class="dashboard_ul">
		 <ul id="ulTable_address">
	     	<li>
	        	<ul>
	            	<li>이름</li>
	                <li>회사</li>
	                <li>작부서/직급</li>
	                <li>휴대폰</li>
	            </ul>
	       	</li>
	       	<!-- 게시물이 출력될 영역 -->
	       	<li>
	       	<c:forEach var="address" items="${listAddress}">
				<ul>
					<li>${address.name }</li>
					<li>${address.company }</li>
					<li>${address.dept_position }</li>
					<li>${address.phone }</li>
				</ul>
			</c:forEach>
			</li>
		</ul>
	</div>
</div>
<div class="dashboard_notice">
	<div class="div_title">
		<h3>공지사항</h3>
	</div>
	<div class="plusBnt">
		<a href="${pageContext.request.contextPath}/notice_board" class="navBtn">더보기</a>
	</div>
	<div class="dashboard_ul">
		 <ul id="ulTable_notice">
	     	<li>
	        	<ul>
	            	<li>제목</li>
	                <li>작성일</li>
	                <li>조회수</li>
	            </ul>
	       	</li>
	       	<li>
	       	<!-- 게시물이 출력될 영역 -->
	       	<c:forEach var="notice" items="${list}">
				<ul>
					<li>${notice.title}</li>
					<li><fmt:formatDate value="${notice.reg_date}" pattern="yyyy-MM-dd" /></li>
					<li>${notice.view_count}</li>
				</ul>
			</c:forEach>
			</li>
		</ul>
	</div>
</div>
<div class="dashboard_chat">
	<div class="div_title">
			<h3>나의 채팅</h3>
	</div>
	<div class="plusBnt">
		<a href="${pageContext.request.contextPath}/chat_window" class="navBtn">더보기</a>
	</div>
	<div class="chat_list_area">
		<ul id="ulTable_chat">
	     	<li>
	        	<ul>
	            	<li>　</li>
	                <li>부서</li>
	                <li>직급</li>
	                <li>이름</li>
	                <li>제목</li>
	                <li>시간</li>
	            </ul>
	       	</li>
	       	<!-- 게시물이 출력될 영역 -->
	       	<li>
	        	<c:forEach var="chatWindow" items="${chatWindowDTOList }">
	        	<ul>
	            	<li class="chat_window_li" data-cwindow-no="${chatWindow.cwindowNo }">
	            		<span class="thumb_path">
	            		<c:if test="${chatWindow.chatMemberCount <= 2 }">
	            		<!-- 2인 이하의 채팅방인 경우 -->
	            			<img src="${chatWindow.thumbPath }" alt="${chatWindow.dName} ${chatWindow.position } ${chatWindow.mName } 님의 프로필 사진" />
	            		</c:if>
	            		<c:if test="${chatWindow.chatMemberCount > 2 }">
	            		<!-- 3인 이상의 채팅방인 경우 -->
	            			<span class="number_profile">${chatWindow.chatMemberCount }</span>
	            		</c:if>
	            		</span>
	            	</li>
	            	<li class="chat_window_li" data-cwindow-no="${chatWindow.cwindowNo }">
	            		<span class="d_name">
	            		${chatWindow.dName}
		            	</span>
		            </li>
		            <li class="chat_window_li" data-cwindow-no="${chatWindow.cwindowNo }">
		            	<span class="position">
		            		${chatWindow.position }
		            	</span>
		            </li>
		            <li class="chat_window_li" data-cwindow-no="${chatWindow.cwindowNo }">
		            	<span class="m_name">
		            		${chatWindow.mName }
		            	</span>
		            </li>
		            <li class="chat_window_li" data-cwindow-no="${chatWindow.cwindowNo }">
		            	<span class="message">
		            		${chatWindow.message }
		            	</span>
		            </li>
		           	<li class="chat_window_li" data-cwindow-no="${chatWindow.cwindowNo }">
		            	<span class="time">
		            		${chatWindow.time }
		            	</span>
		            </li>
		        </ul>
		        </c:forEach>
		</li>
	</ul>
	
	</div>
</div>
<div class="dashboard_report">
	<div class="div_title">
		<h3>내 결재 요청 문서</h3>
	</div>
	<div class="dashboard_ul_report">
		<ul id="ulTable_report">
	     	<li>
	        	<ul>
	        	 	<li>문서번호</li>
	            	<li>종류</li>
	                <li>상태</li>
	                <li>작성일</li>
	            </ul>
	       	</li>
	       	<!-- 게시물이 출력될 영역 -->
	       	<li>
	       	<c:forEach var="report" items="${listReport}">
				<ul>
					<li>${report.vacreport_no }</li>
					<li>${report.type }</li>
					<li>${report.status }</li>
					<li>${report.reg_date }</li>
				</ul>
			</c:forEach>
			</li>
		</ul>
	</div>
</div>
<div class="dashboard_count">
	<div class="div_title">
		<h3>나의 결재 현황</h3>
	</div>
	<div class="count">
		<h1 id="1">${reportCount}</h1>
		<h2><a href="${pageContext.request.contextPath }/consult_report">나의 결제 건수</a></h2>
	</div>
</div>
<div class="dashboard_project">
	<div class="div_title">
		<h3>나의 프로젝트 현황</h3>
	</div>
	<c:forEach var="project" items="${project }">
	<div data-project-no="${project.project_no }" class="projectCell">
		<span class="project_no_info">No.${project.project_no }</span>
		<span class="project_status_info ${project.status }"><span>${project.status }</span></span>
		<div class="projectCellInfo">
			<ul>
         		<li>
            	<input type="hidden" class="project_no" value="${project.project_no }" />
         		</li>
         		<li>
            	<span class="projectListProjectName ${project.status }">${project.name }</span>
         		</li>
         		<li>
            	등록 된 TASK<span class="taskCountSpan">〔${project.taskCount }〕</span>
         		</li>
         		<li>
         		<img src="${pageContext.request.contextPath}/${project.leaderThumb }"/>
         		${project.leaderPosition } ${project.leaderName } 외 ${project.memberCount }명
         		</li>
				<li>
            	${project.start_date }&nbsp; ~ &nbsp;${project.end_date }
         		</li>
				<li>
				<div class="progressBarOuter ${project.status }">
				<div class="progressBarInner" data-width="${project.progress }">
				</div>
				</div>
         		</li>
			</ul>
		</div>
	</div>   
	</c:forEach>
</div>
