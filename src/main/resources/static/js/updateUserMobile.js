//<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

// registration.html
let arrayValid = new Array()

// phone check  id="phone-valid"
$(function (){
    $('input[name=user_mobile]').blur(function (){
        const user_mobile = document.querySelector('input[name="user_mobile"]').value
        const pattern_num = /[0-9]/;	// 숫자
        if(user_mobile.length !=11 || !pattern_num.test(user_mobile)){
            $('#phone-valid').text('형식이 일치하지 않습니다.')
            $('#phone-valid').css('color','red')
            $('#phone-valid').attr('disabled',true)
            arrayValid[0]=0;
        } else {
            $('#phone-valid').text('')
            arrayValid[0]=1;
        }
    })
})

let certNum ;

// 인증번호 전송 check (verification-check)  id="verification-check"
$(function (){ // 인증번호 버튼 누르기
    $('#validation_button').on('click',function (){
        const user_mobile = document.querySelector('input[name="user_mobile"]').value
        const form = {
            user_mobile: user_mobile
        }
        console.log(user_mobile)
        $.ajax({
            "url": '/user/textauth' ,
            "type": 'get',
            "data" : form ,
            "success" : function (data){
                console.log(data)
                certNum = data
                setTimeout(function () {
                   certNum=undefined
                },180000)
                alert("인증번호가 전송되었습니다. \n3분 안에 인증을 완료해주세요.")
            }
        })
    })
})

// 인증번호 확인 check (verification-confirm)  id="verification-confirm"

$(function (){ // 인증번호 확인 버튼 누르기
    $('#verification-confirm').on('click',function (){
        const validation_num = document.querySelector('input[name="validation_num"]').value
        console.log("validation_num"+validation_num)
        console.log("certNum"+certNum)
        if(validation_num != certNum){
            $('#verification-check').text('인증번호가 일치하지 않습니다.')
            $('#verification-check').css('color','red')
            $('#verification-check').attr('disabled',true)
            arrayValid[1]=0;
        } else {
            $('#verification-check').text('')
            arrayValid[1]=1;
            alert("인증되었습니다.")
        }
    })
})

// 수정 버튼
$(function (){
    $('#update').on('click',function (){
        let check = 0;
        for(let i=0; i<arrayValid.length; i++){
            console.log(arrayValid[i])

            if(arrayValid[i] ==1){
                check = check+1
            }
        }
        if(check ==1){  //2 로 변경
            add()
        } else{
            alert("입력 사항을 다시 확인해주세요.")
        }
        })
})

function add(){
    const form = {
        user_mobile : document.querySelector('input[name="user_mobile"]').value
    }
    $.ajax({
        "url": '/user/updateMobile' ,
        "type": 'post',
        "data" : form,
        "success" : function (data){
            location.href='/user/main'
        },
        "fail":function (){
            alert("다시 시도해주세요.")
        }
    })
    }

