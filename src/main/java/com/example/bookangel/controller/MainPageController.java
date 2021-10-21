package com.example.bookangel.controller;

import com.example.bookangel.beans.vo.Criteria;
import com.example.bookangel.beans.vo.MainPageVO;
import com.example.bookangel.beans.vo.PageDTO;
import com.example.bookangel.services.MainPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/main/*")
@RequiredArgsConstructor
public class MainPageController {
    private final MainPageService mainPageService;

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
}
