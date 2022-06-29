$(function(){
	
	
	// response_list에 나열되는 항목들중 오른쪽에있는 div에 'mr0'클래스 추가
	$(".response_list_content:nth-child(even)").addClass("mr0");
	
	
	var checkListClass = $("div").hasClass("response_list_content");
	if(!checkListClass){
		var divElem = $("<div/>",{
			'class' : "response_list_content_empty"
		}).text("결재할 문서가 없습니다.");
		
		$(".right_response_list").append(divElem);
	}
	
	
	/******  .tab_menu를 url경로를 따와 .on을 추가	********/
	url2 = window.location.pathname;
	var project_name = url2.substring(url2.indexOf('/',2)+1, url2.length);
	$(".added_on").each(function(approval_menu){
		approval_menu = $(this).attr("data-target");
		
		if(approval_menu == project_name){
			$(this).addClass("on");
		}
	});
	
});