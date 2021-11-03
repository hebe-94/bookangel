package com.example.bookangel.controller;


import com.example.bookangel.beans.vo.Criteria;
import com.example.bookangel.beans.vo.PageDTO;
import com.example.bookangel.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Controller
@Slf4j
@RequestMapping("/book/*")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    Criteria criteria1 = new Criteria();


    @GetMapping("list")
    public String list(Criteria criteria, Model model){
        log.info("keyword : " + criteria.getKeyword());
        log.info("-------------------------------");
        log.info("list");
        log.info("-------------------------------");

        //bookService.getList(criteria1).forEach(bookVO -> log.info(bookVO.toString()));

        model.addAttribute("list", bookService.getList(criteria));
        model.addAttribute("pageMaker", new PageDTO(bookService.getTotal(criteria), 10, criteria));
        return "book/list";
    }




    //    여러 요청을 하나의 메소드로 받을 때에는 {}를 사용하여 콤마로 구분한다.
    @GetMapping({"read", "modify"})
    public void read(@RequestParam("bookNum") long bookNum, Criteria criteria, Model model, HttpServletRequest request){
        String reqURI = request.getRequestURI();
        String reqType = reqURI.substring(reqURI.indexOf(request.getContextPath()) + 7);
        //read 요청 시 read 출력
        //modify 요청 시 modify 출력
        log.info("-------------------------------");
        log.info(reqType + " : " + bookNum);
        log.info("-------------------------------");


        model.addAttribute("book", bookService.get(bookNum));
        model.addAttribute("criteria", criteria);
    }

}


















