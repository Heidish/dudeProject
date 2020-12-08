//<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

// updateUserPw.html


$(function () {
    $('#check_pw').on('click',function (){
        const user_pw = document.querySelector('input[name="user_pw"]').value
        const form = {
            user_pw: user_pw
        }
    $.ajax({
        "url": '/user/chkPass',
        "type": 'post',
        "data": form,
        "success": function (data) {
            location.href = '/user/updatepw'
        },
        "error": function () {
            alert("비밀번호가 일치하지 않습니다.")
          }
        })
    })
})

