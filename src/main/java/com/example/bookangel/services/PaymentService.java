package com.example.bookangel.services;

import com.example.bookangel.beans.vo.PaymentVO;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    // 구독
    public boolean subscribe(PaymentVO paymentVO);

    // 구독이력 확인
    public boolean paymentExist(PaymentVO paymentVO);

    // 재구독
    public boolean resubscribe(PaymentVO paymentVO);

    // 구독 확인
    public boolean subscribeExist(int memberNum);

    // 회원에 따른 구독 정보 가져오기
    public PaymentVO searchPayment(int memberNum);
}
