package com.example.bookangel.VO;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MemberVO {
    private int memberNum;
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberTel;
    private String memberEmail;
    private String memberZipcode;
    private String memberAddress;
    private String memberAddressDetail;
    private String memberAddressEtc;
    private int memberStatus;
    private int memberType;
}
