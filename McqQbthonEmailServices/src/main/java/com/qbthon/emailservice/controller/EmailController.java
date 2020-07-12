package com.qbthon.emailservice.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qbthon.emailservice.model.Email;
import com.qbthon.emailservice.service.EmailService;
import com.qbthon.emailservice.service.EmailTemplateService;

@CrossOrigin
@Controller
public class EmailController {
	@Autowired
	EmailTemplateService emailTemplateService;
	
	@Autowired
	EmailService emailService;
	
	@RequestMapping(value = "/sendcreateeventmail/{emailType}/{eventId}", method = RequestMethod.GET,headers = "Accept=application/json",produces = "application/json")
	public ResponseEntity<String>  sendCreateEventMail(HttpServletRequest request, @PathVariable("emailType") String emailType,@PathVariable("eventId") String eventId) {
		Email email =emailTemplateService.getEmailDataForTemplate(emailType, eventId);
		try {
		emailService.sendMail(email);
		return ResponseEntity.ok("email sent to all associates");
		}
		
		catch(MailException ex) {
			return new ResponseEntity<>("Error sending email",HttpStatus.BAD_REQUEST);
		}
		
	}
	

	@RequestMapping(value = "/sendmailtolist/{emailType}/", method = RequestMethod.POST,headers = "Accept=application/json",produces = "application/json")
	public ResponseEntity<String>  sendMailtoList(HttpServletRequest request, @RequestBody ArrayList<String> emailids,@PathVariable("emailType") String emailType) {
		Email email =emailTemplateService.getEmailTemplate(emailType);
		email.setTo(emailids);
		 File attachmentsfolder = new File("src/main/resources/"+emailType);
		  File[] attachmentfiles = attachmentsfolder.listFiles();
		try {
		
			emailService.sendMail(emailids,email.getSubject(),email.getBody(),attachmentfiles);
		return ResponseEntity.ok("email sent to all associates");
		}
		
		catch(MailException ex) {
			return new ResponseEntity<>("Error sending email",HttpStatus.BAD_REQUEST);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Error sending email",HttpStatus.BAD_REQUEST);
		}
		
	}

}
