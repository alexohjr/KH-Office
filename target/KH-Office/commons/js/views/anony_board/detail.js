// 댓글 유효성 검사
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
});


//댓글 ajax로 받아오기
$(function() {
   $("#getMoreCommentBtn").on("click", function() {
	   
	   console.log("더보기 버튼 클릭");
      
      var viewNum = $("#viewNum").attr("data-viewNum");
      var aboard_no = $("#aboard_no").val();
      
      var data = {
            "viewNum" : viewNum,
            "aboard_no" : aboard_no
      };
      
      
      $.ajax({
         url: utils.getContextPath() + "/anony_comment/ajax/" + viewNum,
         type : "GET",
         data : data,
         dataType : "json",
         success: function(data) {
            
        	console.log("송수신 성공1");
            $("#viewNum").attr("data-viewNum", data.viewNum);
            console.log("송수신 성공2");
            console.log("gd" + data.commentlist)
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
      var comment_no = list[i].acomment_no;
      var thumb_path = list[i].thumb_path;
      var name = list[i].name;
      var position = list[i].position;
      var reg_date = list[i].reg_date;
      var member_no = list[i].member_no;
      var aboard_no = list[i].aboard_no;
      var content = list[i].content;
       
      
      var formElem = $("<form />", {
         "action" : utils.getContextPath() + "/anony_comment/" + comment_no,
         "method" : "post",
         "class" : "commentForm"
      });
       
      var divElem1 = $("<div />").addClass("thumbnailDiv");
      
      divElem1.append("<p><img src="+utils.getContextPath()+"/resources/icon/noimage.jpg></p>");
      formElem.append(divElem1); // 첫번째 div 넣음    
      //// 해결
      
      var divElem2 = $("<div />").addClass("commenListDiv");
      
      var tableElem1 = $("<table />").addClass("commentMemberList"); // 첫번째 table

      var tbodyElem1 = $("<tbody />");
      
      var trElem1 = $("<tr />"); // 첫번째  tr
      
      var tdElem1 = $("<td />").addClass("commentMemberInfoP"); // 첫번째 td
      tdElem1.append("<p>익명</p>");
      
      trElem1.append(tdElem1);
      
      var tdElem2 = $("<td />"); // 두번째 td
      tdElem2.append("<p class='commentRegdateP'>"+reg_date+"</p>");
      
      trElem1.append(tdElem2);
      
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
      
      divElem2.append(tableElem1);
      divElem2.append(tableElem2);
      
      formElem.append(divElem2);
      
      appendArea.append(formElem);
      appendArea.append("<div class='comment-box-line'></div>");
   }
}


// 버튼 글씨
function formSubmit() {
	document.getElementById(".commentForm").submit();
}
