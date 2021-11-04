/**
 * 	회원가입
 */
var check =false;
var form = document.joinForm;
var smsCheck = false;
$("input#btnCheckSMS").on("click",function () {
    let memberTel = $('input#memberTel').val();
    $.ajax({
        url:"/member/checkTel",
        type:"post",
        data:{"memberTel":memberTel},
        success:function(result){
            if(result=="success"){
                checksms();

            }else if(result=="false"){
                alert("중복된 핸드폰 번호입니다.");
                $("input#memberTel").val("");
                form.memberTel.focus();
                return;
            }
        }
    })
})
function checksms() {
    let memberTel = $('input#memberTel').val();
    alert('인증번호 발송 완료!')

    $.ajax({
        type: "GET",
        url: "/member/membersmscheck",
        data: {
            "memberTel" : memberTel
        },
        success: function(res){
            $('#btnCheckSMSSuccess').click(function(){
                if($.trim(res) ==$('input#checkSMS').val()){
                    alert('휴대폰 인증이 정상적으로 완료되었습니다.')
                    $("input#checkSMS").attr("disabled",true);
                    smsCheck = true;
                    console.log(smsCheck);
                }else{
                    alert('인증번호가 올바르지 않습니다!');
                    return;
                }
            })
        }
    })
}
function formSubmit(){
    if(form.memberId.value==null||form.memberId.value==""){
        alert("아이디를 입력해 주세요");
        form.memberId.focus();
        return;
    }

    var regId = /^[A-Za-z0-9]{6,}$/;
    if(!regId.test($("input#memberId").val())){
        alert("아이디는 6자이상 영문+숫자 조합만 가능합니다.");
        form.memberId.focus();
        return;
    }

    if(form.memberPw.value==null||form.memberPw.value==""){
        alert("비밀번호를를 입력해 주세요");
        form.memberPw.focus();
        return;
    }

    var pwCheck = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,20}$/;

    if (!pwCheck.test(form.memberPw.value)) {
        alert("비밀번호는 영문자+숫자 조합으로 8~20자리 사용해야 합니다");
        form.memberPw.focus();
        return;
    }

    if(form.memberName.value==null||form.memberName.value==""){
        alert("이름을 입력해 주세요");
        form.memberName.focus();
        return;
    }
    var regName=/^[가-힣]{2,}$/;
    if(!regName.test($("input#memberName").val())){
        alert("이름은 한글만 입력 가능합니다.");
        return;
    }

    if(form.memberTel.value==null||form.memberTel.value==""){
        alert("핸드폰 번호를 입력해 주세요");
        form.memberTel.focus();
        return;
    }

    var patt = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
    var res = patt.test( $("input#memberTel").val());

    if(!res){
        alert("핸드폰 번호만 입력 가능합니다.");
        $("input#memberTel").val("");
        $("input#memberTel").focus();
        return;
    }

    if(form.memberEmail.value==null||form.memberEmail.value==""){
        alert("이메일을 입력해 주세요");
        form.memberEmail.focus();
        return;
    }

    var regex=/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    var email = regex.test($("input#memberEmail").val());
    if(!email){
        alert("abc@abc.com 형식으로 작성해 주세요.");
        $("input#memberEmail").val("");
        $("input#memberEmail").focus();
        return;
    }

    if(form.memberZipcode.value==null||form.memberZipcode.value==""){
        alert("우편번호를 입력해 주세요");
        form.memberZipcode.focus();
        return;
    }
    if(form.memberAddress.value==null||form.memberAddress.value==""){
        alert("주소를 입력해 주세요");
        form.memberAddress.focus();
        return;
    }
    if(form.memberAddressDetail.value==null||form.memberAddressDetail.value==""){
        alert("상세주소를 입력해 주세요");
        form.memberAddressDetail.focus();
        return;
    }
    if(form.memberAddressEtc.value==null||form.memberAddressEtc.value==""){
        alert("참고항목을 입력해 주세요");
        form.memberAddressEtc.focus();
        return;
    }

    if(!check){
        alert("중복된 아이디 입니다.");
        $("input#memberId").focus();
        return;
    }

    if(!smsCheck){
        alert("휴대폰 인증을 진행해 주세요.");
        return;
    }

    var text = $("input#memberTel").val();

    var regNumber = /[^0-9]/g;
    phoneData = text.replace(regNumber, "");
    $("input#memberTel").val(phoneData);
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











