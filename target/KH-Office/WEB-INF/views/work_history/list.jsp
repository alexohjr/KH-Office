<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css">

<div id="work_history_list_wrap">
	<div class="contentbox clearfix">
		<div class="left_menu">
			<div class="left_menu_inner">
				<h1>전사원 근태조회</h1>
				<ul class="left_menu_inner_department_wrap">
				<c:forEach items="${departmentList}" var ="departmentList">
					<li>
						<a href="${pageContext.request.contextPath}/work_history?department_no=${departmentList.department_no}&&strYear=${year}&strMonth=${month}" data-department-no="${departmentList.department_no}">
							${departmentList.name}
						</a>
					</li>
				</c:forEach>
				</ul>
			</div>
		</div><!--left_menu(e)-->
		
		
		<div class="right_content">
			<div class="right_content_inner">
				<div class="right_content_inner_top">
					<h2>근태 현황</h2>
					<h3 data-dno="${department_no}"></h3>
				</div>
				
				
				<div class ="right_content_inner_YMD">
					<a class="YMD_left_arrow2" href="${pageContext.request.contextPath}/work_history?department_no=${department_no}&page_num=${page_num}&strYear=${year-1}&strMonth=${month}" >
                       	<span>
                       		<img src="${pageContext.request.contextPath}/resources/icon/calendar_left_double.png"></img>
                       	</span><!-- 이전해 -->
                    </a>
                    <c:if test="${month >0}">
	                    <a class="YMD_left_arrow" href="${pageContext.request.contextPath}/work_history?department_no=${department_no}&page_num=${page_num}&strYear=${year}&strMonth=${month-1}" >
	                        <span>
	                        	<img src="${pageContext.request.contextPath}/resources/icon/calendar_left.png"></img>
	                        </span><!-- 이전달 -->
	                    </a>
                    </c:if>
                   	<c:if test="${month <= 0}">
                        <span class="YMD_left_arrow">
                        	<img src="${pageContext.request.contextPath}/resources/icon/calendar_left.png"></img>
                        </span>
                    </c:if>
                    <span data-year="${year}" data-month="${month}" class=right_content_inner_YM><i>${year}</i>년 <i>${month+1}</i>월</span>
    				<input type="hidden" data-department-no="${department_no}" data-page-num="${page_num}" name="startDate" id="startDate" class="date-picker" value="${year}년 ${month+1}월" readonly />
                    <c:if test="${month <11}">
	                    <a class="YMD_right_arrow" href="${pageContext.request.contextPath}/work_history?department_no=${department_no}&page_num=${page_num}&strYear=${year}&strMonth=${month+1}" >
	                       <span>
	                       		<img src="${pageContext.request.contextPath}/resources/icon/calendar_right.png"></img>
	                       </span><!-- 다음달 -->
	                    </a>
                    </c:if>
                    <c:if test="${month >= 11}">
                        <span class="YMD_right_arrow"><img src="${pageContext.request.contextPath}/resources/icon/calendar_right.png"></img></span>
                    </c:if>
                    <a class="YMD_right_arrow2" href="${pageContext.request.contextPath}/work_history?department_no=${department_no}&page_num=${page_num}&strYear=${year+1}&strMonth=${month}" >
                       <span>
                     	  <img src="${pageContext.request.contextPath}/resources/icon/calendar_right_double.png"></img>
                       </span><!-- 다음해 -->
                    </a>
				</div>

					
				<div class="admin_list_table_wrap">
					<table class="admin_work_history_list">
					<col width="10%"> 
						<tr>
							<th>부서원</th>						
						<c:forEach var="i" items="${list}" varStatus="status">
							<c:if test="${status.first}">
								<th></th><!-- 출근/퇴근/근무/상태 위 공백을 위한 빈 태그 -->
							</c:if>
							<th class="today_dates" data-day="${i}" data-date="${status.count}">${status.count}</th>
						</c:forEach>
						</tr>
						
						<!-- 한사람의 전체 데이터 시작 -->
						<c:forEach items="${workHistoryDTO}" var="parent_list" varStatus="s">
							<tr class="tr_start_work">
								<td class="history_member_info" rowspan="4"> <!-- 사원 정보 -->
									<c:forEach items="${memberListByDepartNo}" var="memberListByDepartNo" varStatus="status">
										<a href="${pageContext.request.contextPath}/work_history/${memberListByDepartNo.member_no}">
											<c:if test="${s.index eq status.index}">
												<span><img src="${pageContext.request.contextPath}/${memberListByDepartNo.thumb_path}"></img></span><p></p>
												<span>${memberListByDepartNo.mname}</span><p></p><!-- p태그는 밑으로 띄워주기위함. -->
												<span>${memberListByDepartNo.position}</span>
											</c:if>
										</a>
									</c:forEach>
								</td>
								<td>출근</td>
								<c:forEach items="${parent_list}" var ="child_list" varStatus ="s2">
									<td class="td_start_work" data-start-work-date="${s.count}">
										${child_list.startTime}						
									</td>
								</c:forEach>
							</tr>
							<tr class="tr_end_work">
								<td>퇴근</td>
								<c:forEach items="${parent_list}" var ="child_list" varStatus ="s2">
									<td class="td_end_work" data-end-work-date="${s.count}">
										${child_list.endTime}
									</td>
								</c:forEach>
							</tr>
							<tr class="tr_work_time">
								<td>근무</td>
								<c:forEach items="${parent_list}" var ="child_list" varStatus ="s2">
									<td class="td_work_time" data-work-time-date="${s.count}">
										${child_list.work_time}
									</td>
								</c:forEach>
							</tr>
							<tr class="tr_memo">
								<td>상태</td>
								<c:forEach items="${parent_list}" var ="child_list" varStatus ="s2">
									<td class="td_memo" data-memo-date="${s.count}">
										${child_list.memo}
									</td>
								</c:forEach>
							</tr>
						</c:forEach>
						<!-- 한사람의 전체 데이터 끝 (for문돌리는구간)-->
						
					</table> <!-- admin_work_history_list(table) 끝 -->
				
				</div> <!-- admin_list_table_wrap 끝 -->
				
				<div class="paging_area">
					<c:if test="${startPagingNum > pageBlock}">
						<div class="left_arrow">
							<span class="page_arrow">
								<a href="${pageContext.request.contextPath}/work_history?department_no=${department_no}&page_num=${startPagingNum - pageBlock}">
									<img src="${pageContext.request.contextPath}/resources/icon/arrow_left.png" alt="" />
								</a>
							</span>
						</div>
					</c:if>
					
					<c:if test="${startPagingNum <= pageBlock}">
						<div class="left_arrow op3">
							<span class="page_arrow"><img src="${pageContext.request.contextPath}/resources/icon/arrow_left.png" alt="" /></span>
						</div>
					</c:if>
					
					<c:forEach var="i" begin="${startPagingNum}" end="${pageCount}" varStatus="status">
                  		<a href="${pageContext.request.contextPath}/work_history?department_no=${department_no}&page_num=${i}&strYear=${year}&strMonth=${month}" data-target="${i}" class="page_num">
                    		<span>${i}</span>
                  		</a>                  
               		</c:forEach>
				
					<c:if test="${endPagingNum < pageCount}">
						<div class="right_arrow">
							<span class="page_arrow">
								<a href="${pageContext.request.contextPath}/work_history?department_no=${department_no}&page_num=${startPage + pageBlock}">
									<img src="${pageContext.request.contextPath}/resources/icon/arrow_right.png" alt="" />
								</a>								
							</span>
						</div>
					</c:if>
					
					<c:if test="${endPagingNum >= pageCount}">
						<div class="right_arrow op3">
							<span class="page_arrow">
								<img src="${pageContext.request.contextPath}/resources/icon/arrow_right.png" alt="" />
							</span>
						</div>
					</c:if>
					
				</div> <!-- paging_area -->
				
			</div> <!-- right_content_inner 끝 -->
			
		</div> <!-- right_content 끝 -->
		
	</div><!-- contentbox clearfix 끝 -->
	
</div> <!-- work_history_list_wrap 끝 -->