<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="holding_div_table">
<h2>보유 자산</h2>

<c:if test="${count eq 0 }">
등록 된 자산이 없습니다.
<div>
	<form action="${pageContext.request.contextPath}/holding_assets" method="get" class="search_form">
		<input type="text" name="keyword" class="keyword_input">
		<input class="button_submit" type="submit" value="검색" >
	</form>
</div>
</c:if>
<c:if test="${count > 0 }">
<div>
	<form action="${pageContext.request.contextPath}/holding_assets" method="get" class="search_form">
		<input type="text" name="keyword" class="keyword_input">
		<input class="button_submit" type="submit" value="검색" >
	</form>
</div>
<div class="">
	<table class="holding_table">
		<tr>
			<th class="holding_th">순번</th>
			<th class="holding_th">자산명</th>
			<th class="holding_th">자산담당자</th>
			<th class="holding_th">수정</th>
			<th class="holding_th">삭제</th>
		</tr>
		<c:forEach var="HoldingAsstes" items="${HoldingAssetsList}">
		<tr>
			<td class="holding_td"><c:out value="${number }"/>
				<c:set var="number" value="${number-1}"/></td>
			<td class="holding_td">${HoldingAsstes.h_name}</td>
			<td class="holding_td">${HoldingAsstes.m_name}</td>
			<td class="holding_td">
				<input class="button_submit" type="button" value="수정" onclick="javacript:location.href='holding_assets/form/${HoldingAsstes.hAssets_no}'"></td>
			<td class="holding_td">
				<form action="holding_assets/${HoldingAsstes.hAssets_no}?type=delete&keyword=${keyword}" method="post" id="holding_assets_delete_form">
					<input class="button_submit" type="submit" value="삭제" id="submitBtn">
				</form>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>

	<c:if test="${count >10 }">
	<c:choose>
		<c:when test="${empty keyword}">
		<div>
			<table >
			  	<tr>
			  		<td>
					<c:set var="pageCount" value="${count/pageSize+(count%pageSize==0 ? 0:1)}"/>
					<c:set var="pageBlock" value="${10}"/>
					<fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />   
					<c:set var="startPage" value="${result *10 +1}"/>   
					<c:set var="endPage" value="${startPage + pageBlock-1}"/>
					<c:if test="${endPage > pageCount}">
					<c:set var="endPage" value="${pageCount}"/>
					</c:if>
					<c:if test="${startPage>10}">
						<a href="holding_assets?pageNum=${startPage-10}">[이전]</a>
					</c:if>
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<a href="holding_assets?pageNum=${i}">[${i}]</a>
					</c:forEach>
					<c:if test="${endpage<pageCount}">
						<a href="holding_assets?pageNum=${startPage+10}">[다음]</a>
					</c:if>
					</td>
				</tr>
			</table>
		</div>	    
		</c:when>
	    <c:otherwise>
	    <div>
	      	<table>
			  	<tr>
			  		<td>
					<c:set var="pageCount" value="${count/pageSize+(count%pageSize==0 ? 0:1)}"/>
					<c:set var="pageBlock" value="${10}"/>
					<fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />   
					<c:set var="startPage" value="${result *10 +1}"/>
					<c:set var="endPage" value="${startPage + pageBlock-1}"/>
					<c:if test="${endPage > pageCount}">
					<c:set var="endPage" value="${pageCount}"/>
					</c:if>
					<c:if test="${startPage>10}">
						<a href="holding_assets?pageNum=${startPage-10}&keyword=${keyword}">[이전]</a>
					</c:if>
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<a href="holding_assets?pageNum=${i}&keyword=${keyword}">[${i}]</a>
					</c:forEach>
					<c:if test="${endpage<pageCount}">
						<a href="holding_assets?pageNum=${startPage+10}&keyword=${keyword}" >[다음]</a>
					</c:if>
					</td>
				</tr>
			</table>
		</div>
	    </c:otherwise>
	</c:choose>
	</c:if>
</c:if>
<div class="holding_assets_btn_area">
	<input  class="button_submit" type="button" value="자산등록" id="holding_form">
</div>
</div>

