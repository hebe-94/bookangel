/**
 * 	회원가입
 */



$("#term").on("click", function(){
    if($(this).is(":checked")){
        //체크가 되어 있다면,
        $(".terms").prop("checked", true);
    }else{
        //체크되어 있지 않다면
        $(".terms").prop("checked", false);
    }
})



var check = false;

function formSubmit(){
    var form = document.joinForm;

    if(!form.member_id.value || !check){
        alert("아이디를 확인해주세요");
        member_id.focus();
        return;
    }


    if(!form.member_pw.value || !check){
        alert("비밀번호를 확인해주세요");
        member_pw.focus();
        return;
    }

    var pwCheck = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,20}$/;

    if (!pwCheck.test(form.member_pw.value)) {
        alert("비밀번호는 영문자+숫자 조합으로 8~20자리 사용해야 합니다");
        form.member_pw.focus();
        return false;
    };


    if(!form.member_name.value || !check){
        alert("이름을 확인해주세요");
        return;
    }

    if(!form.member_tel.value || !check){
        alert("핸드폰 번호를 확인해주세요");
        return;
    }

    if(!form.member_email.value || !check){
        alert("이메일을 확인해주세요");
        return;
    }

    if(!form.member_zipcode.value || !check){
        alert("우편번호를 확인해주세요");
        return;
    }

    if(!form.member_address.value || !check){
        alert("주소를 확인해주세요");
        return;
    }

    if(!form.member_address_detail.value || !check){
        alert("상세주소를 확인해주세요");
        return;
    }

    if(!form.member_address_etc.value || !check){
        alert("참고항목을 확인해주세요");
        return;
    }

    check = false;

    $.each($(".terms"), function(index, item){
        if(!$(item).is(":checked")){
            check = true;
        }
    });

    if(check){
        alert("이용약관 동의가 필요합니다.");
        return;
    }
    alert("회원가입이 완료되었습니다! 기북천사에 오신걸 환영합니다 ");
    form.submit();
}

function checkId(id){
    check = false;

    if(id == ""){
        $("#idCheck_text").text("");
        return;
    }



    $.ajax({
        url:contextPath+"/member/MemberCheckIdOk.me?id=" + id,
        type:"get",
        dataType:"json",
        success:function(result){
            if(result.status == "ok"){
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

$("input[name='member_id']").keyup(function(){
    //중복 검사
    checkId($(this).val())
})











