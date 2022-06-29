<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div id="request_list_wrap">
	<div class="contentbox clearfix">
		<div class="left_menu">
			<div class="left_menu_inner">
				<h1>전자결재</h1>
				<ul>
					<li class="reportStatusLi"><a href="${pageContext.request.contextPath}/consult_report?status=0">결재 대기 문서</a></li>
					<li><a href="${pageContext.request.contextPath}/consult_report?status=1">결재 요청 문서</a></li>
				</ul>
			</div>
		</div><!--left_menu(e)-->
		
		<div class="right_content_wrap">
			<div class="right_content">
				<div class="right_content_inner">
					<div class="right_content_inner_link">
						<h2>결재대기문서</h2>
						<div class ="tab_menu">
							<ul>
								<li class="added_on" data-target="consult_report"><a href="${pageContext.request.contextPath}/consult_report?status=0">품의서</a></li>
								<li class="added_on" data-target="vacation_report"><a href="${pageContext.request.contextPath}/vacation_report?status=0">연차신청서</a></li>
								<li class="added_on" data-target="cooperation_report"><a href="${pageContext.request.contextPath}/cooperation_report?status=0">업무협조요청서</a></li>
							</ul>
						</div>
					</div>
					
					<div class="right_response_list clearfix">
						<c:choose>
							<c:when test="${!empty conApprovalList}">
								<c:forEach var="board" items="${conApprovalList}" varStatus="status">
									<div class="response_list_content">
										<span class="response_list_status">대기중</span>
										<h3>${board.title}</h3>
										<div class="response_list_content_inner clearfix">
											<div class="content_inner_left">
												<p>기안자</p>
												<p>결재양식</p>
											</div>
											<div class="content_inner_right">
												<p>${board.report_name}${board.report_position}<span class="inner_content_regdate">(${board.reg_date})</span></p>
												<p>품의서</p>
											</div>
										</div>
										<div class="content_inner_bottom">
											<a href="${pageContext.request.contextPath}/consult_report/form/${board.conreport_no}?page_num=1&conapproval_no=${board.conapproval_no}&last_approval=${board.last_approval}">결재하기</a>
										</div>
									</div>
								</c:forEach>
							</c:when>
							
							<c:when test="${!empty vacApprovalList}">
								<c:forEach var="board" items="${vacApprovalList}" varStatus="status">
									<div class="response_list_content">
										<span class="response_list_status">대기중</span>
										<h3>${board.type}신청서입니다.</h3>
										<div class="response_list_content_inner clearfix">
											<div class="content_inner_left">
												<p>기안자</p>
												<p>결재양식</p>
											</div>
											<div class="content_inner_right">
												<p>${board.report_name}${board.report_position}<span class="inner_content_regdate">(${board.reg_date})</span></p>
												<p>연차신청서</p>
											</div>
										</div>
										<div class="content_inner_bottom">
											<a href="${pageContext.request.contextPath}/vacation_report/form/${board.vacreport_no}?page_num=1&vacapproval_no=${board.vacapproval_no}&last_approval=${board.last_approval}">결재하기</a>
										</div>
									</div>
								</c:forEach>
							</c:when>
							
							<c:when test="${!empty cooApprovalList}">
								<c:forEach var="board" items="${cooApprovalList}" varStatus="status">
									<div class="response_list_content">
										<span class="response_list_status">대기중</span>
										<h3>${board.title}</h3>
										<div class="response_list_content_inner clearfix">
											<div class="content_inner_left">
												<p>기안자</p>
												<p>결재양식</p>
											</div>
											<div class="content_inner_right">
												<p>${board.report_name}${board.report_position}<span class="inner_content_regdate">(${board.reg_date})</span></p>
												<p>업무협조요청서</p>
											</div>
										</div>
										<div class="content_inner_bottom">
											<a href="${pageContext.request.contextPath}/cooperation_report/form/${board.cooreport_no}?page_num=1&cooapproval_no=${board.cooapproval_no}&last_approval=${board.last_approval}">결재하기</a>
										</div>
									</div>
								</c:forEach>
							</c:when>
						</c:choose>
					
										<%-- <table>
							<col width="8%"><col><col width="12%"><col width="13%"><col width="13%">
							<tr>
								<th class="h_no bdl">번호</th>
								<th class="h_tit">제목</th>
								<th class="h_date">작성일</th>
								<th class="h_writer">작성자</th>
							</tr>
							<c:if test="${!empty conApprovalList}">
							<c:forEach var="board" items="${conApprovalList}" varStatus="status">
							<!--  status_ing : 결재진행중, status_com : 승인, status_turn : 반려 -->
							<tr>
								<td class="li_no bdl">${board.conapproval_no}</td>
								<td class="li_tit"><a href="${pageContext.request.contextPath}/consult_report/form/${board.conreport_no}?page_num=1&conapproval_no=${board.conapproval_no}&last_approval=${board.last_approval}">${board.title}</a></td>
								<td class="li_date">${board.reg_date}</td>
								<td class="li_name">${board.approval_name} </td>
							</tr>
							</c:forEach>
							</c:if>
							<c:if test="${empty conApprovalList}">
								<tr>
									<td class="bdl" colspan="4"><span>승인할 문서가 없습니다.</span></td>
								</tr>
							</c:if>
						</table> --%>
						
		
						
		<%-- 				<c:when test="${vacApprovalList ne null}">
							<table>
								<col width="8%"><col><col width="12%"><col width="13%"><col width="13%">
								<tr>
									<th class="h_no bdl">번호</th>
									<th class="h_tit">제목</th>
									<th class="h_date">작성일</th>
									<th class="h_writer">작성자</th>
								</tr>
								<c:if test="${!empty vacApprovalList}">
								<c:forEach var="board" items="${vacApprovalList}" varStatus="status">
								<!--  status_ing : 결재진행중, status_com : 승인, status_turn : 반려 -->
								<tr>
									<td class="li_no bdl">${board.vacapproval_no}</td>
									<td class="li_tit"><a href="${pageContext.request.contextPath}/vacation_report/form/${board.vacreport_no}?page_num=1&vacapproval_no=${board.vacapproval_no}&last_approval=${board.last_approval}">${board.type}신청서</a></td>
									<td class="li_date">${board.reg_date}</td>
									<td class="li_name">${board.approval_name} </td>
								</tr>
								</c:forEach>
								</c:if>
								<c:if test="${empty vacApprovalList}">
									<tr>
										<td class="bdl" colspan="4"><span>승인할 문서가 없습니다.</span></td>
									</tr>
								</c:if>
							</table>
						</c:when>
						
						<c:when test="${cooApprovalList ne null}">
							<table>
								<col width="8%"><col><col width="12%"><col width="13%"><col width="13%">
								<tr>
									<th class="h_no bdl">번호</th>
									<th class="h_tit">제목</th>
									<th class="h_date">작성일</th>
									<th class="h_writer">작성자</th>
								</tr>
								<c:if test="${!empty cooApprovalList}">
								<c:forEach var="board" items="${cooApprovalList}" varStatus="status">
								<!--  status_ing : 결재진행중, status_com : 승인, status_turn : 반려 -->
								<tr>
									<td class="li_no bdl">${board.cooapproval_no}</td>
									<td class="li_tit"><a href="${pageContext.request.contextPath}/cooperation_report/form/${board.cooreport_no}?page_num=1&cooapproval_no=${board.cooapproval_no}&last_approval=${board.last_approval}">${board.title}</a></td>
									<td class="li_date">${board.reg_date}</td>
									<td class="li_name">${board.approval_name} </td>
								</tr>
								</c:forEach>
								</c:if>
								<c:if test="${empty cooApprovalList}">
									<tr>
										<td class="bdl" colspan="4"><span>승인할 문서가 없습니다.</span></td>
									</tr>
								</c:if>
							</table>
						</c:when> --%>
					
					</div>
					<div class="paging_area">
					
					</div><!--paging_area(e)-->
						
				</div><!--right_content_inner(e)-->
				
			</div><!--right_content(e)-->
		
		</div><!--right_content_wrap(e)-->
		
	</div><!--contentbox(e)-->

</div><!--#request_list_wrap(e)-->