package com.example.bookangel.beans.dao;

import com.example.bookangel.beans.vo.CouponVO;
import com.example.bookangel.beans.vo.PaymentVO;
import com.example.bookangel.mappers.CouponMapper;
import com.example.bookangel.mappers.PaymentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CouponDAO {
    private final CouponMapper couponMapper;

    // 쿠폰 사용 가능여부 확인
    public Long checkCoupon(CouponVO couponVO){
        log.info("[DAO]-쿠폰 확인...............");
        return couponMapper.checkCoupon(couponVO);
    }

    // 쿠폰 사용
    public boolean useCoupon(CouponVO couponVO){
        log.info("[DAO]-쿠폰 사용...............");
        return couponMapper.useCoupon(couponVO) == 1;
    }

    // 기업 쿠폰 리스트 조회
    public List<CouponVO> companyCouponList(long memberNum){
        log.info("[DAO]-기업 쿠폰 리스트 확인...............");
        return couponMapper.companyCouponList(memberNum);
    }

    public boolean isExist(String couponName){
        log.info("[DAO]-쿠폰이 존재하는지...............");
        return couponMapper.isExist(couponName) == 1L;
    }

    // 쿠폰만들기
    public boolean makeCoupon(CouponVO couponVO){
        log.info("[DAO]-쿠폰 생성...............");
        return couponMapper.makeCoupon(couponVO) == 1L;
    }

    //  기업 쿠폰 리스트 조회시 몇개 있는지확인
    public long companyCouponListCNT(long memberNum){
        log.info("[DAO]-기업 쿠폰 리스트 개수 확인...............");
        return couponMapper.companyCouponListCNT(memberNum);
    }
}
