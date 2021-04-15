package com.fivePoints.firstProject;

import com.fivePoints.firstProject.Models.User;
import com.fivePoints.firstProject.Repositries.UserRepository;
import com.fivePoints.firstProject.Services.FilesStorageService;
import com.fivePoints.firstProject.Services.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.Resource;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableScheduling
@SpringBootApplication
public class FirstProjectApplication implements ApplicationRunner {

	@Autowired
	private SendMailService sendMailService;

	@Autowired
	private UserRepository userRepository;

	@Resource
	FilesStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(FirstProjectApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		//c'est pour creer un seeder data (user parDefaut creer lorsqu'on run la premiere fois notre application)
		// (on peut ajouter le nbr de users souhaité)
		// si j'ai deja des users dans mon base de donné il n'ajoute rien, il est valable just si mon table est vide
		long countUser = this.userRepository.count();
		if (countUser == 0){
			User user = new User();
			user.setFirstName("sara");
			user.setLastName("chammakhi");
			user.setEmail("sara@gmail.com");
			user.setPassword("123456");
			this.userRepository.save(user);
		}

		storageService.deleteAll();
		storageService.init();
	}

	//c'est pour sending un simple email avec gmail
	//@EventListener(ApplicationReadyEvent.class)
	//public void triggerWhenStarts(){
	//sendMailService.sendEmail("chourabiseif94@gmail.com", "Hi Hello", "Test");
	//}



}
