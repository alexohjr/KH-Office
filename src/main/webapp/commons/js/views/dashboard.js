$(function() {
	// 채팅제외 목록에 nicescroll 먹이기
	$(".dashboard_ul").niceScroll({
		cursorcolor : "#283442",
		cursorwidth : "5px",
	});
	
	// 채팅목록에 nicescroll 먹이기
	$(".chat_list_area").niceScroll({
		cursorcolor : "#283442",
		cursorwidth : "5px",
	});
	// 내결제요청에 nicescroll 먹이기
	$(".dashboard_ul_report").niceScroll({
		cursorcolor : "#283442",
		cursorwidth : "5px",
	});
	
	// 프로젝트 셀클릭 (상세페이지 진입)
	$(document).on("click", ".projectCell", function() {
		var project_no = $(this).attr("data-project-no");
		location.href = utils.getContextPath() + "/project/" + project_no;
	});
});
//채팅창 열기
$(function(){
	$(".chat_window_li").on("click", function(){
		var cwindowNo = $(this).attr("data-cwindow-no");
		utils.popupCenter(utils.getContextPath() + "/chat_message/" + cwindowNo, "chat_window_" + cwindowNo, 600, 700);
	});
});

// 프로젝트 상태 문자열로 변환
$(function() {
	$(".project_status_info.p span").text("진행중");
	$(".project_status_info.c span").text("완료");
	$(".project_status_info.w span").text("보류");
	$(".project_status_info.s span").text("중단");
});

// 태스크 상세 프로그레스 바
$(function() {

	(function($) {
		"gdgd";
		$(function() {
			function animated_contents() {
				$(".progressBarOuter > .progressBarInner").each(function(i) {
					var $this = $(this);
					var skills = $this.attr('data-width');

					$this.css({
						'width' : skills + '%'
					});
				});
			}

			if (jQuery().appear) {
				$(".progressBarOuter").appear().on('appear', function() {
					animated_contents();
				});
			} else {
				animated_contents(); // 프로그래싱바 그리기 호출
			}
		});
	}(jQuery));
});

var speed = 150;

/* Call this function with a string containing the ID name to
 * the element containing the number you want to do a count animation on.*/
function incEltNbr(id) {
  elt = document.getElementById(id);
  endNbr = Number(document.getElementById(id).innerHTML);
  incNbrRec(0, endNbr, elt);
}

/*A recursive function to increase the number.*/
function incNbrRec(i, endNbr, elt) {
  if (i <= endNbr) {
    elt.innerHTML = i;
    setTimeout(function() {//Delay a bit before calling the function again.
      incNbrRec(i + 1, endNbr, elt);
    }, speed);
  }
}

/*Function called on button click*/
function incNbr(){
  incEltNbr("1");
}

incEltNbr("1"); /*Call this funtion with the ID-name for that element to increase the number within*/