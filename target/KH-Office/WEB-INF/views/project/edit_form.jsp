<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<input type="hidden" value="${session_memberNo }" id="session_id" />
<div id="projectEditFormJsp">
 
<span id="projectEditFormJspHeader">${project.pName }</span><span id="editCautionSpan"> (프로젝트 수정)</span>
<span class="projectEditFormStatusSpan ${project.status }">${project.status }</span>
 
<form action="${pageContext.request.contextPath}/project/${project.project_no }" method="post" id="editForm">

<div id="projectEditFormDiv">

<ul id="projectEditFormUl">
	<li>
		
		<div id="projectLeaderDiv" class="projectAllInputDiv">
			<span class="projectAllInputSpan">
				프로젝트 리더 
			</span>
			
		<div>
			<img src="${pageContext.request.contextPath}/${leader.thumb_path }"/>
			<span id="projectLeaderInfo">${leader.position } ${leader.name }</span>
			<input type="hidden" name="is_leader" value="${leader.member_no }"/>
		</div>
		</div>
		
		<div id="projectNameDiv"  class="projectAllInputDiv">
			<span class="projectAllInputSpan">
				프로젝트 명
			</span> 
		<div>
			<input type="text" name="name" value="${project.pName }" class="projectName"/>
		</div>
		</div>
		
		<div id="projectDateDiv"  class="projectAllInputDiv">
			<span class="projectAllInputSpan">
				프로젝트 기간
			</span>
		<div>
			<p><span id="projectDateInfo">시작</span> <span>종료</span></p>
			<input type="date" name="start_date" class="start_date" value="${project.start_date }"/>
		<input type="date" name="end_date" class="end_date" value="${project.end_date }"/>
		</div> 
		</div>
		
		<div id="projectOutlineDiv"  class="projectAllInputDiv">
			<span class="projectAllInputSpan">
				프로젝트 개요
			</span>
		<div>
			<textarea name="outline" class="projectOutline">${project.outline }</textarea>
		</div>
		</div>
		
		<div id="projectStatusDiv"  class="projectAllInputDiv">
			<span class="projectAllInputSpan">
				프로젝트 상태
			</span>
		<div>
			<select name="status" id="status">
				<option value="0">진행중</option>
				<option value="1">완료</option>
				<option value="2">보류</option>
				<option value="3">중단</option>
			</select>
			<input type="hidden" id="projectStatus" value="${project.status }"/>
		</div> 
		</div>
		
		<input type="hidden" name="type" value="edit"/>
		<input type="hidden" id="project_no" name="project_no" value="${project.project_no }"/>
		<input type="hidden" id="loginMember" value="${loginMember }"/>
	</li>
	
	<li>
		<div id="projectMemberSelectArea">
		<span class="projectAllInputSpan">프로젝트 팀원</span>
		<input type="button" value="팀원선택" id="addProjectMemberBtn" data-btn="hideMemberBtn"/>
	
			<div id="projectMemberSelect" class="member_select_outer">
				<tiles:insertDefinition name="department_selectbox"></tiles:insertDefinition>
			</div>
		</div>
	
	</li>
	
	<li>
		
		<div id="selectedProjectMemberDiv">
		<span class="projectAllInputSpan">팀원을 선택하면 이곳에 추가됩니다.</span>
		<div id="selectedProjectMemberArea">

			<div class="added_member_area">
			</div>
			<div class="added_input_area"></div>
		</div>
		</div>
		  
	</li> 
	
	
	<li>
		<input type="hidden" id="loadMemberStatus" value="0">
		<c:forEach var="pMembers" items="${projectMember }">
			<div class="projectMemberDiv">
			<p>
				<input type="hidden" class="projectMemberNo" name="projectMemberNo" value="${pMembers.member_no }"/>
			</p>
			</div>
		</c:forEach>
	</li>
	
	<li id="projectEditFormBtnArea">
		<input type="submit" value="수정" id="updateBtn"/>
		<input type="button" value="취소" id="updateCancelBtn"/>	
	</li>
	  
	
	

	</ul>
		
		
	
	


</div>
</form>
</div>