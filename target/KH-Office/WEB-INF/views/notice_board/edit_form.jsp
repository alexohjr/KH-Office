<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<div id="contentTotalDiv">
	<div id="contentTable">
		<span id="contentTitle">공지게시판</span><span id="contentDepartLine">|</span>
	</div>
	<div id="contentTextDiv">


		<form
			action="${pageContext.request.contextPath}/notice_board/${board.nboard_no}?type=edit"
			method="post" enctype="multipart/form-data">

			<table class="addFormcontentStyle">
				<tr>
					<td class="AlignText">제 목</td>
					<td colspan=2><input type="text" name="title"
						value="${board.title}" class="title"></td>
				</tr>
				<tr>
					<td class="AlignText">파일첨부</td>
					<td class="file_input"><input type="file"
						name="file" value="${board.file_path}"></td>
					<td class="IsTopIntup"><input type="checkbox" name="is_top">상단고정</td>
				</tr>



				<tr>
					<td colspan=3><textarea name="content" id="content"
							class="content">${board.content}</textarea></td>
				</tr>
			</table>

			<div>
				<table id="submitCancelWriteTb">
					<tr>
						<td colspan=3 align="center"><input type="button" class="submitCancelBtn" value="취소"
							OnClick="window.location='${pageContext.request.contextPath}/notice_board/${board.nboard_no}?pageNum=${pageNum}&search_type=${search_type}&keyword=${keyword}'">
							<input type="submit" value="수정" id="submitModifyBtn">
					</tr>
				</table>
			</div>

		</form>
	</div>
</div>