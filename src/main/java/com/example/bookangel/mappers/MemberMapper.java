package com.example.bookangel.mappers;

import com.example.bookangel.VO.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    //회원가입
    public void insert(MemberVO memberVO);
    //아이디 중복검사
    public int select(String memberId);
}
