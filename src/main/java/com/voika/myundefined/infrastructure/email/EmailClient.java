package com.voika.myundefined.infrastructure.email;

import com.voika.myundefined.infrastructure.entity.email.SendEmailDO;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class EmailClient {

    @Resource
    private JavaMailSender mailSender;

    public void sendEmail(SendEmailDO dto) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(dto.getFromUser());
        mail.setTo(dto.getToUser());
        mail.setSubject(dto.getSubject());
        mail.setText(dto.getContent());
        mailSender.send(mail);
    }

}
