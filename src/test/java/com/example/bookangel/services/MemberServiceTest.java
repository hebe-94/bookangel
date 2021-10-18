package com.example.bookangel.services;

import com.example.bookangel.VO.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Test
    public void testJoin(){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberId("hamsh");
        memberVO.setMemberPw("hamse0606");
        memberVO.setMemberName("함세훈123");
        memberVO.setMemberEmail("hamsh95@nav.com");
        memberVO.setMemberTel("010-5559-9403");
        memberVO.setMemberZipcode("12345");
        memberVO.setMemberAddress("서울시");
        memberVO.setMemberAddressDetail("강남구");
        memberVO.setMemberAddressEtc("역삼동");
        memberVO.setMemberStatus(0);
        memberVO.setMemberType(0);
        memberService.join(memberVO);
    }
}
