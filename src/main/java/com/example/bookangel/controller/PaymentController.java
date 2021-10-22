package com.example.bookangel.controller;

import com.example.bookangel.services.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@Slf4j
@RequestMapping("/payment/*")
@RequiredArgsConstructor
public class PaymentController {

    // PaymentService 객체 선언
    private final PaymentService paymentService;

    // 페이지 이동
    @GetMapping("subscribe")
    public void subscribe(){}

    @GetMapping("subscribeCancel")
    public void subscribeCancel(){}

    @GetMapping("payment")
    public void payment(){}

    @GetMapping("subscribeDetail")
    public void subscribeDetail(){}

    // 작업용


    @PostMapping("subscribeDetail")
    public String goSubscribe(@RequestParam("monthType") String monthType, Model model){
        log.info("paymentService : 결제 상세정보 넘어가기 ----------------------------------");

        if(monthType.equals("1")){
        // 가격, 구독기간, 다음 결제일 정보 담아서 보내기!
            log.info("1");
            LocalDate now = LocalDate.now();
            LocalDate after30 = now.plusMonths(1).minusDays(1);
            LocalDate after60 = now.plusMonths(2).minusDays(1);
            LocalDate nextPayment = now.plusMonths(1);
            model.addAttribute("date", "| " + now.toString() + " ~ " + after30.toString());
            model.addAttribute("couponDate", "| " + now.toString() + " ~ " + after60.toString());
            model.addAttribute("nextPayment", "| " +nextPayment.toString());
            model.addAttribute("price", "9,900");
            model.addAttribute("monthType", "1");


            log.info("현재 날짜 : " + now.toString());
            log.info("만료 날짜 : " + after30.toString());
            log.info("쿠폰 만료 날짜 : " + after60.toString());
            log.info("다음 결제 날짜 : " + "| " +nextPayment.toString());
            log.info("결제 금액 : " + "9,900원");
        }else if(monthType.equals("12")){
            log.info("12");
            LocalDate now = LocalDate.now();
            LocalDate afterYear = now.plusYears(1).plusMonths(1).minusDays(1);
            LocalDate nextPayment = now.plusMonths(1);
            model.addAttribute("date", "| " +now.toString() + " ~ " + afterYear.toString());
            model.addAttribute("nextPayment", "| " +nextPayment.toString());
            model.addAttribute("price", "99,900원");
            model.addAttribute("monthType", "12");

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
//    @PostMapping("subscribeDetail")
//    public String doSubscribe(@RequestBody PaymentVO paymentVO){
//        log.info("paymentService : 결제하기 ----------------------------------");
//        paymentService.subscribe(paymentVO);
//        log.info("----------------------------------------------------------");
//
//        return "redirect:/main/mainPage";
//    }
}
