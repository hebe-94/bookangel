package com.example.bookangel.mappers;

import com.example.bookangel.beans.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MemberMapperTest {

    @Autowired
    private MemberMapper mapper;

    @Test
    public void testCount(){
        mapper.count("ddd");
    }
    @Test
    public void testInsert(){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberId("1233");
        memberVO.setMemberPw("1234");
        memberVO.setMemberName("함세훈");
        memberVO.setMemberEmail("123@naver.com");
        memberVO.setMemberTel("010-1111-9401");
        memberVO.setMemberZipcode("12345");
        memberVO.setMemberAddress("서울시");
        memberVO.setMemberAddressDetail("강남구");
        memberVO.setMemberAddressEtc("역삼동");
        memberVO.setMemberStatus(0);
        memberVO.setMemberType(0);
        mapper.insert(memberVO);
    }
    @Test
    public void testSelect(){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberId("1233");
        memberVO.setMemberPw("1234");
        mapper.select(memberVO);
    }
    @Test
    public void testInfo(){
        mapper.info("hamsh95");
    }
    @Test
    public void testCheck(){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberPw("1234");
        memberVO.setMemberId("1234");
        mapper.check(memberVO);
    }

}
