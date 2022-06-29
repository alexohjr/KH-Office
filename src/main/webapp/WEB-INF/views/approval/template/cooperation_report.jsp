<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="roundrobin"> <!-- popup start -->

	<h1>업무협조요청서</h1><!-- letter-spacing:20px; 으로 nbsp; 효과를 넣을 수 있음. -->

	<div class="top clearfix">
	<!-- 결재작성에서 로그인한 사용자의 기본정보를 불러올 getInfo 부분 -->
	<c:if test = "${getInfo ne null}">
		<table class="info" width="40%">
			<col width="30%"><col>
			<tr>
				<th>요청자</th>
				<td>${getInfo.mname}</td><!-- 불러오는데이터 -->
			</tr>
			<tr>
				<th>작성일</th>
				<td>${getInfo.reg_date}</td><!-- 불러오는데이터 -->
			</tr>
			<tr>
				<th>문서번호</th>
				<td>${cooreport_no}</td><!-- 불러오는데이터 -->
			</tr>
			<tr>
				<th>요청부서</th>
				<td>${getInfo.dname}</td><!-- 불러오는데이터 -->
			</tr>
		</table>
		<input type="hidden" name="mname" value="${getInfo.mname}"/>
		<input type="hidden" name="dname" value="${getInfo.dname}"/>
		<input type="hidden" name="reg_date" value="${getInfo.reg_date}"/>
		<input type="hidden" name="cooreport_no" value="${cooreport_no}"/>
	</c:if>
	
	<!-- detail.jsp와 approval_form.jsp에서 사용할 등록된 데이터  -->
	<c:if test="${approvalMap.approvalMan1 ne null}">
		<table class="info" width="40%">
			<col width="30%"><col>
			<tr>
				<th>요청자</th>
				<td>${approvalMap.approvalMan1.mname}</td><!-- 불러오는데이터 -->
			</tr>
			<tr>
				<th>작성일</th>
				<td>${approvalMap.approvalMan1.reg_date}</td><!-- 불러오는데이터 -->
			</tr>
			<tr>
				<th>문서번호</th>
				<td>${cooreport_no}</td><!-- 불러오는데이터 -->
			</tr>
			<tr>
				<th>요청부서</th>
				<td>${approvalMap.approvalMan1.department_no}</td><!-- 불러오는데이터 -->
			</tr>
		</table>
	</c:if>
		<table class="sign" width="40%">
			<col width="10%"><col width="10%"><col width="10%">
			<tr class="tr">
				<th>요청부서</th>
				<th>협조부서</th>
				
			</tr>
			<tr class="signbox">
				<td class ="added_member_area" id="approval_name1"><c:if test="${approvalMan ne null}">${approvalMan.approvalName1}</c:if></td>
				<td class ="added_member_area" id="approval_name2"><c:if test="${approvalMan ne null}">${approvalMan.approvalName2}</c:if></td>
			</tr>
		</table>
<!-- 		<input type="hidden" name="request_team_leader" value="5"/>
		<input type="hidden" name="cooperation_team_leader" value="6"/> -->
	</div><!-- top (e) -->

	<div class="content">
		
		<table width="100%">
			<col width="15%"><col><col width="15%"><col>
			<tr>
				<th><span class="i_red">*</span>협조부서</th>
				<td>
			<c:if test="${getInfo ne null}">
				<form:select path="department_no" name ="department_no">
					<option value="">선택해주세요</option>
					<form:options items="${department_no}" itemLabel="name" itemValue="department_no"/>
				</form:select>
			</c:if>
			<c:if test="${approvalMap.approvalMan1 ne null}">
				<span>${getNameByDepartmentNo}</span>
			</c:if>
				</td>
			</tr>
			
			<tr>
				<th><span class="i_red">*</span>요청기한</th>
			<c:if test="${getInfo ne null}">
				<td colspan="3"><input type="date" id="cooperation_deadline" name="deadline"/></td>
			</c:if>
			<c:if test="${approvalMap.approvalMan1 ne null}">
				<td colspan="3"><span>${approvalMap.approvalMan1.deadline}</span>
			</c:if>
				
			</tr>
			<tr>
				<th><span class="i_red">*</span>제목</th>
			<c:if test="${getInfo ne null}">
				<td colspan="3"><input type="text" id="cooperation_title" name="title"/></td>
			</c:if>
			<c:if test="${approvalMap.approvalMan1 ne null}">
				<td colspan="3">${approvalMap.approvalMan1.title}</td>
			</c:if>
			</tr>
			
			<tr class="trh2">
				<th><span class="i_red">*</span>내용</th>
			<c:if test="${getInfo ne null}">
				<td colspan="3"><textarea id="cooperation_content" name="content"></textarea></td>
			</c:if>
			<c:if test="${approvalMap.approvalMan1 ne null}">
				<td colspan="3">${approvalMap.approvalMan1.content}</td>
			</c:if>
			</tr>
			
		</table>

	</div><!-- content(e) -->

</div><!-- roundrobin (e) -->
