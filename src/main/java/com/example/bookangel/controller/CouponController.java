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
import java.util.Random;

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
//
//        // 멤버 로그인을 위해 작업 나중에 지울것!
//        session.setAttribute("memberNum", 21);

        log.info("쿠폰 사용 확인...........");
        CouponVO couponVO = new CouponVO();
        couponVO.setCouponName(couponName);
        Long result = couponService.checkCoupon(couponVO);
        if(result != null){
            session.setAttribute("couponNum",result.longValue());
            log.info("--------------------------------------");
            log.info("CouponController : couponNum [" + result.intValue() + "]");
            log.info("CouponController : couponNum_Session [" + session.getAttribute("couponNum") + "]");
        }
        return result != null?
                new ResponseEntity<>(new String("1".getBytes(),"UTF-8"), HttpStatus.OK) :
                new ResponseEntity<>(new String("0".getBytes(),"UTF-8"), HttpStatus.INTERNAL_SERVER_ERROR);
    }




//    @PostMapping(value = "makeCoupon", produces = "text/plain; charset=utf-8")
//    public ResponseEntity<String> makeCoupon(String couponAmount, HttpServletRequest request) throws UnsupportedEncodingException{
//
//        HttpSession session = request.getSession();
//
//        // 로그인 했다 치고
//        session.setAttribute("memberNum",1);
//
//        CouponVO couponVO = new CouponVO();
//        couponVO.setMemberNum((int) session.getAttribute("memberNum"));
//
//
//        log.info("쿠폰 생성하기 [생성 수량 : " + couponAmount + "개]");
//        String data = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        String tempCoupon = "";
//        Random r = new Random();
//        boolean result = false;
//        int makeCnt = 0;
//
//        //// 16자리의 쿠폰번호 만들기
//        while (makeCnt < Integer.parseInt(couponAmount)){
//            tempCoupon = "";
//
//            // 쿠폰번호 생성
//            for (int i = 0; i < 16; i++) {
//                tempCoupon += data.charAt(r.nextInt(data.length()));
//                if(i % 4 == 3 && i < 15){
//                    tempCoupon += "-";
//                }
//            } // for
//
//            // 중복 쿠폰번호 확인
//            couponVO.setCouponName(tempCoupon);
//            if(couponService.isExist(tempCoupon)){
//                result = true; // 쿠폰 번호가 존재
//                continue;
//            }else{
//                // 쿠폰 생성
//                if(couponService.makeCoupon(couponVO)){
//                    // 쿠폰 생성 완료
//                    makeCnt++;
//
//                }else{
//                    result = true; // 생성 실패
//                    continue;
//                }
//            } // 쿠폰 생성
//
//        } // while
//
//
//        return !result? // result : false면 쿠폰 없는거, true면 있는거 (조건식 true면 쿠폰 생성 완료)
//                new ResponseEntity<>(new String("1".getBytes(),"UTF-8"), HttpStatus.OK) :
//                new ResponseEntity<>(new String("0".getBytes(),"UTF-8"), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

}
