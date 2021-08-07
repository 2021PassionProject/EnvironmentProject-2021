/*
        핸드폰 자동 하이픈 생성 구현
        최종수정일 : 2021.08.03
        작성자 : 한정수(Intrager)
        // 소스 출처 : https://juein.tistory.com/43
 */
var autoHypenPhone = function (str) {
    str = str.replace(/[^0-9]/g, '');
    var tmp = '';
    if (str.length < 4) {
        return str;
    } else if (str.length < 7) {
        tmp += str.substr(0, 3);
        tmp += '-';
        tmp += str.substr(3);
        return tmp;
    } else if (str.length < 11) {
        tmp += str.substr(0, 3);
        tmp += '-';
        tmp += str.substr(3, 3);
        tmp += '-';
        tmp += str.substr(6);
        return tmp;
    } else {
        tmp += str.substr(0, 3);
        tmp += '-';
        tmp += str.substr(3, 4);
        tmp += '-';
        tmp += str.substr(7);
        return tmp;
    }
    return str;
}
var phone = document.getElementById('phone');

phone.onkeyup = function(){
    console.log(this.value);
    this.value = autoHypenPhone( this.value ) ;
}