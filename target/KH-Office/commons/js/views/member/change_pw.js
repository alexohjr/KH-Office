$(function () {
    $("#change_btn").on("click", function () {
        var password = document.getElementById("password").value;
        var newPassword = document.getElementById("newPassword").value;
        var newPassword2 = document.getElementById("newPassword2").value;

        if (password == "") {
            swal("비밀번호를 입력해주세요.");
        } else if (newPassword == "") {
            swal("새 비밀번호를 입력해주세요.");
        } else if (newPassword2 == "") {
            swal("비밀번호 확인을 입력해주세요.");
        } else {
            swal({
                title: "변경하시겠습니까?",
                icon: "warning",
                buttons: true,
                dangerMode: true,
                buttons: ["아니오", "예"]
            })
                .then((willEdit) => {
                    if (willEdit) {
                        $("#password_area").submit();

                    }

                });
        }
        return false;
    });
});

function checkPassword(){
	var password = $('#newPassword').val();
	var checkPassword = $('#newPassword2').val();
	
    if(password == checkPassword){
        $('#check_message').html("<p id='ok'>비밀번호 확인이 일치합니다.</p>");                
    }else if (password != checkPassword){
        $('#check_message').html("<p id='no'>비밀번호 확인이 일치하지 않습니다.</p>");
    }
};

