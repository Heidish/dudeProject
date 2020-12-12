//<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

// findID.html
let arrayValid = new Array()

//  id 만 입력
$(function (){
    $('input[name=user_id]').blur(function (){
        const user_id = document.querySelector('input[name="user_id"]').value
        console.log("user_id"+user_id)
        const pattern_spc = /[~!@#$%^&*()+|<>?:{}]/; // 특수문자
        const pattern_kor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/; // 한글체크
        if(user_id.length<5 || user_id.length>20 || pattern_kor.test(user_id) || pattern_spc.test(user_id) ){
            $('#id-valid').text('5~20자의 영문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.')
            $('#id-valid').css('color','red')
            $('#id-valid').attr('disabled',true)
            arrayValid[0]=0;
        } else {
            $('#id-valid').text('')
            arrayValid[0]=1;
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
            arrayValid[1]=0;
        } else {
            $('#phone-valid').text('')
            arrayValid[1]=1;
        }
    })
})

let certNum ;

// 인증번호 전송 check (verification-check)  id="verification-check"
$(function (){ // 인증번호 버튼 누르기
    $('#validation_button').on('click',function (){
        const user_id = document.querySelector('input[name="user_id"]').value
        const user_mobile = document.querySelector('input[name="user_mobile"]').value
        const form = {
            user_mobile: user_mobile
        }
        console.log(user_mobile)
        $.ajax({
            "url": '/user/mobileCheck2' ,
            "type": 'get',
            "data" : form ,
            "success" : function (data){
                console.log(data)
                console.log(data.user_mobile)
                console.log(data.user_id)

                if(data.user_id == 0){
                    alert("등록된 핸드폰 번호가 아닙니다.")
                } else if(user_id != data.user_id){
                    alert("작성한 정보를 다시 확인해 주세요.")
                }else {
                    texting()
                }

            }
        })
    })
})

function texting(){
    const user_mobile = document.querySelector('input[name="user_mobile"]').value
    const form = {
        user_mobile: user_mobile
    }
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
}

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
            arrayValid[2]=0;
        } else {
            $('#verification-check').text('')
            arrayValid[2]=1;
            alert("인증되었습니다.")
        }
    })
})

// 비밀번호 재설정 버튼
$(function (){
    $('#find_id_button').on('click',function (){
        const user_id = document.querySelector('input[name="user_id"]').value
        let check = 0;
        for(let i=0; i<arrayValid.length; i++){
            console.log(arrayValid[i])

            if(arrayValid[i] ==1){
                check = check+1
            }
        }
        console.log(check)
        if(check ==1){ // 3으로 변경
            alert(check)
            location.href='/user/findPwdResult?user_id='+user_id
        } else{
            alert("입력 사항을 다시 확인해주세요.")
        }
    })
})

function findPwd(){
    const form = {
        user_mobile : document.querySelector('input[name="user_mobile"]').value
    }
    $.ajax({
        "url": '/user/findPwd',
        "type": 'post',
        "data" : form,
        "success" : function (data){
            console.log(data.user_id)
            user_id = data.user_id
            location.href='/user/findIDResult'
        },
        "fail":function (){
            alert("다시 시도해주세요.")
        }
    })
}

