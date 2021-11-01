package com.example.bookangel.beans.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PaymentVO {

    private long paymentNum;
    private long memberNum;
    private long couponNum;
    private String impUid;
    private String merchantUid;
    private long paymentType;
    private long subMonth;
    private String subDate;
    private String expireDate;
}
