$(document).ready(function() { 
	
	// 담당자 란에 nicescroll 먹이기
	$("#projectMemberSelect").niceScroll({
		cursorcolor:"#283442",
		cursorwidth:"5px",
		cursordragontouch: true
	});
	
	// 선택된 담당자란에 nicescroll 먹이기
	$("#selectedProjectMemberArea").niceScroll({
		cursorcolor:"#283442",
		cursorwidth:"5px",
		cursordragontouch: true
	});
	
	$("#projectMemberSelect").hide();
	$("#projectLeaderSelect").hide();
});

// 유효성검사
$(function() {
	// 특문 배턴
	$(".projectName").bind("keyup",function(){
		re = /[~!@\#$%^&*\()\-=+_']/gi; 
		var temp=$(".projectName").val();
		
		if(re.test(temp)){ //특수문자가 포함되면 삭제하여 값으로 다시셋팅
			swal("특수문자는 사용 불가능 합니다.","", "warning");
			$(".projectName").val(temp.replace(re,""));
			$(".projectName").focus();
		} 
	});
	
	/*$("#addForm").submit(function() {
		console.log("서브밋버튼클릭");
		var isValid = checkAndSubmit();
		console.log("유효성검사진행후");
		return isValid;
	});*/
	
	$(".addFormCancelBtn").on("click", function() {
		location.href = utils.getContextPath() + "/project";
	});
});

// 유효성 검사
$(function() {
	$(".addProjectBtn").on("click", function () {
		
		var startDate = $('.start_date').val();
	    var endDate = $('.end_date').val();
	    //-을 구분자로 연,월,일로 잘라내어 배열로 반환
	    var startArray = startDate.split('-');
	    var endArray = endDate.split('-');   
	    //배열에 담겨있는 연,월,일을 사용해서 Date 객체 생성
	    var start_date = new Date(startArray[0], startArray[1], startArray[2]);
	    var end_date = new Date(endArray[0], endArray[1], endArray[2]);
	    
		swal({
		    title: "새로운 프로젝트를 생성하시겠습니까?",  
		    icon: "warning",
		    buttons: true,
		    dangerMode: true, 
		    buttons: ["아니오", "예"]
		})
		.then(function(isConfirm) {
			if(isConfirm) {
				swal("프로젝트 생성이 완료되었습니다.", {
		            icon: "success",
				}).then(function(data) {
					if (data == true) {
						$("#addForm").submit();
					}
				});
			} else {
				swal("프로젝트 생성이 취소되었습니다.");
			}
		});
		
		if( $(".projectName").val() == null || $(".projectName").val() == "") {
			swal("프로젝트 명을 입력해주세요", "", "warning");
			return false;
		}
		
		if ( $(".start_date").val() == null || $(".start_date").val() == "") {
			swal("시작일을 선택해주세요", "", "warning");
			return false;
		} 
		
		
		if ( $(".end_date").val() == null || $(".end_date").val() == "") {
			swal("종료일을 선택해주세요", "", "warning");
			return false;
		} 

		 //날짜를 숫자형태의 날짜 정보로 변환하여 비교한다.
	    if(start_date.getTime() > end_date.getTime()) {
	        swal("종료일보다 시작일이 작아야합니다.", "", "warning");
	        return false;
	    }
	    
	    if ( $(".projectOutline").val() == null || $(".projectOutline").val() == "") {
			swal("프로젝트 개요를 입력해주세요.", "", "warning");
			return false;
		}
		
		if ( $(".added_input_area > .added_input").length == 0) {
			swal("팀원을 선택해주세요", "", "warning");
			return false;
		}
		return false;
	});
});



$(function(){
	$(document).on("click", "#addProjectMemberBtn", function() {
		var btnData = $(this).attr("data-btn");
		console.log(btnData);
		if (btnData == "hideMemberBtn") {
			$("#projectMemberSelect").show();
			$(this).attr("data-btn", "showMemberBtn");
		} else {
			$("#projectMemberSelect").hide();
			$(this).attr("data-btn", "hideMemberBtn");
		}
	});
});





$(function(){
	$(document).on("click", "#addProjectLeaderBtn", function() {
		var btnData = $(this).attr("data-btn");
		console.log(btnData);
		if (btnData == "hideLeaderBtn") {
			$("#projectLeaderSelect").show();
			$(this).attr("data-btn", "showLeaderBtn");
		} else {
			$("#projectLeaderSelect").hide();
			$(this).attr("data-btn", "hideLeaderBtn");
		}
	});
});

$(function(){
	// 사원 리스트에서 선택
	$(document).on("click", ".member_select_area li.member_li:not(.selected)", function(){
		var memberNo = $(this).attr("data-member-no");
		
		// 선택한 사원에 selected 클래스 추가
		$(this).addClass("selected");
		
		// 선택된 영역에 집어넣기
		checkAndInsertMember();

		// input 에 집어넣기
		checkAndInsertInput(memberNo);
		
		$("#selectedProjectMemberArea").getNiceScroll().resize();
	});
	
	// 사원 선택된 영역에서 제거
	$(document).on("click", ".added_member_area li.member_li.selected", function(){
		var memberNo = $(this).attr("data-member-no");
		console.log(memberNo);
		
		// 선택 리스트에서 selected 제거
		removeSelectedClassOnSelectArea(memberNo);
		
		// 선택된 영역에서 태그 자체를 제거
		$(this).remove(); 
		
		// input 영역에서 태그 자체를 제거
		removeSelectedTagOnInputArea(memberNo);
		
		$("#selectedProjectMemberArea").getNiceScroll().resize();
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
			"name" : "member_nos",
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
		


//사원선택 모듈에서 자기 자신을 안보이게 하는 함수
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





