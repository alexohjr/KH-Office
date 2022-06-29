<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div id="contentTotalDiv">
	<div id="tabledivstyle">

		<div>
			<table id="contentTable">
				<tr>
					<td><span id="contentTitle">${board.title}</span> <span
						id="contentDepartName">| 익명게시판</span></td>

					<td id="contentRegDate"><fmt:formatDate value="${board.reg_date}" pattern="yyyy-MM-dd HH:mm" /></td>
				</tr>
			</table>
		</div>
		<div class="board-box-line"></div>

		<div id="contentName">
			<table id="contentTable">
				<tr>
					<td>익명</td>
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

					<td>
						<input type="button" value="목록" class="inputSubmitBtn" OnClick="window.location='${pageContext.request.contextPath}/anony_board?pageNum=${pageNum}&search_type=${search_type}&keyword=${keyword}'"></td>

				</tr>
			</table>
		</div>
	</div>




	<div class="commentListArea">

		<div id="commentInputArea">
			<form action="${pageContext.request.contextPath}/anony_comment" method="post" id="commentInsertForm" style="width: 950px; margin: 0 auto;">
				<table id="commentInsertTable">
					<tr id="commentInsertTableTr">
						<td id="commentInsertTableTd">
							<div id="commentInsertDiv">
								<textarea id="commentInsertTextarea" name="content" placeholder="댓글을 작성해주세요"></textarea>
							</div>
						</td>

						<td id="commentInsertBtnTd">
							<div id="commentInsertBtnDiv">
								<input type="hidden" id="aboard_no" name="aboard_no" value="${board.aboard_no }" />
								<input type="submit" id="commentInsertBtn" value="등록" />
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>





		<div class="appendArea">
			<c:forEach var="comment" items="${commentlist}">
				<form action="${pageContext.request.contextPath}/anony_comment/${comment.acomment_no}" method="post" class="commentForm">

					<div class="thumbnailDiv">
						<p>
							<img src="${pageContext.request.contextPath}/resources/icon/noimage.jpg"/>
						</p>
					</div>

					<div class="commentListDiv">
						<table class="commentMemberList">
							<tr>
								<td class="commentMemberInfoP">
									<p>익명</p>
								</td>

								<td>
									<p class="commentRegdateP">${comment.reg_date}</p>
								</td>

							</tr>
						</table>

						<table class="commentContentList">
							<tr>
								<td class="commentContentArea" data-cmno="${comment.acomment_no }">
									<p class="commentContentSpan" data-cmno="${comment.acomment_no }">${comment.content}</p>
								</td>
							</tr>
						</table>

					</div>
				</form>
				<div class="comment-box-line"></div>
			</c:forEach>
		</div>

		<div>
		
			<span id="getMoreCommentBtn"></span>
			<input type="hidden" data-viewNum="2" id="viewNum" />
			<input type="hidden" id="aboard_no" value="${board.aboard_no}" />
		</div>



	</div>



</div>