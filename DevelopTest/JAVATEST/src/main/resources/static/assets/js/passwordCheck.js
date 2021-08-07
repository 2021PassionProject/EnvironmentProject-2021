function check_pw() {   // 비밀번호 체크하는 함수
    var pw = document.getElementById('pw').value;
    var SC = ["!", "@", "#", "$", "%"];
    var check_SC = 0;

    if (pw.length < 6 || pw.length > 16) {
        //  비밀번호의 길이가 6자 미만 또는 16자 초과 시
        //  window.alert 메서드를 사용해 오류 메시지를 출력시키고 입력했던 pw를 초기화시킨다.
        window.alert('비밀번호는 6글자 이상, 16글자 이하로 작성해주세요.');
        document.getElementById('pw').value = '';
    }
    for (var i = 0; i < SC.length; i++) {
        //  SC 배열의 모든 값을 pw와 비교한다.
        //  pw.indexOf('SC[i]')는 사용자가 입력한 비밀번호(pw)에 SC[i]가 있는지 확인하는 메소드이며,
        //  SC[i]가 존재할 시 존재하는 위치를 반환하고 그렇지 않을 시 -1을 반환한다.
        if (pw.indexOf(SC[i]) != -1) {
            check_SC = 1;
        }
    }
    if (check_SC == 0) {
        //  check_SC가 0일 경우 특수문자가 들어있지 않다는 메시지와 함께 입력했던 비밀번호를 초기화시킨다.
        window.alert('특수문자를 포함해 주세요.(!,@,#,$,%)');
        document.getElementById('pw').value = '';
    }
    if(document.getElementById('pw').value != '' && document.getElementById('pswd2').value != '') {
        if(document.getElementById('pw').value == document.getElementById('pswd2').value) {
            //  pw의 값과 pswd2의 값이 일치한다면
            document.getElementById('check').innerHTML = '비밀번호가 일치합니다.';
            //  check라는 id를 참조하여 그 안에 데이터를 넣는다.
            document.getElementById('check').style.color='blue';
        }
        else {
            document.getElementById('check').innerHTML = '비밀번호가 일치하지 않습니다.';
            document.getElementById('check').style.color='red';
        }
    }
}