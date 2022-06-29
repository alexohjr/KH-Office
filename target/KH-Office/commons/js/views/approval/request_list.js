$(function(){

	
	// 각 li_status 클래스들을 모아 돌려서 상황에 맞게 class추가.
	$(".li_status").each(function(status){
		status = $(this).attr("data-status");
		console.log(status);
		
		if(status == '대기중'){
			$("td[data-status='대기중']").children("span").addClass("status_wait");
		} else if(status == '진행중'){
			$("td[data-status='진행중']").children("span").addClass("status_ing");
		} else if(status == '결재완료'){
			$("td[data-status='결재완료']").children("span").addClass("status_com");
		} else if(status == '반려'){
			$("td[data-status='반려']").children("span").addClass("status_turn");
		}
	});

	
	
	// 페이지번호 클릭될때마다 숫자아래 image넣기위한 class on 추가.
	url = window.location.href; // 현재 페이지의 URL
	var params = "";
	if(url){
		params = url.substring(url.indexOf('?')+1, url.length);
		params = params.split("&");
		
		var size = params.length;
		var key, value;
		for(var i=0; i< size; i++){
			key = params[i].split("=")[0];
			value = params[i].split("=")[1];
			
			params[key] = value;
			
		}
		var page_num = "";
		
		if(params.page_num == null){
			page_num = 1; // 처음 진입땐 page_num이없으므로 1처리.
		} else {
			page_num = params.page_num;
		}
		
		$(".page_num").each(function(data){
			data = $(this).attr("data-target");
			if(data == page_num){
				$(this).addClass("on");
			}
		});
		
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
	
	console.log("path : " , utils.getContextPath());
	
});