
$(function() {

	/* var position = $(this).attr("data-member-position");
	var name = $(this).attr("data-name");
	var position = $(this).attr("data-thumb-path");*/
	
	// 사원 리스트에서 선택
	$(document).on("click", ".member_select_area li.member_li:not(.selected)", function() {
		var memberNo = $(this).attr("data-member-no");

		var isApprovalName1Full = $("#approval_name1").html().trim() == "" ? false : true;
		var isApprovalName2Full = $("#approval_name2").html().trim() == "" ? false : true;
		
		if($("#approval_name3").html() != undefined){
			var isApprovalName3Full = $("#approval_name3").html().trim() == "" ? false : true;
		}
		
		var clickElem = $(this);

	if($("#approval_name3").html() != undefined){
		
		if(isApprovalName1Full && isApprovalName2Full && isApprovalName3Full){
			swal("더이상 사원을 넣을 수 없습니다. \n추가된 사원을 제거해주세요.");
			return false;
		} else {
			clickElem.addClass("selected");// 선택한 사원에 selected 클래스 추가
			insertMember($(this));// 선택된 영역에 집어넣기
		}
	} else {
		
		if(isApprovalName1Full && isApprovalName2Full){
			swal("더이상 사원을 넣을 수 없습니다. \n추가된 사원을 제거해주세요.")
			return false;
		} else {
			clickElem.addClass("selected");// 선택한 사원에 selected 클래스 추가
			insertMember($(this));// 선택된 영역에 집어넣기
		}
	}
	
	});

	
	// 사원 선택된 영역에서 제거
	$(document).on("click", ".added_member_area li.member_li.selected",	function() {
		var memberNo = $(this).attr("data-member-no");

		// 선택 리스트에서 selected 제거
		removeSelectedClassOnSelectArea(memberNo);
		// 선택된 영역에서 태그 자체를 제거
		$(this).next().remove(); // input hidden태그를 먼저 삭제
		$(this).remove(); // 그 후 li태그를 삭제

	});

	
	
	var result = "";
	$(':input').each(function(index) {
		result += "태그명 : " + this.tagName + ", type 속성명 : "+ $(this).attr('type') + ", value값 : "+ $(this).attr('value') + '\n';});

	console.log(result);

	// 서브밋 할때
	$("#report_add_form").on("submit", function() {
		return true;
	});
	
	
	$(document).on("click", ".member_li", function() {
		// 각각 영역을 클릭하면 member_no를 받아온다.
		var mname = $(this).attr("data-name");
		var member_no = $(this).attr("data-member-no");
		console.log(mname);
		console.log(member_no);

	});
	
	
	$("#cancel").on("click",function(){
		url2 = window.location.pathname;
		var path = url2.substring(url2.indexOf('/',2), url2.length-5);
		window.location.href = utils.getContextPath()+path+"?status=1";
	});
	
	
	
	
	
	
	$("#start_date,#end_date").on("change", function(){
		var start_date = $("#start_date").val();
		var end_date = $("#end_date").val();
		
		var date1 = new Date(start_date);
		var date2 = new Date(end_date);
		
		
		if(start_date != "" && end_date != ""){
			var use_day = (date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24) + 1;
			$("#use_day").val(use_day).attr("readonly","readonly");
		}
	});
	
	

	/* 업무대리인 내자신꺼 삭제. */
	var formSelect = $("#substitute_select");
	var member_no = formSelect.attr("data-memberNo");

	var options = formSelect.find("option");

	$(options).each(function(idex, item) {
		
		if ($(this).val() == member_no) {
			$(this).remove();
		}
	});
	
	   $("#add_form_submit").on("click", function(){
		      swal({
		           title: "정말 결재요청 하시겠습니까?",
		           text: "한번 작성된 후에는 되돌릴 수 없습니다 !!",
		           icon: "warning",
		           buttons: true,
		           dangerMode: true,
		           buttons: ["아니오", "예"]
		       })
		       .then((willDelete) => {
		           if (willDelete) {
		              
		             $("#report_add_form").submit();
		             
		           } else {
		               swal("요청이 취소되었습니다.");
		           }
		       });
		   });
	
	
	
	// 레디펑션 종료.
});

// 팀장/부서장/대표이사 칸에 insert하는 로직
function insertMember(that){
	
	var memberNo = that.attr("data-member-no");
	var isApprovalName1Full = $("#approval_name1").html().trim() == "" ? false : true;
	var isApprovalName2Full = $("#approval_name2").html().trim() == "" ? false : true;
	
	if($("#approval_name3").html() != undefined){
		var isApprovalName3Full = $("#approval_name3").html().trim() == "" ? false : true;
	}
	var clickedMember = that.clone();
	
	if(!isApprovalName1Full){
		if($("#approval_name3").html() != undefined){
			var team_leaderInput = $("<input />", {
				"type" : "hidden",
				"name" : "team_leader",
				"id" : "member_no",
				"value" : memberNo,
				"class" : "added_input"
			});
		$("#approval_name1").append(clickedMember).append(team_leaderInput);
		
		} else {
			var request_team_leaderInput = $("<input/>",{
				"type" : "hidden",
				"name" : "request_team_leader",
				"id" : "member_no",
				"value" : memberNo,
				"class" : "added_input"
			});
			$("#approval_name1").append(clickedMember).append(request_team_leaderInput);
		}
		
		return false;
		
	}else if(!isApprovalName2Full){ // cooperation_team_leader
		
		if($("#approval_name3").html() != undefined){
			
			var depart_leaderInput = $("<input />", {
				"type" : "hidden",
				"name" : "depart_leader",
				"id" : "member_no",
				"value" : memberNo,
				"class" : "added_input"
			});
			$("#approval_name2").append(clickedMember).append(depart_leaderInput);
			
			return false;
			
		} else {
			var cooperation_team_leaderInput = $("<input/>",{
				"type" : "hidden",
				"name" : "cooperation_team_leader",
				"id" : "member_no",
				"value" : memberNo,
				"class" : "added_input"
		});
			$("#approval_name2").append(clickedMember).append(cooperation_team_leaderInput);
		
		}
		return false;
		
	}else if(!isApprovalName3Full && $("#approval_name3").html() != undefined){
		var ceoInput = $("<input />", {
			"type" : "hidden",
			"name" : "ceo",
			"id" : "member_no",
			"value" : memberNo,
			"class" : "added_input"
		});
		$("#approval_name3").append(clickedMember).append(ceoInput);
		return false;
	} 
}

// 사번을 받아 사원 리스트에서 해당 사원의 selected 클래스 제거
function removeSelectedClassOnSelectArea(memberNo) {
	// 선택된 영역의 선택된 사원 배열
	var selectedMemberArr = $(".member_select_area li.member_li.selected");

	for (var i = 0; i < selectedMemberArr.length; i++) {
		var currMemberNo = $(selectedMemberArr[i]).attr("data-member-no");
		if (memberNo == currMemberNo) {
			// selected 제거
			$(".member_select_area li.member_li.selected[data-member-no=" + memberNo + "]").removeClass("selected");
		}
	}
}


function checkError(){
	
	var approval_name1 = $("#approval_name1");
	var approval_name2 = $("#approval_name2");
	var approval_name3 = $("#approval_name3");
	var consult_title = $("#consult_title");
	var consult_content = $("#consult_content");
	var cooperation_deadline = $("#cooperation_deadline");
	var cooperation_title = $("#cooperation_title");
	var cooperation_content = $("#cooperation_content");
	var cooperation_department_no = $("#department_no");
	var vacation_type = $("#vacation_type");
	var vacation_start_date = $("#start_date");
	var vacation_end_date = $("#end_date");
	var vacation_reason = $("#reason");
	
	var remainder_leave = parseInt(vacation_type.attr("data-remain")); // 내게 남아있는 연차일 수
	var use_day = parseInt($("#use_day").val()); // 연차사용일
	var report_name = vacation_type.attr("data-name"); // 작성자 이름
	
	if(approval_name1.html() == ""){
		swal("부서에서 직원을 선택해주세요. \n순서대로 왼쪽부터 선택됩니다.");
		approval_name1.focus();
		return false;
	} else if(approval_name2.html() == ""){
		swal("부서에서 직원을 선택해주세요.");
		approval_name2.focus();
		return false;
	} else if(approval_name3.html() == ""){
		swal("부서에서 최종 책임자를 선택해주세요.");
		approval_name3.focus();
		return false;
	} else if(consult_title.val() == ""){
		swal("제목을 입력해주세요.");
		consult_title.focus();
		return false;
	} else if(consult_content.val() == ""){
		swal("내용을 입력해주세요.");
		consult_content.focus();
		return false;
	} else if(cooperation_deadline.val() == ""){
		swal("요청기한을 입력해주세요.");
		cooperation_deadline.focus();
		return false;
	} else if(cooperation_title.val() == ""){
		swal("제목을 입력해주세요.");
		cooperation_title.focus();
		return false;
	} else if(cooperation_content.val() == ""){
		swal("내용을 입력해주세요.");
		cooperation_content.focus();
		return false;
	} else if(cooperation_department_no.val() == ""){
		swal("협조부서를 선택해주세요.");
		cooperation_department_no.focus();
		return false;
	} else if(vacation_type.val() == ""){
		swal("휴가 종류를 선택해주세요.");
		vacation_type.focus();
		return false;
	} else if(vacation_start_date.val() == ""){
		swal("휴가 시작일을 선택해주세요.");
		vacation_start_date.focus();
		return false;
	} else if(vacation_end_date.val() == ""){
		swal("휴가 종료일을 선택해주세요.");
		vacation_end_date.focus();
		return false;
	} else if(vacation_type.val() == "연차" && (use_day > remainder_leave)){
		swal("가능한 연차일을 넘어섰습니다 \n[" + report_name +"]님이 사용할 수 있는 연차일은 [" + remainder_leave +"일] 입니다.");
		return false;
	} else if(vacation_reason.val() == ""){
		swal("휴가 사유를 적어주세요.");
		vacation_reason.focus();
		return false;
	}
	
}



