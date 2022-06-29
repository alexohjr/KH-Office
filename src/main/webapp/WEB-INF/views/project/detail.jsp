<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="projectDetailjsp">

<span id="projectAddFormHeader">${project.pName } </span>
<span class="projectStatusSpan ${project.status }"> ${project.status } </span>
<span id="projectDateSpan">(${project.start_date }&nbsp; ~ &nbsp;${project.end_date })</span>

<div id="projectDetailInfoArea">
<ul id="projectDetailInfoUl">
	<li>  
		<input type="hidden" id="project_no" value="${project.project_no }"/>
		<input type="hidden" id="loginMember" value="${loginMember }"/>
		<input type="hidden" id="projectStatus" name="projectStatus" value="${project.status }" />
	</li>
	 
	<li>
		<div id="projectOutlineDiv">
			${project.outline }
		</div>
	</li> 
	 
	<li id="projectLeaderLi">
		<span id="projectLeaderP">리더</span>
		<img class="leaderImg" src="${pageContext.request.contextPath}/${leader.thumb_path }" /> ${leader.position } ${leader.name }
		<input type="hidden" name="is_leader" value="${leader.member_no }"/>
		
		<p id="projectMemberP">팀원  ${projectMemberCount }명</p>
		
		<div id="projectMemberArea">
		<c:forEach var="projectMember" items="${projectMember }">
			<p>   
			<img class="projectMemberImg" src="${pageContext.request.contextPath}/${projectMember.thumb_path }" />
			${projectMember.position } ${projectMember.name }
			</p>  
		</c:forEach>
		</div>
	
	</li>
	
</ul> 
<div id="taskArea">  
	<div id="taskTotalInfo">
	<span id="taskTextSpan">TASK</span><span class="taskTagIcon"></span>
	<span id="taskCountText">총 <span id="allTaskCount"></span>  &emsp;담당 <span id="myTaskCount"></span>  &emsp;완료 <span id="completeTaskCount"></span></span>
	<input type="button" value="태스크 생성" id="addTaskBtn"/>
</div>
 
<div id="allTaskArea">

<div id="taskListArea">
		<c:forEach var="task" items="${taskList }">
			<div class="taskCell">
			<ul>
				<li> <span class="taskNameSpan">${task.name }</span> <span class="taskDeadlineSpan">${task.deadline }</span> 
					<input type="hidden" id="task_no" value="${task.task_no }"/>
				</li> 
				
				<li>   
					<c:if test="${!empty task.file_path}">
					업로드 된 파일 :: ${task.file_path }
					<input type="button" value="다운로드" class="downloadTaskFile" data-tno="${task.task_no }"/>
					</c:if> 
					  
					<c:if test="${empty task.file_path}">
					업로드 된 파일이 없습니다
					</c:if>
				<li>
				
				<div class="taskBarOuter">
					<div class="taskBarInner" data-width="${task.status }" data-task-no="${task.task_no }">
					</div>
				</div>
				
				</li>
				<li>
				담당인원〔${fn:length(task.memberList)}〕 
				
				<input type="button" value="전체보기 ▼" class="showTaskMemberBtn" data-task-no="${task.task_no }"/>
				<!-- <span class="showTaskMemberBtn"><span class="arrow-down">Down</span></span> -->
					<c:forEach var="member" items="${task.memberList }">
					<c:if test="${loginMember == member.member_no }">
					<div class="radioCell">
						<label class="container"><span class="taskStatusText">대기</span><input type="radio" class="taskStatus w" name="${task.task_no }" value="0" data-task-no="${task.task_no }" /><span class="checkmark"></span></label>
						<label class="container"><span class="taskStatusText">진행중</span><input type="radio" class="taskStatus p" name="${task.task_no }" value="1" data-task-no="${task.task_no }"/><span class="checkmark"></span></label>
						<label class="container"><span class="taskStatusText">완료</span><input type="radio" class="taskStatus c" name="${task.task_no }" value="2" data-task-no="${task.task_no }"/><span class="checkmark"></span></label>
						<input type="hidden" class="status" value="${task.status }" data-task-no="${task.task_no }"/>
					</div>
					</c:if>
					</c:forEach>	
				
				</li>
					<li>
					<div class="taskMemberArea" data-task-no="${task.task_no }">
					<c:forEach var="member" items="${task.memberList }">
					 <div class="taskMemberAreaDiv"> <img class="taskMemberImg" src="${pageContext.request.contextPath}/${member.thumb_path }"/> ${member.position } ${member.name }</div>
					 <input type="hidden" class="pmember_no" value="${member.member_no }" data-no="${member.member_no }"/>
					</c:forEach>
					</div>  
					</li> 
			</ul>
			</div>  
		</c:forEach>
		</div> 
	
</div>
</div>
</div>

<div id="progressBarandBtnArea"> 
<div class="projectBarOuter">
			<div class="projectBarInner" data-width="${projectProgress }">
				<span id="projectBarText"></span>
			</div>
</div>

<div id="projectBtnDiv">
<c:if test="${leader.member_no == loginMember }">
	<form action="${pageContext.request.contextPath}/project/${project.project_no }" method="post" id="projectDeleteForm">
	 
	
	<input type="submit" value="삭제" id="projectDeleteBtn" class="deleteBtn"/>
	<input type="hidden" value="delete" name="type"/>
	<input type="button" value="수정" id="projectEditBtn"  class="updateBtn"/>
	<input type="button" value="목록" class="goProjectListBtn goListBtn"/>
	</form>
</c:if> 

<c:if test="${leader.member_no != loginMember }">
	<input type="button" value="목록" class="goProjectListBtn goListBtn"/>
</c:if>
</div>
</div>	

<div id="commentCountSpan">
&nbsp;&nbsp;댓글 ${projectCommentCount } 
</div>

<div id="projectCommentTotalDiv"> 
<!-- 프로젝트 코멘트 영역 --> 
<div id="commentInputArea">
    
	<form action="${pageContext.request.contextPath}/project_comment" method="post" class="newCommentForm">
		<table id="commentInsertTable">
			<tbody>
				<tr id="commentInsertTableTr">
					<td id="commentInsertTableTd">
						<div id="commentInsertDiv">
							<textarea id="commentInsertTextarea" name="content" placeholder="댓글을 작성해주세요."></textarea>						
						</div>
					</td>
					
					<td id="commentInsertBtnTd">
						<div id="commentInsertBtnDiv">
							<input type="submit" id="commentInsertBtn" value="등록" />
							<input type="hidden" id="project_no" name="project_no" value="${project.project_no }"/>
	    	           		<input type="hidden" id="loginMember" name="member_no" value="${loginMember }"/>
						</div>
					</td>
         		</tr>
			</tbody>
		</table>
	</form>  
<div id="insertCommentBoxLine"></div>
</div>  
<!-- 프로젝트 코멘트 영역 -->


<div class="commentListArea">
<div class="appendArea">
         <c:forEach var="comment" items="${projectCommentList}">
            <form action="${pageContext.request.contextPath}/project_comment/${comment.pcomment_no}" method="post" class="commentForm">

               <div class="thumbnailDiv">
                        <p>
                           <img src="${pageContext.request.contextPath}/${comment.thumb_path}" />
                        </p>
               </div> 
               
               <div class="commentListDiv">
                  <table class="commentMemberList">
                     <tr>
                        <td>
                           <p class="commentMemberInfoP">${comment.position} ${comment.name}</p>
                        </td> 

                        <td>
                           <p class="commentRegdateP">${comment.reg_date}</p>
                        </td>
						
                        <td class="commentBtnArea" data-mno="${comment.member_no}">

	                        <span>
	                              <input type="button" value="수정" class="commentUpdateBtn" data-cmno="${comment.pcomment_no }" />
	                       	</span> 
	                       	
	                       	<span>
	                              <input type="submit" value="| 삭제" class="commentDeleteBtn" data-cmno="${comment.pcomment_no }" />
	                              <input type="button" value="수정취소" class="commentCancelBtn" data-cmno="${comment.pcomment_no }" />
	                        </span> 
                        
                        	<input type="hidden" name="type" class="btnType" value="delete" data-cmno="${comment.pcomment_no }" />
                       		<input type="hidden" class="loginMember" name="member_no" value="${loginMember }" />
                           	<input type="hidden" class="project_no" name="project_no" value="${project.project_no }"/>

                        </td>
                     </tr>
                  </table>
                  
                  <table class="commentContentList">
                  	<tr>
                        <td class="commentContentArea" data-cmno="${comment.pcomment_no }">
                           <p class="commentContentSpan" data-cmno="${comment.pcomment_no }">${comment.content}</p>
                        </td>
                  	</tr>
                  </table>
                  
                  <table>
                     <tr>
                        <td>
                           <textarea name="content" class="updateContentForm" data-cmno="${comment.pcomment_no }"></textarea>
                        </td>
                        <td class="commentContentSub">
                           <input type="submit"  class="commentUpdateSubmitBtn" value="수정" data-cmno="${comment.pcomment_no }" />
                           
                        </td> 

                     </tr>
                  </table>
               </div>
            </form>
            <div class="comment-box-line"></div>
         </c:forEach>
      </div>
</div>


<c:if test="${empty projectCommentList }">
<div class="getCommentListBtnDiv">
<span class="getCommentListTextSpan">등록된 댓글이 없습니다.</span>
</div>
</c:if>

<c:if test="${projectCommentCount > 5 }">
<div class="getCommentListBtnDiv">
<span class="getCommentListTextSpan">* 댓글을 더 불러오시려면 +버튼을 클릭해주세요</span>
<span class="getMoreCommentBtn"></span>
<input type="hidden" id="viewNum" data-viewNum="2"/>
</div>
</c:if>

</div>

</div>

