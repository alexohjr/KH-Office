<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div id ="add_form_wrap">
	
	<div class ="contentbox clearfix">
		<div class="left_menu">
			<div class="left_menu_inner">
				<h1>결재작성</h1>
				<ul>
					<li class="reportStatusLi" data-target="consult_report"><a href ="${pageContext.request.contextPath}/consult_report/form">품의서</a></li>
					<li class="reportStatusLi" data-target="vacation_report"><a href="${pageContext.request.contextPath}/vacation_report/form">연차신청서</a></li>
					<li class="reportStatusLi" data-target="cooperation_report"><a href="${pageContext.request.contextPath}/cooperation_report/form">업무협조요청서</a></li>
				</ul>
				<%-- <button type="button" class="changeBtn"
					onclick="javacript:location.href='${pageContext.request.contextPath}/consult_report/form'">품의서</button>
				<button type="button" class="changeBtn"
					onclick="javacript:location.href='${pageContext.request.contextPath}/vacation_report/form'">연차신청서</button>
				<button type="button" class="changeBtn"
					onclick="javacript:location.href='${pageContext.request.contextPath}/cooperation_report/form'">업무협조요청서</button> --%>
			</div>
		</div> <!--left_menu(e)-->
	
		<div class="right_content">
			<div class="right_content_inner">
				<form id="report_add_form" name="submitForm" method="post" action="${pageContext.request.contextPath}/${type}" onsubmit="return checkError()">
		
					<div>
						<tiles:insertDefinition name="department_selectbox"/>
					</div>
	
					<div class="added_input_area"></div>
	
					<tiles:insertDefinition name="${type}"/>
		
					<div class="btn_wrap clearfix">
						<div class="btn_area">
							<button type="button" id="add_form_submit">결재신청</button>
							<button type="button" id="cancel">취소</button>
						</div>	

					</div> <!-- btn_wrap(e) -->

				</form>
			
			</div>	 <!--right_content_inner(e)-->
		
		</div> <!--right_content(e)-->
	
	</div> <!--contentbox(e)-->

</div> <!--#wrap(e)-->