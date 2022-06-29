$(function() {
	$("#projectMemberSelectArea").hide();
	
	// 사원선택에 nicescroll 먹이기
   $("#projectMemberSelectArea").niceScroll({
		cursorcolor:"#283442",
		cursorwidth:"5px",
		cursordragontouch: true
	});
   
   // 선택된 사원영역에 nicescroll 먹이기
   $("#selectedProjectMemberArea").niceScroll({
		cursorcolor:"#283442",
		cursorwidth:"5px",
		cursordragontouch: true
	});
	
	$("#showSelectBoxBtn").on("click", function() {
		var dataBtn = $(this).attr("data-btn");
		if(dataBtn == 0) {
			$("#projectMemberSelectArea").show();
			$(this).attr("data-btn", "1");
		} else {
			$("#projectMemberSelectArea").hide();
			$(this).attr("data-btn", "0");
		}
	});
	
	var project_no = $("#project_no").val();
	var loginMember = $("#loginMember").val();
	var task_no = $("#task_no").val();
	
	
	$("#editCancelBtn").on("click", function() {
		location.href = utils.getContextPath() + "/task/"+task_no+"?project_no="+project_no;
		/*location.href = utils.getContextPath() + "/task/"+task_no+"?member_no="+loginMember+"&project_no="+project_no;*/
	});
	
	// 프로젝트 상태 값 selected
	var taskStatus = $("#taskStatus").val();
	$('#status').val(taskStatus);
});

$(function() {
	var projectMemberArr = $("#projectMemberHiddenDiv .hiddenMember");
	insertSelectMember(projectMemberArr);
});




// 프로젝트 멤버 정보 히든값 가져와서 태그 만들어주기~앙
function insertSelectMember(projectMemberArr) {
	
	var ulElem = $("<ul />");
	
	for(var i=0; i<projectMemberArr.length; i++) {
		var member_no = $(projectMemberArr[i]).attr("data-no");
		var member_name = $(projectMemberArr[i]).attr("data-name");
		var member_position = $(projectMemberArr[i]).attr("data-position");
		var member_thumb = $(projectMemberArr[i]).attr("data-thumb");
		
		var liElem = $("<li />", {
			"data-member-no" : member_no,
			"data-member-name" : member_name,
			"data-member-position" : member_position,
			"data-member-thumb" : member_thumb
		}).addClass("member_li");
		
		var spanElemThumb = $("<span />");
		var imgElem = $("<img />", {
			"src" : utils.getContextPath()+"/"+member_thumb,
			"alt" : member_position + " " + member_name + "님의 사진"
		});
		
		spanElemThumb.append(imgElem);
		
		var spanElemPosition = $("<span />").text(member_position);
		var spanElemName= $("<span />").text(member_name);
		
		// li 안에 태그 넣기
		liElem.append(spanElemThumb)
		.append(spanElemPosition)
	  	.append(spanElemName);
		
		// ul 안에 li 넣기
		ulElem.append(liElem);
		
		$("#projectMemberSelectArea").append(ulElem);
	}
}


$(function() {
	$(document).on("click", "#projectMemberSelectArea li.member_li:not(.selected)", function() {
		var member_no = $(this).attr("data-member-no");
		
		// 선택한 사원에 selected 클래스 추가
		$(this).addClass("selected");
		
		// 선택된 영역에 집어넣기
		insertSelectedArea();
		
		// input 에 집어넣기
		insertNoSeletedArea(member_no);
		
		$("#selectedProjectMemberArea").getNiceScroll().resize();
	});
	
	$(document).on("click", "#selectedProjectMemberArea li.member_li.selected", function() {
		var member_no = $(this).attr("data-member-no");
		
		// 선택 리스트에서 selected 제거
		removeSelectedClassOnSelectArea(member_no);
		
		// 선택된 영역에서 태그 자체를 제거
		$(this).remove();
		
		// input hidden 영역에서 태그 자체를 제거
		removeSelectedTagOnInputArea(member_no);
		
		$("#selectedProjectMemberArea").getNiceScroll().resize();
	});
});

// 태그 복제해서 선택된 멤버 영역에 넣어주기
function insertSelectedArea() {
	var selectedMemberArr = $("#projectMemberSelectArea li.member_li.selected");
	
	// 선택된 영역 비우기
	$("#selectedProjectMemberArea").empty();
	
	// 배열 돌려서 집어넣기
	for(var i = 0; i < selectedMemberArr.length; i++){
		var copiedElem = $(selectedMemberArr[i]).clone();
		$("#selectedProjectMemberArea").append(copiedElem);
	}
}

// 선택된 멤버의 member_no 값 히든으로 넣어주기
function insertNoSeletedArea(member_no) {
	var selectedMemberArr = $("#projectMemberSelectArea li.member_li.selected");
	
	// 선택된 영역 비우기
	$("#selectedProjectMemberHiddenArea").empty();
	
	
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
		$("#selectedProjectMemberHiddenArea").append(inputElem);
	}
}


//사번을 받아 사원 리스트에서 해당 사원의 selected 클래스 제거
function removeSelectedClassOnSelectArea(memberNo){
	// 선택된 영역의 선택된 사원 배열
	var selectedMemberArr = $("#selectedProjectMemberArea li.member_li.selected");

	for(var i = 0; i < selectedMemberArr.length; i++){
		var currMemberNo = $(selectedMemberArr[i]).attr("data-member-no");
		console.log("memberNo :: " + memberNo);
		console.log("currMemberNo :: " + currMemberNo);
		if(memberNo == currMemberNo){
			// 사원 선택 박스에서 selected 제거
			$("#projectMemberSelectArea li.member_li.selected[data-member-no=" + memberNo + "]").removeClass("selected");
		}
	}
}

//사번을 받아 input 영역에서 해당 사원의 태그를 제거
function removeSelectedTagOnInputArea(memberNo){
	// input 영역의 선택된 사원 배열
	var addedInputArr = $("#selectedProjectMemberHiddenArea input.added_input");

	for(var i = 0; i < addedInputArr.length; i++){
		var currMemberNo = $(addedInputArr[i]).val();
		if(memberNo == currMemberNo){
			// selected 제거
			$("#selectedProjectMemberHiddenArea input.added_input[value=" + memberNo + "]").remove();
		}
	}
}

$(function() {
	var taskOGMemberArr = $("#projectMemberHiddenDiv .taskOGMember");
	var projectMemberArr = $("#projectMemberSelectArea li.member_li:not(.selected)");
	
	for(var i=0; i<taskOGMemberArr.length; i++) {
		
		var taskMemberNo = $(taskOGMemberArr[i]).attr("data-no");
		
		for (var j=0; j<projectMemberArr.length; j++) {
			
			var projectMemberNo = $(projectMemberArr[j]).attr("data-member-no");
			console.log("팀원 넘버 :: " + projectMemberNo);
			
			if(taskMemberNo == projectMemberNo) {
				$(projectMemberArr[j]).trigger("click");
			}
		}
		console.log("오쥐 멤버 넘버 :: " + taskMemberNo);
	}
});



//유효성검사
$(function() {
	// 특문 배턴
	$(".taskName").bind("keyup",function(){
		re = /[~!@\#$%^&*\()\-=+_']/gi; 
		var temp=$(".taskName").val();
		
		if(re.test(temp)){ //특수문자가 포함되면 삭제하여 값으로 다시셋팅
			swal("특수문자는 사용 불가능 합니다.","", "warning");
			$(".taskName").val(temp.replace(re,""));
			$(".taskName").focus();
		} 
	});
	
	$("#taskEditBtn").on("click", function() {
		
		swal({
		    title: "TASK를 수정하시겠습니까?",  
		    icon: "warning",
		    buttons: true,
		    dangerMode: true, 
		    buttons: ["아니오", "예"]
		})
		.then(function(isConfirm) {
			if(isConfirm) {
				swal("TASK 수정이 완료되었습니다.", {
		            icon: "success",
				}).then(function(data) {
					if (data == true) {
						$("#taskEditForm").submit();
					}
				});
			} else {
				swal("TASK 수정이 취소되었습니다.");
			}
		});
		
		
		if ( $("#selectedProjectMemberHiddenArea > .added_input").length == 0) {
			swal("담당자를 선택해주세요", "", "warning");
			return false;
		}

		if ( $(".taskDeadline").val() == null || $(".taskDeadline").val() == "") {
			swal("마감일을 선택해주세요", "", "warning");
			return false;
		}  
		
		
		if ( $(".taskContent").val() == null || $(".taskContent").val() == "") {
			swal("TASK 설명을 입력해주세요.", "", "warning");
			return false;
		}
		
		if( $(".taskName").val() == null || $(".taskName").val() == "") {
			swal("TASK 명을 입력해주세요", "", "warning");
			return false;
		}
		return false;
	});
	
	$(".cancelBtn").on("click", function() {
		var project_no = $(".project_no").val();
		location.href = utils.getContextPath() + "/project/"+project_no;
	});
});
 
 
//파일 업로드
const realFileBtn = document.getElementById("real-file");
const customBtn = document.getElementById("taskFileUploadBtn");
const customTxt = document.getElementById("taskFileUploadText");

customBtn.addEventListener("click", function() {
  realFileBtn.click();
});
 
realFileBtn.addEventListener("change", function() {
	if (realFileBtn.value) {
		var res = realFileBtn.value.substring(12);
		$("#taskFileUploadText").text(res);
  } else {
    customTxt.innerHTML = "파일을 선택해주세요.";
  }
});
