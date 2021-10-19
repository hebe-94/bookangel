package com.example.bookangel.mappers;

import com.example.bookangel.beans.vo.PaymentVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PaymentMapperTest {

    @Autowired
    private PaymentMapper paymentMapper;

    @Test
    public void testSubscribe(){
        PaymentVO paymentVO = new PaymentVO();
        paymentVO.setMemberNum(1);
        paymentVO.setCouponNum(1);
        paymentVO.setImpUid("테스트 결제 코드");
        paymentVO.setApprovalNum("테스트 승인 코드");
        paymentVO.setPaymentType(0);
        paymentVO.setSubMonth(1);

        paymentMapper.subscribe(paymentVO);

        log.info("---------------------------------------------");
        log.info("mapper 테스트 결과 : 성공");
        log.info("---------------------------------------------");

    }


}
