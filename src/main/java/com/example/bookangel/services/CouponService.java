package com.example.bookangel.services;

import com.example.bookangel.beans.dao.CouponDAO;
import com.example.bookangel.beans.vo.CouponVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CouponService {
    private final CouponDAO couponDAO;

    // 쿠폰 사용 여부 확인
    public Integer checkCoupon(CouponVO couponVO){
        log.info("Coupon Service : check...........");
        return couponDAO.checkCoupon(couponVO);
    }

    // 쿠폰 사용
    public boolean useCoupon(CouponVO couponVO){
        log.info("Coupon Service : useCoupon............");
        return couponDAO.useCoupon(couponVO);
    }

    // 기업 쿠폰 리스트 조회
    public List<CouponVO> companyCouponList(int memberNum){
        log.info("Coupon Service : 기업 쿠폰 리스트 확인...............");
        return couponDAO.companyCouponList(memberNum);
    }

}
