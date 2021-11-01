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
        return paymentMapper.subscribe(paymentVO) == 1L;
    }

    public boolean paymentExist(PaymentVO paymentVO){
        log.info("[DAO]-결제내역 있는지 확인하기...............");
        return paymentMapper.paymentExist(paymentVO) == 1L;
    }

    public boolean resubscribe(PaymentVO paymentVO){
        log.info("[DAO]-재결제 하기...............");
        return paymentMapper.resubscribe(paymentVO) == 1L;
    }

    // 구독중인지 확인
    public boolean subscribeExist(long memberNum){
        log.info("[DAO]-구독 확인하기...............");
        return paymentMapper.subscribeExist(memberNum) == 1L;
    }

    // 회원에 따른 구독 정보 가져오기
    public PaymentVO searchPayment(long memberNum) {
        log.info("[DAO]-구독 확인하기...............");
        return paymentMapper.searchPayment(memberNum);
    }

    // 구독 취소
    public boolean subscribeCancel(long memberNum){
        log.info("[DAO]-구독 취소하기...............");
        return paymentMapper.subscribeCancel(memberNum) == 1L;

    }



}
