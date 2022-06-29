<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h1>자산 예약</h1>

<div class="add_form_div">
	<form name="reserve_assets_add_form" action="${pageContext.request.contextPath}/reserve_assets" method="post" id="reserve_assets_add_form">
		<table class="reserve_table">
			<tbody>
				<tr>
					<td>자산명</td>
					<td>
						<select name="name" class="selectName" data-taget="selectName">
						<option value="">자산을 선택 하세요</option>
						<c:forEach var="HoldingAsstes" items="${HoldingAssetsList}">
						<option data-name="${HoldingAsstes.m_name}" data-hAssets-no="${HoldingAsstes.hAssets_no }"  value="${HoldingAsstes.h_name}">${HoldingAsstes.h_name }</option>
						</c:forEach>
						</select>
					</td>
					<td>자산담당자</td>
					<td>
						<div class="select_name"></div>
					</td>
				</tr>
				<tr>
					<td>예약 날짜</td>
					<td>
						<input class="dateDay" id="dateDay" type="date" name="reservationDate">
					</td>
					<td>
						<select class="startTimeSelector" name="start">	
						<option value="">대여가능 시작 시간</option>
						</select>
					</td>
					<td>
						<select class="endTimeSelector" name="end">
						<option value="" >대여가능 마지막 시간</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>인원</td>
					<td>	
						<input class="people" type="number" name="people">
					</td>
				</tr>
				<tr>
					<td>용무</td>
				</tr>
				<tr>
					<td colspan="4">  
						<textarea class="purpose"  name="purpose"></textarea>
					</td>
				</tr>
			</tbody>
		</table>
		<input class="hAssets_no" type="hidden" id="hAssets_no" name="hAssets_no" value="">
		<input class="pageNum" type="hidden" id="pageNum" value="${pageNum }">
		<input class="day" type="hidden" id="day" value="${day}">
		<div class="reserve_btn_area">
			<input class="button_submit" type="submit" value="예약" id="submitBtn">
			<input class="button_submit" type="button" value="취소" id="add_form_cancle">
		</div>
	</form>

</div>