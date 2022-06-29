<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div id="wrapper">
<label id="editLabel">사원정보수정</label>
	<form action="${pageContext.request.contextPath}/member/${memberNo}?type=edit&page_num=${pageNum}&keyword=${keyword}&is_bookmark=${isBookmark}" method="post" id="edit_form" enctype="multipart/form-data">
		<c:forEach var="detailList" items="${detailList }">
		<ul>
			<li id="upload_form">
				<span class="title">사진</span>
				<span class="contents">
					<span class="imgArea">
						<img src="${pageContext.request.contextPath}/${detailList.thumb_path}" class="profile_img">
						<input type="button" id="imgEdit_btn" value="사진 변경">
					</span>
					<input type="file" name="uploadFile" id="uploadFile" value="">
						<span id="textLabel">
							360X480 사이즈의 jpg,png 파일만 업로드 가능합니다.
						</span>
				</span>
			</li> 
			<li>
				<span class="title">이름</span>
				<span class="contents"><input type="text" name="name" class="inputSmall"  value="${detailList.name}" /></span>
			</li>
			<li>
				<span class="title">부서</span>
				<span class="contents"><select id="department" name="department_no" class="inputSmall">
					<c:forEach var="list" items="${deptList}">
						<option value="${list.department_no}" <c:if test="${list.name == detailList.department_name}">selected="selected"</c:if>>
						${list.name}
						</option>
					</c:forEach>
				</select> 
				</span>
			</li>
			<li>
				<span  class="title">직위</span>
				<span class="contents">
					<select id="position" name="position" class="inputSmall">
							<option value="사원" <c:if test="${detailList.position eq '사원'}">selected="selected"</c:if>>사원</option>
							<option value="주임" <c:if test="${detailList.position eq '주임'}">selected="selected"</c:if>>주임</option>
							<option value="대리" <c:if test="${detailList.position eq '대리'}">selected="selected"</c:if>>대리</option>
							<option value="과장" <c:if test="${detailList.position eq '과장'}">selected="selected"</c:if>>과장</option>
							<option value="차장" <c:if test="${detailList.position eq '차장'}">selected="selected"</c:if>>차장</option>
							<option value="부장" <c:if test="${detailList.position eq '부장'}">selected="selected"</c:if>>부장</option>
							<option value="사장" <c:if test="${detailList.position eq '사장'}">selected="selected"</c:if>>사장</option>
							<option value="회장" <c:if test="${detailList.position eq '회장'}">selected="selected"</c:if>>회장</option>
					</select>
				</span>
			</li>
			<li>
				<span class="title">사내메일 </span> 
				<span class="contents"><label class="fixedInfo">${detailList.email} </label></span>
			</li>
			<li>
				<span class="title">내선 번호 </span>
				<span class="contents"><input type="text" id="tel" name="tel" value="${detailList.tel}" class="inputBig"></span>
			</li>
			<li>
				<span  class="title">휴대폰 번호 </span> 
				<span class="contents"><input type="text" id="phone" name="phone" value="${detailList.phone}" class="inputBig"></span>
			</li>
			<li id="address_form">
				<span class="title">자택 주소 </span> 
				<span class="contents">
					<input type="text" id="zipcode" name="zipcode" value="${detailList.zipcode}" class="inputSmall">
					<input type="button" id="findZipcode_btn" onclick="DaumPostcode()" value="우편번호 찾기"><br>
					<input type="text" id="main_address" name="main_address" value="${detailList.main_address}" class="inputBig">
					<input type="text" id="detail_address" name="detail_address" value="${detailList.detail_address}" class="inputBig">
				</span>
			</li>
			<li>
				<span class="title">총 연차 </span>
				<span class="contents"><input type="text" name="total_leave" class="inputSmall" value="${detailList.total_leave}" > 일 </span>
			</li>
			<li>
				<span class="title">잔여 연차</span> 
				<span class="contents"><input type="text" name="remainder_leave" class="inputSmall" value="${detailList.remainder_leave}"> 일</span>
			</li>
			<li>
				<span class="title">입사일</span>
				<span class="contents"><label class="fixedInfo"> ${detailList.hire_date}</label></span>
			</li>
			<li>
				<span class="title">채용구분</span>
				<span class="contents"><label class="fixedInfo">${detailList.hire_type}</label></span>
			</li>
			<li>
				<span class="title">직원구분</span>
				<span class="contents radioButtons" >
					<input type="radio" name="emp_type1" id="emp_type" value="정규직" <c:if test="${detailList.emp_type eq '정규직'}">checked="checked"</c:if>>
						<label for="emp_type" class="radio-label">정규직</label>
					<input type="radio" name="emp_type1" id="emp_type2" value="계약직" <c:if test="${detailList.emp_type eq '계약직'}">checked="checked"</c:if>>
						<label for="emp_type2" class="radio-label">계약직</label>
					<input type="hidden" name="emp_type" value="${detailList.emp_type}" />
				</span>
			</li>
			<li>
				<span class="title">생년월일</span>
				<span class="contents"><label class="fixedInfo">${detailList.birthday}</label></span>
			</li>
			<li>
				<span class="title">성별</span>
				<span class="contents"><label class="fixedInfo"> ${detailList.gender}</label></span>
			</li>
			<li>
				<span class="title">결혼 여부</span>
				<span class="contents radioButtons">
					<input type="radio" name="marriage1" id="marriage1" value="0" <c:if test="${detailList.marriage eq '0'}">checked="checked"</c:if>>
						<label for="marriage1" class="radio-label">미혼</label>
					<input type="radio" name="marriage1" id="marriage2"value="1" <c:if test="${detailList.marriage eq '1'}">checked="checked"</c:if>>
						<label for="marriage2" class="radio-label">기혼</label>
					<input type="hidden" name="marriage" value="${detailList.marriage}" />
				</span>
				
			</li>
			<li>
				<span class="title">장애 여부</span>
				<span class="contents radioButtons">
					<input type="radio" name="disabled1" id="disabled1" value="0" <c:if test="${detailList.disabled eq '0'}">checked="checked"</c:if>>
						<label for="disabled1" class="radio-label">비장애</label>
					<input type="radio" name="disabled1" id="disabled2" value="1" <c:if test="${detailList.disabled eq '1'}">checked="checked"</c:if>>
						<label for="disabled2" class="radio-label">장애</label>
					<input type="hidden" name="disabled" value="${detailList.disabled}" />
				</span>
			</li>
		</ul>
		<span id="buttonArea">
				<input type="submit" value="수정" id="edit_btn">
				<input type="button" id="cancel_btn" onclick="javascript:history.go(-1)" value="취소">
		</span>
		</c:forEach>
	</form>
	
</div>


<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>