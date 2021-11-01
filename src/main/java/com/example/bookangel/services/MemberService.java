package com.example.bookangel.services;

import com.example.bookangel.beans.vo.MemberVO;
import com.example.bookangel.beans.dao.MemberDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberDAO memberDAO;
    public void join(MemberVO memberVO){memberDAO.join(memberVO);}
    public int checkId(String memberId){return memberDAO.checkId(memberId);}
    public MemberVO login(MemberVO memberVO){return memberDAO.login(memberVO);}
    public MemberVO getMyInfo(String memberId){return memberDAO.getMyInfo(memberId);}
    public int withDrawCheck(MemberVO memberVO){return memberDAO.withDrawCheck(memberVO);}
    public void withDraw(int memberNum){memberDAO.withDraw(memberNum);}
    public void modifyPw(MemberVO memberVO){memberDAO.modifyPw(memberVO);}
    public String findId(MemberVO memberVO){return memberDAO.findId(memberVO);}
    public void modifyInfo(MemberVO memberVO){memberDAO.modifyInfo(memberVO);}
}
