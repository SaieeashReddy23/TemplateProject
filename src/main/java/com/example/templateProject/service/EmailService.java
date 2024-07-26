package com.example.templateProject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    public void sendMail(){
        log.info("sending a mail from saieeashreddy@gmail.com to saieeashreddy1@gmail.com");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("saieeashreddy@gmail.com");
        mailMessage.setTo("saieeashreddy1@gmail.com");
        mailMessage.setSubject("Dummy Email");
        mailMessage.setText("Dummy mail , to test sending a mail using JavaMailSender in springboot");
        javaMailSender.send(mailMessage);
    }
}
