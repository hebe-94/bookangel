package com.example.bookangel.mappers;

import com.example.bookangel.beans.vo.CouponVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Test
    public void useCoupon(){
        CouponVO couponVO = new CouponVO();
        couponVO.setCouponNum(17);
        couponVO.setMemberNum(21);

        log.info("---------------------------------------------");
        log.info("mapperTest : "+ couponMapper.useCoupon(couponVO));
    }

    @Test
    public void TimeTest(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSS");

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime after30 = now.plusMonths(1).minusDays(1);

        String nowTime = now.format(formatter);
        String after30Time = now.plusMonths(2).minusDays(1).format(formatter);

        log.info("---------------------------------------------");
        log.info("구독 날짜 : "+ nowTime);
        log.info("구독 만료 날짜 : " +after30Time);


    }

    // 기업 전용 쿠폰 리스트 확인
    @Test
    public void TestCompanyCouponList(){

        log.info("---------------------------------------------");
        couponMapper.companyCouponList(1).forEach(couponVO -> log.info(couponVO.toString()));
        log.info("---------------------------------------------");

    }

    // 쿠폰이 있는지
    @Test
    public void TestIsExist(){

        log.info("---------------------------------------------");
        couponMapper.isExist("AAAA-AAAA-AAAA-AAAA");
        log.info("---------------------------------------------");

    }

    // 기업 쿠폰 리스트 조회시 몇개 있는지 확인
    @Test
    public void companyCouponListCNT(){
        log.info("---------------------------------------------");
        log.info(couponMapper.companyCouponListCNT(1L) + "");
    }
}
