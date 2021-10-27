package com.example.bookangel.beans.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CouponVO {

    private int couponNum;
    private String couponName;
    private int couponStatus;
    private int memberNum; // 사용자에 대한 정보
    private int ownNum; // 소유 기업에 대한 정보
    
}
