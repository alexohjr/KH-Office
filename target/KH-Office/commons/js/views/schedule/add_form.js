$(function() {
	// 로드시 헤더 제거
	$("header").remove();
	
	// 로드시 위젯 제거
	$(".check_work_area").remove();
	
	$("#startDate").datepicker({
		dateFormat : 'yy-mm-dd'
	});
	$("#endDate").datepicker({
		dateFormat : 'yy-mm-dd'
	});
});
$(function() {
	$("#submitBtn").on("click", function() {
		var flag = false;
		var isValid = true;

		swal({
			title : "일정을 저장 하시겠습니까?",
			icon : "warning",
			buttons : true,
			dangerMode : true,
			buttons : [ "아니오", "예" ]
		}).then(function(isConfirm) {
			if (isConfirm) {
				swal("일정 저장이 완료 되었습니다.", {
					icon : "success",
				}).then(function(data) {
					if (data == true) {
						
						var name = $("#name").val();
						var startDate = $("#startDate").val();
						var endDate = $("#endDate").val();
						console.log(name);
						console.log(startDate);
						console.log(startDate);
						var data = {
							"name" : name,
							"startDate" : startDate,
							"endDate" : endDate}
						
						$.ajax({
							url : utils.getContextPath() + "/schedule",
							type : "POST",
							data : data,
							dataType : "json",
							success : function(args) {
								if(args.data===1){
									opener.parent.location.reload();
									self.close();
								}
						
									},

									error : function(jqXHR, textStatus,	errorThrown) {
										alert("에러 발생 \n" + textStatus + " : " + errorThrown);
										console.log("textStatus : ",textStatus);
										console.log("errorThrown : ",errorThrown);
									}
								});
						
						
						
					}
				});
			} else {
				isValid = false;
				swal("일정 저장을 취소 하셨습니다.");
			}
		})


		if ($("#Name").val() == "") {
			swal("일정 내용을 입력하세요.");
			return flag;
		}

		if ($("#startDate").val() === "") {
			swal("일정 시작 날짜를 입력하세요.");
			return flag;
		}
		if ($("#endDate").val() === "") {
			swal("일정 끝나는 시간을 입력하세요.");
			return flag;
		}

		return flag;
		
		
	});

});
