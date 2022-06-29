<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="taskDetailJsp">
<span id="taskDetailHeader">${taskDTO.name }</span>
&emsp;<span id="taskDeadlineSpan">${taskDTO.deadline }</span>
<span class="taskStatusSpan ${taskDTO.status }"></span>
<form action="${pageContext.request.contextPath}/task/${taskDTO.task_no}" method="post" id="taskDetailForm" enctype="multipart/form-data">
<div id="taskDetailDiv">
<ul id="taskDetailUl">  

	<li>
		<input type="hidden" id="project_no" name="project_no" value="${project_no }"/>
		<input type="hidden" id="member_no" name="loginMember" value="${member_no }"/>
		<input type="hidden" id="task_no" value="${taskDTO.task_no }"/>
		<input type="hidden" name="type" value="delete"/>
		<input type="hidden" name="projectStatus" value="${projectStatus }" />
	</li>
	
	<li>
	
		<div class="taskAllInputDiv">
			<span class="taskAllInputSpan">
				TASK 설명		
			</span>
			
			<div id="taskContentDiv">
				${taskDTO.content }
			</div>
		</div> 
		
		<div class="taskAllInputDiv">
			<span class="taskAllInputSpan">
			TASK 파일 
			</span>
			<c:if test="${!empty taskDTO.file_path }">
			
			<div>
				${taskDTO.file_path }
				<input type="button" value="다운로드" id="downloadTaskFile" class="taskDetailBtns"/>
			</div>
			</c:if>
			 
			<c:if test="${empty taskDTO.file_path }">
			<div>
			업로드 된 파일이 없습니다.
			</div>
			</c:if> 
		</div>
	
	</li> 
	  
	<li>
		<div id="taskMemberDiv">
		<span class="taskAllInputSpan">담당자</span> 
		<div id="taskMemberShowDiv">
		
			<c:forEach var="member" items="${taskDTO.memberList }">
				<p>
					<img class="taskMemberImg" src="${pageContext.request.contextPath}/${member.thumb_path }"/>
					${member.position } ${member.name } 
				</p>
				
				<p> <input type="hidden" class="pmember_no" value="${member.member_no }"/></p> 
			</c:forEach>
	
		</div> 
		</div> 
	</li> 
	
	<li>
		
		
		
	</li>
		
	<li id="taskDetailBtnArea">
		<c:if test="${flag > 0 }">	  
		<input type="button" value="수정" id="taskEditBtn" class="taskDetailBtns"/>
		<input type="submit" value="삭제" id="deleteTaskBtn" class="taskDetailBtns"/>
		</c:if>
		<input type="button" value="뒤로" id="goProjectDetailBtn" class="taskDetailBtns"/>
	</li> 
	
</ul>
</div>
</form>
</div>