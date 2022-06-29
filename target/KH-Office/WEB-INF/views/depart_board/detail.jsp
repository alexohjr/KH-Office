<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<input type="hidden" id="loginMember" value="${member_no}">
<div id="contentTotalDiv">
	<div id="tabledivstyle">

		<div>
			<table id="contentTable">
				<tr>
					<td> <span id="contentTitle">${board.title}</span> <span id="contentDepartName">| ${depart_name}</span></td>
					
					<td id="contentRegDate"><fmt:formatDate value="${board.reg_date}" pattern="yyyy-MM-dd HH:mm" /></td>
				</tr>
			</table>
		</div>
		<div class="board-box-line"></div>

		<div id="contentName">
			<table id="contentTable">
				<tr>
					<td id="namefont"><img src="${pageContext.request.contextPath}/${board.thumb_path}" class="thumbpathImgSize"> ${board.name} ${board.position}</td>
					<td id="contentFile">첨부파일 <a href="${pageContext.request.contextPath}/commons/download/depart_board/${board.dboard_no }">${board.file_path}</a></td>
				</tr>
			</table>
		</div>


		<div id="contentCt">
			<table>
				<tr>
					<td><div>${board.content}</div></td>
				</tr>
			</table>
		</div>
	</div>

<div id="contentViewList">
	<div id="contentView">
		<table>
			<tr>
				<td class="commentCountStyle">댓글 ${comment_count}개</td>
				<td class="viewCountStyle">조회수 ${board.view_count}회</td>
			</tr>
		</table>
	</div>


	<div id="ListSubmitBtn">
		<table id="ListSubmitBtnTable">
			<tr>
		<c:if test="${board.member_no == session_memberNo}">
				<td class="ListSubmitBtnTd">
					<form action="${pageContext.request.contextPath}/depart_board/${board.dboard_no}?type=delete"
						method="post" id="deleteForm">
						<input type="submit" value="삭제" class="deleteBtn">
					</form>
				</td>
				
				<td class="ListSubmitBtnTd"><input type="button" value="수정" class="inputSubmitBtn" onclick="javascript:location.href='${pageContext.request.contextPath}/depart_board/form/${board.dboard_no}'"></td>
		</c:if>
					<td class="ListSubmitBtnTd"><input type="button" value="목록" class="inputSubmitBtn" OnClick="window.location='${pageContext.request.contextPath}/depart_board?pageNum=${pageNum}&search_type=${search_type}&keyword=${keyword}'"></td>
	
			</tr>
		</table>
	</div>
</div>







	<div class="commentListArea">

		<div id="commentInputArea">
			<form action="${pageContext.request.contextPath}/depart_comment" method="post" id="commentInsertForm" style="width: 950px; margin: 0 auto;">
				<table id="commentInsertTable">
					<tr id="commentInsertTableTr">
						<td id="commentInsertTableTd">
							<div id="commentInsertDiv">
								<textarea id="commentInsertTextarea" name="content"
									placeholder="댓글을 작성해주세요"></textarea>
							</div>
						</td>

						<td id="commentInsertBtnTd">
							<div id="commentInsertBtnDiv">
								<input type="hidden" id="dboard_no" name="dboard_no" value="${board.dboard_no }" />
								<input type="submit" id="commentInsertBtn" value="등록" />
								<input type="hidden" id="loginMember" name="member_no" value="${member_no }" />
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>


	<div class="appendArea">
			<c:forEach var="comment" items="${commentlist}">
	            <form action="${pageContext.request.contextPath}/depart_comment/${comment.dcomment_no}" method="post" class="commentForm">
	
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
	                                 <input type="button" value="수정" class="commentUpdateBtn" data-cmno="${comment.dcomment_no }" />
	                             </span> 
	                             
	                             <span>
	                                 <input type="submit" value="| 삭제" class="commentDeleteBtn" data-cmno="${comment.dcomment_no }" />
	                                 <input type="button" value="수정취소" class="commentCancelBtn" data-cmno="${comment.dcomment_no }" />
	                           </span> 
	                        
	                           <input type="hidden" name="type" class="btnType" value="delete" data-cmno="${comment.dcomment_no }" />
	                             <input type="hidden" id="dboard_no" name="dboard_no" value="${board.dboard_no}" />
									<input type="hidden" class="loginMember" name="member_no" value="${member_no }" />
	
	                        </td>
	                     </tr>
	                  </table>
	                  
	                  <table class="commentContentList">
	                     <tr>
	                        <td class="commentContentArea" data-cmno="${comment.dcomment_no }">
	                           <p class="commentContentSpan" data-cmno="${comment.dcomment_no }">${comment.content}</p>
	                        </td>
	                        </tr>
	                  </table>
	                  <table>
	                     <tr>
	                        <td>
	                           <textarea name="content" class="updateContentForm" data-cmno="${comment.dcomment_no }"></textarea>
	                        </td>
	                        <td class="commentContentSub">
	                           <input type="submit"  class="commentUpdateSubmitBtn" value="수정" data-cmno="${comment.dcomment_no }" />
	                           
	                        </td>
	
	                     </tr>
	                  </table>
	               </div>
	            </form>
	            <div class="comment-box-line"></div>
	         </c:forEach>
	      </div>
	
		<div>
			<input type="hidden" class="loginMember" name="member_no" value="${member_no }" />
			
			
			<span id="getMoreCommentBtn"></span>
			<input type="hidden" data-viewNum="2" id="viewNum" />
			<input type="hidden" id="dboard_no" value="${board.dboard_no}" />
		</div>



</div>



</div>
