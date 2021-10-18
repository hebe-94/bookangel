package com.example.bookangel.controller;

import com.example.bookangel.VO.MemberVO;
import com.example.bookangel.services.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/member/*")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

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



    //회원가입
    @PostMapping("join")
    public String join(MemberVO memberVO){
        memberService.join(memberVO);
        return "/member/login";
    }

    // ID중복검사
    @PostMapping("checkId")
    @ResponseBody
    public String checkId(@RequestParam("memberId") String memberId){
            log.info(memberId);
            int cnt = memberService.checkId(memberId);
            if(cnt==1){
                return "fail";
            }else{
                return "success";
            }
        }
    }

