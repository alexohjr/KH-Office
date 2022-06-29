$(function(){
	// 로드시 헤더 제거
	$("header").remove();
	
	// 로드시 위젯 제거
	$(".check_work_area").remove();
	
	$(".message_area, .member_info_area").niceScroll({
		cursorcolor:"#283442",
		cursorwidth:"5px",
		cursordragontouch: true,
		emulatetouch: true
	});

	// 채팅로직 구현
	var webSocket = getWebsocket();
	
	// 채팅방 나가기 버튼
	$(".exit_btn_area >button").on("click", function(){
		
		swal({
	        title: "정말 채팅방을 나가시겠습니까?",
	        text: "나간 방에는 초대되어야 들어올 수 있습니다.",
	        icon: "warning",
	        buttons: true,
	        dangerMode: true,
	        buttons: ["아니오", "예"]
	    })
	    .then((willDelete) => {
	        if (willDelete) {
	        	exitAndCloseChatWindow(webSocket);
	        } else {
	            swal("취소되었습니다.");
	        }
	    });
		
	});
    
	// 채팅창 입력란 키업 이벤트 바인딩
	// 엔터키를 통해 send함
	$(document).on("keyup", "#message", function(){
		if (window.event.keyCode == 13) {
			send(webSocket);
		}
	});
	
	// 메시지 보내기 버튼
	$(document).on("click", ".send_area >button", function(){
		send(webSocket);
	});
	
	
	
	
	// 채팅멤버 추가할 사원 선택시
	$(document).on("click", ".member_select_area li.member_li:not(.selected)", function(){
		var memberNo = $(this).attr("data-member-no");
		
		// 선택한 사원에 selected 클래스 추가
		$(this).addClass("selected");
		
		// input 에 집어넣기
		checkAndInsertInput(memberNo);
	});
	
	// 이미 선택된 사원 리스트에서 선택시 제거
	$(document).on("click", ".member_select_area li.member_li.selected", function(){
		var memberNo = $(this).attr("data-member-no");
		
		// 선택 리스트에서 selected 제거
		removeSelectedClassOnSelectArea(memberNo);
		
		// input 영역에서 태그 자체를 제거
		removeSelectedTagOnInputArea(memberNo);
	});
	
	// 선택한 상대 초대 버튼
	$(".member_add_area >button").on("click", sendAddChatMemberAjax);
	
});





//선택된 사원들을 체크해서 input 영역에 넣는 함수
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

//사번을 받아 사원 리스트에서 해당 사원의 selected 클래스 제거
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

//사번을 받아 input 영역에서 해당 사원의 태그를 제거
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






// 웹소켓 연결 함수
function getWebsocket() {
	var sender = $("#session_id").val(); // 로그인한 내 사원번호
    var cwindowNo = $("#cwindow_no").val(); // 채팅방 번호

    // 웹소켓 연결
    var webSocket = new WebSocket(utils.getWebSocketHost() + utils.getContextPath() + "/echo/" + cwindowNo + "/" + sender);

    // 에러발생시
    webSocket.onerror = function (event) {
    	console.log("웹소켓 에러");
        onError(event)
    };

    // 웹소켓 연결시
    webSocket.onopen = function (event) {
        console.log("웹소켓 연결됨~");
        onOpen(event)
    };

    // 메시지 수신시
    webSocket.onmessage = function (event) {
        console.log("메시지 들어옴!!!");
        onMessage(event)
    };
    
    webSocket.onclose = function(event){
    	console.log("웹소켓 연결 종료");
    	onClose(event);
    }
    
    return webSocket;
}




// 메시지 수신
function onMessage(event) {
	var sessionId = $("#session_id").val();
	var jsonData = JSON.parse(event.data);
	console.log("jsonData : " , jsonData);
	
	var isEnterMessage = jsonData.isEnterMessage;
	var isExitMessage = jsonData.isExitMessage;
	
	if(isEnterMessage === true){
		// 입장메시지용 변수
		var memberNo = jsonData.memberNo;
		var name = jsonData.name;
		var position = jsonData.position;
		
		// 입장메시지인 경우
		var pElem = $("<p />", {
			"data-member-no" : memberNo,
			"data-name" : name,
			"data-position" : position,
			"data-is-enter-message" : isEnterMessage,
			"class" : "enter_message"
		}).text(position + " " + name + " 님이 입장하셨습니다.");
		
		$(".message_area").append(pElem);
	} else if (isExitMessage === true) {
		// 퇴장 메시지용 변수
		var memberNo = jsonData.memberNo;
		var name = jsonData.name;
		var position = jsonData.position;
		
		// 퇴장메시지인 경우
		var pElem = $("<p />", {
			"data-member-no" : memberNo,
			"data-name" : name,
			"data-position" : position,
			"data-is-enter-message" : isEnterMessage,
			"class" : "enter_message"
		}).text(position + " " + name + " 님이 퇴장하셨습니다.");
		
		$(".message_area").append(pElem);
	}else {
		// 일반메시지용 변수
		var cmessageNo = jsonData.cmessageNo;
		var memberNo = jsonData.memberNo;
		var thumbPath = jsonData.thumbPath;
		var position = jsonData.position;
		var name = jsonData.name;
		var message = jsonData.message;
		var time = jsonData.time;
		
		if (message == "") {
	        // 내용이 빈 경우
	    } else {
	    	// 내용이 있는 경우
	    	var pElem = $("<p />", {
	    		"data-cmessage-no" : cmessageNo,
	    		"data-member-no" : memberNo,
	    	});
	    	
	    	if(memberNo == sessionId){
	    		// 내 메시지인 경우
	    		pElem.addClass("my_message");
	    	} else {
	    		// 타인의 메시지인 경우
	    		pElem.addClass("other_message");
	    		
	    		var imgSpanElem = $("<span />").addClass("thumb_path");
		    	var imgElem = $("<img />", {
		    		"src" : utils.getContextPath() + "/" + thumbPath,
		    		"alt" : position + " " + name + " 님의 프로필 사진",
		    	});

		    	// 이미지 span에 img 붙이기
		    	imgSpanElem.append(imgElem);
		    	
		    	var positionNameSpan = $("<span />").addClass("position_name").text(position + " " + name);
	    	}
	    	
	    	var messageSpan = $("<span />").addClass("message").text(message);
	    	
	    	var timeSpan = $("<span />").addClass("time").text(time);
	    	
	    	// p 에 모두 붙이기
	    	if(memberNo == sessionId){
	    		pElem
	    		.append(timeSpan)
	    		.append(messageSpan);
	    	} else {
	    		pElem
	    		.append(imgSpanElem)
		    	.append(positionNameSpan)
		    	.append(messageSpan)
		    	.append(timeSpan);
	    	}
	    	
	    	$(".message_area").append(pElem);
	    	
	    	var messageArea = $(".message_area");
	    	
	    	var MATotalHeight = messageArea.prop("scrollHeight");
	    	var MAScrollHeight = messageArea.prop("scrollTop");
	    	var MAHeight = messageArea.outerHeight(); 
	    	
	    	console.log("차이 : " , MATotalHeight - MAScrollHeight - MAHeight);
	    	// 거의 최하단까지 스크롤이 되어있는 경우에만 자동 스크롤링 실행
	    	if((MATotalHeight - MAScrollHeight - MAHeight) < 100){
	    		scrollLast();
	    	}
	    }
	}
	
}

// 웹소켓 연결
function onOpen(event) {
	scrollLast();
}

// 웹소켓 종료
function onClose(event){
	console.log(event);
}

// 웹소켓 에러
function onError(event) {
	console.log(event);
    swal("웹소켓 에러", event, "error");
}

// 메시지 송신
function send(webSocket) {
	var messageInputElem = $("#message");
	var message = messageInputElem.val();
	
    webSocket.send(message);
    
    messageInputElem.val("");
}

// 스크롤 최하단 내리기 함수
function scrollLast(){
	var messageArea = $(".message_area");
	messageArea.prop("scrollTop", messageArea.prop("scrollHeight"));
}

// 채팅방 나가기
function exitAndCloseChatWindow(webSocket){
    var cwindowNo = $("#cwindow_no").val(); // 채팅방 번호
	
	$.ajax({
        url: utils.getContextPath() + "/chat_member/ajax/" + cwindowNo,
        type: "POST",
        dataType: "json",
        success: function(data){
        	var isDeleted = data.data;
        	
        	if(isDeleted){
        		// 삭제된 경우
        		swal("완료되었습니다.", {
	                icon: "success",
	            }).then((res) => {
	        		// 부모창 새로고침
	        		if(window.opener != null){
	        			window.opener.location.reload();
	        		}
	        		
	            	// 클라이언트에서 웹소켓 접속 끊기
	        		webSocket.close();
	        		
	        		// 채팅창 닫기
	        		window.close();
	            });
        		
        	} else {
        		swal("채팅방 퇴장에 실패했습니다.", "", "info");
        	}
        	
        },
        error: function(jqXHR, textStatus, errorThrown){
        	swal("에러 발생", textStatus + " : " + errorThrown, "error");
            console.log("textStatus : " , textStatus );
            console.log("errorThrown : " , errorThrown );
        }
    });
}

// 사원선택 모듈 ajax 통신시마다 현재 채팅창에 존재하는지 체크하여 안보이게 바꿔주는 함수
function checkAndExceptChatMember(){
	$(".member_info_area ul li").each(function(index, item){
		var existingMemberNo = $(item).attr("data-member-no");
		
		$(".member_select_area li.member_li").each(function(newIndex, newItem){
			var newMemberNo = $(newItem).attr("data-member-no");
			if(existingMemberNo == newMemberNo){
				// 동일한 사원 발견시
				$(newItem).css("display", "none");
			}
		});
		
	});
}

// 채팅 멤버추가
function sendAddChatMemberAjax(){
	// 추가된 member input 갯수 체크
	var addedInputArr = $(".added_input_area input.added_input");
	
	if(addedInputArr.length == 0){
		swal("채팅에 초대할 사람을 선택해주세요.", "", "success");
		return false;
	}
	
	var cwindowNo = $("#cwindow_no").val(); // 채팅방 번호
	var data = $("#add_chat_member").serialize();
	console.log(data);
	
	$.ajax({
        url: utils.getContextPath() + "/chat_window/ajax/" + cwindowNo,
        type: "POST",
        data: data,
        dataType: "json",
        success: function(data){
        	var isInserted = data.data;
        	console.log("isInserted : " , isInserted);
        	if(isInserted){
        		window.location.reload();
        	} else {
        		swal("채팅방 초대에 실패했습니다.", "", "error");
        	}
        },
        error: function(jqXHR, textStatus, errorThrown){
        	swal("에러 발생", textStatus + " : " + errorThrown, "error");
            console.log("textStatus : " , textStatus );
            console.log("errorThrown : " , errorThrown );
        }
    });
	
}




