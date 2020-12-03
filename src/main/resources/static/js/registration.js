//<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
// id check id="id-valid"
$(function (){
    $('input[name=user_id]').blur(function (){
        const form = {
            user_id: document.getElementById('input[name=user_id]').value
        }
        $.ajax({
            "url": '/user/idCheck' ,
            "type":'get',
            "data" : form,
            "success" :function (data){
                if(data==1){
                    //document.getElementsById('id-valid').innerHTML='중복된 아이디입니다.'
                    $('#id-valid').text('사용중인 아이디입니다.');
                    $('#id-valid').css('사용중인 아이디입니다.');
                    $('#id-valid').attr('disabled',true);

                }
            }
        })
    })
})

// password check  id="password-valid"


// password check check  id="password-check"


// phone check  id="phone-valid"


// 인증번호 전송 check (verification-check)  id="verification-check"


// 인증번호 확인 check (verification-confirm)  id="verification-confirm"