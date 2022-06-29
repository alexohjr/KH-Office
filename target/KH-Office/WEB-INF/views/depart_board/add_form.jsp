<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div id="contentTotalDiv">
	<div id="contentTable">
		<span id="contentTitle">부서게시판</span><span id="contentDepartLine">|</span>
		<span id="contentDepartName"> ${depart_name}</span>
	</div>
	<div id="contentTextDiv">
		<form action="${pageContext.request.contextPath}/depart_board" method="post" enctype="multipart/form-data">
			<table class="addFormcontentStyle">
				<tr>
					<td class="AlignText">제 목</td>
					<td colspan=2><input type="text" name="title" class="title"></td>
				</tr>
				<tr>
					<td class="AlignText">파일첨부</td>
					<td colspan=2 class="file_input"><input type="file" name="file"></td>
				</tr>
				<tr>
					<td colspan=3>
					<textarea name="content" id="content" class="content"></textarea></td>
				</tr>
			</table>
			<div>
				<table id="submitCancelWriteTb">
					<tr>
						<td colspan=3 align="center">
						<input type="button" value="취소" class="submitCancelBtn" OnClick="javascript:location.href='${pageContext.request.contextPath}/depart_board'">
							<input type="submit" value="글쓰기" class="submitWriteBtn"></td>
					</tr>
				</table>
			</div>

		</form>
	</div>
</div>