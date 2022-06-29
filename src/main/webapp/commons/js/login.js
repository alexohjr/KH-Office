$(function(){
   
	// 탭 클릭시
	$(".tab_area").find("li").on("click", changeTab);
	
	// 임직원 탭 클릭시
	$(".member_btn").on("click", function(){
		$(".input_area >div").stop().transition({display:"none"},500);
		$(".member_input").stop().transition({display:"block"},500);
	});
	
	// 관리자 탭 클릭시
	$(".admin_btn").on("click", function(){
		$(".input_area >div").stop().transition({display:"none"},500);
		$(".admin_input").stop().transition({display:"block"},500);
	});
   
});

function changeTab(){
	// 탭 전환효과
	$(".tab_area").find("li").removeClass("on");
	$(this).toggleClass("on");
}