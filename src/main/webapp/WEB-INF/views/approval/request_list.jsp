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
					<li>
						<a href="${pageContext.request.contextPath}/consult_report?status=0">결재 대기 문서</a>
					</li>
					<li class="reportStatusLi">
						<a href="${pageContext.request.contextPath}/consult_report?status=1">결재 요청 문서</a>
					</li>
				</ul>
			</div>
		</div><!--left_menu(e)-->
		
		
		<div class="right_content">
			<div class="right_content_inner">
				<div class="right_content_inner_top">
				<a class="button_base b09_electric" href ="${pageContext.request.contextPath}/consult_report/form" id="approval_report_go">결재작성</a>
					<h2>결재요청문서</h2>
					<div class ="tab_menu">
						<ul>
							<li class="added_on" data-target="consult_report"><a href="${pageContext.request.contextPath}/consult_report?status=1">품의서</a></li>
							<li class="added_on" data-target="vacation_report"><a href="${pageContext.request.contextPath}/vacation_report?status=1">연차신청서</a></li>
							<li class="added_on" data-target="cooperation_report"><a href="${pageContext.request.contextPath}/cooperation_report?status=1">업무협조요청서</a></li>
						</ul>
					</div>
				</div>
				
				<c:choose>
				
				<c:when test="${conDTOList ne null}">
				
					<table class="request_list">
						<col width="8%"><col><col width="12%"><col width="13%"><col width="13%">
						<tr>
							<th class="h_no bdl">번호</th>
							<th class="h_tit">제목</th>
							<th class="h_progress">상태</th>
							<th class="h_date">작성일</th>
							<th class="h_writer">작성자</th>
						</tr>
						<c:if test="${!empty conDTOList}">
						<c:forEach var="board" items="${conDTOList}" varStatus="status">
						<!--  status_ing : 결재진행중, status_com : 승인, status_turn : 반려 -->
						<tr>
							<td class="li_no bdl">${board.conreport_no}</td>
							<td class="li_tit">
								<a href="${pageContext.request.contextPath}/consult_report/${board.conreport_no}?page_num=1">${board.title}</a>
							</td>
							<td class="li_status" data-status="${board.status}">
								<span>
									${board.status}
								</span>
							</td>
							<td class="li_date">${board.reg_date}</td>
							<td class="li_name">${board.name} </td>
						</tr>
						</c:forEach>
						</c:if>
						<c:if test="${empty conDTOList}">
							<span>결재 요청한 문서가 없습니다.</span>
						</c:if>
					</table>
				
				</c:when>
				<c:when test="${vacDTOList ne null}">
					 <table class="request_list">
					 <col width="8%"><col><col width="12%"><col width="13%"><col width="13%">
						<tr>
							<th>문서번호</th>
							<th>제목</th>
							<th>상태</th>
							<th>작성일</th>
							<th>신청자</th>
							<th>업무대리인</th>
						</tr>
						<c:forEach var="board" items="${vacDTOList}">
						<tr>
							<td class="li_no bdl"><c:out value="${board.vacreport_no}"/></td>
							<td class="li_tit">
								<a href="${pageContext.request.contextPath}/vacation_report/${board.vacreport_no}?page_num=1">${board.type} 신청서입니다.</a>
							</td>
							<td class="li_status" data-status="${board.status}">
								<span>
									${board.status}
								</span>
							</td>
							<td class="li_date">${board.reg_date}</td>
							<td class="li_name">${board.name}</td>
							<td class="li_name">${board.substitute}</td>
						</tr>
						</c:forEach>
					</table>
				
				</c:when>
				<c:when test="${cooDTOList ne null}">
					<table class="request_list">
					<col width="8%"><col><col width="12%"><col width="13%"><col width="13%">
						<tr>
							<th>문서번호</th>
							<th>제목</th>
							<th>상태</th>
							<th>작성일</th>
							<th>작성자</th>
						</tr>
						<c:forEach var="board" items="${cooDTOList}">
						<tr>
							<td class="li_no bdl"><c:out value="${board.cooreport_no}"/></td>
							<td class="li_tit">
								<a href="${pageContext.request.contextPath}/cooperation_report/${board.cooreport_no}?page_num=1">${board.title}</a>
							</td>
							<td class="li_status" data-status="${board.status}">
								<span>
									${board.status}
								</span>
							</td>
							<td class="li_date">${board.reg_date}</td>
							<td class="li_name">${board.name}</td>
						</tr>
						</c:forEach>
					</table>
					
					</c:when>
				</c:choose>
				
				

				<div class="paging_area">
				
					<c:set var="pageCount" value="${count / pageSize + (count % pageSize == 0 ? 0:1)}"/>
					<c:set var="pageBlock" value="${10}"/> 
					<fmt:parseNumber var="result" 
						value="${(currentPage % pageBlock) == 0 ? (currentPage/pageBlock)-1 : (currentPage/pageBlock)}" integerOnly="true" />   
					<c:set var="startPage" value="${result * pageBlock + 1}"/>   
					<c:set var="endPage" value="${startPage + pageBlock - 1}"/>
					<c:if test="${endPage > pageCount}">
						<c:set var="endPage" value="${pageCount}"/>
					</c:if>	
					
					<!-- page '이전' 기능 -->
					<c:if test="${startPage>10}">
						<div class="left_arrow">
							<span class="page_arrow">
								<c:choose>
									<c:when test="${conDTOList ne null}">
										<a href="${pageContext.request.contextPath}/consult_report?status=1&page_num=${startPage-10}">
											<img src="${pageContext.request.contextPath}/resources/icon/arrow_left.png" alt="" />
										</a>
									</c:when>
									<c:when test="${vacDTOList ne null}">
										<a href="${pageContext.request.contextPath}/vacation_report?status=1&page_num=${startPage-10}">
											<img src="${pageContext.request.contextPath}/resources/icon/arrow_left.png" alt="" />
										</a>
									</c:when>
									<c:when test="${cooDTOList ne null}">
										<a href="${pageContext.request.contextPath}/cooperation_report?status=1&page_num=${startPage-10}">
											<img src="${pageContext.request.contextPath}/resources/icon/arrow_left.png" alt="" />
										</a>
									</c:when>
								</c:choose>	
							</span>
						</div>
					</c:if>
					
					<c:if test="${startPage<=10}">
						<div class="left_arrow op3">
							<span class="page_arrow"><img src="${pageContext.request.contextPath}/resources/icon/arrow_left.png" alt="" /></span>
						</div>
					</c:if>
					
					<!-- 페이지 번호 -->
					<c:forEach var="i" begin="${startPage}" end="${endPage}" varStatus="status">
						<c:choose>
							<c:when test="${conDTOList ne null}">
								<a href="${pageContext.request.contextPath}/consult_report?status=1&page_num=${i}" data-target="${i}" class="page_num">
									<span>${i}</span>
								</a>
							</c:when>
							<c:when test="${vacDTOList ne null}">
								<a href="${pageContext.request.contextPath}/vacation_report?status=1&page_num=${i}" data-target="${i}" class="page_num">
									<span>${i}</span>
								</a>						
							</c:when>
							<c:when test="${cooDTOList ne null}">
								<a href="${pageContext.request.contextPath}/cooperation_report?status=1&page_num=${i}" data-target="${i}" class="page_num">
									<span>${i}</span>
								</a>						
							</c:when>
						</c:choose>
					</c:forEach>

					<!-- page '다음' 기능 -->
					<c:if test="${endPage<pageCount}">
						<div class="right_arrow">
							<span class="page_arrow">
								<c:choose>
									<c:when test="${conDTOList ne null}">
										<a href="${pageContext.request.contextPath}/consult_report?status=1&page_num=${startPage+10}">
											<img src="${pageContext.request.contextPath}/resources/icon/arrow_right.png" alt="" />
										</a>
									</c:when>
									<c:when test="${vacDTOList ne null}">
										<a href="${pageContext.request.contextPath}/vacation_report?status=1&page_num=${startPage+10}">
											<img src="${pageContext.request.contextPath}/resources/icon/arrow_right.png" alt="" />
										</a>								
									</c:when>
									<c:when test="${cooDTOList ne null}">
										<a href="${pageContext.request.contextPath}/cooperation_report?status=1&page_num=${startPage+10}">
											<img src="${pageContext.request.contextPath}/resources/icon/arrow_right.png" alt="" />
										</a>								
									</c:when>
								</c:choose>
							</span>
						</div>
					</c:if>
					<c:if test="${endPage>=pageCount}">
						<div class="right_arrow op3">
							<span class="page_arrow">
								<img src="${pageContext.request.contextPath}/resources/icon/arrow_right.png" alt="" />
							</span>
						</div>
					</c:if>
				
				</div><!--paging_area(e)-->
					
			</div><!--right_content_inner(e)-->
			
		</div><!--right_content(e)-->

	</div><!--contentbox(e)-->

</div><!--#wrap(e)-->