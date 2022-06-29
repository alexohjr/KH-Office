$(function () {
	var pageNum = $("#datepicker").attr("data-target-pageNum");
	var reserve = $(".reserve");
	
	// 예약현황 값 설정하기
	 for (var i = 0; i < reserve.length; i++) {
        var reserveData = $(reserve[i]).attr("data-time");
        if (reserveData == "-") {
            $(".reserve[data-time=" + reserveData + "]").empty();
            $(".reserve[data-time=" + reserveData + "]").addClass("none");
            $(".reserve[data-time=" + reserveData + "]").removeClass("reserve");

        } else if (reserveData != "-") {
        }
    }

    $.datepicker.setDefaults({
        dateFormat: 'yy-mm-dd',
        prevText: '이전 달',
        nextText: '다음 달',
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월',
            '11월', '12월'],
        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월',
            '10월', '11월', '12월'],
        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        showMonthAfterYear: true,
        yearSuffix: 'Year'
    });

	$('#datepicker').datepicker();

    // 예약 취소 버튼 클릭시
    $(".button_submit:not(#reserve_form)").on("click", function () {
    	var that = $(this);

        swal({
            title: "예약 취소 하시겠습니까?",
            icon: "warning",
            buttons: true,
            dangerMode: true,
            buttons: ["아니오", "예"]
        }).then(function (isConfirm) {
            if (isConfirm) {
                swal("예약 취소 되었습니다.", {
                    icon: "success",
                })
                    .then(function (data) {
                        if (data == true) {
                        	that.closest(".reserve_assets_delete_form").submit();
                        }
                    });
            } else {
                isValid = false;
                swal("예약취소를 중지 하였습니다.");
            }
        })

        // 무조건 서브밋을 막아준다.
        return false;
    });

    // 예약하기 버튼 클릭시
    $("#reserve_form").on("click", function () {
        var day = $("#datepicker").val();
        var pageNum = $("#datepicker").attr("data-target-pageNum");

        location.href = "reserve_assets/form?day=" + day + "&pageNum=" + pageNum;
    });

    //$('#datepicker').document.getElementById('datepicker').value= new Date();

    // datepicker 다음 버튼 클릭시
    $('.next-day').on('click', function (e) {
        e.preventDefault();
        var date = $('#datepicker').datepicker('getDate');

        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate() + 1;
        var fullDate = year + "-" + month + "-" + day;

        $('#datepicker').attr("value", fullDate);
        $('#datepicker').trigger("change");
    });

    // datepicker 이전 버튼 클릭시
    $('.prev-day').on('click', function (e) {
        e.preventDefault();
        var date = $('#datepicker').datepicker('getDate');

        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate() - 1;
        var fullDate = year + "-" + month + "-" + day;

        $('#datepicker').attr("value", fullDate);
        $('#datepicker').trigger("change");
    });

    // datepicker 오늘 버튼 클릭시
    $('.today-date').on('click', function (e) {
        e.preventDefault();
        var date = $.datepicker.formatDate("yy-mm-dd", new Date());

        $('#datepicker').attr("value", date);
        $('#datepicker').trigger("change");
    });

    // datepicker 값이 변경될 시
    $(document).on("change", "#datepicker", function () {
        var day = $(this).val();

        console.log(pageNum);
        location.href = "reserve_assets?day=" + day + "&pageNum=" + pageNum;
    });   

});