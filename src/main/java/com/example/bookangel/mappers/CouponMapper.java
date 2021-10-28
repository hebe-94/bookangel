package com.example.bookangel.mappers;

import com.example.bookangel.beans.vo.CouponVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CouponMapper {
    // 사용 가능한 쿠폰인지
    public Long checkCoupon(CouponVO couponVO);

    // 쿠폰 사용
    public long useCoupon(CouponVO couponVO);

    // 기업 쿠폰 리스트 조회
    public List<CouponVO> companyCouponList(long memberNum);

    // 쿠폰이 있는지
    public long isExist(String couponName);

    // 쿠폰만들기
    public long makeCoupon(CouponVO couponVO);
}

