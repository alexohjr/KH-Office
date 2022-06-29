$(function(){
	// 사원 리스트에서 선택
	$(document).on("click", ".member_select_area li.member_li:not(.selected)", function(){
		var memberNo = $(this).attr("data-member-no");
		/*var memberNo = $(this).attr("data-member-no");
		var position = $(this).attr("data-member-position");
		var name = $(this).attr("data-name");
		var position = $(this).attr("data-thumb-path");*/
		
		// 선택한 사원에 selected 클래스 추가
		$(this).addClass("selected");
		
		// 선택된 영역에 집어넣기
		checkAndInsertMember();
		// input 에 집어넣기
		checkAndInsertInput(memberNo);
		// 현재 선택된 사원수 체크해서 메시지 노출
		checkAddedMemberAreaAndInsertText();
	});
	
	// 사원 선택된 영역에서 제거
	$(document).on("click", ".added_member_area li.member_li.selected", function(){
		var memberNo = $(this).attr("data-member-no");
		
		// 선택 리스트에서 selected 제거
		removeSelectedClassOnSelectArea(memberNo);
		
		// 선택된 영역에서 태그 자체를 제거
		$(this).remove();
		
		// input 영역에서 태그 자체를 제거
		removeSelectedTagOnInputArea(memberNo);

		// 현재 선택된 사원수 체크해서 메시지 노출
		checkAddedMemberAreaAndInsertText();
	});
	
	// 채팅방 만들기 버튼 클릭
	$("#create_chat_window_btn").on("click", sendCreateChatWindowAjax);
	
	// 취소 버튼 클릭
	$("#create_chat_cancel_btn").on("click", function(){
//		history.go(-1);
		location.href = utils.getContextPath() + "/chat_window";
	});
});

// 선택된 사원들을 체크해서 선택된 영역에 넣는 함수
function checkAndInsertMember(){
	var selectedMemberArr = $(".member_select_area li.member_li.selected");
	// 선택된 영역 비우기
	$(".added_member_area").empty();
	
	// 배열 돌려서 집어넣기
	for(var i = 0; i < selectedMemberArr.length; i++){
		var copiedElem = $(selectedMemberArr[i]).clone();
		$(".added_member_area").append(copiedElem);
	}
}

// 사번을 받아 사원 리스트에서 해당 사원의 selected 클래스 제거
function removeSelectedClassOnSelectArea(memberNo){
	// 선택된 영역의 선택된 사원 배열
	var selectedMemberArr = $(".member_select_area li.member_li.selected");

	for(var i = 0; i < selectedMemberArr.length; i++){
		var currMemberNo = $(selectedMemberArr[i]).attr("data-member-no");
		if(memberNo == currMemberNo){
			// selected 제거
			$(".member_select_area li.member_li.selected[data-member-no=" + memberNo + "]").removeClass("selected");
		}
	}
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

// 채팅방 개설 요청
function sendCreateChatWindowAjax(){
	var nameInput = $("#name");
	var name = nameInput.val();
	
	if(name.trim() == ""){
		swal("채팅방 이름을 입력해주세요.", "", "warning");
		nameInput.val("");
		nameInput.focus();
		return false;
	}
	
	// 추가된 member input 갯수 체크
	var addedInputArr = $(".added_input_area input.added_input");
	
	if(addedInputArr.length == 0){
		swal("채팅에 참여할 사람을 선택해주세요.", "", "warning");
		return false;
	}
	
	var data = $("#create_chat_form").serialize();
	console.log(data);
	$.ajax({
        url: utils.getContextPath() + "/chat_window/ajax",
        type: "POST",
        data: data,
        dataType: "json",
        success: function(data){
        	var cwindowNo = data.data;
//        	setTimeout(window.open(utils.getContextPath() + "/chat_message/" + cwindowNo, "chat_window_" + cwindowNo, "width=400, height=700, location=no, resizable=yes, toolbar=no, menubar=no, scrollbars=yes"), 2000); // 채팅창 열기
        	utils.popupCenter(utils.getContextPath() + "/chat_message/" + cwindowNo, "chat_window_" + cwindowNo, 600, 700);
        	location.href = utils.getContextPath() + "/chat_window"; // 현재페이지는 목록으로 돌아가기
        },
        error: function(jqXHR, textStatus, errorThrown){
        	swal("에러 발생", textStatus + " : " + errorThrown, "warning");
            console.log("textStatus : " , textStatus );
            console.log("errorThrown : " , errorThrown );
        }
    });
	
}

// 사원선택 모듈에서 자기 자신을 안보이게 하는 함수
function checkAndExceptSessionId(){
	var sessionId = $("#session_id").val();
	$(".member_select_area li.member_li").each(function(Index, Item){
		var memberNo = $(Item).attr("data-member-no");
		if(sessionId == memberNo){
			// 로그인 한 자신 발견시
			$(Item).css("display", "none");
		}
	});
}

// 현재까지 선택된 사원 수를 체크해서 알맞은 메시지 노출하는 함수
function checkAddedMemberAreaAndInsertText(){
	var len = $(".added_member_area >li").length;
	console.log(len);
	if(len >= 1){
		// 선택된 사원이 1명이라도 존재하는 경우
		var spanElem = $("<span />").text("추가된 사원을 다시 클릭하면 제거됩니다.");
		$(".added_member_area_message").html(spanElem);
	} else if(len == 0){
		// 모두 제거된 경우
		var spanElem = $("<span />").text("사원을 클릭하면 이곳에 추가됩니다.");
		$(".added_member_area").append(spanElem);
		$(".added_member_area_message").empty();
	}
}


