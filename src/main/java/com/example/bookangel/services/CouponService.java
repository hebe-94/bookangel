package com.example.bookangel.services;

import com.example.bookangel.beans.dao.CouponDAO;
import com.example.bookangel.beans.vo.CouponVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CouponService {
    private final CouponDAO couponDAO;

    public int checkCoupon(CouponVO couponVO){
        log.info("Coupon Service : check...........");
        return couponDAO.checkCoupon(couponVO);
    }

}
