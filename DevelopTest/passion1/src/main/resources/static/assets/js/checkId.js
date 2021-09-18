 function checkId() {
        var email = $('#email').val();  // "email"인 입력란의 값을 저장
        $.ajax({
            url:'/idCheck', // Controller에서 인식할 주소
            type:'post',    // POST 방식으로 전달
            data:{email:email},
            success:function(cnt) {
                if(cnt != 1) {  // cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디
                    $('.email_ok').css("display","inline-block");
                    $('.email_already').css("display","none");
                }else { // cnt가 1일 경우 -> 이미 존재하는 아이디
                    $('.email_already').css("display","inline-block");
                    $('.email_ok').css("display","none");
                }
            },
            error:function() { alert("에러입니다."); }
        });
    };