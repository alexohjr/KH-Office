//댓글등록
function btn_js_alert_click() {
	swal({
		title : "등록완료",
		text : "댓글 등록이 완료되었습니다.",
		icon : "success",
		button : "확인",
	});
}

// 글 삭제 submit
$(function() {
	$(".deleteBtn").on("click", function() {
		
		swal({
			title : "정말 삭제하시겠습니까?",
			text : "한번 삭제된 후에는 되돌릴 수 없습니다.",
			icon : "warning",
			buttons : true,
			dangerMode : true,
			buttons : [ "아니오", "예" ]
		}).then(function(isConfirm) {
			if (isConfirm) {
				swal("프로젝트 수정이 완료되었습니다.", {
					icon : "success",
				}).then(function(data) {
					if (data == true) {
						$("#deleteForm").submit();
					}
				});
			} else {
				isValid = false;
				swal("프로젝트 수정이 취소되었습니다.");
			}
		})

		return false;
	});
});

//댓글 수정 유효성 검사
$(function() {
	// 댓글 등록
	$("#commentInsertBtn").on("click", function() {
		if ( $("#commentInsertTextarea").val() == null || $("#commentInsertTextarea").val() == "") {
			swal("내용을 입력해주세요.", "", "warning");
		} else {
			$("#commentInsertForm").submit();
		}
		return false;
	});
	
	// 수정
	$(document).on("click", ".commentUpdateSubmitBtn", function() {
		var cmno = $(this).attr("data-cmno");
		var content = $(".updateContentForm[data-cmno="+cmno+"]").val();
      
      	if(content == null || content == "") {
      		swal("내용을 입력해주세요.", "", "warning");
      		return false;
      	}
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

//댓글 수정/삭제 
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



//댓글 수정/삭제 
$(function() {
   
   hideCommentBtn();
   
   $(".updateContentForm").hide(); // 댓글 수정폼 숨김
   $(".commentUpdateSubmitBtn").hide(); // 수정 서브밋 버튼 숨김
   $(".commentCancelBtn").hide(); // 수정 취소 버튼 숨김
   
   $(document).on("click", ".commentUpdateBtn", function() {
      console.log("버튼 클릭");
      $(this).hide(); // 수정 폼 진입 버튼 숨김
      var cmno = $(this).attr("data-cmno"); // 클릭한 버튼의 코멘트 넘버
      console.log("cmno :: " + cmno);
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

//댓글 ajax로 받아오기
$(function() {
   $("#getMoreCommentBtn").on("click", function() {
	   
	   console.log("더보기 버튼 클릭");
      
      var viewNum = $("#viewNum").attr("data-viewNum");
      var dboard_no = $("#dboard_no").val();
      
      var data = {
            "viewNum" : viewNum,
            "dboard_no" : dboard_no
      };
      
      
      $.ajax({
         url: utils.getContextPath() + "/depart_comment/ajax/" + viewNum,
         type : "GET",
         data : data,
         dataType : "json",
         success: function(data) {
            
        	console.log("송수신 성공1");
            $("#viewNum").attr("data-viewNum", data.viewNum);
            console.log("송수신 성공2");
            
            insertMoreComment(data.commentlist);
            
            // 바디에 nicescroll 다시 먹이기
     	   	$("body").getNiceScroll().resize();
         },
         error: function(jqXHR, textStatus, errorThrown){
               alert("에러 발생 \n" + textStatus + " : " + errorThrown);
           }
      });
   });
});



//받아온 댓글 화면에 출력하기
function insertMoreComment(list) {
   var appendArea = $(".appendArea");
   
   if(list.length == 0) {
      swal("더이상 불러올 댓글이 없습니다.","","info");
      return false;
   } 
   
   
   for (var i=0; i<list.length; i++) {
      var comment_no = list[i].dcomment_no;
      var thumb_path = list[i].thumb_path;
      var name = list[i].name;
      var position = list[i].position;
      var reg_date = list[i].reg_date;
      var member_no = list[i].member_no;
      var dboard_no = list[i].dboard_no;
      var content = list[i].content;
       
      
      var formElem = $("<form />", {
         "action" : utils.getContextPath() + "/depart_comment/" + comment_no,
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
         "name" : "dboard_no",
         "class" : "dboard_no",
         "value" : dboard_no
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

// 버튼 글씨
function formSubmit() {
	document.getElementById(".commentForm").submit();
}
