<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="roundrobin"> <!-- popup start -->

	<h1>품&nbsp&nbsp의&nbsp&nbsp서</h1>

	<div class="top clearfix">
	
	<!-- 결재작성에서 로그인한 사용자의 기본정보를 불러올 getInfo 부분 -->
	<c:if test = "${getInfo ne null}">
		<table class="info" width="40%">
			<col width="30%"><col>
			<tr>
				<th>기안자</th>
				
				<td>${getInfo.mname}</td><!-- 불러오는데이터 -->
			</tr>
			<tr>
				<th>소속</th>
				<td>${getInfo.dname}</td><!-- 불러오는데이터 -->
			</tr>
			<tr>
				<th>직위</th>
				<td>${getInfo.position}</td><!-- 불러오는데이터 -->
			</tr>
			<tr>
				<th>작성일</th>
				<td>${getInfo.reg_date}</td><!-- 불러오는데이터 -->
			</tr>
			<tr>
				<th>문서번호</th>
				<td>${conreport_no}</td><!-- 불러오는데이터 -->
			</tr>
		</table>
		
		<input type="hidden" name="mname" value="${getInfo.mname}"/>
		<input type="hidden" name="dname" value="${getInfo.dname}"/>
		<input type="hidden" name="position" value="${getInfo.position}"/>
		<input type="hidden" name="reg_date" value="${getInfo.reg_date}"/>
		<input type="hidden" name="conreport_no" value="${conreport_no}"/>
	</c:if>
	
	
	<!-- detail.jsp와 approval_form.jsp에서 사용할 등록된 데이터  -->
	<c:if test="${approvalMap.approvalMan1 ne null}">
		<table class="info" width="40%">
			<col width="30%"><col>
			<tr>
				<th>기안자</th>
				
				<td>${approvalMap.approvalMan1.mname}</td><!-- 불러오는데이터 -->
			</tr>
			<tr>
				<th>소속</th>
				<td>${approvalMap.approvalMan1.dname}</td><!-- 불러오는데이터 -->
			</tr>
			<tr>
				<th>직위</th>
				<td>${approvalMap.approvalMan1.position}</td><!-- 불러오는데이터 -->
			</tr>
			<tr>
				<th>작성일</th>
				<td>${approvalMap.approvalMan1.reg_date}</td><!-- 불러오는데이터 -->
			</tr>
			<tr>
				<th>문서번호</th>
				<td>${conreport_no}</td><!-- 불러오는데이터 -->
			</tr>
		</table>
	</c:if>
		<table class="sign" width="40%">
			<col width="10%"><col width="10%"><col width="10%">
			<tr class="tr">
				<th>팀장</th>
				<th>부서장</th>
				<th>대표이사</th>
			</tr>
			<tr class="signbox">
				<td class ="added_member_area" id="approval_name1"><c:if test="${approvalMan ne null}">${approvalMan.approvalName1}</c:if></td><!-- 불러오는데이터 -->
				<td class ="added_member_area" id="approval_name2"><c:if test="${approvalMan ne null}">${approvalMan.approvalName2}</c:if></td><!-- 불러오는데이터 -->
				<td class ="added_member_area" id="approval_name3"><c:if test="${approvalMan ne null}">${approvalMan.approvalName3}</c:if></td><!-- 불러오는데이터 -->
			</tr>
		</table>

	</div><!-- top (e) -->

	<div class="content">
		
		<table width="100%">
			<col width="15%"><col>
			<tr>
				<th>제목</th>
				<c:if test="${getInfo ne null}">
					<td>
						<input type="text" id="consult_title" name ="title">
					</td><!-- 직접입력 -->
				</c:if>
				<c:if test="${approvalMap.approvalMan1 ne null}">
					<td>
						${approvalMap.approvalMan1.title}
					</td>
				</c:if>
			</tr>
			<tr class="trh">
				<th>내용</th>
				<c:if test="${getInfo ne null}">
					<td>
						<textarea id="consult_content" name="content" rows="8" cols="10"></textarea>
					</td><!-- 직접입력 -->
				</c:if>
				<c:if test="${approvalMap.approvalMan1 ne null}">
					<td>${approvalMap.approvalMan1.content}</td>
				</c:if>
			
			</tr>
	
		</table>

	</div><!-- content(e) -->

</div><!-- roundrobin (e) -->
