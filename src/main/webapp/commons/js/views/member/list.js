// 즐겨찾기 버튼
$(function(){	
	$(".bookmark_btn").on("click", function(){
		var that = $(this);
		
		var memberNo = that.closest("ul.memberContents").attr("data-member-no");
		var pageNum = that.closest("ul.memberContents").attr("data-page-num");
		var keyword = that.closest("ul.memberContents").attr("data-keyword");
		var isBookmark = that.hasClass("on") == true ? 1 : 0; //클릭한 요소의 Class에 'on'이 있으면 1, 없으면 0
		
		var data = {
			"page_num" : pageNum,
			"keyword" : keyword,
			"is_bookmark" : isBookmark
		}
		
		$.ajax({
			url : utils.getContextPath() + "/member/ajax/" + memberNo,
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
	
	$(".clickArea").on("click",function(){
		var memberNo = $(this).closest("ul.memberContents").attr("data-member-no");
		var pageNum = $(this).closest("ul.memberContents").attr("data-page-num");
		var keyword = $(this).closest("ul.memberContents").attr("data-keyword");
		var isBookmark = $(this).closest("ul.memberContents").attr("data-is-bookmark");
		location.href = utils.getContextPath() + "/member/" + memberNo + "?page_num=" + pageNum + "&keyword=" + keyword + "&is_bookmark=" + isBookmark;
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