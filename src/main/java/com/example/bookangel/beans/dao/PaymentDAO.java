package com.example.bookangel.beans.dao;


import com.example.bookangel.mappers.PaymentMapper;
import com.example.bookangel.beans.vo.PaymentVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PaymentDAO {
    private final PaymentMapper paymentMapper;

    public void subscribe(PaymentVO paymentVO){
        log.info("[DAO]-결제하기...............");
        paymentMapper.subscribe(paymentVO);
    }

}
