$(function(){
	$(".chat_window_li").on("click", function(){
		var cwindowNo = $(this).attr("data-cwindow-no");
		utils.popupCenter(utils.getContextPath() + "/chat_message/" + cwindowNo, "chat_window_" + cwindowNo, 600, 700);
	});
});

//chat_message/28