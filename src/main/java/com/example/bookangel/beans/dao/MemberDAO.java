package com.example.bookangel.beans.dao;

import com.example.bookangel.beans.vo.MemberVO;
import com.example.bookangel.mappers.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberDAO {

    private final MemberMapper memberMapper;

    public void join(MemberVO memberVO){memberMapper.insert(memberVO);}
    public int checkId(String memberId){return memberMapper.count(memberId);}
    public MemberVO login(MemberVO memberVO){return memberMapper.select(memberVO);}
    public MemberVO getMyInfo(String memberId){return memberMapper.info(memberId);}
    public int withDrawCheck(MemberVO memberVO){return memberMapper.check(memberVO);}
    public void withDraw(int memberNum){memberMapper.status(memberNum);}
    public void modifyPw(MemberVO memberVO){memberMapper.change(memberVO);}
    public String findId(MemberVO memberVO){return memberMapper.getId(memberVO);}
}
