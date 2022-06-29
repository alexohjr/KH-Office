$(function() {
	$(".project_name").on("click", function() {
		var project_no = $(this).attr("data-project-no");
		location.href = utils.getContextPath() + "/project/"+project_no;
	});
	
	$(".task_name").on("click", function() {
		var project_no = $(this).attr("data-project-no");
		var task_no = $(this).attr("data-task-no");
		location.href = utils.getContextPath() + "/task/"+task_no+"?project_no="+project_no;
	});
});