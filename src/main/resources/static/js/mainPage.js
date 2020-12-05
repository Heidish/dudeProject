

function inputTimeColon(time) {
    // replace 함수를 사용하여 콜론( : )을 공백으로 치환한다.
    var replaceTime = time.value.replace(/\:/g, "");

    // 텍스트박스의 입력값이 3-4글자 사이가 되는 경우에만 실행한다. (오전/오후 선택은 별도)
    if(replaceTime.length >= 3 && replaceTime.length < 5) {

        var hours = replaceTime.substring(0, 2);      // 선언한 변수 hours에 시간을 담는다.
        var minute = replaceTime.substring(2, 4);    // 선언한 변수 minute에 분을 담는다.

        // isFinite함수를 사용하여 문자가 선언되었는지 확인한다.
        if(isFinite(hours + minute) == false) {
            $('#time-valid').text('문자는 입력하실 수 없습니다.')
            $('#time-valid').css('color','red')
            $('#time-valid').attr('disabled',true)
            time.value = "00:00"
            return false
        }
        // 두 변수의 시간과 분을 합쳐 입력한 시간이 24시가 넘는지를 체크한다.
        if(hours + minute > 2400) {
            $('#time-valid').text('시간은 24시를 넘길 수 없습니다.')
            $('#time-valid').css('color','red')
            $('#time-valid').attr('disabled',true)
            time.value = "24:00"
            return false
        }
        // 입력한 분의 값이 60분을 넘는지 체크한다.
        if(minute > 60) {
            $('#time-valid').text('분은 60분을 넘길 수 없습니다.')
            $('#time-valid').css('color','red')
            $('#time-valid').attr('disabled',true)
            time.value = hours + ":00";
            return false;
        }
        time.value = hours + ":" + minute;
    }
}

$(function (){
    $('#set_time').on('click',function (){
        const time = document.querySelector('input[name="time_set_home"]').value
        $.ajax({
            "url":'/timer/setTimer',
            "type":'post',
            "data":time,
            "succes":function (){
                console.log()

            }
        })
    })
})