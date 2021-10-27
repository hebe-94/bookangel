package com.example.bookangel.mappers;

import com.example.bookangel.beans.vo.PaymentVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
@Slf4j
public class PaymentMapperTest {

    @Autowired
    private PaymentMapper paymentMapper;

    @Test
    public void testSubscribe(){
        PaymentVO paymentVO = new PaymentVO();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSS");
        LocalDateTime now = LocalDateTime.now();
        // 현재 시간
        paymentVO.setMemberNum(21);//
        paymentVO.setCouponNum(17);//
        paymentVO.setSubMonth(2);//

//        LocalDateTime after60 = now.plusMonths(2).minusDays(1);
//        String nowTime = now.format(formatter);
//        String after60Time = after60.format(formatter);
//
//        nowTime = nowTime.replaceAll("[-:]", "");
//        after60Time = after60Time.replaceAll("[-:]", "");
//
//        log.info(nowTime);
//        log.info(after60Time);
//
//        paymentVO.setSubDate(nowTime);//
//        paymentVO.setExpireDate(after60Time);//

        paymentMapper.subscribe(paymentVO);

        log.info("---------------------------------------------");
        log.info("mapper 테스트 결과 : 성공");
        log.info("---------------------------------------------");

    }

    @Test
    public void testPaymentExist(){
        PaymentVO paymentVO = new PaymentVO();
        paymentVO.setMemberNum(21);//
        log.info("---------------------------------------------");
        log.info("mapper 테스트 결과 : " + paymentMapper.paymentExist(paymentVO));
        log.info("---------------------------------------------");

    }

}
