$(function(){
	//멤버 - 개인 비번 수정 버튼
	$("#password_edit_form_btn").on("click",function(){
		var memberNo = $(this).attr("data-member-no");
		location.href = utils.getContextPath() + "/member/form/" + memberNo + "?type=change_pw";
		});
	
	//어드민 - 수정버튼
	$("#edit_form_btn").on("click",function(){
		var memberNo = $(this).parent().attr("data-member-no");
		var pageNum = $(this).parent().attr("data-page-num");
		var keyword = $(this).parent().attr("data-keyword");
		var isBookmark = $(this).parent().attr("data-is-bookmark");
		location.href = utils.getContextPath() + "/member/form/" + memberNo + "?page_num=" +pageNum + "&keyword=" + keyword + "&is_bookmark=" + isBookmark;
		});
	
	//어드민 - 삭제버튼
	$("#delete_form_btn").on("click", function(){
		var name = $("#delete_form_btn").attr("data-name");
			
			swal({
		        title: name + "님을 퇴사 처리 하시겠습니까?",
		        icon: "warning",
		        buttons: true,
		        dangerMode: true,
		        buttons: ["아니오", "예"]
		    })
		    .then((willEdit) => {
		        if (willEdit) {
		            swal("퇴사 처리되었습니다.", {
		                icon: "success",
		            })
		            .then(function(data){
		            	if(data==true){
		            		 $("#detail_form").submit();
		            	}
		            });
		        } else {
		            swal("취소되었습니다.");
		        }
		    });
		return false;
	});
	
	//어드민 - 목록으로 돌아가기 버튼
	$("#list_btn").on("click",function(){
		var pageNum = $(this).parent().attr("data-page-num");
		var keyword = $(this).parent().attr("data-keyword");
		var isBookmark = $(this).parent().attr("data-is-bookmark");
		location.href = utils.getContextPath() + "/member?page_num=" + pageNum + "&keyword=" + keyword + "&is_bookmark=" + isBookmark;
	})
	
	
});