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
					<td> <span id="contentTitle">${board.title}</span> <span id="contentDepartName">| 공지사항</span></td>
					
					<td id="contentRegDate"><fmt:formatDate value="${board.reg_date}" pattern="yyyy-MM-dd HH:mm" /></td>
				</tr>
			</table>
		</div>
		<div class="board-box-line"></div>

		<div id="contentName">
			<table id="contentTable">
				<tr>
					<td>관리자</td>
					<td id="contentFile">첨부파일 
					<a href="${pageContext.request.contextPath}/commons/download/notice_board/${board.nboard_no }">${board.file_path}</a></td>
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
				<td class="viewCountStyle">조회수 ${board.view_count}회</td>
			</tr>
		</table>
	</div>
	
	<div class="board-box-line"></div>

	<div id="ListSubmitBtn">
		<table id="ListSubmitBtnTable">
			<tr>
			<c:if test="${!empty session_adminID}">
				<td>
					<form action="${pageContext.request.contextPath}/notice_board/${board.nboard_no}?type=delete" method="post" id="deleteForm">
						<input type="submit" value="삭제" class="deleteBtn">
					</form>
				</td>
				<td><input type="button" value="수정" class="inputSubmitBtn" onclick="javascript:location.href='${pageContext.request.contextPath}/notice_board/form/${board.nboard_no}'"></td>
			</c:if>
				<td><input type="button" value="목록" class="inputSubmitBtn" OnClick="window.location='${pageContext.request.contextPath}/notice_board?pageNum=${pageNum}&search_type=${search_type}&keyword=${keyword}'"></td>
				
			</tr>
		</table>
	</div>
</div>
</div>