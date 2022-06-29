$(function(){
   // 로드시 화면전환 효과 제거
   $(".loader-moving").addClass("end");
   
   // 스크롤시 출퇴근 위젯 이동하는 함수 호출
   scrollCheckWorkWidgetShrink();
   
   // 바디에 nicescroll 먹이기
   $("body").niceScroll({
		cursorcolor:"#283442",
		cursorwidth:"5px",
		cursordragontouch: true
	});

   $(".offStartWork").attr("disabled", true);
   $(".offEndWork").attr("disabled", true);
   
   // 사원선택모듈
   // 데이터 없는 부서 클릭시 사원 가져오기
   $(".member_select_area >ul >li.department_li.off").on("click", getMemberByDepartmentNo);
   
   // 데이터 들어와있는 부서 클릭시 슬라이드 토글
   $(document).on("click", ".member_select_area >ul >li.department_li.on", function(){
      changePlusMinusImg($(this));
      $(this).next("ul").slideToggle();
   });
   
   // 출근버튼 클릭시
   $(".check_work_area .btn_area .start_btn").on("click", sendStartWorkAjax);
   
   // 퇴근버튼 클릭시
   $(".check_work_area .btn_area .end_btn").on("click", sendEndWorkAjax);
   
}); /*** 레디펑션 끝 ***/




// 부서 번호로 해당 부서의 사원들 가져오기
function getMemberByDepartmentNo(){
   var that = $(this);
   var departmentNo = that.attr("data-department-no");
   var data = {
         "departmentNo" : departmentNo
   }
   
   $.ajax({
        url: utils.getContextPath() + "/member/ajax",
        type: "GET",
        data: data,
        dataType: "json",
        success: function(data){
        	appendDepartmentMember(that, data.data);
        	
        	// 사원들이 들어온 상태
        	// 채팅창에서 채팅상대 추가 모듈 동작시
        	// 현재 존재하는 회원이면 선택 못하도록 막기
        	if(typeof checkAndExceptChatMember != 'undefined'){
        		checkAndExceptChatMember();
        	}
        	
        	// 사원선택 모듈 동작시
        	// 본인(세션)이면 선택 못하도록 막기
        	// 채팅방 생성, 프로젝트 생성시 동작
        	if(typeof checkAndExceptSessionId != 'undefined'){
        		checkAndExceptSessionId();
        	}
        	
        	// 프로젝트 수정폼에서 기존 선택된 사원을 걸러내기 위한 이벤트 강제호출 => edit_form.js 에서 이벤트 캐치
        	$("#loadMemberStatus").val(1).trigger('change');
        	
        	// 바디에 nicescroll 다시 먹이기
    	   $("body").getNiceScroll().resize();
    	   
    	   // 프로젝트 생성페이지에서 팀원선택란 스크롤 리사이징
    	   if($("#projectMemberSelect") != 0){
    		   $("#projectMemberSelect").getNiceScroll().resize();
    	   }
    	   
    	   // 자산예약 페이지에서 팀원선택란 스크롤 리사이징
    	   if($(".member_select_area") != 0){
    		   $(".member_select_area").getNiceScroll().resize();
    	   }
    	   
        },
        error: function(jqXHR, textStatus, errorThrown){
            alert("에러 발생 \n" + textStatus + " : " + errorThrown);
            console.log("textStatus : " , textStatus );
            console.log("errorThrown : " , errorThrown );
        }
    });
} /*** getMemberByDepartmentNo - End - ***/

function appendDepartmentMember(that, memberArr){
   changePlusMinusImg(that);
   // ul 만들기
   var ulElem = $("<ul />"); 
      
   for(var i = 0; i < memberArr.length; i++){
      var member = memberArr[i];
      // li 만들기
      var liElem = $("<li />", {
         "data-member-no" : member.member_no,
         "data-email" : member.email,
         "data-name" : member.name,
         "data-position" : member.position,
         "data-thumb-path" : member.thumb_path
      }).addClass("member_li");
      
      var spanElemThumb = $("<span />");
      var imgElem = $("<img />", {
         "src" : utils.getContextPath() + "/" + member.thumb_path,
         "alt" : member.position + " " + member.name + "님의 사진"
      });
      
      spanElemThumb.append(imgElem);
      
      var spanElemPosition = $("<span />").text(member.position);
      var spanElemName= $("<span />").text(member.name);
      
      // li 안에 태그 넣기
      liElem.append(spanElemThumb)
      .append(spanElemPosition)
      .append(spanElemName);
      
      // ul 안에 li 넣기
      ulElem.append(liElem);
   }
   // 완성된 ul 을 클릭요소 뒤에 삽입
   that.after(ulElem);
   
   // 클래스 추가 및 삭제
   that.removeClass("off");
   that.addClass("on");
   
   // 이벤트 바인딩 제거
   that.off("click", getMemberByDepartmentNo);
   
}
/*** appendDepartmentMember - End - ***/

function changePlusMinusImg(that){
	var displayValue = that.next("ul").css("display")
	var originSrc = that.find("img").attr("src" ); 
	if(displayValue == "block"){
		var replaceSrc = originSrc.replace("minus", "plus");
	} else if (displayValue == "none" || displayValue == undefined){
		var replaceSrc = originSrc.replace("plus", "minus");
	}
	that.find("img").attr("src", replaceSrc);
}
/*** changePlustMinusImg - End - ***/




// 시계 위젯 js
/*document.addEventListener('DOMContentLoaded', () =>
requestAnimationFrame(updateTime)
)

function updateTime() {
document.documentElement.style.setProperty('--timer-day', "'" + moment().format("dd") + "'");
document.documentElement.style.setProperty('--timer-hours', "'" + moment().format("k") + "'");
document.documentElement.style.setProperty('--timer-minutes', "'" + moment().format("mm") + "'");
document.documentElement.style.setProperty('--timer-seconds', "'" + moment().format("ss") + "'");
requestAnimationFrame(updateTime);
}*/


//출근하기 ajax
function sendStartWorkAjax(){
   
   var startBtn = event.path[0];
   
   var todayHasStartWork = moment().format("YYYY-MM-DD");
   var member_no = "출근";
   var data = {
         "start_time" : 1
   };
   url = window.location.pathname;
   var params = url.substring(url.indexOf('/',2)+1, url.length-2) +"/start";
   
   $.ajax({
      'url' : utils.getContextPath() + "/work_history/ajax/"+member_no,
      'type' : "POST",
      'data' : data,
      'dataType' : "json",
      success : function(data){
         swal("출근하셨습니다 !","좋은 하루되세요^^","success");
         var work_history_no = data.work_history_no;
         $(startBtn).removeClass("onStartWork");
         $(startBtn).addClass("offStartWork");
         $(startBtn).attr("disabled", true);
         $(startBtn).next().removeClass("offEndWork");
         $(startBtn).next().addClass("onStartWork");
         $(startBtn).next().removeAttr("disabled");
         $(startBtn).next().attr("data-url","work_history/end");
         $(startBtn).next().attr("work_history_no",work_history_no);
         
         if($(startBtn).attr("data-url") == params){
            var start = "start";
            appendGetDataWorkHistory(start,work_history_no);
         }
         
      },
      error : function(e){
         console.log("error : " , e);
      }
   
   });
} /*** senStartWorkAjax - End - ***/


//퇴근하기 ajax
function sendEndWorkAjax(){
   var endBtn = event.path[0];
   var d = new Date().getDate();
   
   var todayHasEndWork = moment().format("YYYY-MM-DD");
   var member_no = "퇴근";
   var data = {
         "end_time" : 1,
         "member_no" : member_no
   };
   url = window.location.pathname;
   var params = url.substring(url.indexOf('/',2)+1, url.length-2) +"/end";
   
   $.ajax({
      'url' : utils.getContextPath() + "/work_history/ajax/"+member_no,
      'type' : "POST",
      'data' : data,
      'dataType' : "json",
      success : function(data){
         swal("퇴근하셨습니다 !","오늘 하루 수고하셨어요","success");
         $(endBtn).removeClass("onStartWork");
         $(endBtn).addClass("offStartWork");
         $(endBtn).attr("disabled", true);
         
         if($(endBtn).attr("data-url") == params){
            var end = "end";
            appendGetDataWorkHistory(end,data);
            /** 퇴근시간 찍은 후 3초뒤 refresh하는 기능. **/
            /*setTimeout(function(){
                 javascript:window.location.reload(true);
            },3000);*/
         }
         
      },
      error : function(e){
         console.log("error : " , e);
      }
   });
   
} /*** sendEndWorkAjax - End -  ***/




/*** history/{member_no} url경로일때에만
      start_time / end_time insert후 시간을 가져와 append ***/
function appendGetDataWorkHistory(data,work_history_no){
   
   var date = new Date();
   
   var d = date.getDate();
   var h = date.getHours().toString();
   var m = date.getMinutes().toString();
   var s = date.getSeconds().toString();
   
   if(h <= 12 && h >= 0){
      h = "오전 "+h 
   } else {
      h = "오후 "+h
   }
   if(m.length < 2){
      m = "0"+m;
   }
   if(s.length < 2){
      s = "0"+s;
   }
   
   if(data == "start"){
      var start_work_time = h+":"+m+":"+s;
      var li_start_work = $(".li_start_work[data-date="+d+"]");
         li_start_work.append(start_work_time);
      var li_memo = $(".li_memo[data-date="+d+"]");
         li_memo.attr("data-workhistory-no",work_history_no);
   } else if(data =="end"){
      var end_work_time = h+":"+m+":"+s;
      var li_end_work = $(".li_end_work[data-date="+d+"]");
      li_end_work.append(end_work_time);
   }
} /** appendGetDataWorkHistory - End - **/







//스크롤시 출퇴근위젯 올리기
function scrollCheckWorkWidgetShrink() {
	window.addEventListener('scroll', function(e) {
		var distanceY = window.pageYOffset || document.documentElement.scrollTop; // 내려간 세로거리
		var shrinkOn = 200; // 기준점인 y 픽셀.

		if (distanceY > shrinkOn) { // 내려간 경우
			$(".check_work_area").stop().transition({top:"calc(12% - 50px)"}, 500);
		} else { // 내려가지 않은 경우
			$(".check_work_area").stop().transition({top:"calc(16% - 50px)"}, 500);
		}
	});
}
