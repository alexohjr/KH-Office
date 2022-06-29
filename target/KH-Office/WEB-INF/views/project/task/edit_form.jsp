<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="taskEditFormJsp">
<span id="taskEditFormJspHeader">${taskDTO.name }</span><span id="editCautionSpan"> (TASK 수정)</span>
<form action="${pageContext.request.contextPath}/task/${taskDTO.task_no}" method="post" enctype="multipart/form-data" id="taskEditForm">
<div id="taskEditFormDiv">
<ul id="taskEditFormUl">

	<li>
		<div id="taskNameDiv">
			<span class="taskAllInputSpan">
				TASK 명 
			</span>
		
			<div>
				<input type="text" name="name" value="${taskDTO.name }" class="taskName"/>
			</div>
		</div>
		
		<div id="taskDeadlineDiv">
			<span class="taskAllInputSpan">
				마감일
			</span>
		
			<div>
				<input type="date" name="deadline" value="${taskDTO.deadline }" class="taskDeadline"/>
			</div>
		</div>
		
		<div id="taskContentDiv">
			<span class="taskAllInputSpan">
				설명
			</span>
			
			<div class="taskContentInput">
				<textarea name="content" class="taskContent">${taskDTO.content }</textarea>
			</div>
		
		</div>
		
		<div id="taskFileDiv">
			
			<span class="taskAllInputSpan">
			TASK 파일&nbsp; <button type="button" id="taskFileUploadBtn">파일선택</button>
			</span>
			
			<div id="taskFileDiv2">
				<input type="file" id="real-file" name="file" hidden="hidden" />
				<span id="taskFileUploadText">파일을 선택해주세요.</span>
			</div>
			
			<c:if test="${!empty taskDTO.file_path }">
				<span class="taskAllInputSpan">기존 파일</span>	
				${taskDTO.file_path }
			</c:if>
		
			<c:if test="${empty taskDTO.file_path }">
				업로드 된 파일이 없습니다.
			</c:if> 
		 
		</div> 
		
		<span class="taskAllInputSpan">TASK 상태</span>
		<select name="status" id="status">
			<option value="0">대기</option>
			<option value="1">진행중</option>
			<option value="2">완료</option>
		</select>
		<input type="hidden" id="taskStatus" value="${taskDTO.status }"/>
		
		<input type="hidden" id="project_no" name="project_no" value="${project_no }"/>
		<input type="hidden" name="type" value="edit"/>
		<input type="hidden" id="task_no" name="task_no" value="${taskDTO.task_no }"/>
		<input type="hidden" id="loginMember" name="loginMember" value="${loginMember }"/>
		<input type="hidden" name="projectStatus" value="${projectStatus }" />
	</li> 
	
	<li>
		<div id="selectTaskMember">
			<span class="taskAllInputSpan">담당자 선택</span>
			<input type="button" value="담당자 선택" id="showSelectBoxBtn" data-btn="0"/>
			
			<div id="projectMemberHiddenDiv">
			<!-- 프로젝트 전체 멤버들 -->
			<c:forEach var="member" items="${projectMemberList }">
				<ul>
					<li>
						<input type="hidden" class="hiddenMember" data-no="${member.member_no }" data-thumb="${member.thumb_path }" data-position="${member.position }" data-name="${member.name }"/>
					</li>
				</ul>
			</c:forEach>
			
			<!-- 기존 태스크 멤버들 -->
			<c:forEach var="taskMember" items="${taskDTO.memberList }">
				<ul>
					<li>
						<input type="hidden" class="taskOGMember" data-no="${taskMember.member_no }"/>
					</li>
				</ul>
			</c:forEach>
			
			</div>
		
			<!-- 멤버 선택 박스 영역 -->
			<div id="projectMemberSelectArea">
		
		
			</div>
		
		</div>
		
	</li>
	
	<li>
			<div id="selectedTaskMemberDiv">
				<span id="selectedTaskMemberSpan">담당자를 선택하면 이곳에 추가됩니다.</span>
			<div id="selectedTaskMember">
		
			<!-- 선택된 멤버 보여지는 영역  -->
			<div id="selectedProjectMemberArea">
			</div>
			
			<!-- input hidden 영역  --> 
			<div id="selectedProjectMemberHiddenArea">
			</div>
			</div>
			
			
		</div>
		
		
	</li>

	<li>
		<%-- 파일 첨부
		<input type="file" name="file" accept="application/zip" value="${taskDTO.file_path }"/>
		<input type="hidden" name="ogFile_path" value="${taskDTO.file_path }"/> --%>
	</li>
	  
	<li id="taskEditFormBtnArea">
		<input type="submit" value="수정" id="taskEditBtn"/>
		<input type="button" value="취소" id="editCancelBtn"/>
	</li> 

</ul>
</div>

</form>

</div><!-- <div id="taskEditFormJsp"> -->