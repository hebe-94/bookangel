package com.example.bookangel.controller;

import com.example.bookangel.beans.vo.BookVO;
import com.example.bookangel.beans.vo.Criteria;
import com.example.bookangel.beans.vo.PageDTO;
import com.example.bookangel.beans.vo.PaymentVO;
import com.example.bookangel.services.BookBasketService;
import com.example.bookangel.services.CouponService;
import com.example.bookangel.services.MemberService;
import com.example.bookangel.services.PaymentService;
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
    private final PaymentService paymentService;
    private final CouponService couponService;
    private final MemberService memberService;

    // main에서 책담을때 사용하는것
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


        if(session.getAttribute("sub").equals("true")) {
            PaymentVO paymentVO = paymentService.searchPayment((Long) session.getAttribute("memberNum"));
            model.addAttribute("startSub", paymentVO.getSubDate().substring(0,10));
            model.addAttribute("endSub", paymentVO.getExpireDate().substring(0,10));
            model.addAttribute("end",paymentVO.getSubMonth());
        }
        if(bookBasketService.myBasketCNT((Long)session.getAttribute("memberNum"))!=0){
            model.addAttribute("myBaskets",bookBasketService.myBasket((Long)session.getAttribute("memberNum")));
        }else{
            model.addAttribute("myBaskets","null");
        }
        String memberId = (String)session.getAttribute("memberId");
        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("sessionType", session.getAttribute("memberType"));
        model.addAttribute("memberId", memberId);
        model.addAttribute("memberName", session.getAttribute("memberName"));
        model.addAttribute("sub", session.getAttribute("sub"));
        model.addAttribute("myCouponCnt", couponService.companyCouponListCNT((Long)session.getAttribute("memberNum")));
        log.info("쿠폰"+couponService.companyCouponListCNT((Long)session.getAttribute("memberNum")));
        model.addAttribute("myInfo", memberService.getMyInfo(memberId));
        if(couponService.companyCouponListCNT((Long)session.getAttribute("memberNum"))!=0){
            model.addAttribute("coupons",couponService.companyCouponList((Long)session.getAttribute("memberNum")));
        }else{
            model.addAttribute("coupons", "null");
        }

        return "member/mypage";
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
