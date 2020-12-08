//<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

// findID.html
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
        const user_name = document.querySelector('input[name="user_name"]').value
        const user_mobile = document.querySelector('input[name="user_mobile"]').value
        const form = {
        //    user_name : user_name,
            user_mobile: user_mobile
        }
        console.log(user_mobile)
        $.ajax({
            "url": '/user/mobileCheck' ,
            "type": 'get',
            "data" : form ,
            "success" : function (data){
                console.log(data)
                console.log(data.user_mobile)
                console.log(data.user_name)


                console.log(user_name, user_mobile)
                if(data.user_name == 0){
                    alert("등록된 핸드폰 번호가 아닙니다.")
                } else if(user_name != data.user_name){
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
            alert("인증번호가 전송되었습니다.")
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

// 아이디 찾기 버튼
$(function (){
    $('#find_id_button').on('click',function (){
        let check = 0;
        for(let i=0; i<arrayValid.length; i++){
            console.log(arrayValid[i])

            if(arrayValid[i] ==1){
                check = check+1
            }
        }
        if(check ==2){ // 3으로 변경
            alert(check)
            findId()
        } else{
            alert("입력 사항을 다시 확인해주세요.")
        }
    })
})

function findId(){
    const form = {
        user_mobile : document.querySelector('input[name="user_mobile"]').value
    }
    $.ajax({
        "url": '/user/findID',
        "type": 'post',
        "data" : form,
        "success" : function (data){
            console.log(data.user_id)
            user_id = data.user_id
            location.href='/user/findIDResult?user_id='+user_id
        },
        "fail":function (){
            alert("다시 시도해주세요.")
        }
    })
}

