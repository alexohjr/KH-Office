$(function() {
	
	$("#homeIcon").on("click", function() {
		location.href = utils.getContextPath() + "/dashboard";
	}); 
	
	$("#backIcon").on("click", function() {
		history.back();
	});
}); 