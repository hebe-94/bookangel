/**
 * 	회원가입
 */
var check =false;
var form = document.joinForm;
function formSubmit(){
    if(form.memberId.value==null&&form.memberId.value==""){
        alert("아이디를 입력해 주세요");
        form.memberId.focus();
        return;
    }
    if(form.memberPw.value==null&&form.memberPw.value==""){
        alert("비밀번호를를 입력해 주세요");
        form.memberPw.focus();
        return;
    }
    if(form.memberName.value==null&&form.memberName.value==""){
        alert("이름을 입력해 주세요");
        form.memberName.focus();
        return;
    }
    if(form.memberTel.value==null&&form.memberTel.value==""){
        alert("전화번호를 입력해 주세요");
        form.memberTel.focus();
        return;
    }
    if(form.memberEmail.value==null&&form.memberEmail.value==""){
        alert("이메일을 입력해 주세요");
        form.memberEmail.focus();
        return;
    }
    if(form.memberZipcode.value==null&&form.memberZipcode.value==""){
        alert("우편번호를 입력해 주세요");
        form.memberZipcode.focus();
        return;
    }
    if(form.memberAddress.value==null&&form.memberAddress.value==""){
        alert("주소를 입력해 주세요");
        form.memberAddress.focus();
        return;
    }
    if(form.memberAddressDetail.value==null&&form.memberAddressDetail.value==""){
        alert("상세주소를 입력해 주세요");
        form.memberAddressDetail.focus();
        return;
    }
    if(form.memberAddressEtc.value==null&&form.memberAddressEtc.value==""){
        alert("참고항목을 입력해 주세요");
        form.memberAddressEtc.focus();
        return;
    }


    var pwCheck = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,20}$/;

    if (!pwCheck.test(form.memberPw.value)) {
        alert("비밀번호는 영문자+숫자 조합으로 8~20자리 사용해야 합니다");
        form.memberPw.focus();
        return;
    }
    if(!check){
        alert("중복된 아이디 입니다.");
        $("input#memberId").focus();
        return;
    }
    alert("회원가입이 완료되었습니다! 기북천사에 오신걸 환영합니다 ");
    form.submit();
}

function checkId(memberId){

    check = false;
    if(memberId == ""){
        $("#idCheck_text").text("");
        return;
    }

    $.ajax({
        url:"/member/checkId",
        type:"post",
        data:{"memberId":memberId},
        success:function(result){
            if(result=="success"){
                //DOM
                $("#idCheck_text").text("사용 가능");
                $("#idCheck_text").css("color", "blue");
                check = true;
            }else{
                //DOM
                $("#idCheck_text").text("사용 불가");
                $("#idCheck_text").css("color", "red");
            }
        },
        error:function(){
            console.log("오류");
        }
    });
}

$("input[name='memberId']").keyup(function(){
    //중복 검사
    checkId($(this).val())
})











