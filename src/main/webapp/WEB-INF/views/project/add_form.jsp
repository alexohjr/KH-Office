<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<input type="hidden" value="${session_memberNo }" id="session_id" />
<div id="projectAddFormJsp">
 
<p id="projectAddFormJspHeader">새로운 프로젝트</p>


<form action="${pageContext.request.contextPath}/project" method="post" id="addForm">

<div id="projectAddFormDiv">

<ul id="projectAddFormUl">

	<li>
		 
		<div id="projectLeaderDiv" class="projectAllInputDiv">
			<span class="projectAllInputSpan">
				프로젝트 리더 
			</span>
			
			<span id="projectLeaderCaution">*프로젝트 생성 한 사람이 리더입니다.</span>
		<div>
			<img src="${pageContext.request.contextPath}/${member.thumb_path }"/>
			<span id="projectLeaderInfo">${member.position } ${member.name }</span>
			<input type="hidden" name="is_leader" value="${member.member_no }"/>
		</div>
		</div>
		
		<div id="projectNameDiv"  class="projectAllInputDiv">
			<span class="projectAllInputSpan">
				프로젝트 명
			</span> 
		<div>
			<input type="text" name="name" class="projectName"/>
		</div>
		</div>
		
		<div id="projectDateDiv"  class="projectAllInputDiv">
			<span class="projectAllInputSpan">
				프로젝트 기간
			</span>
		<div>
			<p><span id="projectDateInfo">시작</span> <span>종료</span></p>
			<input type="date" name="start_date" class="start_date"/>
			<input type="date" name="end_date" class="end_date"/>
		</div> 
		</div>
		 
		<div id="projectOutlineDiv"  class="projectAllInputDiv">
			<span class="projectAllInputSpan">
				프로젝트 개요
			</span>
		<div>
			<textarea name="outline" class="projectOutline"></textarea>
		</div>
		</div> 
	</li>
	
	<!-- 팀원선택영역 -->
	<li>
		<div id="projectMemberSelectArea">
		<span class="projectAllInputSpan">프로젝트 팀원</span>
		<input type="button" value="팀원선택" id="addProjectMemberBtn" data-btn="hideMemberBtn"/>
	
		
		<div id="projectMemberSelect" class="member_select_outer">
			<tiles:insertDefinition name="department_selectbox"></tiles:insertDefinition>
		</div>
		</div>
	</li>
	<!--// 팀원선택영역 -->
	
	
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

	<li id="projectAddFormBtnArea">
		<input type="submit" value="생성" class="addProjectBtn"/>
		<input type="button" value="취소" class="addFormCancelBtn"/>	
	</li>
	
</ul> 
	 
	

</div>
  
</form> 
 
 
</div>
