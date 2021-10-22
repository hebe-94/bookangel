package com.example.bookangel.mappers;

import com.example.bookangel.beans.vo.CouponVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CouponMapper {
    // 사용 가능한 쿠폰인지
    public int checkCoupon(CouponVO couponVO);


}

