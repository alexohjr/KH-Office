<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> 
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />


<div class="container">
     <h2 class="schedule_h2">개인 일정 추가</h2>
     
     <p>개인 일정을 추가하실 수 있습니다.</p>
      
        <form id="schedule_add_form" class="form-inline" action="${pageContext.request.contextPath}/schedule" method="post">
        <!-- action="/administer/ScheduleManage.do" -->
          <div class="form-group">
            <label>일정명</label>
            <input type="text" class="form-control" id="name" placeholder="행사명을 입력하세요" name="subject">
          </div>          
          <div class="form-group">
            <label>시작일:</label>
            <input type="text" class="form-control" id="startDate" placeholder="여기를 클릭하세요" name="start_date">
          </div>
          <div class="form-group">
            <label>종료일:</label>
            <input type="text" class="form-control" id="endDate" placeholder="여기를 클릭하세요" name="end_date">
          </div><br>
          <!-- <input type="submit" class="form-control" value="제출"/> -->
          <div align="center"><input type="submit" class="submitBtn" value="일정추가" id="submitBtn" /></div>
        </form>
</div>