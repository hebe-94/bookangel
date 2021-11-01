package com.example.bookangel.services;

import com.example.bookangel.beans.vo.MailVO;
import com.example.bookangel.util.MailHandler;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService {
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "YOUR_EMAIL_ADDRESS";

    public void mailSend(MailVO mailVO) {
        try {
            MailHandler mailHandler = new MailHandler(mailSender);

            // 받는 사람
            mailHandler.setTo(mailVO.getAddress());
            // 보내는 사람
            mailHandler.setFrom(MailService.FROM_ADDRESS);
            // 제목
            mailHandler.setSubject("[기북천사] "+mailVO.getTitle());
            // HTML Layout
            String htmlContent = "<p>" + mailVO.getMessage() + "<p> <img src='cid:sample-img'>";
            mailHandler.setText(htmlContent, true);

            mailHandler.send();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
