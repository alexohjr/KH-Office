<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="fourdollor-message-section">
	<div class="fourdollor-message-section-section">
		<h2>개인 일정</h2>
	</div>
	<div id="company_schedule">
		<DIV id="content">
		
			<table class="schedule_table_button">
				<tr>
					<td>
						<input type="button" class="btn btn-warning goto_today_btn" onclick="javascript:location.href='<c:url value='schedule' />'"
						 value="오늘" />
						<input type="button" class="btn btn-warning add_schedule_btn" onclick="add_holiday()" value="일정 추가" />
					</td>
				</tr>
			</table>
			
			<!--날짜 네비게이션  -->
			<table class="schedule_table_nav" id="KOO">
				<tr>
					<td>
						<table class="schedule_table_picker">
							<tr>
								<td>
								
								
								
									<a href="<c:url value='schedule' />?year=${year-1 }&amp;month=${month}" target="_self">
								 		<img src="${pageContext.request.contextPath }/resources/icon/calendar_left_double.png" alt="이전 년도 가기" class="btn" /> <!-- 이전해 -->
								 	</a> 
									<c:if test="${month > 0}">
										<a href="<c:url value='schedule' />?year=${year }&amp;month=${month-1}" target="_self">
											<img src="${pageContext.request.contextPath }/resources/icon/calendar_left.png" alt="이전 월 가기" class="btn" /> <!-- 이전달 -->
										</a>
									</c:if> 
									<c:if test="${month <= 0}">
										<img src="${pageContext.request.contextPath }/resources/icon/calendar_left.png" alt="이전 월 가기" class="btn" />
									</c:if>
									 <%-- &nbsp;&nbsp; ${year }년 ${month+1 }월 &nbsp;&nbsp; --%>
									 
									<span>${year }년 ${month+1 }월</span>
									
									<input type="hidden" name="startDate" id="startDate" class="date-picker" value="${year }년 ${month+1 }월" readonly/>
									
									<c:if test="${month < 11 }">
										<a href="<c:url value='schedule' />?year=${year }&amp;month=${month+1}"	target="_self">
										 	<img src="${pageContext.request.contextPath }/resources/icon/calendar_right.png" alt="다음 월  가기" class="btn" /> <!-- 다음달 --> 
										</a>
									</c:if> 
									<c:if test="${month >= 11 }">
										<img src="${pageContext.request.contextPath }/resources/icon/calendar_right.png" alt="다음 월  가기" class="btn" />
									</c:if> 
									<a href="<c:url value='schedule' />?year=${year+1 }&amp;month=${month}"	target="_self"> 
										<img src="${pageContext.request.contextPath }/resources/icon/calendar_right_double.png" alt="다음 년도 가기" class="btn" /> <!-- 다음해 --> 
									</a>


									
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<br>
			<table class="schedule_table_date">
				<THEAD>
					<TR class="schedule_tr_week">
						<TD class="schedule_td_week">
							<DIV>
								<font color="red">일</font>
							</DIV>
						</TD>
						<TD class="schedule_td_week">
							<DIV>월</DIV>
						</TD>
						<TD class="schedule_td_week">
							<DIV>화</DIV>
						</TD>
						<TD class="schedule_td_week">
							<DIV>수</DIV>
						</TD>
						<TD class="schedule_td_week">
							<DIV>목</DIV>
						</TD>
						<TD class="schedule_td_week">
							<DIV>금</DIV>
						</TD>
						<TD class="schedule_td_week">
							<DIV>
								<font color="#529dbc">토</font>
							</DIV>
						</TD>
					</TR>
				</THEAD>
				<TBODY>
					<TR>
						<c:forEach var="index" begin="1" end="${start-1 }">
							<td>&nbsp;</td>
							<c:set var="newLine" value="${newLine + 1}" />
						</c:forEach>

						<c:forEach var="index" begin="1" end="${endDay }">
							<c:set var="color" value="" />
							<c:choose>
								<c:when test="${newLine == 0 }">
									<c:set var="color" value="RED" />
								</c:when>
								<c:when test="${newLine == 6 }">
									<c:set var="color" value="BLUE" />
								</c:when>
								<c:otherwise>
									<c:set var="color" value="BLACK" />
								</c:otherwise>
							</c:choose>

							<c:set var="backColor" value="#EFEFEF" />
							<c:if test="${index == today_date && year == today_year && month == today_month }">
								<c:set var="backColor" value="#777" />
							</c:if>

							<TD class="schedule_td_data" bgcolor="${backColor }" nowrap>
								<font color="${color }">${index }</font><br>
								<c:forEach var="list" items="${list}">
									<c:set var="h_name" value="${list.h_name }" />
									<c:set var="date0" value="${list.s_date }" />
									<c:set var="year1" value="${fn:substring(date0, 0, 4)}" />
									<c:set var="month1" value="${fn:substring(date0, 5, 7)}" />
									<c:set var="date1" value="${fn:substring(date0, 8, 10)}" />
									<c:if test="${index == date1 && month == month1-1 && year == year1}">
										<form class="schedule_delete_form${list.h_num}" action="schedule/${list.h_num}?year=${year}&month=${month}"
										 method="post" id="schedule_delete_form">
											<div class="nameColor" data-scheduleNo="${list.h_num}" data-hName="${h_name}" data-color="${list.status}">${h_name
												}</div>
											<input class="submitBtn" type="submit" />
										</form>
									</c:if>
								</c:forEach><br>
							</TD>
							
							<c:set var="newLine" value="${newLine+1 }" />
							<c:if test="${newLine == 7 }">
					</tr>
					<c:if test="${index <= endDay }">
						<tr>
					</c:if>
					<c:set var="newLine" value="0" />
					</c:if>
					</c:forEach>
					<c:forEach var="index" begin="1" end="6">
						<c:if test="${newLine > 0 && newLine <7}">
							<td>&nbsp;</td>
							<c:set var="newLine" value="${newLine+1 }" />
						</c:if>
					</c:forEach>
					</tr>
				</TBODY>
			</TABLE>
		</DIV>
	</div>
</div>