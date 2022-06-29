//삭제 submit
$(function() {
	$(".deleteBtn").on("click", function() {

		swal({
          title: "정말 삭제하시겠습니까?",
          text: "한번 삭제된 후에는 되돌릴 수 없습니다.",
          icon: "warning",
          buttons: true,
          dangerMode: true, 
          buttons: ["아니오", "예"]
      })
      .then(function(isConfirm) {
         if(isConfirm) {
        	 swal("프로젝트 수정이 완료되었습니다.", {
             icon: "success",
       		})
       		.then(function(data){
         if(data == true){
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