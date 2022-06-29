$(function(){
	
	var status = $(".nameColor")
	for ( var i=0; i<status.length; i++){
		statusDate = $(status[i]).attr("data-color");
		if(statusDate == "1"){
			$(".nameColor[data-color="+statusDate+"]").addClass("schedule");
			//$(".nameColor[data-color="+statusDate+"]").removeClass("nameColor");
		}else if(statusDate == "2"){
			$(".nameColor[data-color="+statusDate+"]").addClass("project");
			$(".nameColor[data-color="+statusDate+"]").removeClass("nameColor");
		}else if(statusDate == "3"){
			$(".nameColor[data-color="+statusDate+"]").addClass("vacation");
			$(".nameColor[data-color="+statusDate+"]").removeClass("nameColor");
		}
	}
	
	$(".nameColor").hover(function() {
		   var hname = $(this).attr("data-hName");
		   var hnum = $(this).attr("data-scheduleNo");
		   $(this).css("color", "red");
		   $(this).text("삭제");
		   
		   $(this).on("click",function(){
			   swal({
					title : "개인일정을 삭제 하시겠습니까?",
					icon : "warning",
					buttons : true,
					dangerMode : true,
					buttons : [ "아니오", "예" ]
				}).then(function(isConfirm) {
					if (isConfirm) {
						swal("개인일정이 삭제 되었습니다.", {
							icon : "success",
						})
						.then(function(data) {
							if (data == true) {
								$(".schedule_delete_form"+hnum).submit();
							}
						});
					} else {
						isValid = false;
						swal("개인일정 삭제를 취소 하였습니다.");
					}
				});

				return false;
		   });
		}, function(){
			var hname = $(this).attr("data-hName");
			$(this).css("color", "white");
		    $(this).text(hname);
	});
	
	$(".submitBtn").hide();
	
	$(function() {
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
	        monthNames : [ '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12' ],
			monthNamesShort : [ '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12' ],
	        onClose: function(dateText, inst) { 
	            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
	            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
	            $(this).datepicker('setDate', new Date(year, month, 1));
	            window.location.href=utils.getContextPath()+'/schedule?year='+year+'&month='+month;
	        }
	    });
	});
	
	
});


function add_holiday() {
   url = "/schedule/form";
   utils.popupCenter(utils.getContextPath() + url, "일정추가", 700, 400);
}
