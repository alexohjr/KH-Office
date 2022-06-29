$(function(){
	
	$(".member_select_area").niceScroll({
		cursorcolor:"#283442",
		cursorwidth:"5px",
		cursordragontouch: true
	});
	
	$("#submitBtn").on("click", function(){
		
		var flag = false;
		
		if ($(".name").val() == "") {
			swal("자산을 다시 입력해 주세요.");
			return flag;

		}
		
		var isValid = true;

		swal({
			title : "자산을 수정 하시겠습니까?",
			icon : "warning",
			buttons : true,
			dangerMode : true,
			buttons : [ "아니오", "예" ]
		}).then(function(isConfirm) {
			if (isConfirm) {
				swal("자산 수정이 완료 되었습니다.", {
					icon : "success",
				})
				.then(function(data) {
					if (data == true) {
						$("#holding_assets_edit_form").submit();
					}
				});
			} else {
				isValid = false;
				swal("자산 수정을 취소 하였습니다.");
			}
		})
		
		return flag;
	});
	
	var pageNum = $(".pageNum").val();
	var keyword = $(".keyword").val();
	
	$("#edit_form_cancle").click(function(){
		location.href = utils.getContextPath() + "/holding_assets?pageNum="+pageNum+"&keyword="+keyword;
	})
});



$(function(){
	$(document).on("click", ".member_select_area li.member_li:not(.selected)", function(){
		console.log($(this));
		var memberNo = $(this).attr("data-member-no");
		$(".member_li").removeClass("selected");
		
		$(this).addClass("selected");
		
		checkAndInsertMember(memberNo)
        //$(".added_member_area").append($(this).addClass("selected"));
		checkAndInsertInput(memberNo);
		
	});
	
});



// 선택된 사원들을 체크해서 선택된 영역에 넣는 함수
function checkAndInsertMember(memberNo){
	var selectedMemberArr = $(".member_select_area li.member_li.selected");
	// 선택된 영역 비우기
	$(".added_member_area").empty();
	
	var copiedElem = $(selectedMemberArr).clone();
	$(".added_member_area").append(copiedElem);
	
	
	
	
	
}


// 선택된 사원들을 체크해서 input 영역에 넣는 함수
function checkAndInsertInput(){
	var selectedMemberArr = $(".member_select_area li.member_li.selected");
	// 선택된 영역 비우기
	$(".added_input_area").empty();
	
	// 배열 돌려서 집어넣기
	for(var i = 0; i < selectedMemberArr.length; i++){
		var memberNo = $(selectedMemberArr[i]).attr("data-member-no");
		
		// input 만들기
		var inputElem = $("<input />", {
			"type" : "hidden",
			"name" : "member_no",
			"id" : "member_no",
			"value" : memberNo,
			"class" : "added_input"
		});
		
		// 만든 input 집어넣기
		$(".added_input_area").append(inputElem);
	}
	
}

// 사번을 받아 input 영역에서 해당 사원의 태그를 제거
function removeSelectedTagOnInputArea(memberNo){
	console.log("memberNo : " , memberNo);
	// input 영역의 선택된 사원 배열
	var addedInputArr = $(".added_input_area input.added_input");

	for(var i = 0; i < addedInputArr.length; i++){
		var currMemberNo = $(addedInputArr[i]).val();
		if(memberNo == currMemberNo){
			// selected 제거
			$(".added_input_area input.added_input[value=" + memberNo + "]").remove();
		}
	}
}














