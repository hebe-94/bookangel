package com.example.bookangel.controller;

import com.example.bookangel.beans.vo.MailVO;
import com.example.bookangel.services.MailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class MailController {
    /*메일 관련 부분 */
    private final MailService mailService;

    @GetMapping("/board/mail")
    public String dispMail() {
        return "board/mail";
    }

    @PostMapping("/mail")
    public String execMail(MailVO mailVO) {
        // 메일 발송
        mailService.mailSend(mailVO);
        return "board/mail";
    }
}
