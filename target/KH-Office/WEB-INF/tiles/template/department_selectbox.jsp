<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<tiles:importAttribute name="departmentDTOList"/>

<div class="member_select_area">
	<ul>
		<c:forEach var="departmentDTO" items="${departmentDTOList}">
			<li data-department-no="${departmentDTO.department_no}" class="department_li off">
				<img src="${pageContext.request.contextPath }/resources/icon/department_plus.png" alt="부서내 사원 조회 버튼" class="department_btn">
				 <span>${departmentDTO.name}</span>
			</li>
		</c:forEach>
	</ul>
</div>


