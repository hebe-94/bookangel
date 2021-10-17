package com.example.bookangel.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/member/*")
@RequiredArgsConstructor
public class MemberController {

    // 페이지 이동
    @GetMapping("mypage")
    public void mypage(){}

    @GetMapping("withdraw")
    public void withdraw(){}

    @GetMapping("memberModify")
    public void memberModify(){}

    @GetMapping("login")
    public void login(){}

    @GetMapping("join")
    public void join(){}

    @GetMapping("changePW")
    public void changePw(){}

    @GetMapping("findPW")
    public void findPw(){}

    @GetMapping("findedID")
    public void findedID(){}

    @GetMapping("findID")
    public void findID(){}

    @GetMapping("qna")
    public void qna(){}

}
