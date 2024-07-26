package com.example.templateProject.job;

import com.example.templateProject.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MailNotificationJob {

    @Autowired
    EmailService emailService;

//    Every 30 seconds
//    @Scheduled(fixedRate = 30000)
//    public void scheduleTaskAtFixedRate(){
//        log.info("Fixed rate task - ", System.currentTimeMillis());
//        emailService.sendMail();
//    }
}
