package com.example.bookangel.mappers;

import com.example.bookangel.beans.vo.PaymentVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {
    // 결제하기
    public long subscribe(PaymentVO paymentvo);

    // 결제 이력 확인하기
    public long paymentExist(PaymentVO paymentVO);

    // 재결제 [첫결제의 한달 무료가 없다]
    public long resubscribe(PaymentVO paymentVO);

    // 구독중인지 확인
    public long subscribeExist(long memberNum);

    // 회원에 따른 구독 정보 가져오기
    public PaymentVO searchPayment(long memberNum);

    // 구독 취소
    public long subscribeCancel(long memberNum);
}
