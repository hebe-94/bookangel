package com.example.bookangel.mappers;

import com.example.bookangel.beans.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    //회원가입
    public void insert(MemberVO memberVO);
    //아이디 중복검사
    public int count(String memberId);
    //로그인
    public MemberVO select(MemberVO memberVO);
    //내 정보
    public MemberVO info(String memberId);
    //탈퇴 비밀번호 확인
    public int check(MemberVO memberVO);
    //회원탈퇴
    public void status(int memberNum);
    //비밀번호 변경
    public void change(MemberVO memberVO);
    //아이디 찾기
    public String getId(MemberVO memberVO);
    //정보 수정
    public void modify(MemberVO memberVO);
    //휴대폰번호 중복검사
    public int countTel(String memberTel);
}
