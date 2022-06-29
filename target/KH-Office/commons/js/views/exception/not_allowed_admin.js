$(function() {
	
	$("#homeIcon").on("click", function() {
		location.href = utils.getContextPath() + "/member";
	});
	
	$("#backIcon").on("click", function() {
		history.back();
	});
});