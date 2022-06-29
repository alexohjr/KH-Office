<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"> -->
<!-- <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"> -->
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> -->


<h2>자산 예약</h2> 
<h3>예약 현황</h3>
<div align="center">
	<button class="prev-day btn btn-outline-secondary"><i class="fa fa-angle-left" aria-hidden='true'></i></button>
	<button class='today-date btn btn-outline-secondary'>Today</button>
	<input type='text' id='datepicker' name="day" value="${day}" data-target-pageNum="${pageNum}"  readonly>
	<button class='next-day btn btn-outline-secondary' ><i class='fa fa-angle-right' aria-hidden='true'></i></button>
</div>

<div class="holding_div">
	<table class="holding_table">
		<tbody>
			<tr class="holding_tr" align="center">
				<th></th>
				<th>09~10</th>
				<th>10~11</th>
				<th>11~12</th>
				<th>12~13</th>
				<th>13~14</th>
				<th>14~15</th>
				<th>15~16</th>
				<th>16~17</th>
				<th>17~18</th>
			</tr>
			<c:forEach var="holdingReserve" items="${holdingReserveList}">
			<tr class="holding_tr" align="center">
				<td>${holdingReserve.h_name}</td>
				<c:forEach var="reserve" items="${holdingReserve.reserve}">
				<td class="reserve" data-time="${reserve }">${reserve}</td>
				</c:forEach>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<h3>내 예약 현황</h3>
<c:if test="${reserveCount eq 0}">
	<span class="no_reserve_message">예약이 없습니다.</span>
</c:if>
<c:if test="${reserveCount >0 }">
<div class="myReserve"></div>
<table class="reserve_table">
	<tr align="center">
		<th class="reserve_th">자산명</th>
		<th class="reserve_th">자산 담당자</th>
		<th class="reserve_th">예약시간</th>
		<th class="reserve_th">예약인원</th>
		<th class="reserve_th">사용목적</th>
		<th class="reserve_th"> </th>
	</tr>
<c:forEach var="reserveMyMember" items="${reserveMyMemberListDTO}">
	<tr align="center">  
		<td class="reserve_td">${reserveMyMember.h_name}</td>
		<td class="reserve_td">${reserveMyMember.m_name} ${reserveMyMember.position }</td>
		<td class="reserve_td">${reserveMyMember.start_time}시 ~ ${reserveMyMember.end_time}시</td>
		<td class="reserve_td">${reserveMyMember.people}</td>
		<td class="reserve_td">${reserveMyMember.purpose}</td>
		<td class="reserve_td">
			<form action="reserve_assets/${reserveMyMember.rAssets_no}?pageNum=${pageNum}&day=${day}" method="post" class="reserve_assets_delete_form">
				<input class="button_submit" type="submit"value="예약취소">
			</form>
		</td>
	</tr>
</c:forEach>
</table>

<%-- <c:if test="${reserveCount >1 }"> --%>
<div align="center">
	<table>
		<tr>
			<td>
			<c:set var="pageCount" value="${reserveCount/pageSize+(reserveCount%pageSize==0 ? 0:1)}"/>
			<c:set var="pageBlock" value="${10}"/>
			<fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />   
			<c:set var="startPage" value="${result *10 +1}"/>   
			<c:set var="endPage" value="${startPage + pageBlock-1}"/>
			<c:if test="${endPage > pageCount}">
			<c:set var="endPage" value="${pageCount}"/>
			</c:if>
			<c:if test="${startPage>10}">
				<a href="reserve_assets?pageNum=${startPage-10}&day=${day}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a href="reserve_assets?pageNum=${i}&day=${day}">[${i}]</a>
			</c:forEach>
			<c:if test="${endpage<pageCount}">
				<a href="reserve_assets?pageNum=${startPage+10}&day=${day}">[다음]</a>
			</c:if>
			</td>
		</tr>
	</table>
</div>
</c:if>
<%-- </c:if> --%>
<div align="right" class="reserve_button">
<input class="button_submit" type="button" value="예약하기" id="reserve_form">
</div>