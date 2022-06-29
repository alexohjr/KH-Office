<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<tiles:insertDefinition name="${type}"/><p>

<c:if test="${conapproval_no ne null}">
	<form id="updateSubmit" action="${pageContext.request.contextPath}/consult_approval/${conapproval_no}?type=edit&page_num=1&last_approval=${last_approval}" method="post">
		<input type="hidden" id="conreport_no" name="conreport_no" value="${conreport_no}">
		<input type="hidden" id="conapproval_no" name="conapproval_no" value="${conapproval_no}">
	</form>
</c:if>


<c:if test="${cooapproval_no ne null}">
	<form id="updateSubmit" action="${pageContext.request.contextPath}/cooperation_approval/${cooapproval_no}?type=edit&page_num=1&last_approval=${last_approval}" method="post">
		<input type="hidden" id="cooreport_no" name="cooreport_no" value="${cooreport_no}">
		<input type="hidden" id="cooapproval_no" name="cooapproval_no" value="${cooapproval_no}">
	</form>
</c:if>


<c:if test="${vacapproval_no ne null}">
	<form id="updateSubmit" action="${pageContext.request.contextPath}/vacation_approval/${vacapproval_no}?type=edit&page_num=1&last_approval=${last_approval}" method="post">
		<input type="hidden" id="vacreport_no" name="vacreport_no" value="${vacreport_no}">
		<input type="hidden" id="vacapproval_no" name="vacapproval_no" value="${vacapproval_no}">
		<input type="hidden" id="use_day" name="use_day" value="${approvalMap.approvalMan1.use_day}">
	</form>
</c:if>

<div class="btn_wrap clearfix">
	<div class="btn_area">
		<button id="approval_submit_btn" class="approval_submit_btn left_btn" data-status="1" type="button">승인</button>
		<button id="approval_opposite_btn" class="approval_submit_btn right_btn btnr" data-status="2" type="button">반려</button>
	</div>

	<button id="approval_back_btn" class="list_btn" type="button">목록으로</button>
</div>
