$(function() {
	$(".selectName").on("change", function() {
		var name = $(".selectName option:selected").attr("data-name");
		var hno = $(".selectName option:selected").attr("data-hAssets-no");
		$(".select_name").empty();
		$(".startOption").remove();
		$(".endOption").remove();
		$(".dateDay").val("");
		$(".people").val('');
		$(".purpose").val('');
		$(".select_name").append(name);
		$(".hAssets_no").val(hno);
	});

	$(".dateDay")
			.on(
					"change",
					function() {
						var hAssets_no = $(".hAssets_no").val();
						var day = $(".dateDay").val();

						var data = {
							"hAssets_no" : hAssets_no,
							"day" : day
						}
						$
								.ajax({
									url : utils.getContextPath()
											+ "/reserve_assets/ajax_start",
									type : "GET",
									data : data,
									dataType : "json",
									success : function(args) {
										$(".startOption").remove();
										for (var idx = 0; idx < args.data.length; idx++) {
											$(".startTimeSelector").append(
													"<option class='startOption' value='"
															+ args.data[idx]
															+ "'>"
															+ args.data[idx]
															+ "</option>");
										}
									},

									error : function(jqXHR, textStatus,
											errorThrown) {
										alert("에러 발생 \n" + textStatus + " : "
												+ errorThrown);
										console
												.log("textStatus : ",
														textStatus);
										console.log("errorThrown : ",
												errorThrown);
									}
								});
					});

	$(".startTimeSelector").on(
			"change",
			function() {
				var hAssets_no = $(".hAssets_no").val();
				var day = $(".dateDay").val();
				var startTime = $(".startTimeSelector").val();

				var data = {
					"hAssets_no" : hAssets_no,
					"day" : day,
					"startTime" : startTime
				}
				$.ajax({
					url : utils.getContextPath() + "/reserve_assets/ajax_end",
					type : "GET",
					data : data,
					dataType : "json",
					success : function(args) {
						$(".endOption").remove();
						for (var idx = 0; idx < args.data.length; idx++) {
							$(".endTimeSelector").append(
									"<option class='endOption' value='"
											+ args.data[idx] + "'>"
											+ args.data[idx] + "</option>");
						}
					},

					error : function(jqXHR, textStatus, errorThrown) {
						alert("에러 발생 \n" + textStatus + " : " + errorThrown);
						console.log("textStatus : ", textStatus);
						console.log("errorThrown : ", errorThrown);
					}
				});
			});

});

$(function() {
	$("#submitBtn").on("click", function() {
		var flag = false;
		var isValid = true;

		swal({
			title : "예약을 하시겠습니까?",
			icon : "warning",
			buttons : true,
			dangerMode : true,
			buttons : [ "아니오", "예" ]
		}).then(function(isConfirm) {
			if (isConfirm) {
				swal("예약이 완료 되었습니다.", {
					icon : "success",
				}).then(function(data) {
					if (data == true) {
						$("#reserve_assets_add_form").submit();
					}
				});
			} else {
				isValid = false;
				swal("예약을 취소하였습니다.");
			}
		})

		//regNumber = /^[0-9]*$/;

		if ($(".selectName option:selected").val() == "") {
			swal("자산을 선택 해 주세요.");
			return flag;
		}

		if ($(".dateDay").val() === "") {
			swal("날짜를 선택 해 주세요.");
			return flag;
		}
		if ($(".startTimeSelector").val() === "") {
			swal("시작시간을 선택 해 주세요.");
			return flag;
		}
		if ($(".endTimeSelector").val() === "") {
			swal("마지막 시간을 선택 해 주세요.");
			return flag;
		}
		if ($(".people").val() === "") {
			swal("인원을 입력 해 주세요");
			return flag;
		}
		/*if (!regNumber.test('입력값')) {
			swal("숫자만 입력해주세요.");
			return flag;
		}
*/
		if ($(".purpose").val() === "") {
			swal("사유를 작성 하세요.");
			return flag;
		}

		return flag;
	});
	
	var pageNum = $(".pageNum").val();
	var day = $(".day").val();
	console.log(pageNum);
	console.log(day);
	
	
	$(document).on("click","#add_form_cancle",function() {
		location.href = utils.getContextPath() + "/reserve_assets?day="+day+"&pageNum="+pageNum;
	});
});
