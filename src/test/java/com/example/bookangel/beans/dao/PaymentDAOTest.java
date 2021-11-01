package com.example.bookangel.beans.dao;

import com.example.bookangel.beans.vo.PaymentVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PaymentDAOTest {

    @Autowired
    private PaymentDAO paymentDAO;


/*    #{memberNum}, #{couponNum}, #{impUid}, #{approvalNum}, #{paymentType}, #{subMonth}*/
    @Test
    public void testSubscribe(){
        PaymentVO paymentVO = new PaymentVO();
        paymentVO.setMemberNum(1L);
        paymentVO.setCouponNum(1);
        paymentVO.setImpUid("테스트 결제 코드");
        paymentVO.setMerchantUid("테스트 승인 코드");
        paymentVO.setPaymentType(0);
        paymentVO.setSubMonth(1);

        paymentDAO.subscribe(paymentVO);

        log.info("---------------------------------");
        log.info("구독 신청 완료");
        log.info("---------------------------------");
    }

}
