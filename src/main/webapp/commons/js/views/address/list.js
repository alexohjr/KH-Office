$(function(){	
	// 즐겨찾기 버튼
	$(".bookmark_btn").on("click", function(){
		var that = $(this);
		
		var addressNo = that.closest("ul.addressContents").attr("data-address-no");
		var pageNum = that.closest("ul.addressContents").attr("data-page-num");
		var keyword = that.closest("ul.addressContents").attr("data-keyword");
		var isBookmark = that.hasClass("on") == true ? 1 : 0; //클릭한 요소의 Class에 'on'이 있으면 1, 없으면 0
		
		var data = {
			"page_num" : pageNum,
			"keyword" : keyword,
			"is_bookmark" : isBookmark
		}
		
		$.ajax({
			url : utils.getContextPath() + "/address/ajax/" + addressNo,
	        type : "POST",
	        dataType : "json",
	        data : data,
	        success : function(result){
	        	that.toggleClass("on");
	        	
	        	var isUpdated = result.data;
	        	if(isUpdated){                             
	        		changeBookmarkImg(that);
	        	} else {
	        		swal("변경에 실패했습니다.");
	        	}
	        },
	        error : function(e){
	        	console.error(e);
	        	swal('통신실패!!');
	        }
	    });
	});
	
	//수정 버튼 클릭 시 인풋폼 보이게
	$(".edit_btn").on("click", function(){
		var listForm = $(this).closest(".listform");
		var inputForm = listForm.next();

		listForm.css({"display":"none"});
		inputForm.css({"display":"block"});
	});
	
	//취소버튼 클릭 시 다시 인풋 폼 가려지게
	$(".cancel_btn").on("click",function(){
	
	var inputForm = $(this).closest(".inputform");
	var listForm = inputForm.prev();
	    listForm.css({"display":"block"});
	    inputForm.css({"display":"none"});
	});

	//숫자만 입력받도록
	$("#phone").on("keyup", function() {
		$(this).val($(this).val().replace(/[^0-9]/g,""));
	
	});
	
	//숫자만 입력받도록
	$("#edit_phone").on("keyup", function() {
		$(this).val($(this).val().replace(/[^0-9]/g,""));
	
	});

	//이메일 유효성검사, 이름 검사
	$("#add_form").on("submit",function(){
		var email = document.getElementById("email").value;
		var name = document.getElementById("name").value;
		var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
		var result = false;
			if(exptext.test(email)==false){ 
				swal("이메일형식이 올바르지 않습니다.");
				 $("#email").focus(); 
			} else if(name ==""){
				swal("이름은 필수입력 항목입니다.");
				$("#name").focus();
			} else{
				result = true;
			}
		
		return result;
	});
	
	//수정 폼 이메일 유효성 검사
	$(".inputform").on("submit",function(){
		var edit_email = document.getElementById("edit_email").value;
		var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
		var result = false;
			if(exptext.test(edit_email)==false){ 
				swal("이메일형식이 올바르지 않습니다.");
				 $("#edit_email").focus(); 
			} else{
				result = true;
			}
		
		return result;
	});
	
	




});

// 인자로 받은 엘리먼트의 src 값을 변경하는 함수
function changeBookmarkImg(that){
	var newSrc = "";
	var srcStr = that.attr("src");

	// 현재 클릭한 요소가 src 값이 bookmark.png 인가?
	var isBookmark = srcStr.indexOf("non_bookmark") == -1 ? true : false;
	
	if(isBookmark){
		// none 으로 바꾸기
		newSrc = "non_bookmark.png";
	} else {
		// bookmark로 바꾸기
		newSrc = "bookmark.png";
	}
	that.attr("src", utils.getContextPath() + "/resources/icon/" + newSrc);
}


