$(function() {
	// nicescroll 먹이기
   $("#taskMemberShowDiv").niceScroll({
		cursorcolor:"#283442",
		cursorwidth:"5px",
		cursordragontouch: true
	});
	   
	var project_no = $("#project_no").val();
	var member_no = $("#member_no").val();
	var task_no = $("#task_no").val();
	
	$("#goProjectDetailBtn").on("click", function() {
		location.href = utils.getContextPath() + "/project/"+project_no;
		/*location.href = utils.getContextPath() + "/project/"+project_no+"?member_no="+member_no;*/
	});
	
	$("#taskEditBtn").on("click", function() {
		location.href = utils.getContextPath() +"/task/form/"+task_no+"?project_no="+project_no; 
		/*location.href = utils.getContextPath() +"/task/form/"+task_no+"?project_no="+project_no+"&member_no="+member_no;*/ 
	});
	
	$("#deleteTaskBtn").on("click", function() {
		swal({
		    title: "정말 삭제하시겠습니까?",  
		    icon: "warning",
		    buttons: true,
		    dangerMode: true, 
		    buttons: ["아니오", "예"]
		})
		.then(function(isConfirm) {
			if(isConfirm) {
				swal("TASK 삭제가 완료되었습니다.", {
		            icon: "success",
				}).then(function(data) {
					if (data == true) {
						$("#taskDetailForm").submit();
					}
				});
			} else {
				swal("TASK 삭제가 취소되었습니다.");
			}
		});
		return false;
	});
	 
	$("#downloadTaskFile").on("click", function() {
		location.href = utils.getContextPath() + "/commons/download/task/"+task_no;
		/*location.href = utils.getContextPath() + "/task/file/"+task_no+"?project_no="+project_no;*/
/*		location.href = utils.getContextPath() + "/task/file/"+task_no+"?project_no="+project_no+"&member_no="+member_no;*/
	});
}); 
  
$(function() {
	$(".taskStatusSpan.p").text("진행중");
   	$(".taskStatusSpan.c").text("완료");
    $(".taskStatusSpan.w").text("대기");
});