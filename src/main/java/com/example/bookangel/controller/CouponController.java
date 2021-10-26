package com.example.bookangel.controller;

import com.example.bookangel.beans.vo.CouponVO;
import com.example.bookangel.beans.vo.PaymentVO;
import com.example.bookangel.services.CouponService;
import com.example.bookangel.services.MemberService;
import com.example.bookangel.services.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@Slf4j
@RequestMapping("/coupon/*")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;
    private final MemberService memberService;
    private final PaymentService paymentService;

    @PostMapping(value = "checkCoupon", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> checkCoupon(String couponName, HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        HttpSession session = request.getSession();

        // 멤버 로그인을 위해 작업 나중에 지울것!
        session.setAttribute("memberNum", 21);

        log.info("쿠폰 사용 확인...........");
        CouponVO couponVO = new CouponVO();
        couponVO.setCouponName(couponName);
        Integer result = couponService.checkCoupon(couponVO);
        if(result != null){
            session.setAttribute("couponNum",result.intValue());
            log.info("--------------------------------------");
            log.info("CouponController : couponNum [" + result.intValue() + "]");
            log.info("CouponController : couponNum_Session [" + session.getAttribute("couponNum") + "]");
        }
        return result != null?
                new ResponseEntity<>(new String("1".getBytes(),"UTF-8"), HttpStatus.OK) :
                new ResponseEntity<>(new String("0".getBytes(),"UTF-8"), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
