package com.example.bookangel.mappers;

import com.example.bookangel.beans.vo.PaymentVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {
    // 결제하기
    public void subscribe(PaymentVO paymentvo);


}
