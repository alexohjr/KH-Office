<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<c:if test="${historyDTO.todayHasStartWork ne 'admin'}">
		<tiles:importAttribute name="historyDTO"/>
	</c:if>
	
<div class="check_work_area">
	<div class="clock-container">
		<div class="clock-col">
			<p class="clock-day clock-timer"></p>
			<p class="clock-label">Day</p>
		</div>
		<div class="clock-col">
			<p class="clock-hours clock-timer"></p>
			<p class="clock-label">Hours</p>
		</div>
		<div class="clock-col">
			<p class="clock-minutes clock-timer"></p>
			<p class="clock-label">Minutes</p>
		</div>
		<div class="clock-col">
			<p class="clock-seconds clock-timer"></p>
			<p class="clock-label">Seconds</p>
		</div>
	</div>
	
	<c:if test="${historyDTO ne null}">
	<div class="btn_area">
		<c:if test="${historyDTO.todayHasStartWork eq 'empty'}">
			<button type="button" data-url="work_history/start" class="start_btn onStartWork">출근하기</button>
		</c:if>
		<c:if test="${historyDTO.todayHasStartWork eq 'has'}">
			<button type="button" class="start_btn offStartWork">출근하기</button>
		</c:if>
		<c:if test="${historyDTO.todayHasStartWork eq 'has' && historyDTO.todayHasEndWork eq 'empty'}">
			<button type="button" data-url="work_history/end" class="end_btn onEndWork">퇴근하기</button>
		</c:if>
		<c:if test="${historyDTO.todayHasEndWork eq 'has' || historyDTO.todayHasStartWork eq 'empty'}">
			<button type="button" class="end_btn offEndWork">퇴근하기</button>
		</c:if>
	</div>
	</c:if>
</div>


