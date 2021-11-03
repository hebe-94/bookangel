package com.example.bookangel.controller;

import com.example.bookangel.beans.vo.BookVO;
import com.example.bookangel.beans.vo.Criteria;
import com.example.bookangel.beans.vo.PageDTO;
import com.example.bookangel.services.BookBasketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/bookBasket/*")
@RequiredArgsConstructor
public class BookBasketController {

    private final BookBasketService bookBasketService;

    // main에서 책담을때 사용하는것
    @PostMapping("addBookBasket")
    public String addBookBasket(@RequestParam("imgSrc") String imgSrc, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();

        long memberNum = Long.parseLong(session.getAttribute("memberNum").toString());
        if (bookBasketService.isExist(imgSrc, memberNum) == 0) { // 0이면 책가방에 안담겨있음
            if(bookBasketService.addBookBasket(imgSrc, memberNum)){
                // 책가방 담기 성공
            }else{
                // 책가방 담기 실패
            }
        } else { // 책가방에 담겨있음

        }

        return "main/mainPage";
    }

    /*2021.11.04 kkh*/
    // 책검색 후 상세보기에서 책가방 담기
    @GetMapping("addBookBasketToBookNum")
    public String addBookBasketToBookNum(@RequestParam("bookNum") long bookNum, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();

        long memberNum = Long.parseLong(session.getAttribute("memberNum").toString());
        if (bookBasketService.isExistToBookNum(bookNum, memberNum) == 0) { // 0이면 책가방에 안담겨있음
            if(bookBasketService.addBookBasketToBookNum(bookNum, memberNum)){
                // 책가방 담기 성공
                log.info("------------------------------------");
                log.info("책가방 담기 성공");
                model.addAttribute("result","success");
            }else{
                // 책가방 담기 실패
                log.info("------------------------------------");
                log.info("책가방 담기 실패");
                model.addAttribute("result","fail");

            }
        } else { // 책가방에 담겨있음
            model.addAttribute("result","fail");
        }
        String referer = request.getHeader("Referer");

        return "redirect"+referer;
        /*return "member/mypage";*/
    }

    @PostMapping("myBasket")
    public String myBasket(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();

        long memberNum = Long.parseLong(session.getAttribute("memberNum").toString());
        List<BookVO> myBookList = bookBasketService.myBasket(memberNum);
            if(myBookList == null){
                // 책가방 리스트 실패
            }else{
                // 책가방 리스트 성공
                model.addAttribute("bookList",myBookList);
            }


        return "member/mypage";
    }





}
