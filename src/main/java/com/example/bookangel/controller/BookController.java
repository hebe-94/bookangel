package com.example.bookangel.controller;


import com.example.bookangel.beans.vo.Criteria;
import com.example.bookangel.beans.vo.PageDTO;
import com.example.bookangel.beans.vo.PaymentVO;
import com.example.bookangel.services.BookService;
import com.example.bookangel.services.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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


    private final PaymentService paymentService;

    @GetMapping("list")
    public String list(Criteria criteria, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();

        log.info("keyword : " + criteria.getKeyword());
        log.info("-------------------------------");
        log.info("list");
        log.info("-------------------------------");

        //bookService.getList(criteria1).forEach(bookVO -> log.info(bookVO.toString()));

        model.addAttribute("list", bookService.getList(criteria));
        model.addAttribute("pageMaker", new PageDTO(bookService.getTotal(criteria), 10, criteria));


        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("memberType", session.getAttribute("memberType"));
        model.addAttribute("memberId", session.getAttribute("memberId"));
        model.addAttribute("memberName", session.getAttribute("memberName"));
        model.addAttribute("sub", session.getAttribute("sub"));

        return "book/list";
    }




    //    ?????? ????????? ????????? ???????????? ?????? ????????? {}??? ???????????? ????????? ????????????.
    @GetMapping({"read", "modify"})
    public void read(@RequestParam("bookNum") long bookNum, Criteria criteria, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();

        String reqURI = request.getRequestURI();
        String reqType = reqURI.substring(reqURI.indexOf(request.getContextPath()) + 7);
        //read ?????? ??? read ??????
        //modify ?????? ??? modify ??????
        log.info("-------------------------------");
        log.info(reqType + " : " + bookNum);
        log.info("-------------------------------");


        model.addAttribute("book", bookService.get(bookNum));
        model.addAttribute("criteria", criteria);

        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("memberType", session.getAttribute("memberType"));
        model.addAttribute("memberId", session.getAttribute("memberId"));
        model.addAttribute("memberName", session.getAttribute("memberName"));
        model.addAttribute("sub", session.getAttribute("sub"));

        log.info("sub : " + model.getAttribute("sub"));
        log.info("memberNum : " + model.getAttribute("memberNum"));

    }

}







