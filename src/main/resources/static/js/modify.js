/**
 * 	회원가입
 */

console.log("돌아감");

$("#term").on("click", function(){
    if($(this).is(":checked")){
        //체크가 되어 있다면,
        $(".terms").prop("checked", true);
    }else{
        //체크되어 있지 않다면
        $(".terms").prop("checked", false);
    }
})




function formSubmit(){

    var id =  document.getElementById("member_id");
    var pw = document.getElementById("member_pw");
    var name = document.getElementById("member_name");
    var tel = document.getElementById("member_tel");
    var email = document.getElementById("member_email");
    var zipcode = document.getElementById("member_zipcode");
    var address = document.getElementById("member_address");
    var address_detail = document.getElementById("member_address_detail");
    var address_etc = document.getElementById("member_address_etc");

    console.log( $(member_id.val()));
    console.log("아이디 : "+id);
    console.log("비밀번호 : "+pw);
    console.log("이름 : "+name);
    console.log("전화번호 : "+tel);
    console.log("이메일 : "+email);
    console.log("우편번호 : "+zipcode);
    console.log("주소 : "+address);
    console.log("상세주소 : "+address_detail);
    console.log("기타주소 : "+address_etc);

    //location.href="/member/MemberModifyOk.me";
    //form.submit();
}













