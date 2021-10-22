package com.example.bookangel.beans.dao;

import com.example.bookangel.beans.vo.CouponVO;
import com.example.bookangel.mappers.CouponMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Slf4j
public class CouponDAOTest {

    @Autowired
    private CouponDAO couponDAO;

    @Test
    public void checkCoupon(){
        CouponVO couponVO = new CouponVO();
        couponVO.setCouponName("AAAA-AAAA-AAAA-AAAA");
        log.info("---------------------------------------------");
        log.info("DAOTest : "+ couponDAO.checkCoupon(couponVO));

    }

}
