$(function(){
	$("header").remove();
	swal("로그인 실패입니다. 로그인 정보를 다시 확인해주세요.")
	.then(() => {
		history.go(-1);
	});
	
});