package com.example.bookangel.beans.dao;

import com.example.bookangel.beans.vo.CouponVO;
import com.example.bookangel.beans.vo.PaymentVO;
import com.example.bookangel.mappers.CouponMapper;
import com.example.bookangel.mappers.PaymentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CouponDAO {
    private final CouponMapper couponMapper;

    public int checkCoupon(CouponVO couponVO){
        log.info("[DAO]-쿠폰 확인...............");
        return couponMapper.checkCoupon(couponVO);
    }
}
