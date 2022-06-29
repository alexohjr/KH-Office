<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="add_form_div">
    <h2>자산 등록</h2>
    <form action="${pageContext.request.contextPath}/holding_assets" method="post" id="add_form_submit">
        <table>
            <tbody>
                <tr>
                    <td>자산명</td>
                    <td><input class="name" type="text" name="name" /></td>
                </tr>
            </tbody>
        </table>
        
        <table>
            <tbody>
            	<tr>
                    <td>자산담당자</td>
                </tr>
                <tr>
                    <td class="member_select_area">
                        <tiles:insertDefinition name="department_selectbox"></tiles:insertDefinition>
                    </td>
				</tr>
				<tr>
				    <td>
                        <div class="added_member_area">
                            <span>사원을 클릭하면 이곳에 추가됩니다.</span>
                        </div>
                    </td>
				</tr>
            </tbody>
        </table>

        <div class="holding_assets_btn_area">
        
            <div class="added_input_area"></div>
            
            <input class="pageNum" type="hidden" value="${pageNum }">
            <input class="keyword" type="hidden" value="${keyword }">
            <input type="submit" value="등록" id="submitBtn">
            <input type="button" value="취소" id="add_form_cancle">
        </div>
    </form>
</div>
