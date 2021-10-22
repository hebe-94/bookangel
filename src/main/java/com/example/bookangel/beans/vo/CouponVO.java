package com.example.bookangel.beans.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CouponVO {

    private int couponNum;
    private String couponName;
    private int couponStatus;
    private int memberNum;
}
