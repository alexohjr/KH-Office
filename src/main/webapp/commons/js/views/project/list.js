$(function() {
	
	// 프로젝트 생성 버튼
	$("#addProjectBtn").on("click", function() {
		location.href = utils.getContextPath() + "/project/form";
	});
	
	// 프로젝트 셀클릭 (상세페이지 진입)
	$(document).on("click",".projectCell", function() {
		var project_no = $(this).attr("data-project-no");
		location.href = utils.getContextPath()+"/project/"+ project_no;
	});
});

// 프로젝트 상태 문자열로 변환
$(function() {
	$(".project_status_info.p span").text("진행중");
	$(".project_status_info.c span").text("완료");
	$(".project_status_info.w span").text("보류");
	$(".project_status_info.s span").text("중단");
});

//태스크 상세 프로그레스 바
$(function(){
	
	(function( $ ) {
        "gdgd";
        $(function() {
            function animated_contents() {
                $(".progressBarOuter > .progressBarInner").each(function (i) {
                    var $this  = $(this);
                    var skills = $this.attr('data-width');
                    
                    $this.css({'width' : skills + '%'});
                });
            }
            
            if (jQuery().appear) {
                $(".progressBarOuter").appear().on('appear', function() {
                    animated_contents();
                });
            } else {
                animated_contents(); // 프로그래싱바 그리기 호출
            }
            
            $(".getProjectListBtn").on("click", function() {
        		var viewNum = $("#viewNum").attr("data-viewNum");
        		console.log("viewNum :: " + viewNum);

        		var data = { "viewNum" : viewNum };
        		
        		$.ajax({
        			url:utils.getContextPath() + "/project/ajax/"+viewNum,
        			type : "GET",
        			data : data,
        			dataType : "json",
        			success: function(data) {
        				if(data.projectList.length == 0) {
        					swal("더이상 불러올 목록이 없습니다.", "", "info");
        					return false;
        				}
        				
        				console.log("에이잭스 송수신 성공");
        	 			console.log("리스트 사이즈 :: " + data.projectList.length);
        				$("#viewNum").attr("data-viewNum", data.viewNum);
        				
        				var list = data.projectList;
        				 
        				var appendArea = $(".projectAppendDiv");
        				
        				for (var i=0; i<list.length; i++) {
        					
        					var divElem = $("<div />", {
        						"data-project-no" : list[i].project_no,
        					}).addClass("projectCell");
        					
        					
        					divElem.append("<span class='project_no_info'>No."+list[i].project_no+"</span>");
        					
        					var spanElem1 = $("<span />", {
        						"class" : list[i].status
        					}).addClass("project_status_info");
        					
        					spanElem1.append("<span>"+list[i].status+"</span>");
        					divElem.append(spanElem1);
        					
        					divElem2 = $("<div />", {
        						"class" : "projectCellInfo"
        					});
        					
        					var ulElem = $("<ul/>");
        					
        					ulElem.append("<li> <input type='hidden' class='project_no' value="+ list[i].project_no +"/></li>");
        					ulElem.append("<li><span class='projectListProjectName "+list[i].status+"'>"+ list[i].name +"</span></li>");
        					/*ulElem.append("<li> 상태 : "+ list[i].status +"</li>");*/
        					/*ulElem.append("<li> 팀원수 : "+ list[i].memberCount +"</li>");*/
        					ulElem.append("<li>등록 된 TASK<span class='taskCountSpan'> 〔"+ list[i].taskCount +"〕</span></li>");
        					ulElem.append("<li><img src="+utils.getContextPath()+"/"+list[i].leaderThumb +"> "+ list[i].leaderPosition +" "+ list[i].leaderName +" 외 "+list[i].memberCount+"명</li>");
        					ulElem.append("<li>"+ list[i].start_date +"&nbsp; ~ &nbsp;"+ list[i].end_date +"</li>");
        					ulElem.append("<li> <div class='progressBarOuter "+list[i].status+"'> <div class='progressBarInner' data-width="+ list[i].progress +"/></div> </li>");
        					
        					divElem2.append(ulElem);
        					divElem.append(divElem2);
        					appendArea.append(divElem);
        					
        					$(".project_status_info.p span").text("진행중");
        					$(".project_status_info.c span").text("완료");
        					$(".project_status_info.w span").text("보류");
        					$(".project_status_info.s span").text("중단");
        					
        					animated_contents();
        					
        					// 바디에 nicescroll 다시 먹이기
        			    	$("body").getNiceScroll().resize();
        					
        				}
        			},
        			error: function(jqXHR, textStatus, errorThrown){
        	            alert("에러 발생 \n" + textStatus + " : " + errorThrown);
        	            console.log("textStatus : " , textStatus );
        	            console.log("errorThrown : " , errorThrown );
        	        }
        		});
        		
        	});
        });
    }(jQuery));
});

/*$(function() {
	$(".project_status_info").hasClass("진행중").css({'background-color' : '#38BE5E'});
	$(".project_status_info").hasClass("완료").css({'background-color' : '#2457BD'});
	$(".project_status_info").hasClass("보류").css({'background-color' : '#353535'});
	$(".project_status_info").hasClass("중단").css({'background-color' : 'rgb(181, 64, 23)'});
});*/

 




















