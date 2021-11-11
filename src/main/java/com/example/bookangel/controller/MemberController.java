package com.example.bookangel.controller;

import com.example.bookangel.beans.vo.BookBasketVO;
import com.example.bookangel.beans.vo.MemberVO;
import com.example.bookangel.beans.vo.PaymentVO;
import com.example.bookangel.services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Random;

@Controller
@Slf4j
@RequestMapping("/member/*")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PaymentService paymentService;
    private final CouponService couponService;
    private final BookBasketService bookBasketService;
    // 페이지 이동
    @GetMapping("mypage")
    public void mypage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        if(session.getAttribute("sub").equals("true")) {
            PaymentVO paymentVO = paymentService.searchPayment((Long) session.getAttribute("memberNum"));
            model.addAttribute("startSub", paymentVO.getSubDate().substring(0,10));
            model.addAttribute("endSub", paymentVO.getExpireDate().substring(0,10));
            model.addAttribute("end",paymentVO.getSubMonth());
        }
        if(bookBasketService.myBasketCNT((Long)session.getAttribute("memberNum"))!=0){
            model.addAttribute("myBaskets",bookBasketService.myBasket((Long)session.getAttribute("memberNum")));
        }else{
            model.addAttribute("myBaskets","null");
        }
        String memberId = (String)session.getAttribute("memberId");
        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("sessionType", session.getAttribute("memberType"));
        model.addAttribute("memberId", memberId);
        model.addAttribute("memberName", session.getAttribute("memberName"));
        model.addAttribute("sub", session.getAttribute("sub"));
        model.addAttribute("myCouponCnt", couponService.companyCouponListCNT((Long)session.getAttribute("memberNum")));
        log.info("쿠폰"+couponService.companyCouponListCNT((Long)session.getAttribute("memberNum")));
        model.addAttribute("myInfo", memberService.getMyInfo(memberId));
        if(couponService.companyCouponListCNT((Long)session.getAttribute("memberNum"))!=0){
            model.addAttribute("coupons",couponService.companyCouponList((Long)session.getAttribute("memberNum")));
        }else{
            model.addAttribute("coupons", "null");
        }
    }

    @GetMapping("withdraw")
    public void withdraw(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("sessionType", session.getAttribute("memberType"));
        model.addAttribute("memberId", session.getAttribute("memberId"));
        model.addAttribute("memberName", session.getAttribute("memberName"));
    }

    @GetMapping("memberModify")
    public void memberModify(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        model.addAttribute("myInfo", memberService.getMyInfo((String)session.getAttribute("memberId")));
        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("sessionType", session.getAttribute("memberType"));
        model.addAttribute("memberId", session.getAttribute("memberId"));
        model.addAttribute("memberName", session.getAttribute("memberName"));
    }

    @GetMapping("login")
    public void login(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        model.addAttribute("memberId", session.getAttribute("memberId"));
    }

    @GetMapping("join")
    public void join(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        model.addAttribute("memberId", session.getAttribute("memberId"));
    }

    @PostMapping("changePW")
    public void changePw(MemberVO memberVO, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        model.addAttribute("check", session.getAttribute("memberId"));
        model.addAttribute("memberId", memberVO.getMemberId());
        model.addAttribute("memberTel", memberVO.getMemberTel());
    }

    @GetMapping("findPW")
    public void findPw(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        model.addAttribute("memberId", session.getAttribute("memberId"));
    }

    @GetMapping("findedID")
    public void findedID(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        model.addAttribute("memberId", session.getAttribute("memberId"));
    }

    @GetMapping("findID")
    public void findID(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        model.addAttribute("memberId", session.getAttribute("memberId"));
    }


    @GetMapping("logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "member/login";
    }

    //회원가입
    @PostMapping("join")
    public String join(MemberVO memberVO,HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        model.addAttribute("memberId", session.getAttribute("memberId"));
        String memberTel = memberVO.getMemberTel();
        if(memberTel.length() == 10) {
            memberTel = memberTel.substring(0, 3) + "-" + memberTel.substring(3, 6) + "-" + memberTel.substring(6, 10);
        } else if(memberTel.length() == 11) {
            memberTel = memberTel.substring(0, 3) + "-" + memberTel.substring(3, 7) + "-" + memberTel.substring(7, 11);
        }
        memberVO.setMemberTel(memberTel);
        memberService.join(memberVO);
        return "member/login";

    }

    // ID중복검사
    @PostMapping("checkId")
    @ResponseBody
    public String checkId(@RequestParam("memberId") String memberId){
            log.info(memberId);
            int cnt = memberService.checkId(memberId);
            if(cnt==1){
                return "fail";
            }else{
                return "success";
            }
    }
    @PostMapping("login")
    public String login(MemberVO memberVO, HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        MemberVO vo = memberService.login(memberVO);
        PaymentVO paymentVO = new PaymentVO();
        boolean check = vo != null;
        if (check) {
            paymentVO.setMemberNum(vo.getMemberNum());
            boolean status = vo.getMemberStatus()==1;
            if(status){
                model.addAttribute("withDraw","withDraw");
                return "member/login";
            }else {
                if(paymentService.subscribeExist(vo.getMemberNum())){
                    session.setAttribute("sub", "true");
                }else{
                session.setAttribute("sub", "false");
                }

                log.info("vo.getMemberNum : " + vo.getMemberNum());
                log.info(" sub : " + session.getAttribute("sub"));

                session.setAttribute("memberNum", vo.getMemberNum());
                session.setAttribute("memberType", vo.getMemberType());
                session.setAttribute("memberId", vo.getMemberId());
                session.setAttribute("memberName", vo.getMemberName());
                log.info("------------------------------");
                log.info("------------------------------");
                log.info("------------------------------");
                log.info("------------------------------");
                log.info("memberNum : " + session.getAttribute("memberNum"));
                log.info("memberId : " + session.getAttribute("memberId"));
                log.info("------------------------------");
                log.info("------------------------------");

                return "redirect:/main/mainPage";
            }
        }
        else{
            model.addAttribute("flag","false");
            return "/member/login";
        }
    }
    @PostMapping("withdrawcheck")
    @ResponseBody
    public String withdrawcheck(@RequestParam("memberPw") String memberPw, HttpServletRequest request){
        HttpSession session = request.getSession();
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberId((String)session.getAttribute("memberId"));
        memberVO.setMemberPw(memberPw);
        int cnt = memberService.withDrawCheck(memberVO);
        if (cnt == 1){
            return"success";
        }else{
            return "fail";
        }
    }
    @PostMapping("withdraw")
    public String withdraw(HttpServletRequest request){
        HttpSession session = request.getSession();
        Long memberNum = (Long)session.getAttribute("memberNum");
        memberService.withDraw(memberNum);
        session.invalidate();
        return "/member/login";
    }
    @GetMapping("membersmscheck")
    public @ResponseBody
    String membersmscheck(String memberTel){
        Random r  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(r.nextInt(10));
            numStr+=ran;
        }
        if(memberTel.length() == 13) {
            memberTel.replaceAll("-","");
        } else if(memberTel.length() == 12) {
            memberTel.replaceAll("-","");
        }
        log.info(memberTel);

        // sms 내용
        String api_key = "NCS8DMXDKOGK0ARJ";
        String api_secret = "7MNFLAGA5VL4Z1OXIEQCIGNXRLHQ6ODB";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", memberTel);    // 수신전화번호
        params.put("from", "01031203302");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "기북천사 본인인증 : 인증번호는" + "["+numStr+"]" + "입니다.");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
        log.info(numStr);
        return numStr;
    }
    @PostMapping("modifypw")
    public String modifypw(MemberVO memberVO){
        String memberTel = memberVO.getMemberTel();
        if(memberTel.length() == 10) {
            memberTel = memberTel.substring(0, 3) + "-" + memberTel.substring(3, 6) + "-" + memberTel.substring(6, 10);
        } else if(memberTel.length() == 11) {
            memberTel = memberTel.substring(0, 3) + "-" + memberTel.substring(3, 7) + "-" + memberTel.substring(7, 11);
        }
        memberVO.setMemberTel(memberTel);
        log.info(memberTel);
        memberService.modifyPw(memberVO);
        return "member/login";
    }
    @PostMapping("findID")
    public String findID(MemberVO memberVO, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String memberTel = memberVO.getMemberTel();
        if(memberTel.length() == 10) {
            memberTel = memberTel.substring(0, 3) + "-" + memberTel.substring(3, 6) + "-" + memberTel.substring(6, 10);
        } else if(memberTel.length() == 11) {
            memberTel = memberTel.substring(0, 3) + "-" + memberTel.substring(3, 7) + "-" + memberTel.substring(7, 11);
        }
        memberVO.setMemberTel(memberTel);
        model.addAttribute("memberNum", session.getAttribute("memberNum"));
        model.addAttribute("memberId", memberService.findId(memberVO));
        return "member/findedID";
    }
    @PostMapping("memberModify")
    public String memberModify(MemberVO memberVO, HttpServletRequest request){
            HttpSession session = request.getSession();
            memberVO.setMemberNum((Long)session.getAttribute("memberNum"));
        String memberTel = memberVO.getMemberTel();
        if(memberTel.length() == 10) {
            memberTel = memberTel.substring(0, 3) + "-" + memberTel.substring(3, 6) + "-" + memberTel.substring(6, 10);
        } else if(memberTel.length() == 11) {
            memberTel = memberTel.substring(0, 3) + "-" + memberTel.substring(3, 7) + "-" + memberTel.substring(7, 11);
        }
        memberVO.setMemberTel(memberTel);
            memberService.modifyInfo(memberVO);
            return "redirect:/main/mainPage";
    }
    @PostMapping("subscribeCancel")
    @ResponseBody
    public String subscribeCancel(@RequestParam("memberNum") Long memberNum){
        String result = null;
        if(paymentService.subscribeCancel(memberNum)) {
            result = "success";
        }else{
            result = "false";
        }
        return result;
    }
    @PostMapping("checkTel")
    @ResponseBody
    public String checkTel(@RequestParam("memberTel") String memberTel){
        String result = null;
        String tel = memberTel;
        if(memberTel.length() == 10) {
            tel = memberTel.substring(0, 3) + "-" + memberTel.substring(3, 6) + "-" + memberTel.substring(6, 10);
        } else if(memberTel.length() == 11) {
            tel = memberTel.substring(0, 3) + "-" + memberTel.substring(3, 7) + "-" + memberTel.substring(7, 11);
        }
        boolean check = memberService.checkTel(tel);
        if(check){
            result = "success";
        }else{
            result = "false";
        }
        return result;
    }
    @PostMapping("changeTel")
    @ResponseBody
    public String changeTel(@RequestParam("memberTel") String memberTel, HttpServletRequest request){
        HttpSession session = request.getSession();
        String tel = memberTel;
        if(memberTel.length() == 10) {
            tel = memberTel.substring(0, 3) + "-" + memberTel.substring(3, 6) + "-" + memberTel.substring(6, 10);
        } else if(memberTel.length() == 11) {
            tel = memberTel.substring(0, 3) + "-" + memberTel.substring(3, 7) + "-" + memberTel.substring(7, 11);
        }
        log.info("전화번호!"+tel);
        if(!memberService.getMyInfo((String)session.getAttribute("memberId")).getMemberTel().equals(tel)) {
            boolean check = memberService.checkTel(tel);
            if (check) {
                return "success";
            } else {
                return "false";
            }
        }else {
            return "success";
        }
    }
    @PostMapping("checkIdForTel")
    @ResponseBody
    public String checkIdForTel(String memberId,String memberTel){
        String result = null;
        if(memberTel.length() == 10) {
            memberTel = memberTel.substring(0, 3) + "-" + memberTel.substring(3, 6) + "-" + memberTel.substring(6, 10);
        } else if(memberTel.length() == 11) {
            memberTel = memberTel.substring(0, 3) + "-" + memberTel.substring(3, 7) + "-" + memberTel.substring(7, 11);
        }
        log.info("전화번호 : "+memberTel);
        if(memberTel.equals(memberService.checkIdForTel(memberId))){
            result = "success";
        }else{
            result = "false";
        }
        return result;
    }
    @PostMapping("pickBookCancel")
    @ResponseBody
    public String pickBookCancel(Long memberNum, Long bookNum){
        String result = null;
        if(bookBasketService.delete(memberNum, bookNum)){
            if(bookBasketService.myBasketCNT(memberNum)==0){
                return "none";
            }
            result = "success";
        }else{
            result = "false";
        }
        return result;
    }
}

