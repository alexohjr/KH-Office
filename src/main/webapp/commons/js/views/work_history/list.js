$(function(){
	// 바디에 nicescroll 먹이기
   $(".admin_list_table_wrap").niceScroll({
		cursorcolor:"#283442",
		cursorwidth:"5px",
	});
	
	/* datepicer 기능 */
	$('.date-picker').datepicker( {
		showOn: "button",
		buttonImage: utils.getContextPath()+"/resources/icon/calendar.png",
		buttonImageOnly: true, // 버튼에 있는 이미지만 표시한다.
        changeYear: true,
        changeMonth: true,
        showButtonPanel: true,
        currentText : "오늘",
        closeText : "선택",
        dateFormat: 'yy MM',
        monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월',
         '11월', '12월' ],
        monthNamesShort : [ 1, 2, '3', '4', '5', '6', '7', '8', '9', '10', '11', '12' ],
        onClose: function(dateText, inst) { 
            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
            $(this).datepicker('setDate', new Date(year, month, 1));
            var department_no = $(this).attr("data-department-no");
            var page_num = $(this).attr("data-page-num");
            
            window.location.href=utils.getContextPath()+'/work_history?department_no='+department_no+'&page_num='+page_num+'&strYear='+year+'&strMonth='+month;
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

	/* admin_list에서 오늘 날짜 표시하기위한 'todayCheck' 추가 로직. */
	var date = new Date();
	var today = date.getDate();
	$(".admin_work_history_list tr").find(":not(.td_start_work)td:eq("+today+')').addClass("todayCheck");
	$(".admin_work_history_list tr").find(".td_start_work:eq("+(today-1)+')').addClass("todayCheck");
	
	/*  */
	var todayYear = $(".right_content_inner_YM").attr("data-year");
	var todayMonth = parseInt($(".right_content_inner_YM").attr("data-month"));
	
	
	$(".today_dates").each(function(){
		var todayDay = $(this).attr("data-day");
		var saturday = $("<span/>",{
			'class' : 'todaySunday_days'
		}).text("(토)");
		
		var sunday = $("<span/>",{
			'class' : 'todaySunday_days'
		}).text("(일)");
		
		
		if(todayDay == "토"){
			$(this).addClass("todaySaturday");
			$(this).append(saturday);
		} else if (todayDay == "일"){
			$(this).addClass("todaySunday");
			$(this).append(sunday);
		}
	});
	
	// 선택한 부서의 넘버값 가져오기
	var bodyDepartNo = $(".right_content_inner_top h3").attr("data-dno");
	
	// 선택한 부서의 넘버값과 같은 부서명에 css 
	$(".left_menu_inner_department_wrap a[data-department-no="+bodyDepartNo+"]").css({"font-weight" : "bold"});
	
	var depart_name = $(".left_menu_inner_department_wrap a[data-department-no="+bodyDepartNo+"]").text();
	console.log("gdgd" + depart_name);
	
	$(".right_content_inner_top h3").text(depart_name);
	
	
}); /*** readyFunction - End- ***/


function getToday(){
	 
	var week = new Array('일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일');
    var today = new Date().getDay();
    var todayLabel = week[today];
	    
    return todayLabel; 
}

$(function() {
	
	
});
