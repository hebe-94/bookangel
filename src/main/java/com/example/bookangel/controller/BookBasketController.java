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

    @PostMapping("addBookBasket")
    @ResponseBody
    public String addBookBasket(@RequestParam("imgSrc") String imgSrc, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();

        long memberNum = Long.parseLong(session.getAttribute("memberNum").toString());
        if (bookBasketService.isExist(imgSrc, memberNum) == 0) { // 0이면 책가방에 안담겨있음
            if(bookBasketService.addBookBasket(imgSrc, memberNum)){
                return "success";
            }else{
                return "false";
            }
        } else { // 책가방에 담겨있음
            return "overlap";
        }

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
