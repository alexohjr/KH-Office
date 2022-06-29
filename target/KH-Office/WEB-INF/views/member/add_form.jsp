<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<div id="wrapper">
<label id="registerLabel">사원등록</label>
	<form action="${pageContext.request.contextPath}/member?page_num=${pageNum}&keyword=${keyword}&is_bookmark=${isBookmark}" method="post" id="register_form" enctype="multipart/form-data">
		<ul>
			 <li id="upload_form">
				<span class="title">사진</span>
				<span class="contents">
					<img src="" class="profile_img">
					<input type="file" name="uploadFile" value="" id="uploadFile" >
						<span id="textLabel">
							360X480 사이즈의 jpg,png 파일만 업로드 가능합니다.
						</span>
				</span>
			</li>
			<li>
				<span class="title">*이름</span>
				<span class="contents"><input type="text" id="name" name="name" class="inputBig" placeholder="한글 또는 영어 대소문자"></span>
			</li>
			<li>
				<span class="title">비밀번호 </span>
				<span class="contents"><label class="fixedInfo"> 1234 (사원 첫 등록 시 비밀번호는 1234로 지정됩니다)</label></span>
			</li>
			<li>
				<span class="title">*부서</span>
				<span class="contents">
					<select id="department" name="department_no" class="inputSmall">
						<c:forEach var="list" items="${list}">
							<option value="${list.department_no}">${list.name}</option>
						</c:forEach>
					</select>
				</span>
			</li> 
			<li>
				<span class="title">*직위</span>
				<span class="contents">
					<select id="position" name="position" class="inputSmall">
							<option value="사원">사원</option>
							<option value="주임">주임</option>
							<option value="대리">대리</option>
							<option value="과장">과장</option>
							<option value="차장">차장</option>
							<option value="부장">부장</option>
							<option value="사장">사장</option>
							<option value="회장">회장</option>
					</select>
				</span>
			</li>
			<li>
				<span class="title">*사내메일</span>
				<span class="contents"><input type="email" id="email" name="email" class="inputBig" oninput="checkEmail()" placeholder="이메일 주소 입력"></span>
				<span id="check_message"></span>
			</li> 
			<li>
				<span class="title">*휴대폰 번호</span>
				<span class="contents"><input type="text" id="phone" name="phone" class="inputBig" placeholder="숫자만 입력(-제외)"></span>
			</li>
			<li>
				<span class="title">내선 번호</span>
				<span class="contents"><input type="text" id="tel" name="tel" class="inputBig" placeholder="숫자만 입력(-제외)"></span>
			</li> 
			<li id="address_form">
				<span class="title">*자택 주소</span>
				<span class="contents">
					<span>
						<input type="text" id="zipcode" name="zipcode" class="inputSmall" placeholder="우편번호">
						<input type="button" id="findZipcode_btn" onclick="DaumPostcode()" value="우편번호 찾기">
					</span>
					<span>
						<input type="text" id="main_address" name="main_address" class="inputBig" placeholder="주소">
						<input type="text" id="detail_address" name="detail_address" class="inputBig" placeholder="상세주소">
					</span>
				</span>
			</li>
			<li>
				<span class="title">입사일</span> 
				<span class="contents"><input type="date" id="hire_date" name="hire_date"></span>
			</li> 
			<li>
				<span class="title">총 연차</span>
				<span class="contents"><input type="text" id="total_leave" name="total_leave" class="inputSmall" value="15"> 일</span>
			</li>
			<li>
				<span class="title">잔여 연차</span>
				<span class="contents"><input type="text" id="remainder_leave" name="remainder_leave" class="inputSmall" value="15"> 일</span>
			</li>
			<li>
				<span class="title">채용구분</span>
				<span class="contents radioButtons">
					<input type="radio" name="hire_type1" id="hire_type1" value="신입" checked="checked">
						<label for="hire_type1" class="radio-label">신입</label>
					<input type="radio" name="hire_type1" id="hire_type2" value="경력">
						<label for="hire_type2" class="radio-label">경력</label>
					<input type="hidden" name="hire_type" value="신입" />
				</span>
			</li>
			<li>
				<span class="title">직원구분 </span>
				<span class="contents radioButtons">
					<input type="radio" name="emp_type1" id="emp_type1" value="정규직" checked="checked">
						<label for="emp_type1" class="radio-label">정규직</label>
					<input type="radio" name="emp_type1" id="emp_type2"value="계약직">
						<label for="emp_type2" class="radio-label">계약직</label>
					<input type="hidden" name="emp_type" value="정규직" />
				</span>
			</li>
			<li>
				<span class="title">생년월일</span>
				<span class="contents"><input type="date" name="birthday" id="birthday"></span>
			</li>
			<li>
				<span class="title">성별</span> 
				<span class="contents radioButtons">
					<input type="radio" name="gender1" id="gender1" value="여성" checked="checked">
						<label for="gender1" class="radio-label">여성</label>
					<input type="radio" name="gender1" id="gender2" value="남성">
						<label for="gender2" class="radio-label">남성</label>
					<input type="hidden" name="gender"  value="여성" />
				</span>
			</li>
			<li>
				<span class="title">결혼 여부</span>
				<span class="contents radioButtons">
					<input type="radio" name="marriage1" id="marriage1" value="0" checked="checked">
						<label for="marriage1" class="radio-label">미혼</label>
					<input type="radio" name="marriage1" id="marriage2" value="1">
						<label for="marriage2" class="radio-label">기혼</label>
					<input type="hidden" name="marriage" value="0" />
				</span>
			</li>
			<li>
				<span class="title">장애 여부</span>
				<span class="contents radioButtons">
					<input type="radio" name="disabled1" id="disabled1" value="0" checked="checked">
						<label for="disabled1" class="radio-label">비장애</label>
					<input type="radio" name="disabled1" id="disabled2" value="1">
						<label for="disabled2" class="radio-label">장애</label>
					<input type="hidden" name="disabled" value="0" />
				</span>
			</li>
		</ul>
		<span id="buttonArea">
			<input type="submit" id="submit_btn" value="등록">
			<input type="button" id="cancel_btn" onclick="javascript:history.go(-1)" value="취소">
		</span>
	</form>
	
</div>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>