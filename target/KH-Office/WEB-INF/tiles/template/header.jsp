<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%-- <tiles:importAttribute name="menuList"/> --%>
<header id="header">
   <div>
      <div class="logo_area">
         <h1>
         	<c:if test="${session_memberNo != null }">
         		<a href="${pageContext.request.contextPath }/dashboard">
	               <img src="${pageContext.request.contextPath }/resources/icon/logo.png" alt="KH 오피스 로고" />
	            </a>
         	</c:if>
            <c:if test="${session_adminID != null}">
         		<a href="${pageContext.request.contextPath }/member">
	               <img src="${pageContext.request.contextPath }/resources/icon/logo.png" alt="KH 오피스 로고" />
	            </a>
         	</c:if>
         </h1>
      </div>
      <div class="nav_menu">
         <ul>
         <c:if test="${session_memberNo != null}">
            <li><a href="${pageContext.request.contextPath }/dashboard">홈</a></li>
         </c:if>
            <li><a href="${pageContext.request.contextPath }/member">주소록</a></li>
            <li><a href="${pageContext.request.contextPath }/work_history/${session_memberNo}">근태관리</a></li>
         <c:if test="${session_memberNo != null}">   
            <li><a href="${pageContext.request.contextPath }/schedule">캘린더</a></li>
         </c:if>
            
         <c:if test="${session_memberNo != null}">
            <li><a href="${pageContext.request.contextPath }/consult_report">전자결재</a></li>
         </c:if>
         <c:if test="${session_memberNo != null}">   
            <li><a href="${pageContext.request.contextPath }/reserve_assets">예약/대여</a></li>
         </c:if>
         <c:if test="${session_memberNo != null}">
            <li>
               <a href="${pageContext.request.contextPath }/notice_board">게시판</a>
               <div class="nav_menu_2">
                  <ul>
                     <li><a href="${pageContext.request.contextPath }/notice_board">공지 게시판</a></li>
                     <c:if test="${session_memberNo != null}">
                        <li><a href="${pageContext.request.contextPath }/depart_board">부서 게시판</a></li>
                     </c:if>
                     <li><a href="${pageContext.request.contextPath }/anony_board">익명 게시판</a></li>
                  </ul>
               </div>
            </li>
         </c:if>
         <c:if test="${session_memberNo == null}">
            <li><a href="${pageContext.request.contextPath }/notice_board">공지 게시판</a></li>
         </c:if>
         <c:if test="${session_memberNo == null}">
            <li><a href="${pageContext.request.contextPath }/holding_assets">자산 등록</a></li>
         </c:if>
         <c:if test="${session_memberNo != null}">   
            <li><a href="${pageContext.request.contextPath }/project">프로젝트</a></li>
         </c:if>
         <c:if test="${session_memberNo != null}">
            <li><a href="${pageContext.request.contextPath }/task">파일저장소</a></li>
         </c:if>
         <c:if test="${session_memberNo != null}">
            <li><a href="${pageContext.request.contextPath }/chat_window">채팅</a></li>
         </c:if>
         </ul>
      </div>
      <div class="util_menu">
         <ul>
            <c:if test="${session_adminID == null}">  <!-- member 로그인 한 경우에만 노출 -->
               <li><a href="${pageContext.request.contextPath }/member/${session_memberNo}">내정보</a></li>
            </c:if>
            <li id="logout"><a href="#" onclick="return false;">로그아웃</a></li>
         </ul>
      </div>
   </div>
   
</header>



