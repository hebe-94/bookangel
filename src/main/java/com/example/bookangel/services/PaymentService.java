package com.example.bookangel.services;

import com.example.bookangel.beans.vo.PaymentVO;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    // 구독
    public void subscribe(PaymentVO paymentVO);
}
