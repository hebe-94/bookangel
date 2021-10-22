package com.example.bookangel.mappers;

import com.example.bookangel.beans.vo.CouponVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class CouponMapperTest {

    @Autowired
    private CouponMapper couponMapper;

    @Test
    public void checkCoupon(){
        CouponVO couponVO = new CouponVO();
        couponVO.setCouponName("AAAA-AAAA-AAAA-AAAA");
        log.info("---------------------------------------------");
        log.info("mapperTest : "+ couponMapper.checkCoupon(couponVO));

    }
}
