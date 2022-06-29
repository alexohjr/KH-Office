<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page trimDirectiveWhitespaces="true" %>

<div id="work_history_detail_wrap">
	<div class="contentbox clearfix">
		<div class="left_menu">
			<div class="left_menu_inner">
				<h1>근태 현황</h1>
				<c:if test="${admin eq 'admin'}">
					<a href="${pageContext.request.contextPath}/work_history">전 사원 근태조회</a>
				</c:if>
			</div>
		</div><!--left_menu(e)-->
		
		<div class="right_content">
			<div class="right_content_inner">
				<div class="right_content_inner_top">
					<h2>근태 현황</h2>
				</div>
				<div class="history_member_info">
					<span><img src="${pageContext.request.contextPath}/${detailDTO[0].thumb_path}"/></span>
					<span class="info_dname">${detailDTO[0].department_name}</span>
					<span class="info_name">${detailDTO[0].name}</span>
				</div>

				<div class ="right_content_inner_YMD">
						<a class="YMD_left_arrow2" href="${pageContext.request.contextPath}/work_history/${member_no}?strYear=${year-1}&strMonth=${month}" >
	                        <span>
	                        	<img src="${pageContext.request.contextPath}/resources/icon/calendar_left_double.png"/><!-- 이전해 -->
	                        </span>
	                    </a>
                    <c:if test="${month >0}">
	                     <a class="YMD_left_arrow" href="${pageContext.request.contextPath}/work_history/${member_no}?strYear=${year}&strMonth=${month-1}" >
	                        <span>
	                      		 <img src="${pageContext.request.contextPath}/resources/icon/calendar_left.png"/><!-- 이전달 -->
	                        </span>
	                    </a>
                    </c:if>
                   	<c:if test="${month <= 0}">
                        <span class="YMD_left_arrow" class="YMD_left_arrow">
                      		  <img src="${pageContext.request.contextPath}/resources/icon/calendar_left.png"/><!-- 1월이면 이전달 클릭 X -->
                        </span>
                    </c:if>
                    <span class=right_content_inner_YM>
                    	<i>${year}</i>년 <i>${month+1}</i>월
                    </span>
    				<input type="hidden" data-page-num="${page_num}" name="startDate" id="startDate" class="date-picker" value="${year}년 ${month+1}월" readonly />
                    <c:if test="${month <11}">
	                    <a class="YMD_right_arrow" href="${pageContext.request.contextPath}/work_history/${member_no}?strYear=${year}&strMonth=${month+1}" >
	                       <span>
	                     		 <img src="${pageContext.request.contextPath}/resources/icon/calendar_right.png"/><!-- 다음달 -->
	                       </span>
	                    </a>
                    </c:if>
                    <c:if test="${month >= 11}">
                       <span class="YMD_right_arrow"><img src="${pageContext.request.contextPath}/resources/icon/calendar_right.png"/></span><!-- 12월이면 다음달 클릭 X -->
                    </c:if>
                    <a href="${pageContext.request.contextPath}/work_history/${member_no}?strYear=${year+1}&strMonth=${month}" >
                       <span class="YMD_right_arrow2"><img src="${pageContext.request.contextPath}/resources/icon/calendar_right_double.png"/></span><!-- 다음해 -->
                    </a>
				</div>
				
				<table class="work_history_detail">
				<col width="10%"><col width="22%"><col width="23%"><col width="10%"><col width="35%"> 
					<tr>
						<th class="h_date bdl">날짜</th>
						<th class="h_start_work" data-member-no="${member_no}">출근</th>
						<th class="h_end_work" data-member-no="${member_no}">퇴근</th>
						<th class="h_work_time">근무시간</th>
						<th class="h_memo">상태</th>
					</tr>
					<!-- 이자리에 for문으로 그 달의 1일부터 전체 일수 구해야함. -->
					<c:forEach var="i" items="${list}" varStatus="status">
						<tr data-member-no="${member_no}" data-year="${year}">
							<td class="li_date bdl">${month+1}-${status.count}(${i})</td>
							<td class="li_start_work"  data-start="${dtoList.strStart}" data-member-no="${member_no}" <c:if test="${admin eq 'admin'}">data-admin="${admin}"</c:if> data-year="${year}" data-month="${month+1}" data-date="${status.count}">
								<c:forEach var="dtoList" items="${historyDTOList}" varStatus="s">
									<c:if test="${status.count eq dtoList.db_date && month eq dtoList.db_month}">
										<span class="s_start_work">
											${dtoList.strStart}
										</span>
										<span>
											<img class="start_ip" data-ip="${dtoList.start_ip}" id="modal" src="${pageContext.request.contextPath}/resources/icon/work_history_ip.png" alt="" />
										</span>
									</c:if>
								</c:forEach>
							</td>
							<td class="li_end_work li_admin_end_work" data-end="${dtoList.strEnd}"data-member-no="${member_no}"	<c:if test="${admin eq 'admin'}">data-admin="${admin}"</c:if> data-year="${year}" data-month="${month+1}" data-date="${status.count}" >
							<c:forEach var="dtoList" items="${historyDTOList}" varStatus="s">
								<c:if test="${status.count eq dtoList.db_date && month eq dtoList.db_month}">
									<span class="s_end_work">
										${dtoList.strEnd}
									</span>
									<c:if test="${dtoList.end_ip ne null}">
										<span>
											<img class="end_ip" data-ip="${dtoList.end_ip}" src="${pageContext.request.contextPath}/resources/icon/work_history_ip.png" alt="" />
										</span>
									</c:if>
								</c:if>
							</c:forEach>
							</td>
							<td class="li_work_time">
								<c:forEach var="dtoList" items="${historyDTOList}" varStatus="s">
									<c:if test="${status.count eq dtoList.db_date && month eq dtoList.db_month}">
										${dtoList.work_time}
									</c:if>
								</c:forEach>
							</td>
							<td class="li_memo" data-date="${status.count}"	<c:forEach var="dtoList" items="${historyDTOList}" varStatus="s"><c:if test="${status.count eq dtoList.db_date && month eq dtoList.db_month}">data-workhistory-no="${dtoList.work_history_no}" data-memo="${dtoList.memo}"</c:if></c:forEach>>
								<c:forEach var="dtoList" items="${historyDTOList}" varStatus="s">
									<c:if test="${status.count eq dtoList.db_date && month eq dtoList.db_month}">${dtoList.memo}</c:if>
								</c:forEach>
							</td>
						</tr>
					</c:forEach>
					 
				</table> <!-- work_history_detail(table) -->
				
			</div> <!-- right_content_inner -->
			
		</div> <!-- right_content -->
		
	</div> <!-- contentbox clearfix -->
	
</div> <!-- work_history_detail_wrap -->
					