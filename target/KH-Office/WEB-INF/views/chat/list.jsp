<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h2>채팅 목록</h2>

<c:if test="${chatWindowDTOList != null && chatWindowDTOList.size() !=0 }">
	
	<!-- 목록 영역 -->
	<div class="chat_list_area">
		<ul>
	        <c:forEach var="chatWindow" items="${chatWindowDTOList }">
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
	            	<span class="d_name">
	            		${chatWindow.dName}
	            	</span>
	            	<span class="position">
	            		${chatWindow.position }
	            	</span>
	            	<span class="m_name">
	            		${chatWindow.mName }
	            	</span>
	            	<span class="message">
	            		${chatWindow.message }
	            	</span>
	            	<span class="time">
	            		${chatWindow.time }
	            	</span>
	            </li>
	        </c:forEach>
	    </ul>
	</div>
    <!--// 목록 영역 -->
    
    <!-- 페이저 영역 -->
	<div class="pager_area">
            <c:if test="${pager.postCount > 0}">
                <c:if test="${pager.startPager > pager.pagerSize}">
                    <a href="${pageContext.request.contextPath}/chat_window?page_num=${pager.startPager - 1}"
                        class="pager_left pager_direction"><img src="${pageContext.request.contextPath}/resources/icon/pager_left.png"
                            alt="이전 버튼" /></a>
                </c:if>

                <c:forEach var="i" begin="${pager.startPager}" end="${pager.endPager}">
                    <c:if test="${pager.pageNum == i}">
                        <a href="${pageContext.request.contextPath}/chat_window?page_num=${i}" class="curr_page">${i}</a>
                    </c:if>
                    <c:if test="${pager.pageNum != i}">
                        <a href="${pageContext.request.contextPath}/chat_window?page_num=${i}">${i}</a>
                    </c:if>
                </c:forEach>

                <c:if test="${pager.endPager < pager.pageCount}">
                    <a href="${pageContext.request.contextPath}/chat_window?page_num=${pager.endPager + 1}"
                        class="pager_right pager_direction"><img src="${pageContext.request.contextPath}/resources/icon/pager_right.png"
                            alt="다음 버튼" /></a>
                </c:if>
            </c:if>
        </div>
        <!--// 페이저 영역 -->
        
</c:if>

<c:if test="${chatWindowDTOList == null || chatWindowDTOList.size() == 0}">
    <div class="post_not_exist">채팅방이 존재하지 않습니다.</div>
</c:if>

<div class="btn_area">
	<a href="chat_window/form">채팅방 개설</a>
</div>

