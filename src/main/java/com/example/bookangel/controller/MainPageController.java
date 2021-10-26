package com.example.bookangel.controller;

import com.example.bookangel.beans.vo.Criteria;
import com.example.bookangel.beans.vo.MailVO;
import com.example.bookangel.beans.vo.MainPageVO;
import com.example.bookangel.beans.vo.PageDTO;
import com.example.bookangel.services.MailService;
import com.example.bookangel.services.MainPageService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/main/*")
@RequiredArgsConstructor
public class MainPageController {
    private final MainPageService mainPageService;
    private final MailService mailService;
//    @GetMapping("mainPage")
//    public String mainPage(){
//        return "main/mainPage";
//    }

    @GetMapping("mainPage")
    public String list(Criteria criteria, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        log.info((String) session.getAttribute("memberId"));
        log.info("-------------------------------");
        log.info("mainPage");
        log.info("-------------------------------");
        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("sessionType", session.getAttribute("memberType"));
        model.addAttribute("memberId", session.getAttribute("memberId"));
        model.addAttribute("memberName", session.getAttribute("memberName"));
        model.addAttribute("list", mainPageService.getOkList(criteria));
        model.addAttribute("total", mainPageService.getOkTotal(criteria));
        model.addAttribute("pageMaker", new PageDTO(mainPageService.getOkTotal(criteria), 10, criteria));
        return "main/mainPage";
    }

    @GetMapping("qna")
    public String qna(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("sessionType", session.getAttribute("memberType"));
        model.addAttribute("memberName", session.getAttribute("memberName"));
        return "main/qna";
    }

    @GetMapping("/main/qnaMail")
    public String qnaMail() {
        return "/main/qnaMail";
    }

    @PostMapping("/main/qnaMail")
    public String qnaEMail(MailVO mailVO) {
        // 메일 발송
        mailService.mailSend(mailVO);
        return "/main/qnaMail";
    }

    @GetMapping("expressBook")
    public String express(){
        return "book/expressView";
  }
    @GetMapping("dreamBook")
    public String dreamView(){
        return "book/dreamView";
    }
}
