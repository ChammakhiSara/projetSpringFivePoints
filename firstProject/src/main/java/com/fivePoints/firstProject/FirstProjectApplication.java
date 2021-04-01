package com.fivePoints.firstProject;

import com.fivePoints.firstProject.Services.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableScheduling
@SpringBootApplication
public class FirstProjectApplication {

	@Autowired
	private SendMailService sendMailService;

	public static void main(String[] args) {
		SpringApplication.run(FirstProjectApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void triggerWhenStarts(){
		sendMailService.sendEmail("chourabiseif94@gmail.com", "Hi Hello", "Test");
	}
}
