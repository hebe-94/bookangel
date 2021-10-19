package com.example.bookangel.controller;

import com.example.bookangel.beans.vo.MemberVO;
import com.example.bookangel.services.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    @PostMapping("login")
    public String login(MemberVO memberVO, HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        MemberVO vo = memberService.login(memberVO);
        boolean ckeck = vo != null;
        if (ckeck) {
            session.setAttribute("memberType", vo.getMemberType());
            session.setAttribute("memberId", vo.getMemberId());
            return "redirect:/main/mainPage";
        }
        else{
            model.addAttribute("flag","false");
            return "member/login";
        }
    }
}

