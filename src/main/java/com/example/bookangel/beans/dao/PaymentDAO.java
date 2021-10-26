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

    public boolean subscribe(PaymentVO paymentVO){
        log.info("[DAO]-결제하기...............");
        return paymentMapper.subscribe(paymentVO) == 1;
    }

    public boolean paymentExist(PaymentVO paymentVO){
        log.info("[DAO]-결제내역 있는지 확인하기...............");
        return paymentMapper.paymentExist(paymentVO) == 1;
    }

    public boolean resubscribe(PaymentVO paymentVO){
        log.info("[DAO]-재결제 하기...............");
        return paymentMapper.resubscribe(paymentVO) == 1;
    }

}
