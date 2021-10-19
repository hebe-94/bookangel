package com.example.bookangel.services;

import com.example.bookangel.VO.MemberVO;
import com.example.bookangel.dao.MemberDAO;
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
}
