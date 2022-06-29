$(function() {
	// nicescroll 먹이기
   $("#taskListArea").niceScroll({
		cursorcolor:"#283442",
		cursorwidth:"5px",
		cursordragontouch: true
	});
   
   $("#projectMemberArea").niceScroll({
		cursorcolor:"#283442",
		cursorwidth:"5px",
		cursordragontouch: true
	});
	
	// 댓글 등록 유효성 검사
	$("#commentInsertBtn").on("click", function() {
		
		if ( $("#commentInsertTextarea").val() == null || $("#commentInsertTextarea").val() == "") {
			swal("내용을 입력해주세요.", "", "warning");
		} else {
			console.log("gdgd");
			$(".newCommentForm").submit();
		}
		return false;
	});
});

// 태스크 개수 카운트 및 라디오 선택
$(function() {
	// 태스크 라디오 버튼 선택해주기
	$(".radioCell").each(function(index, item) {
		checkTaskStatusRadioButton(index, item);
	});
	
	// 총 태스크 개수 카운트
	var taskCount = $(".taskCell").length;
	console.log("taskCount :: " + taskCount);
	$("#allTaskCount").text(taskCount);
	
	// 나의 태스크 개수 카운트
	var myTaskCount = $(".radioCell").length;
	console.log("myTaskCount :: " + myTaskCount);
	$("#myTaskCount").text(myTaskCount);
	
	completeTaskCount();
});

function completeTaskCount() {
	var completeTaskCount = $(".status[value='2']").length;
	console.log("completeTaskCount :: " + completeTaskCount);
	$("#completeTaskCount").text(completeTaskCount);
}

//태스크 라디오 버튼 선택해주기
function checkTaskStatusRadioButton(index, item){
	var taskDiv = $(item);
	var statusVal = taskDiv.find(".status").val();
	taskDiv.find("input.taskStatus[value=" + statusVal + "]").attr("checked", true);
}


function hideCommentBtn() {
	
	var member_no = $("#loginMember").val();
	
	$(".commentBtnArea").each(function() {
		var areaMno = $(this).attr("data-mno");
		console.log("areaMno :: " , areaMno);
		console.log("member_no :: ", member_no);
		if (areaMno != member_no) {
			$(this).children("span").hide();
		}
	});
}

// 댓글 수정/삭제 
$(function() {
	
	hideCommentBtn();
	
	$(".updateContentForm").hide(); // 댓글 수정폼 숨김
	$(".commentUpdateSubmitBtn").hide(); // 수정 서브밋 버튼 숨김
	$(".commentCancelBtn").hide(); // 수정 취소 버튼 숨김
	
	$(document).on("click", ".commentUpdateBtn", function() {
		console.log("버튼 클릭");
		$(this).hide(); // 수정 폼 진입 버튼 숨김
		var cmno = $(this).attr("data-cmno"); // 클릭한 버튼의 코멘트 넘버
		var content = $(".commentContentSpan[data-cmno="+cmno+"]").text();
		console.log("content :: " + content);
		$(".updateContentForm[data-cmno="+cmno+"]").val(content);
		$(".commentContentSpan[data-cmno="+cmno+"]").hide(); // 기존 내용 숨김
		$(".commentDeleteBtn[data-cmno="+cmno+"]").hide(); // 삭제 버튼 숨김
		$(".commentCancelBtn[data-cmno="+cmno+"]").show(); // 수정 취소 버튼 보이기
		$(".updateContentForm[data-cmno="+cmno+"]").show(); // 수정 폼 보이기
		$(".commentUpdateSubmitBtn[data-cmno="+cmno+"]").show(); // 수정 버튼 보이기
		
		var type = $(".btnType[data-cmno="+cmno+"]"); // 타입 edit 으로 변경
		type.attr("value", "edit");
		
		$(document).on("click", ".commentCancelBtn", function() {
			$(this).hide(); // 취소 버튼 숨김
			var cmno = $(this).attr("data-cmno"); // 코멘트 넘버 가져오기
			$(".commentContentSpan[data-cmno="+cmno+"]").show(); // 기존 내용 출력
			$(".commentDeleteBtn[data-cmno="+cmno+"]").show(); // 삭제 버튼 출력
			$(".commentCancelBtn[data-cmno="+cmno+"]").hide(); // 수정 취소 버튼 숨기기
			$(".updateContentForm[data-cmno="+cmno+"]").hide(); // 수정 폼 숨기기
			$(".commentUpdateSubmitBtn[data-cmno="+cmno+"]").hide(); // 수정 폼 숨기기
			$(".commentUpdateBtn[data-cmno="+cmno+"]").show(); // 수정폼 진입 버튼 출력
			
			var type = $(".btnType[data-cmno="+cmno+"]"); // 타입 delete 로 변경
			type.attr("value", "delete");
		});
	});
});

// 버튼 function
$(function() {
	var project_no = $("#project_no").val();
	var loginMember = $("#loginMember").val();
	$(".taskMemberArea").hide(); 
	 

	$("#projectEditBtn").on("click", function() {
		location.href = utils.getContextPath()+"/project/form/"+project_no;
	});
	
	$(".goProjectListBtn").on("click", function() {
		location.href = utils.getContextPath()+"/project";
	});
	
	$("#projectDeleteBtn").on("click", function() {
		
		swal({
		    title: "정말 삭제하시겠습니까?",  
		    icon: "warning",
		    buttons: true,
		    dangerMode: true, 
		    buttons: ["아니오", "예"]
		})
		.then(function(isConfirm) {
			if(isConfirm) {
				swal("프로젝트 삭제가 완료되었습니다.", {
		            icon: "success",
				}).then(function(data) {
					if (data == true) {
						$("#projectDeleteForm").submit();
					}
				});
			} else {
				swal("프로젝트 삭제가 취소되었습니다.");
			}
		});
		return false;
	});
	
	$("#addTaskBtn").on("click", function() {
		location.href = utils.getContextPath()+"/task/form?project_no="+project_no;
	});
	
	$(".showTaskMemberBtn").on("mouseenter", function() {
		var task_no = $(this).attr("data-task-no");
		$(".taskMemberArea[data-task-no="+task_no+"]").show();
		$("#taskListArea").getNiceScroll().resize();
	});
	
	$(".taskCell").on("mouseleave", function() {
		$(this).find(".taskMemberArea").hide();
		$("#taskListArea").getNiceScroll().resize();
//		var task_no = $(this).attr("data-task-no");
//		$(".taskMemberArea[data-task-no="+task_no+"]").hide();
//		$("#taskListArea").getNiceScroll().resize();
	});
	
	$("#taskListArea div.taskCell").on("click", function() {

		var task_no = $(this).find("#task_no").val();
		location.href = utils.getContextPath() + "/task/"+task_no+"?project_no="+project_no;
	});
	
	// 태스크 파일 다운로드
	$(".downloadTaskFile").on("click", function(event) {
		event.stopPropagation();
		var task_no = $(this).attr("data-tno");
		location.href = utils.getContextPath() + "/commons/download/task/"+task_no;
	});
	
	$("#removeCommentBtn").on("click", function() {
		var size = $("form.commentForm").length;
		
		console.log("size : " + size);
		var viewNum = $("#viewNum").attr("data-viewNum");
		
		$("#viewNum").attr("data-viewNum", viewNum-1);
		
		$("form.commentForm").indexOf(size).remove;
	});
	
	// 댓글 삭제
	$(document).on("click", ".commentDeleteBtn", function() {
		var form = $(this).parents("form");
		swal({
         title: "정말 삭제하시겠습니까?",  
           icon: "warning",
           buttons: true,
           dangerMode: true, 
            buttons: ["아니오", "예"] 
       })
       .then(function(isConfirm) {
          if(isConfirm) {
             swal("댓글 삭제가 완료되었습니다.", {
                   icon: "success",
             }).then(function(data) {
                if (data == true) {
                  form.submit();
                }
             });
          	} else {
        	  swal("댓글 삭제가 취소되었습니다.");
          }
       });
       return false;
	});
	
	// 댓글 수정
	$(document).on("click", ".commentUpdateSubmitBtn", function() {
	    var form = $(this).parents("form");
	    var cmno = $(this).attr("data-cmno");
		var content = $(".updateContentForm[data-cmno="+cmno+"]").val();
			
		if(content == null || content == "") {
				swal("내용을 입력해주세요.", "", "warning");
				return false;
		}
	      
	      swal({
	         title: "정말 수정하시겠습니까?",  
	           icon: "info",
	           buttons: true,
	           dangerMode: true, 
	            buttons: ["아니오", "예"]  
	       })
	       .then(function(isConfirm) {
	          if(isConfirm) {
	             swal("댓글 수정이 완료되었습니다.", {
	                   icon: "success",
	             }).then(function(data) {
	                if (data == true) {
	                  form.submit();
	                }
	             });
	          } else {
	             swal("댓글 수정이 취소되었습니다.");
	          }
	       });
	       return false;
	 });
});

function countingTask() {
	
}

// 태스크 상세 프로그레스 바
$(function(){
	
	(function( $ ) {
        "gdgd";
        $(function() {
            function animated_contents() {
                $(".taskBarOuter > .taskBarInner").each(function (i) {
                    var $this  = $(this);
                    var skills = $this.attr('data-width');
                    if (skills == 1) {
                    	skills = 50;
                    } else if (skills == 2) {
                    	skills = 100;
                    }
                    
                    if (skills == 50) {
                    	$this.css({'background-color' : '#38BE5E'});
                    } else if (skills == 100) {
                    	$this.css({'background-color' : '#2457BD'});
                    }
                    $this.css({'width' : skills + '%'});
                });
                
                $(".projectBarOuter > .projectBarInner").each(function (i) {
                    var $this  = $(this);
                    var progress = $this.attr('data-width');
                    if (progress == 100) {
                    	$this.css({'background-color' : '#2457BD'});
                    } else {
                    	$this.css({'background-color' : '#38BE5E'});
                    }
                    
                    $("#projectBarText").text(progress+"%");
                    $this.css({'width' : progress-4 + '%'});
                    /*$this.css({'width' : progress + '%'});*/
                });
            }
            
            if (jQuery().appear) {
                $(".taskBarOuter, .projectBarOuter").appear().on('appear', function() {
                    animated_contents();
                });
            } else {
                animated_contents(); // 프로그래싱바 그리기 호출
            }
             
            $(".radioCell .container").on("click", function(event) {
            	event.stopPropagation();
        		var status = $(this).find(".taskStatus").val();
        		console.log("status :: " + status);
        		
        		var task_no = $(this).find(".taskStatus").attr("data-task-no");
        		var project_no = $("#project_no").val();
        		var projectStatus = $("#projectStatus").val();
        		
        		$(".status[data-task-no="+task_no+"]").val(status);
        		
        		// ajax 송수신
        		var data = {
        				"status" : status,
        				"project_no" : project_no,
        				"projectStatus" : projectStatus
        		};
        		
        		$.ajax({
        			url:utils.getContextPath() + "/task/ajax/"+task_no,
        			type : "POST",
        			data : data,
        			dataType : "json",
        			success: function(data) {
        				
        				var changedTask = $(".taskBarOuter .taskBarInner[data-task-no=" + data.task_no + "]");
        				changedTask.attr("data-width", data.status);
        				$(".projectBarInner").attr("data-width", data.projectProgress);
        				console.log("ajax projectStatus :: " + projectStatus)
        				if (projectStatus != "w" && projectStatus != "s") {
        					if(data.projectProgress == 100) {
	        					$(".projectStatusSpan").css({'background-color' : '#2457BD'});
	        					$(".projectStatusSpan").text("완료");
	        				} else {
	        					$(".projectStatusSpan").css({'background-color' : '#38BE5E'});
	        					$(".projectStatusSpan").text("진행중");
	        				}
        				}
        				
        				var completeTaskCount = $(".status[value='2']").length;
        				console.log("completeTaskCount :: " + completeTaskCount);
        				$("#completeTaskCount").text(completeTaskCount);
        				
        				animated_contents();
        			},
        			error: function(jqXHR, textStatus, errorThrown){
        	            alert("에러 발생 \n" + textStatus + " : " + errorThrown);
        	        }
        		});
        	});
        });
    }(jQuery)); 
});

// 댓글 ajax로 받아오기
$(function() {
	$(".getMoreCommentBtn").on("click", function() {
		
		var viewNum = $("#viewNum").attr("data-viewNum");
		var project_no = $("#project_no").val();
		
		var data = {
				"viewNum" : viewNum,
				"project_no" : project_no
		};
		
		
		$.ajax({
			url: utils.getContextPath() + "/project_comment/ajax/"+viewNum,
			type : "GET",
			data : data,
			dataType : "json",
			success: function(data) {
				
				$("#viewNum").attr("data-viewNum", data.viewNum);
				
				insertMoreComment(data.projectCommentList);
				
				// 바디에 nicescroll 다시 먹이기
				$("body").getNiceScroll().resize();
				
			},
			error: function(jqXHR, textStatus, errorThrown){
	            alert("에러 발생 \n" + textStatus + " : " + errorThrown);
	        }
		});
	});
});

// 받아온 댓글 화면에 출력하기
function insertMoreComment(list) {
	var appendArea = $(".appendArea");
	
	if(list.length == 0) {
		swal("더이상 불러올 댓글이 없습니다.","","info");
		return false;
	} 
	
	
	for (var i=0; i<list.length; i++) {
		
		var comment_no = list[i].pcomment_no;
		var thumb_path = list[i].thumb_path;
		var name = list[i].name;
		var position = list[i].position;
		var reg_date = list[i].reg_date;
		var member_no = list[i].member_no;
		var project_no = list[i].project_no;
		var content = list[i].content;
		
		var formElem = $("<form />", {
			"action" : utils.getContextPath() + "/project_comment/" + comment_no,
			"method" : "post",
			"class" : "commentForm"
		});
		 
		var divElem1 = $("<div />").addClass("thumbnailDiv");
		
		divElem1.append("<p><img src="+utils.getContextPath()+"/"+thumb_path+"></p>");
		formElem.append(divElem1); // 첫번째 div 넣음
		//// 해결
		
		var divElem2 = $("<div />").addClass("commenListDiv");
		
		var tableElem1 = $("<table />").addClass("commentMemberList"); // 첫번째 table

		var tbodyElem1 = $("<tbody />");
		
		var trElem1 = $("<tr />"); // 첫번째  tr
		
		var tdElem1 = $("<td />"); // 첫번째 td
		tdElem1.append("<p class='commentMemberInfoP'>"+position+" "+name+"</p>");
		
		trElem1.append(tdElem1);
		
		var tdElem2 = $("<td />"); // 두번째 td
		tdElem2.append("<p class='commentRegdateP'>"+reg_date+"</p>");
		
		trElem1.append(tdElem2);
		
		var tdElem3 = $("<td />", { // 세번째 td
			"class" : "commentBtnArea",
			"data-mno" : member_no
		});
		
		var spanElem1 = $("<span />");
		spanElem1.append("<input type='button' value='수정' class='commentUpdateBtn' data-cmno="+comment_no+">");
		
		tdElem3.append(spanElem1);
		 
		var spanElem2 = $("<span />");
		spanElem2.append("<input type='submit' value=' | 삭제' class='commentDeleteBtn' data-cmno="+comment_no+">");
		spanElem2.append("<input type='button' value='수정취소' class='commentCancelBtn' data-cmno="+comment_no+">");
		
		tdElem3.append(spanElem2);
		
		tdElem3.append($("<input />", {
			"type" : "hidden",
			"name" : "type",
			"class" : "btnType",
			"value" : "delete",
			"data-cmno" : comment_no
		}));
		
		tdElem3.append($("<input />", {
			"type" : "hidden",
			"name" : "project_no",
			"class" : "project_no",
			"value" : project_no
		}));
		
		tdElem3.append($("<input />", {
			"type" : "hidden",
			"name" : "member_no",
			"class" : "loginMember",
			"value" : member_no
		}));
		
		trElem1.append(tdElem3);
		
		tableElem1.append(tbodyElem1).append(trElem1);
		
		var tableElem2 = $("<table />").addClass("commentContentList");
		
		var tbodyElem2 = $("<tbody />");
		
		var trElem2 = $("<tr />");
		
		var tdElem4 = $("<td />", {
			"class" : "commentContentArea",
			"data-cmno" : comment_no
		});
		
		tdElem4.append("<p class='commentContentSpan' data-cmno="+comment_no+">"+content+"</p>");
		trElem2.append(tdElem4);
		tableElem2.append(tbodyElem2).append(trElem2);
		
		var tableElem3 = $("<table />"); // 세번째 테이블
		var tbodyElem3 = $("<tbody />");
		var trElem3 = $("<tr />");
		var tdElem5 = $("<td />");
		tdElem5.append("<textarea name='content' class='updateContentForm' data-cmno="+comment_no+"></textarea>")
		
		trElem3.append(tdElem5);
		
		var tdElem6 = $("<td />").addClass("commentContentSub");
		
		tdElem6.append($("<input />", {
			"type" : "submit",
			"value" : "수정",
			"class" : "commentUpdateSubmitBtn",
			"data-cmno" : comment_no
		}));
		 
		trElem3.append(tdElem6);
		
		tableElem3.append(tbodyElem3).append(trElem3);
		
		divElem2.append(tableElem1);
		divElem2.append(tableElem2);
		divElem2.append(tableElem3);
		
		formElem.append(divElem2);
		
		appendArea.append(formElem);
		appendArea.append("<div class='comment-box-line'></div>");
		
		$(".updateContentForm").hide(); // 댓글 수정폼 숨김
		$(".commentUpdateSubmitBtn").hide(); // 수정 서브밋 버튼 숨김
		$(".commentCancelBtn").hide();
		
		hideCommentBtn();
	}
}
 
$(function() {
	$(".projectStatusSpan.p").text("진행중");
   	$(".projectStatusSpan.c").text("완료");
    $(".projectStatusSpan.w").text("보류");
    $(".projectStatusSpan.s").text("중단");
});
