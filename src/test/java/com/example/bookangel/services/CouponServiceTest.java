package com.example.bookangel.services;

import com.example.bookangel.beans.vo.CouponVO;
import com.example.bookangel.beans.vo.PaymentVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class CouponServiceTest {

    @Autowired
    private CouponService couponService ;

    @Test
    public void testSubscribe(){
        CouponVO couponVO = new CouponVO();
        couponVO.setCouponName("AAAA-AAAA-AAAA-AAAA");
        log.info("---------------------------------");
        log.info("[Coupon Service Test] : " + couponService.checkCoupon(couponVO));
        log.info("---------------------------------");
    }
}
