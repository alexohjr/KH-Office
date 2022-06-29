$(function(){
	
	var result = "";
	$(':input').each(function(index) {
		result += "태그명 : " + this.tagName + ", type 속성명 : "+ $(this).attr('type') + ", value값 : "+ $(this).attr('value') + '\n';});
	console.log(result);
	
		
	$("#approval_submit_btn").on("click",function(){
		swal({
	        title: "정말 승인하시겠습니까?",
	        text: "한번 승인된 후에는 되돌릴 수 없습니다.",
	        icon: "warning",
	        buttons: true,
	        dangerMode: true,
	        buttons: ["아니오", "예"]
	    })
	    .then((willDelete) => {
	        if (willDelete) {
	        	
	        	$("input[name='status']").remove();
	    		
	    		var status = $(this).attr("data-status");
	    		var hiddenInput = $("<input/>",{
	    			'type' : "hidden",
	    			'name' : "status",
	    			'value' : status
	    		});
	    		$("#updateSubmit").append(hiddenInput);
	    		$("#updateSubmit").submit();
	    		
	        } else {
	            swal("승인이 취소되었습니다.");
	        }
	    });
	});
	
	$("#approval_opposite_btn").on("click",function(){
		swal({
	        title: "정말 반려하시겠습니까?",
	        text: "한번 반려한 후에는 되돌릴 수 없습니다.",
	        icon: "warning",
	        buttons: true,
	        dangerMode: true,
	        buttons: ["아니오", "예"]
	    })
	    .then((willDelete) => {
	        if (willDelete) {
	        	
	        	$("input[name='status']").remove();
	    		
	    		var status = $(this).attr("data-status");
	    		
	    		
	    		var hiddenInput = $("<input/>",{
	    			'type' : "hidden",
	    			'name' : "status",
	    			'value' : status
	    		});
	    		$("#updateSubmit").append(hiddenInput);
	    		$("#updateSubmit").submit();  
	            
	        } else {
	            swal("반려가 취소되었습니다.");
	        }
	    });
	});
	
	$("#approval_back_btn").on("click",function(){
		swal({
	        title: "목록으로 돌아가시겠습니까?",
	        icon: "info",
	        buttons: true,
	        dangerMode: true,
	        buttons: ["아니오", "예"]
	    })
	    .then((willDelete) => {
	        if (willDelete) {
	            javascript:history.back();
	            
	        } else {
	            swal("취소하셨습니다.");
	        }
	    });
	});
	
	
});