/**
 *  로그인
 */

function check(){
    var form = document.loginForm;

    if(!form.member_id.value){
        alert("아이디를 입력해주세요.");
        return;
    }

    if(!form.member_pw.value){
        alert("비밀번호를 입력해주세요.");
        return ;
    }

    form.submit();
}