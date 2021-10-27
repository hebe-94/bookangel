package com.example.bookangel.services;

import com.example.bookangel.beans.dao.PaymentDAO;
import com.example.bookangel.beans.vo.PaymentVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Test
    public void testSubscribe(){
        PaymentVO paymentVO = new PaymentVO();
        paymentVO.setMemberNum(1);
        paymentVO.setCouponNum(1);
        paymentVO.setImpUid("테스트 결제 코드");
        paymentVO.setMerchantUid("테스트 승인 코드");
        paymentVO.setPaymentType(0);
        paymentVO.setSubMonth(1);

        paymentService.subscribe(paymentVO);

        log.info("---------------------------------");
        log.info("[서비스 단] - 구독 신청 완료");
        log.info("---------------------------------");
    }

    @Test
    public void testSubscribeExist(){

        log.info("---------------------------------");
        log.info("[payment service : " + paymentService.subscribeExist(1));
        log.info("---------------------------------");
    }

    // 회원 구독 현황 가져오기
    @Test
    public void testSearchPayment(){
        log.info("---------------------------------");
        log.info("[payment service : " + paymentService.searchPayment(1));
        log.info("---------------------------------");
    }
}
