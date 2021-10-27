package com.example.bookangel.beans.dao;

import com.example.bookangel.beans.vo.CouponVO;
import com.example.bookangel.mappers.CouponMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
@Slf4j
public class CouponDAOTest {

    @Autowired
    private CouponDAO couponDAO;

    @Test
    public void checkCoupon(){
        CouponVO couponVO = new CouponVO();
        couponVO.setCouponName("AAAA-AAAA-AAAA-AAAv");
        log.info("---------------------------------------------");
        log.info("DAOTest : "+ couponDAO.checkCoupon(couponVO));

    }

    @Test
    public void useCoupon(){
        CouponVO couponVO = new CouponVO();
        couponVO.setCouponNum(17);
        couponVO.setMemberNum(21);

        log.info("---------------------------------------------");
        log.info("DAOTest : "+ couponDAO.useCoupon(couponVO));
    }

    @Test
    public void TestcompanyCouponList(){
        log.info("[DAO]-기업 쿠폰 리스트 확인...............");
        couponDAO.companyCouponList(1).forEach(couponVO -> log.info(couponVO.toString()));
    }

}
