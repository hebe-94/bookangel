package com.example.bookangel.services;

import com.example.bookangel.beans.vo.CouponVO;
import com.example.bookangel.beans.vo.PaymentVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class CouponServiceTest {

    @Autowired
    private CouponService couponService ;

    @Test
    public void testSubscribe(){
        CouponVO couponVO = new CouponVO();
        couponVO.setCouponName("AAAA-AAAA-AAAA-AAAa");
        log.info("---------------------------------");
        log.info("[Coupon Service Test] : " + couponService.checkCoupon(couponVO));
        log.info("---------------------------------");
    }

    @Test
    public void useCoupon(){
        CouponVO couponVO = new CouponVO();
        couponVO.setCouponNum(17);
        couponVO.setMemberNum(21);

        log.info("---------------------------------------------");
        log.info("ServiceTest : "+ couponService.useCoupon(couponVO));
    }

    @Test
    public void companyCouponList(){
        log.info("Coupon Service : 기업 쿠폰 리스트 확인...............");
        couponService.companyCouponList(1).forEach(couponVO -> couponVO.toString());
    }
}
