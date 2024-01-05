package com.jeunice.iBuild.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSender implements SendMail{

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void send(String to, String email) {

        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            mimeMessageHelper.setFrom("jeunicemwakisha@lanstar.co.ke");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText(email);
            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage() + "error");
        }
    }


}
