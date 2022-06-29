$(function(){
	// 스크롤시 헤더 줄이는 함수 호출
	scrollShrink();
	
	$(".nav_menu >ul >li").on("mouseover", function(){
		$(this).children(".nav_menu_2").stop().slideToggle(200);
	});
	
	$(".nav_menu >ul >li").on("mouseout", function(){
		$(this).children(".nav_menu_2").stop().slideToggle(200);
	});
	
	$("#logout").on("click",function(){
		swal({
	        title: "로그아웃 하시겠습니까?",
	        icon: "warning",
	        buttons: true,
	        dangerMode: true,
	        buttons: ["아니오", "예"]
	    })
	    .then((willLogout) => {
	        if (willLogout) {
	         location.href = utils.getContextPath() + "/member/logout";
	            	}
	            });
		return false;
	});
	
});



//스크롤시 헤더 줄이기
function scrollShrink() {
	window.addEventListener('scroll', function(e) {
		var distanceY = window.pageYOffset || document.documentElement.scrollTop; // 내려간 세로거리
		var shrinkOn = 200; // 기준점인 y 픽셀.

		if (distanceY > shrinkOn) { // 내려간 경우
			$("#header").addClass("shrink");
			$('#header >div').stop().transition({ width: '1100px' }, 500);
		} else { // 내려가지 않은 경우
			$("#header").removeClass("shrink");
			$('#header >div').stop().transition({ width: '1280px' }, 500);
		}
	});
}