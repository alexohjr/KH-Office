<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="roundrobin"> <!-- popup start -->

	<h1>연&nbsp차&nbsp신&nbsp청&nbsp서</h1>

	<div class="top clearfix">
	<!-- 결재작성에서 로그인한 사용자의 기본정보를 불러올 getInfo 부분 -->
	<c:if test = "${getInfo ne null}">
		<table class="info" width="40%">
			<col width="30%"><col>
			<tr>
				<th>작성자</th>
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
				<td>${vacreport_no}</td><!-- 불러오는데이터 -->
			</tr>
		</table>
		<input type="hidden" name="mname" value="${getInfo.mname}"/>
		<input type="hidden" name="dname" value="${getInfo.dname}"/>
		<input type="hidden" name="position" value="${getInfo.position}"/>
		<input type="hidden" name="reg_date" value="${getInfo.reg_date}"/>
		<input type="hidden" name="vacreport_no" value="${vacreport_no}"/>
	</c:if>
	
	
	<!-- detail.jsp와 approval_form.jsp에서 사용할 등록된 데이터  -->
	<c:if test="${approvalMap.approvalMan1 ne null}">
		<table class="info" width="40%">
			<col width="30%"><col>
			<tr>
				<th>작성자</th>
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
				<td>${vacreport_no}</td><!-- 불러오는데이터 -->
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
				<td class ="added_member_area" id="approval_name1"><c:if test="${approvalMan ne null}">${approvalMan.approvalName1}</c:if></td>
				<td class ="added_member_area" id="approval_name2"><c:if test="${approvalMan ne null}">${approvalMan.approvalName2}</c:if></td>
				<td class ="added_member_area" id="approval_name3"><c:if test="${approvalMan ne null}">${approvalMan.approvalName3}</c:if></td>
			</tr>
		</table>
	</div><!-- top (e) -->

	
	
	
	<div class="content">
		
		<table width="100%">
			<col width="15%"><col>
			<tr>
				<th>업무 대리인</th>
				<td>
				<c:if test="${getInfo ne null}">
				<!-- <input type="text" name="substitute" placeholder="없으면 입력 안하셔도 됩니다."/> -->
					<form:select id="substitute_select" data-memberNo="${member_no}" path="substitute" name="substitute">
						<option value="">선택해주세요</option>
						<form:options items="${substitute}" itemLabel="name" itemValue="member_no"/>
					</form:select>
				</c:if>
				<c:if test="${approvalMap.approvalMan1 ne null}">
					${approvalMap.approvalMan1.substitute}
				</c:if>
				</td>
			</tr>
			<tr>
				<th><span class="i_red">*</span>휴가 종류</th>
				<td>
				<c:if test="${getInfo ne null}">
					<select id="vacation_type" name="type" data-name="${getInfo.mname}" data-remain="${remainder_leave}">
						<option value="">선택하세요</option>
						<option value="월차">월차</option>
						<option value="연차">연차</option>
						<option value="휴가">휴가</option>
						<option value="공가">공가</option>
						<option value="병가">병가</option>
					</select>
				</c:if>
				<c:if test="${approvalMap.approvalMan1 ne null}">
					<span>${approvalMap.approvalMan1.type}</span>
				</c:if>
				</td>
			</tr>
			<tr>
				<th><span class="i_red">*</span>휴가 기간</th>
				<td>
				<c:if test="${getInfo ne null}">
					<input type="date" id="start_date" name="start_date"> ~ <input type="date" id="end_date" name="end_date">
				</c:if>
				<c:if test="${approvalMap.approvalMan1 ne null}">
					<span>
						${approvalMap.approvalMan1.start_date} ~ ${approvalMap.approvalMan1.end_date}
					</span>
				</c:if>
					<span class="use_date">사용일수 : </span>
				<c:if test="${getInfo ne null}">
					<input type="text" id="use_day" name="use_day">
				</c:if>
				
				<c:if test="${approvalMap.approvalMan1 ne null}">${approvalMap.approvalMan1.use_day}</c:if>
				<span> 일</span></td>
			</tr>
			
			<tr class="trh2">
				<th><span class="i_red">*</span>휴가 사유</th>
				<c:if test="${getInfo ne null}">
				<td><textarea id="reason" name="reason"></textarea></td>
				</c:if>
				<c:if test="${approvalMap.approvalMan1 ne null}">
				<td>${approvalMap.approvalMan1.reason}</td>
				</c:if>
			</tr>
			
		</table>

	</div><!-- content(e) -->

</div><!-- roundrobin (e) -->
