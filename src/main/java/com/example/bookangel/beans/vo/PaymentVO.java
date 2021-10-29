package com.example.bookangel.beans.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PaymentVO {

    private int paymentNum;
    private Long memberNum;
    private int couponNum;
    private String impUid;
    private String merchantUid;
    private int paymentType;
    private int subMonth;
    private String subDate;
    private String expireDate;
}
