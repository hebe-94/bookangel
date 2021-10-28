package com.example.bookangel.beans.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CouponVO {

    private long couponNum;
    private String couponName;
    private long couponStatus;
    private long memberNum; // 사용자에 대한 정보
    private long ownNum; // 소유 기업에 대한 정보
    
}
