package com.qbthon.emailservice;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.qbthon.emailservice.service.EmailService;



@SpringBootApplication(scanBasePackages="com.qbthon.emailservice")
public class EmailServiceApplication  {

	
	  //@Autowired private EmailService emailService;
	 
	
	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
	}

	
	 /* @Override public void run(String... args) throws Exception {
		  File folder = new File("src/main/resources/createEvent");
		  File[] files = folder.listFiles();
		  
	        
	  emailService.sendMail("sukanya.subramanian84@gmail.com", "Hi", "Ho ho ho",files);*/
	  
	  }
	 

