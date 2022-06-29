<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="projectListJsp">

<div id="addProjectBtnP">

<span id="projectCount">${projectCount }개의 프로젝트가 있습니다.</span>
<button id="addProjectBtn">프로젝트 생성</button>
</div>

<div class="projectAppendDiv"> 

<div class="projectListDiv">

<c:forEach var="project" items="${project }">
	<div data-project-no="${project.project_no }" class="projectCell">
		<span class="project_no_info">No.${project.project_no }</span>
		<span class="project_status_info ${project.status }"><span>${project.status }</span></span>
		<div class="projectCellInfo">
		<ul>
			<li>
				<input type="hidden" class="project_no" value="${project.project_no }" />
			</li>
			 
			<li> 
				<span class="projectListProjectName ${project.status }">${project.name }</span>  
			</li>
			  
			<li>
				등록 된 TASK<span class="taskCountSpan">〔${project.taskCount }〕</span>
			</li>   
			
			<li>
			<img src="${pageContext.request.contextPath}/${project.leaderThumb }"/>
			${project.leaderPosition } ${project.leaderName } 외 ${project.memberCount }명
			</li>
			  
			<li> 
				${project.start_date }&nbsp; ~ &nbsp;${project.end_date }
			</li>
			
			<li>
				<div class="progressBarOuter ${project.status }">
					<div class="progressBarInner" data-width="${project.progress }">
					</div>
				</div>
			</li>
		</ul>
		</div>  
	</div>   
</c:forEach>   
</div>   <!-- projectListDiv -->

</div>  <!-- projectAppendDiv -->

<div id="getProjectListBtnDiv">
<c:if test="${projectCount > 8 }">
<span id="getProjectListTextSpan">* 목록을 더 불러오시려면  +버튼을 클릭해주세요</span>
<span class="getProjectListBtn"></span>
</c:if>
<input type="hidden" id="viewNum" data-viewNum="2"/>
</div>

</div>
