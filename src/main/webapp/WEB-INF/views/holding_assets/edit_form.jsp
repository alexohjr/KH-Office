<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="edit_form_div">
    <h2>글 수정</h2>
    <form action="${pageContext.request.contextPath}/holding_assets/${holdingAssetsMemberDTO.hAssets_no}?type=edit"
        method="post" id="holding_assets_edit_form">
        <table>
        	<tbody>
        		<tr>
	                <td>자산명 </td>
	                <td><input class="name" type="text" name="name" value="${holdingAssetsMemberDTO.h_name}"></td>
	            </tr>
        	</tbody>
            
        </table>
        <table>
        	<tbody>
        		<tr>
	                <td>자산담당자<input type="hidden" value="${holdingAssetsMemberDTO.member_no}"></td>
				</tr>
				<tr>
					<td class="member_select_area">
	                    <tiles:insertDefinition name="department_selectbox"></tiles:insertDefinition>
	                </td>
				</tr>
				<tr>
					<td>
	                    <div class="added_member_area">
	                    	<li class="member_li">
	                    		<span><img src="${pageContext.request.contextPath }/${holdingAssetsMemberDTO.thumb_path }" alt="${holdingAssetsMemberDTO.position } ${holdingAssetsMemberDTO.m_name }님의 사진"></span>
	                        	<span>${holdingAssetsMemberDTO.m_name} ${holdingAssetsMemberDTO.position}
	                            	<input type="hidden" name="member_no" value="${holdingAssetsMemberDTO.member_no }">
	                        	</span>
	                    	</li>
	                    </div>
	                </td>
				</tr>
        	</tbody>
        </table>
        
        <div class="holding_assets_btn_area">
        
        	<div class="added_input_area"></div>
        	
	        <input class="pageNum" type="hidden" value="${pageNum }">
	        <input class="keyword" type="hidden" value="${keyword }">
	        <input type="submit" value="수정" id="submitBtn">
	        <input type="button" value="취소" id="edit_form_cancle">
        </div>
        
    </form>
</div>