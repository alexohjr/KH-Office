$(function(){
	$('#register_form input[type="radio"]').on("change", function(){
		getAndInsertRadioValue($(this));
	});
	
	//숫자만 입력받도록
	$("#phone").on("keyup", function() {
		$(this).val($(this).val().replace(/[^0-9]/g,""));
	
	});
	$("#tel").on("keyup", function() {
		$(this).val($(this).val().replace(/[^0-9]/g,""));
	
	});
	
	$("#uploadFile").on("change", function(){
		var imgElem = $(".profile_img");
		readURL(this, imgElem);
		$("body").getNiceScroll().resize();
	});
	
	$("#submit_btn").on("click", function(){
		var name = document.getElementById("name").value;
		var email = document.getElementById("email").value;
		var phone = document.getElementById("phone").value;
		var zipcode = document.getElementById("zipcode").value;
		var emailCheck = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
		var nameCheck = /^[가-힣a-zA-Z]+$/;
		if(emailCheck.test(email)==false){
			swal("이메일형식이 올바르지 않습니다.");
			$("#email").focus(); 
		}else if(nameCheck.test(name) ==false){ 
			swal("이름은 한글 또는 영어 대소문자만 입력해주세요.");
			$("#name").focus();
		}else if(phone == ""){
			swal("핸드폰 번호는 필수입력 항목입니다.");
			$("#phone").focus();
		}else if(zipcode == ""){
			swal("집 주소는 필수입력 항목입니다.");
			$("#zipcode").focus();
		}else {
			swal({
		        title: "등록하시겠습니까?",
		        icon: "warning",
		        buttons: true,
		        dangerMode: true,
		        buttons: ["아니오", "예"]
		    })
		    .then((willRegister) => {
		        if (willRegister) {
		            swal("등록이 완료되었습니다.", {
		                icon: "success",
		            })
		            .then(function(data){
		            	if(data==true){
		            		 $("#register_form").submit();
		            	}
		            });
		        } else {
		            swal("등록이 취소되었습니다.");
		        }
		    });
		}
		return false;
	});
});

//파일첨부 이미지 미리보기 함수
function readURL(input, imgElem) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			console.log(e);
			imgElem.attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}

function DaumPostcode() {
	var themeObj = {
			bgColor: "#1A237E" //바탕 배경색
	}
	var width = 400; //팝업의 너비
	var height = 500; //팝업의 높이
	
        new daum.Postcode({
        	theme: themeObj,
        	width: width,
        	height: height,
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('main_address').value = fullAddr;

                // 커서를 상세주소 필드로 이동한다.
                document.getElementById('detail_address').focus();
            }
   
        }).open();
    }

function checkEmail(){
	var email = $('#email').val();
	
	$.ajax({
		url : utils.getContextPath() + "/member/ajax/email/check",
        type:'POST',
        data:{
        	email : email
        },
        success:function(data){
            if($.trim(data)==0){
                $('#check_message').html("<div id='ok'>이 이메일 주소를 사용할 수 있습니다.</p>");                
            }else{
                $('#check_message').html("<div id='no'>이 이메일 주소를 사용할 수 없습니다.</p>");
            }
        },
        error:function(){
        	swal("에러입니다");
        }
	});
};

// input:radio 클릭시 같은 부모 li의 마지막 input:hidden 요소에 value 삽입하는 함수
function getAndInsertRadioValue(that){
	var inputValue = that.val();
	
	// 형제 중 마지막 input:hidden 잡기
	var inputHiddenElem = that.parent().children(':last-child');
	
	// input:hidden 에 값 넣기
	inputHiddenElem.val(inputValue);
}

