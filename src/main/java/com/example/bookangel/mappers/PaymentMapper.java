package com.example.bookangel.mappers;

import com.example.bookangel.beans.vo.PaymentVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {
    // 결제하기
    public int subscribe(PaymentVO paymentvo);

    // 결제 이력 확인하기
    public int paymentExist(PaymentVO paymentVO);

    // 재결제 [첫결제의 한달 무료가 없다]
    public int resubscribe(PaymentVO paymentVO);
}
