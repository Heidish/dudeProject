//<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

// updateUserPw.html
let arrayValid = new Array()

// password check  id="password-valid"
$(function (){
    $('input[name=user_pw]').blur(function (){
        const user_pw = document.querySelector('input[name="user_pw"]').value
        console.log(user_pw)
        const pattern_num = /[0-9]/;	// 숫자
        const pattern_eng = /[a-zA-Z]/;	// 문자
        const pattern_spc = /[~!@#$%^&*()+|<>?:{}]/; // 특수문자

        if(user_pw.length<8 || user_pw.length>16 || !pattern_num.test(user_pw) || !pattern_spc.test(user_pw) || !pattern_eng.test(user_pw)){
            $('#password-valid').text('8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.')
            $('#password-valid').css('color','red')
            $('#password-valid').attr('disabled',true)
            arrayValid[0]=0;
        } else {
            $('#password-valid').text('')
            arrayValid[0]=1;
        }
    })
})

// password check check  id="password-check"
$(function (){
    $('input[name=user_pw_check]').blur(function (){
        const user_pw_check = document.querySelector('input[name="user_pw_check"]').value
        const user_pw = document.querySelector('input[name="user_pw"]').value
        if(user_pw_check != user_pw){
            $('#password-check').text('비밀번호가 일치하지 않습니다.')
            $('#password-check').css('color','red')
            $('#password-check').attr('disabled',true)
            arrayValid[1]=0;
        } else {
            $('#password-check').text('')
            arrayValid[1]=1;
        }
    })
})


// 비밀번호 재설정 버튼
$(function (){
    $('#update_pwd').on('click',function (){
        let check = 0;
        for(let i=0; i<arrayValid.length; i++){
            console.log(arrayValid[i])

            if(arrayValid[i] ==1){
                check = check+1
            }
        }
        if(check ==2){ // 3으로 변경
            alert(check)
            setPass()
        } else{
            alert("입력 사항을 다시 확인해주세요.")
        }
    })
})

function setPass(){
    const form = {
        user_id : document.querySelector('input[name="user_id"]').value,
        user_pw : document.querySelector('input[name="user_pw"]').value
    }
    alert(document.querySelector('input[name="user_id"]').value)
    $.ajax({
        "url": '/user/pass',
        "type": 'post',
        "data" : form,
        "success" : function (data){
            location.href='/user/login'
        },
        "fail":function (){
            alert("다시 시도해주세요.")
        }
    })
}

