package com.example.bookangel.beans.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PaymentVO {

    private int paymentNum;
    private int memberNum;
    private int couponNum;
    private String impUid;
    private String approvalNum;
    private int paymentType;
    private int subMonth;
    private String subDate;
    private String expireDate;
}
