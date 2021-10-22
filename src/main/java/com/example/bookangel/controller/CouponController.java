package com.example.bookangel.controller;

import com.example.bookangel.beans.vo.CouponVO;
import com.example.bookangel.services.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@Slf4j
@RequestMapping("/coupon/*")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @PostMapping(value = "checkCoupon", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> checkCoupon(String couponName) throws UnsupportedEncodingException {
        log.info("쿠폰 사용 확인...........");
        CouponVO couponVO = new CouponVO();
        couponVO.setCouponName(couponName);
        return couponService.checkCoupon(couponVO) == 1?
                new ResponseEntity<>(new String("1".getBytes(),"UTF-8"), HttpStatus.OK) :
                new ResponseEntity<>(new String("0".getBytes(),"utf-8"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
