package com.example.bookangel.mappers;

import com.example.bookangel.VO.MemberVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberMapperTest {

    @Autowired
    private MemberMapper mapper;

//    @Test
//    public void testCount(){
//        mapper.count("ddd");
//    }
    @Test
    public void testInsert(){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberId("hamsh95");
        memberVO.setMemberPw("hamse0606");
        memberVO.setMemberName("함세훈");
        memberVO.setMemberEmail("hamsh95@naver.com");
        memberVO.setMemberTel("010-5559-9401");
        memberVO.setMemberZipcode("12345");
        memberVO.setMemberAddress("서울시");
        memberVO.setMemberAddressDetail("강남구");
        memberVO.setMemberAddressEtc("역삼동");
        memberVO.setMemberStatus(0);
        memberVO.setMemberType(0);
        mapper.insert(memberVO);

    }

}
