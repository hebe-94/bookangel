package com.example.bookangel.controller;

import com.example.bookangel.beans.vo.Criteria;
import com.example.bookangel.beans.vo.PageDTO;
import com.example.bookangel.services.MainPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/main/*")
@RequiredArgsConstructor
public class MainPageController {
    private final MainPageService mainPageService;

    @GetMapping("mainPage")
    public String mainPage(){
        return "main/mainPage";
    }

    @GetMapping("list")
    public String list(Criteria criteria, Model model){
        log.info("-------------------------------");
        log.info("list");
        log.info("-------------------------------");
        model.addAttribute("list", mainPageService.getOkList(criteria));
        model.addAttribute("pageMaker", new PageDTO(mainPageService.getTotal(criteria), 10, criteria));
        return "main/mainPage";
    }

}
