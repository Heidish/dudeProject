//<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

// registration.html
let arrayValid = new Array()

// 이름 한글만 입력
$(function (){
    $('input[name=user_name]').blur(function (){
        const user_name = document.querySelector('input[name="user_name"]').value
        const pattern_num = /[0-9]/;	// 숫자
        const pattern_eng = /[a-zA-Z]/;	// 문자
        const pattern_spc = /[~!@#$%^&*()+|<>?:{}]/; // 특수문자
        const pattern_kor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/; // 한글체크

        if(user_name.length<2 || user_name.length>5 || !pattern_kor.test(user_name) || pattern_num.test(user_name) ||  pattern_eng.test(user_name) || pattern_spc.test(user_name)){
            $('#name-valid').text('2~5 자 이내의 한글만 사용 가능합니다.')
            $('#name-valid').css('color','red')
            $('#name-valid').attr('disabled',true)
            arrayValid[0]=0;
        }  else {
            $('#name-valid').text('')
            arrayValid[0]=1;
        }


    })
})

// id check id="id-valid"
$(function (){
    $('input[name=user_id]').blur(function (){
        const user_id = document.querySelector('input[name="user_id"]').value
        const pattern_num = /[0-9]/;	// 숫자
        const pattern_eng = /[a-zA-Z]/;	// 문자
        const pattern_spc = /[~!@#$%^&*()+|<>?:{}]/; // 특수문자
        const pattern_kor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/; // 한글체크
        const form = {
            user_id: user_id
        }
        $.ajax({
            "url": '/user/idCheck' ,
            "type": 'get',
            "data" : form,
            "success" : function (data){
                if(data === 1){
                    //document.getElementsById('id-valid').innerHTML='중복된 아이디입니다.'
                    $('#id-valid').text('사용중인 아이디입니다.')
                    $('#id-valid').css('color','red')
                    $('#id-valid').attr('disabled',true)
                    arrayValid[1]=0;
                } else if(user_id.length<5 || user_id.length>20 || pattern_kor.test(user_id) || pattern_spc.test(user_id) ){
                    $('#id-valid').text('5~20자의 영문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.')
                    $('#id-valid').css('color','red')
                    $('#id-valid').attr('disabled',true)
                    arrayValid[1]=0;
                } else {
                    $('#id-valid').text('')
                    arrayValid[1]=1;
                }
            },
            "fail":function (data) {
                console.log(data)
                alert("fail")
            }
        })
    })
})

// password check  id="password-valid"
$(function (){
    $('input[name=user_pw]').blur(function (){
        const user_pw = document.querySelector('input[name="user_pw"]').value
        const pattern_num = /[0-9]/;	// 숫자
        const pattern_eng = /[a-zA-Z]/;	// 문자
        const pattern_spc = /[~!@#$%^&*()+|<>?:{}]/; // 특수문자

        if(user_pw.length<8 || user_pw.length>16 || !pattern_num.test(user_pw) || !pattern_spc.test(user_pw) || !pattern_eng.test(user_pw)){
            $('#password-valid').text('8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.')
            $('#password-valid').css('color','red')
            $('#password-valid').attr('disabled',true)
            arrayValid[2]=0;
        } else {
            $('#password-valid').text('')
            arrayValid[2]=1;
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
            arrayValid[3]=0;
        } else {
            $('#password-check').text('')
            arrayValid[3]=1;
        }
    })
})

// phone check  id="phone-valid"
$(function (){
    $('input[name=user_mobile]').blur(function (){
        const user_mobile = document.querySelector('input[name="user_mobile"]').value
        const pattern_num = /[0-9]/;	// 숫자
        if(user_mobile.length !=11 || !pattern_num.test(user_mobile)){
            $('#phone-valid').text('형식이 일치하지 않습니다.')
            $('#phone-valid').css('color','red')
            $('#phone-valid').attr('disabled',true)
            arrayValid[4]=0;
        } else {
            $('#phone-valid').text('')
            arrayValid[4]=1;
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
                alert("인증번호가 전송되었습니다.")
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
            arrayValid[5]=0;
        } else {
            $('#verification-check').text('')
            arrayValid[5]=1;
            alert("인증되었습니다.")
        }
    })
})

// 회원가입 버튼
$(function (){
    $('#registration_button').on('click',function (){
        let check = 0;
        for(let i=0; i<arrayValid.length; i++){
            console.log(arrayValid[i])

            if(arrayValid[i] ==1){
                check = check+1
            }
        }
        if(check ==6){
            add()
        } else{
            alert("입력 사항을 다시 확인해주세요.")
        }
        })
})

function add(){ // 인증번호 버튼 누르기
    const form = {
        user_name : document.querySelector('input[name="user_name"]').value,
        user_id: document.querySelector('input[name="user_id"]').value,
        user_pw :document.querySelector('input[name="user_pw"]').value,
        user_mobile : document.querySelector('input[name="user_mobile"]').value
    }
    $.ajax({
        "url": '/user/add' ,
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

