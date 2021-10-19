package com.example.bookangel.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/payment/*")
@RequiredArgsConstructor
public class PaymentController {

    @GetMapping("subscribe")
    public void subscribe(){}

    @GetMapping("subscribeCancel")
    public void subscribeCancel(){}

    @GetMapping("payment")
    public void payment(){}
}
