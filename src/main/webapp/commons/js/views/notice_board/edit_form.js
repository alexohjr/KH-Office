//에디터
window.onload = function()

{
	CKEDITOR.replace('content');
}



//유효성검사
$(function() {
	$("#submitModifyBtn").on("click", function() {
		var flag=false;
			
    if ($(".title").val()==="") {
       swal("제목을 입력하세요");
       return flag;
    }

    if (CKEDITOR.instances.content.getData() == '') {
    	swal("내용을 입력하세요");
       return flag;
    }
    return true;
 })
});