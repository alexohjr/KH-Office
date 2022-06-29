/*
function button_event() {
	e.preventDefault();
	if (confirm("정말 삭제하시겠습니까??") == true) { // 확인
		document.form.submit();
	} else { // 취소
		return false;
	}
}
*/

$(function(){
	$("#submitBtn").on("click", function() {

		swal({
			title : "삭제 하시겠습니까?",
			icon : "warning",
			buttons : true,
			dangerMode : true,
			buttons : [ "아니오", "예" ]
		}).then(function(isConfirm) {
			if (isConfirm) {
				swal("삭제 되었습니다.", {
					icon : "success",
				})
				.then(function(data) {
					if (data == true) {
						$("#holding_assets_delete_form").submit();
					}
				});
			} else {
				isValid = false;
				swal("삭제가 취소 되었습니다.");
			}
		})

		return false;
	});
	
	$("#holding_form").click(function(){
		location.href="holding_assets/form"
	});
});