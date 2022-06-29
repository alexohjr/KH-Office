<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="taskAddFormJsp">
<p id="taskAddFormJspHeader">TASK 생성</p>
<form action="${pageContext.request.contextPath}/task" method="post" enctype="multipart/form-data" id="taskAddForm">
<div id="taskAddFormDiv">
<ul id="taskAddFormUl">

	<li> 
		<div id="taskNameDiv">
		<span class="taskAllInputSpan">
		TASK 명
		</span>
		
		<div>
		<input type="text" name="name" class="taskName"/>
		</div>
		</div>
		
		<div id="taskDeadlineDiv"> 
		<span class="taskAllInputSpan">마감일</span>
		<div>
		<input type="date" name="deadline" class="taskDeadline"/>
		</div>
		</div>
		
		<div id="taskContentDiv">
		<span class="taskAllInputSpan">
		설명 
		</span>
	
		<div class="taskContentInput">
		<textarea name="content" class="taskContent"></textarea>
		<input type="hidden" name="project_no" value="${project_no }" class="project_no"/>
		<input type="hidden" name="loginMember" value="${loginMember }"/>
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
		</div>
	</li>
	
	   
	<li>
		<div id="selectTaskMember">
		<span class="taskAllInputSpan">담당자 선택</span>
		<input type="button" value="담당자 선택" id="showSelectBoxBtn" data-btn="0"/>
		
		 
		
		<div id="projectMemberHiddenDiv">
			<c:forEach var="member" items="${projectMember }">
				<ul>
					<li>
						<input type="hidden" class="hiddenMember" data-no="${member.member_no }" data-thumb="${member.thumb_path }" data-position="${member.position }" data-name="${member.name }"/>
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
		</div>
		
		<!-- input hidden 영역  -->
		<div id="selectedProjectMemberHiddenArea">
		  
		</div>
		</div>
	</li>
	
	<li>
		<!-- 파일 첨부
		<input type="file" name="file" accept="application/zip"/> -->
		
		
		
	</li> 
	   
	<li id="taskAddFormBtnArea">
		<input type="submit" value="생성" class="addTaskBtn"/>
		<input type="button" value="취소" class="cancelBtn" />
	</li> 

</ul>
</div>

</form>
</div>