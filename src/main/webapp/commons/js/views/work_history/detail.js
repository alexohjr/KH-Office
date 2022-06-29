$(function(){
	
	/* datapicker 기능 */
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
            var member_no= $(".li_start_work").attr("data-member-no");
            $(this).datepicker('setDate', new Date(year, month, 1));
            window.location.href=utils.getContextPath()+'/work_history/'+member_no+'?strYear='+year+'&strMonth='+month;
        }
    });
	
	
	/* gps 그림클릭하면 ip보여주는 기능 */
	$(".start_ip, .end_ip").on("click", function(e){
		e.preventDefault();
		e.stopPropagation();
		var data_ip = $(this).attr("data-ip");
		swal("ip: " + data_ip);
	});

	
	/*** admin으로 로그인했을때에만 박혀진 'data-admin' , start_time과 end_time을 update / delete 가능. ***/
	var admin = $(".li_start_work, .li_end_work");
	var admin_isAttr = admin.attr("data-admin");
	
	if(admin_isAttr !== undefined){ // admin계정으로 로그인했을때에만 존재.
		
		/* 출근기록이 하나라도 있는곳에는 데이터 삭제할 수 있는 기능 추가. 그 외엔 존재 'X' */
		$(".li_memo").each(function(index, item){
			var historyNoData = $(this).attr("data-workhistory-no");
			
			if(historyNoData !== undefined){
				
				var  deleteBtn = $("<button/>",{
					'id' : 'deleteBtn',
					'class' : 'cssStyleDelBtn'
				}).text("삭제");
				$(this).after(deleteBtn);
			}
		});
		
		admin.on("click", function(){
			
			if($(this).hasClass("li_end_work")){
				var ttt = $(this).parents("tr").find(".li_start_work").find("span").hasClass("s_start_work");
				if(!ttt){
					swal("출근시간을 먼저 입력해주세요 !" , "출근이없으면 퇴근도 없습니다." , "error");
					return false;
				}
			}
			
			var cloneBackData = $(this).html();
			
			var backBtn = $("#rollBackBtn"); // flag처럼 존재하는지여부에따라 실행
			
			if(backBtn.length === 0){
				$(this).attr("data-backUp", cloneBackData);
				$(this).empty();
				var hourSelectElem = $("<select/>",{
					'name' : 'hour',
					'id' : 'hour'
				});
					for(var i=0; i <= 23; i++){
						var hourOptionElem = $("<option/>",{
							'value' : i
						}).text(i);
						hourSelectElem.append(hourOptionElem);
					}
				var minSelectElem =$("<select/>",{
					'name' : 'min',
					'id' : 'min'
				});
					for(var i=0; i<=60; i++){
						var minOptionElem =$("<option/>",{
							'value' : i
						}).text(i);
						minSelectElem.append(minOptionElem);
					}
				var secSelectElem =$("<select/>",{
					'name' : 'sec',
					'id' : 'sec'
				});
					for(var i=0; i<=60; i++){
						var secOptionElem =$("<option/>",{
							'value' : i
						}).text(i);
						secSelectElem.append(secOptionElem);
					}
				
				if($(this).hasClass("li_start_work")){ /* 출근영역클릭할때 submit */
					var submitBtn = $("<button/>",{
						'id' : 'submitBtn',
						'data-target' : '0',
						'class' : 'cssStyleBtn'
					}).text("수정");
				
				} else { /* 퇴근영역클릭할때 submit */
					var submitBtn = $("<button/>",{
						'id' : 'submitBtn',
						'data-target' : '1',
						'class' : 'cssStyleBtn'
					}).text("수정");
				}
				var rollBackBtn = $("<button/>",{
					'id' : 'rollBackBtn',
					'class' : 'cssStyleBackBtn'
				}).text("취소");
				
				var spanElem = $("<span/>",{
					'class' : 'span'
				}).text(" : ");
				
				var spanElem2 = $("<span/>",{
					'class' : 'span'
				}).text(" : ");
				
				$(this)
				.append(hourSelectElem)
				.append(spanElem)
				.append(minSelectElem)
				.append(spanElem2)
				.append(secSelectElem)
				.append("<p/>")
				.append(submitBtn)
				.append(rollBackBtn)
				
			} /* backBtn.length === 0 영역 - end */	
			
			
		}); /* .li_start_work .li_end_work 클릭이벤트 - end - */
		
	} /* admin_isAttr !== undefined - end - */
	
	
	/* 동적으로 생성된 수정기능들을 취소하는 기능 */
	$(document).on("click", "#rollBackBtn", function(){
		var backUp = $(this).parent().attr("data-backup");
		$(this).parent().empty();
		if(backUp !== undefined){
			$(".li_start_work[data-backup]").append(backUp);
			$(".li_start_work").removeAttr("data-backup");
			$(".li_end_work[data-backup]").append(backUp);
			$(".li_end_work").removeAttr("data-backup");
			
		
		} /* undefined 영역 끝 */
		
	}); /* #rollBackBtn 클릭 이벤트 -end- */
	
	
	/* admin계정 로그인 안한 본인계정으로만 메모 수정 
	  '상태' 영역은 본인만 수정할 수 있게끔 기능 구현   */
	if(admin_isAttr == null){ 
		$(".li_memo").on("click",function(e){
			var that = $(this);
			var data_memo = that.attr("data-memo");
			var work_history_no = $(this).attr("data-workhistory-no");
			var parent_tr = $(this).parent();
			var start_work = $(parent_tr).find(".li_start_work");
			
			var flag = true;
			
			if(that.find("input").length > 0){
				flag = false;
			} else {
				flag = true
			}
			
			if(work_history_no === undefined){
				swal("출석체크가되어야 상태변경 가능합니다.")
				flag = false;
			}
			
			
			if(flag){
				$(that).empty();
				var cancelBtn = $("<button/>",{
					'type' : "button",
					'id' : "cancelBtn"
				}).text("취소");
				
				var inputElem = $('<input/>',{
					'type' : "text",
					'onkeydown' : "javascript:enterKey();",
					'data-workhistory-no' : work_history_no,
					'name' : "memo",
					'id' : "memo"
				}).val(data_memo);
				
				$(this).append(inputElem).append(cancelBtn);
			}
		});
	} /* 메모 수정 End*/
	
	
	/* 동적으로 생성된 취소버튼을 누를때 로직 */
	$(document).on("click" , "#cancelBtn", function(){
		var parent_td = $(this).parent();
		var data_memo = $(parent_td).attr("data-memo");
		$(parent_td).find("input").remove();
		$(parent_td).find("button").remove();
		$(parent_td).text(data_memo);
	}); 
	
	
	/* 동적으로 생성된 수정버튼을 누를때 로직 */
	$(document).on("click", "#submitBtn" , function(){
		
		swal({
	        title: "데이터를 업데이트하시겠습니까?",
	        text: "수정된 후에는 되돌릴 수 없습니다.",
	        icon: "info",
	        buttons: true,
	        dangerMode: true,
	        buttons: ["아니오", "예"]
	    })
	    .then((willDelete) => {
	        if (willDelete) {
	        	
	        	/* 동적으로 생성된 select 데이터 */
	    		
	    		var year = $(this).parents("td").attr("data-year");
	    		var month = $(this).parents("td").attr("data-month");
	    		var date = $(this).parents("td").attr("data-date");
	    		
	    		if(month.length < 2){
	    			month = "0"+month;
	    		}
	    		if(date.length < 2){
	    			date = "0"+date;
	    		}
	    		
	    		var h = $("#hour").val();
	    		var m = $("#min").val();
	    		var s = $("#sec").val();
	    		
	    		if(h.length < 2){
	    			h = "0"+h;
	    		}
	    		if(m.length < 2){
	    			m = "0"+m;
	    		}
	    		if(s.length < 2){
	    			s = "0"+s;
	    		}
	    		
	    		var editYMD = year+"/"+month+"/"+date
	    		var editTime = h+":"+m+":"+s; // admin이 최종 결정한 시간 데이터
	    		
	    		var work_history_no = $(this).parents("tr").find(".li_memo").attr("data-workhistory-no"); // 선택한 영역의 work_history_no
	    		var selectMemberNo = $(this).parents("tr").attr("data-member-no"); // 어드민이 개인 상세페이지로 들어갔을때 그 개인의 member_no
	    		
	    		if(work_history_no != null){ // work_history_no가 있으므로 insert된 영역
	    			editTime = editYMD+" "+editTime
	    			var member_no = "어드민수정";
	    			var data_target = $(this).attr("data-target");
	    			var start_time = "";
	    			var end_time = "";
	    			if(data_target == 0){
	    				start_time = "출근수정";
	    			} else if(data_target == 1){
	    				end_time = "퇴근수정";
	    			}
	    			var data = {
	    					"work_history_no" : work_history_no,
	    					"member_no" : member_no,
	    					"editTime" : editTime,
	    					"start_time" : start_time,
	    					"end_time" : end_time
	    			};	
	    			
	    			$.ajax({
	    				'url' : utils.getContextPath() + "/work_history/ajax/"+member_no,
	    				'type' : "POST",
	    				'data' : data,
	    				dataType : "json",
	    				success : function(data){
	    					javascript:history.go(0);
	    				},
	    				error : function(e){
	    					console.log("error : " , e);
	    				}
	    			}); /** update ajax 끝 **//* */
	    			
	    		} /* work_history_no */ else { // work_history_no가 없으므로 insert되지 않은 영역. insert는 무조건 start부터
	    			
	    			editTime = editYMD+" "+editTime
	    			
	    			var member_no = selectMemberNo;
	    			var data_target = $(this).attr("data-target");
	    			var start_time = "";
	    			if(data_target == 0){
	    				start_time = "출근추가";
	    			}
	    			
	    			var data = {
	    					"member_no" : member_no,
	    					"editTime" : editTime,
	    					"start_time" : start_time,
	    					"admin" : "어드민추가"
	    			};	
	    			
	    			$.ajax({
	    				'url' : utils.getContextPath() + "/work_history/ajax/"+member_no,
	    				'type' : "POST",
	    				'data' : data,
	    				dataType : "json",
	    				success : function(data){
    						javascript:history.go(0);
	    				},
	    				error : function(e){
	    					console.log("error : " , e);
	    				}
	    			}); /** insert ajax 끝 **/
	    			
	    			
	    		} /* work_history_no가 없으므로 insert되지 않은 영역 - end - */
	        	
	        } else {
	            swal("수정이 취소되었습니다.");
	        }
	    });
		
		
		
		
	
	}); /** #submitBtn 영역 끝 **/
	
	
	
	/* 등록되어있는 데이터들만 뜨게되고, 삭제버튼을누르면 삭제되는 기능 */
	$(document).on("click", "#deleteBtn", function(){
		var work_history_no = $(this).prev().attr("data-workhistory-no");
			swal({
		        title: "모든 데이터가 사라집니다.",
		        text: " 정말 삭제하시겠습니까? \n한번 삭제된 후에는 되돌릴 수 없습니다.",
		        icon: "warning",
		        buttons: true,
		        dangerMode: true,
		        buttons: ["아니오", "예"]
		    })
		    .then((willDelete) => {
		        if (willDelete) {
		        	
		        	var member_no = "삭제";
					var data = {
							'work_history_no' : work_history_no,
							'member_no' : member_no,
							"admin" : "어드민삭제"
						};
					
					$.ajax({
						'url' : utils.getContextPath() + "/work_history/ajax/"+member_no,
						'type' : "POST",
						'data' : data,
						dataType : "json",
						success : function(data){
							javascript:history.go(0);
						},
						error : function(e){
							console.log("error : " , e);
						}
						
					}); /* ajax 영역 끝*/
		    		
		        } else {
		            swal("삭제가 취소되었습니다.");
		        }
		    });
		
	}); /* #deleteBtn 영역 끝 */
	
	
	
	
	
	//레디펑션 종료.
	
	
	
	
});


/* 엔터키를 누르면 수정되는 기능 */
function enterKey(){
	if(event.keyCode == 13){
		var that = this;
		var parent = event.path[1];
		var history_no = $(parent).attr("data-workhistory-no");
		
		
		var memo = $("#memo").val();
		var member_no = "edit";
		var data = {
				"memo" : memo, 
				"work_history_no" : history_no
			};
		
		
		$.ajax({
			'url' : utils.getContextPath() + "/work_history/ajax/"+member_no,
			'type' : "POST",
			'data' : data,
			dataType : "json",
			success : function(data){
				var memo = data.memo;
				addedGetMemo(parent, memo);
			},
			error : function(e){
				console.log("error : " , e);
			}
		});
	}
	
} /* EnterKey 기능 - end - */



function addedGetMemo(parent, memo){
	$(parent).empty();
	$(parent).append(memo);
	$(parent).attr("data-memo", memo);
}