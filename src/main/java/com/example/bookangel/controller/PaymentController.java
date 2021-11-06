package com.example.bookangel.controller;

import com.example.bookangel.beans.vo.CouponVO;
import com.example.bookangel.beans.vo.Criteria;
import com.example.bookangel.beans.vo.PageDTO;
import com.example.bookangel.beans.vo.PaymentVO;
import com.example.bookangel.services.CouponService;
import com.example.bookangel.services.MainPageService;
import com.example.bookangel.services.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@Slf4j
@RequestMapping("/payment/*")
@RequiredArgsConstructor
public class PaymentController {

    // PaymentService 객체 선언
    private final PaymentService paymentService;
    private final CouponService couponService;
    private final MainPageService mainPageService;

    // 페이지 이동
    @GetMapping("subscribe")
    public void subscribe(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        log.info((String) session.getAttribute("memberId"));
        log.info("-------------------------------");
        log.info("mainPage");
        log.info("-------------------------------");
        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("sessionType", session.getAttribute("memberType"));
        model.addAttribute("memberId", session.getAttribute("memberId"));
        model.addAttribute("memberName", session.getAttribute("memberName"));
        model.addAttribute("sub",session.getAttribute("sub"));

        log.info("--SUB--" + session.getAttribute("sub"));

    }

    @GetMapping("subscribeCancel")
    public void subscribeCancel(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        log.info((String) session.getAttribute("memberId"));
        log.info("-------------------------------");
        log.info("mainPage");
        log.info("-------------------------------");
        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("sessionType", session.getAttribute("memberType"));
        model.addAttribute("memberId", session.getAttribute("memberId"));
        model.addAttribute("memberName", session.getAttribute("memberName"));
        model.addAttribute("sub",session.getAttribute("sub"));

        log.info("--SUB--" + session.getAttribute("sub"));

    }


    @GetMapping("payment")
    public void payment(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        log.info((String) session.getAttribute("memberId"));
        log.info("-------------------------------");
        log.info("mainPage");
        log.info("-------------------------------");
        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("sessionType", session.getAttribute("memberType"));
        model.addAttribute("memberId", session.getAttribute("memberId"));
        model.addAttribute("memberName", session.getAttribute("memberName"));
        model.addAttribute("sub",session.getAttribute("sub"));


    }

    @GetMapping("subscribeDetail")
    public void subscribeDetail(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        log.info((String) session.getAttribute("memberId"));
        log.info("-------------------------------");
        log.info("mainPage");
        log.info("-------------------------------");
        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("sessionType", session.getAttribute("memberType"));
        model.addAttribute("memberId", session.getAttribute("memberId"));
        model.addAttribute("memberName", session.getAttribute("memberName"));
        model.addAttribute("sub",session.getAttribute("sub"));

        log.info("--SUB--" + session.getAttribute("sub"));

    }

    // 작업용


    @PostMapping("subscribeDetail")
    public String goSubscribe(@RequestParam("monthType") String monthType, HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        log.info((String) session.getAttribute("memberId"));
        log.info("-------------------------------");
        log.info("subscribeDetailPage");
        log.info("-------------------------------");
        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("sessionType", session.getAttribute("memberType"));
        model.addAttribute("memberId", session.getAttribute("memberId"));
        model.addAttribute("memberName", session.getAttribute("memberName"));
        model.addAttribute("sub",session.getAttribute("sub"));


        log.info("paymentService : 결제 상세정보 넘어가기 ----------------------------------");

        PaymentVO paymentVO = new PaymentVO();
        paymentVO.setMemberNum((Long)session.getAttribute("memberNum"));
        log.info(paymentService.paymentExist(paymentVO) + " : 구독했는가?");

        if(monthType.equals("1")){
        // 가격, 구독기간, 다음 결제일 정보 담아서 보내기!
            log.info("1");
            LocalDate now = LocalDate.now();
            LocalDate after30 = now.plusMonths(1).minusDays(1);
            LocalDate after60 = now.plusMonths(2).minusDays(1);
            LocalDate nextPayment = now.plusMonths(1);


            // 구독이력이 있는지 확인

            // [1달 구독]
            if(paymentService.paymentExist(paymentVO)) { //구독한적 있음
                model.addAttribute("date", "| " + now.toString() + " ~ " + after30.toString());
                model.addAttribute("couponDate", "| " + now.toString() + " ~ " + after30.toString());
                model.addAttribute("nextPayment", "| " +nextPayment.toString());
                model.addAttribute("price", "9,900");

                model.addAttribute("monthType", "1");
                model.addAttribute("subExist","true");
            }else{ // 구독이력이 없음
                model.addAttribute("date", "| " + now.toString() + " ~ " + after30.toString());
                model.addAttribute("couponDate", "| " + now.toString() + " ~ " + after60.toString());
                model.addAttribute("nextPayment", "| " +nextPayment.toString());
                model.addAttribute("price", "9,900");
                model.addAttribute("salePrice","9,800");
                model.addAttribute("monthType", "1");
                model.addAttribute("subExist","false");

            }

            log.info("현재 날짜 : " + now.toString());
            log.info("만료 날짜 : " + after30.toString());
            log.info("쿠폰 만료 날짜 : " + after60.toString());
            log.info("다음 결제 날짜 : " + "| " +nextPayment.toString());
            log.info("결제 금액 : " + "9,900원");

            // [12달 구독]
        }else if(monthType.equals("12")){
            log.info("12");
            LocalDate now = LocalDate.now();
            LocalDate afterYear;
            LocalDate nextPayment;


            if(paymentService.paymentExist(paymentVO)) { //구독한적 있음
                afterYear = now.plusYears(1).minusDays(1);
                nextPayment = now.plusYears(1);
                model.addAttribute("date", "| " + now.toString() + " ~ " + afterYear.toString());
                model.addAttribute("nextPayment", "| " +nextPayment.toString());
                model.addAttribute("price", "99,900원");
                model.addAttribute("monthType", "12");
                model.addAttribute("subExist","true");

            }else{ // 구독이력이 없음
                afterYear = now.plusYears(1).plusMonths(1).minusDays(1);
                nextPayment = now.plusMonths(1);
                model.addAttribute("date", "| " + now.toString() + " ~ " + afterYear.toString());
                model.addAttribute("nextPayment", "| " +nextPayment.toString());
                model.addAttribute("price", "99,900원");
                model.addAttribute("salePrice","99,800");
                model.addAttribute("monthType", "12");
                model.addAttribute("subExist","false");

            }

            log.info("현재 날짜 : " + now.toString());
            log.info("만료 날짜 : " + afterYear.toString());
            log.info("다음 결제 날짜 : " + nextPayment.toString());
            log.info("결제 금액 : " + "99,900");
        }else{
            log.info("잘못 넘어옴");
        }
        log.info("----------------------------------------------------------");

        return "payment/subscribeDetail";
    }

    @PostMapping("register")
    public String register(@RequestParam("paymentType") long paymentType, @RequestParam("merchantUid") String merchantUid, @RequestParam("merchantUid") String impUid, HttpServletRequest request){
        log.info("register 컨트롤러 들어옴");
        HttpSession session = request.getSession();

        // 결제 했다고 등록 [결제 데이터]
        PaymentVO paymentVO = new PaymentVO();
        paymentVO.setMemberNum((long)session.getAttribute("memberNum"));


        // 쿠폰 결제는 2개로 나뉜다. 구독한적이 있는가, 구독한적이 없는가.
        log.info("결제 이력 확인");
        if(paymentService.paymentExist(paymentVO)){ // 구독한적 있음
            log.info("결제 이력 있고 쿠폰 결제 시작");
            log.info(paymentService.paymentExist(paymentVO) + " : 구독한적 있는지");

            // 결제 테이블 구독했다고 등록 (insert)
            if(paymentType == 0l){ // 쿠폰으로 결제

                /*--------쿠폰 사용 등록-----------*/
                log.info("paymentService : 쿠폰으로 결제하기 ----------------------------------");
                log.info("쿠폰 넘버 : " + session.getAttribute("couponNum"));
                log.info("멤버 넘버 : " + session.getAttribute("memberNum"));
                log.info("결제 타입 [0:쿠폰, 1:1달구독, 12:1년구독] : "+paymentType);
                log.info("----------------------------------------------------------");

                CouponVO couponVO = new CouponVO();

                log.info("여기까지");

                // 쿠폰 사용했다고 등록 [쿠폰 데이터]
                couponVO.setCouponNum((long)session.getAttribute("couponNum"));
                couponVO.setMemberNum((long)session.getAttribute("memberNum"));

                log.info("돌아감");

                log.info("쿠폰 num : " + couponVO.getCouponNum());
                log.info("멤버 num : " + couponVO.getMemberNum());
                log.info("쿠폰 사용 등록");
                if(couponService.useCoupon(couponVO)){
                    log.info("수정 성공");
                }else{
                    log.info("수정 실패");
                }
                /*--------//쿠폰 사용 등록//-----------*/

//              #{memberNum}, #{couponNum}, null, null, #{subMonth}, SYSDATE, SYSDATE+60
                paymentVO = new PaymentVO();
                paymentVO.setSubMonth(1l);
                paymentVO.setMemberNum((long)session.getAttribute("memberNum"));
                paymentVO.setCouponNum((long)session.getAttribute("couponNum"));

            }else if(paymentType == 1){ // 카드 1달
//              #{memberNum}, #{couponNum = 0}, #{impUid}, #{merchantUid}, #{subMonth}, SYSDATE, SYSDATE+ '')
                paymentVO = new PaymentVO();
                paymentVO.setSubMonth(1l);
                paymentVO.setMemberNum((Long)session.getAttribute("memberNum"));


                log.info("impUid : " + impUid);
                log.info("merchantUid : " + merchantUid);
                paymentVO.setImpUid(impUid);
                paymentVO.setMerchantUid(merchantUid);

            }else if(paymentType == 12l){ // 카드 1년
                paymentVO = new PaymentVO();
                paymentVO.setMemberNum((Long)session.getAttribute("memberNum"));

                paymentVO.setSubMonth(12l);
                paymentVO.setImpUid(impUid);
                paymentVO.setMerchantUid(merchantUid);
            } // 구독 한적 있음

            if(paymentService.resubscribe(paymentVO)){
                log.info("재결제 완료!");
            }else{
                log.info("재결제 실패");
            }
        }else{ // 구독한적 없음
            log.info("결제 이력 없고 쿠폰 결제 시작");

            // 결제 테이블 구독했다고 등록 (insert)
            if(paymentType == 0l){ // 쿠폰으로 결제

                /*--------쿠폰 사용 등록-----------*/
                log.info("paymentService : 쿠폰으로 결제하기 ----------------------------------");
                log.info("쿠폰 넘버 : " + session.getAttribute("couponNum"));
                log.info("멤버 넘버 : " + session.getAttribute("memberNum"));
                log.info("결제 타입 [0:쿠폰, 1:1달구독, 12:1년구독] : "+paymentType);
                log.info("----------------------------------------------------------");


//                버튼 누르면 쿠폰 결제가 되어야 하는데 되지 않음음

                CouponVO couponVO = new CouponVO();

               // 쿠폰 사용했다고 등록 [쿠폰 데이터]
                couponVO.setCouponNum((long)session.getAttribute("couponNum"));
                couponVO.setMemberNum((long)session.getAttribute("memberNum"));

                log.info("쿠폰 num : " + couponVO.getCouponNum());
                log.info("멤버 num : " + couponVO.getMemberNum());
                log.info("쿠폰 사용 등록");
                if(couponService.useCoupon(couponVO)){
                    log.info("수정 성공");
                }else{
                    log.info("수정 실패");
                }
                /*--------//쿠폰 사용 등록//-----------*/

//              #{memberNum}, #{couponNum}, null, null, #{subMonth}, SYSDATE, SYSDATE+60
                paymentVO = new PaymentVO();
                paymentVO.setSubMonth(2l);
                paymentVO.setMemberNum((Long)session.getAttribute("memberNum"));
                paymentVO.setCouponNum((Long)session.getAttribute("couponNum"));

            }else if(paymentType == 1l){ // 카드 1달
//              #{memberNum}, #{couponNum = 0}, #{impUid}, #{merchantUid}, #{subMonth}, SYSDATE, SYSDATE+ '')
                paymentVO = new PaymentVO();
                paymentVO.setSubMonth(2l);
                paymentVO.setMemberNum((long)session.getAttribute("memberNum"));


                log.info("impUid : " + impUid);
                log.info("merchantUid : " + merchantUid);
                paymentVO.setImpUid(impUid);
                paymentVO.setMerchantUid(merchantUid);

            }else if(paymentType == 12l){ // 카드 1년
                paymentVO = new PaymentVO();
                paymentVO.setMemberNum((Long)session.getAttribute("memberNum"));

                paymentVO.setSubMonth(13l);
                paymentVO.setImpUid(impUid);
                paymentVO.setMerchantUid(merchantUid);
            } // 구독 한적 있음

            if(paymentService.subscribe(paymentVO)){
                log.info("결제 완료!");
                session.setAttribute("sub", "true");

            }else{
                log.info("결제 실패");
            }

        } // 구독한적 없음

        return "redirect:/main/mainPage";
    }
}
