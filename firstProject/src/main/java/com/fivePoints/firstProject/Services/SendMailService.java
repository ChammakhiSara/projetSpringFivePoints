package com.fivePoints.firstProject.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String body, String topic){
        System.out.println("sending email");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("sarachammakhi77@gmail.cim");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(topic);
        simpleMailMessage.setText(body);
        javaMailSender.send(simpleMailMessage);
        System.out.println("sent email...");
    }
}
