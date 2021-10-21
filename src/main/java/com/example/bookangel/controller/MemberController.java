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
    public void mypage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        String memberId = (String)session.getAttribute("memberId");
        model.addAttribute("myInfo", memberService.getMyInfo(memberId));
    }

    @GetMapping("withdraw")
    public void withdraw(HttpServletRequest request ,Model model){
        HttpSession session = request.getSession();
        model.addAttribute("memberId", session.getAttribute("memberId"));
    }

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
            boolean status = vo.getMemberStatus()==1;
            if(status){
                model.addAttribute("withDraw","withDraw");
                return "/member/login";
            }else {
                session.setAttribute("memberNum", vo.getMemberNum());
                session.setAttribute("memberType", vo.getMemberType());
                session.setAttribute("memberId", vo.getMemberId());
                session.setAttribute("memberName", vo.getMemberName());
                return "redirect:/main/mainPage";
            }
        }
        else{
            model.addAttribute("flag","false");
            return "/member/login";
        }
    }
    @PostMapping("withdrawcheck")
    @ResponseBody
    public String withdrawcheck(@RequestParam("memberPw") String memberPw, HttpServletRequest request){
        HttpSession session = request.getSession();
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberId((String)session.getAttribute("memberId"));
        memberVO.setMemberPw(memberPw);
        int cnt = memberService.withDrawCheck(memberVO);
        if (cnt == 1){
            return"success";
        }else{
            return "fail";
        }
    }
    @PostMapping("withdraw")
    public String withdraw(HttpServletRequest request){
        HttpSession session = request.getSession();
        int memberNum = (int)session.getAttribute("memberNum");
        memberService.withDraw(memberNum);
        session.invalidate();
        return "member/login";

    }
}

