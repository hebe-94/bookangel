package com.example.bookangel.dao;

import com.example.bookangel.VO.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MemberDAOTest {
    @Autowired
    private MemberDAO memberDAO;

    @Test
    public void testInsert(){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberId("1233");
        memberVO.setMemberPw("1234");
        memberVO.setMemberName("함세훈1");
        memberVO.setMemberEmail("123@naver.com1");
        memberVO.setMemberTel("010-1111-9402");
        memberVO.setMemberZipcode("12345");
        memberVO.setMemberAddress("서울시");
        memberVO.setMemberAddressDetail("강남구");
        memberVO.setMemberAddressEtc("역삼동");
        memberVO.setMemberStatus(0);
        memberVO.setMemberType(0);
        memberDAO.join(memberVO);
    }
    @Test
    public void testLogin(){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberId("1233");
        memberVO.setMemberPw("1234");
        memberDAO.login(memberVO);
    }
}
