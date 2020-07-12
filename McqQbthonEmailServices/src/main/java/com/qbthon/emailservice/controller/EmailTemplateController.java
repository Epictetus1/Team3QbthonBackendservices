package com.qbthon.emailservice.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.qbthon.emailservice.model.Email;
import com.qbthon.emailservice.service.EmailTemplateService;


@CrossOrigin
@Controller
public class EmailTemplateController {

	@Autowired
	EmailTemplateService emailTemplateService;
	
	@GetMapping("/getAllTemplates")
		public ResponseEntity<Object> getAllTemplates(){
		try {
			return new ResponseEntity<Object>(emailTemplateService.getAllTemplates(),HttpStatus.OK);
		}
		
		catch(Exception ex) {
			return new ResponseEntity<Object>("Couldnt get the email templates",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/createTemplate/")
	public ResponseEntity<Object> createEvent(@ModelAttribute Email email) {
		
		try {
		
		//System.out.println("number of attachments are "+email.getAttachments().get(0).getOriginalFilename()+ " "+email.getAttachments().get(1).getOriginalFilename());
		
			String message =emailTemplateService.CreateEmailTemplate(email);
			
			
			return new ResponseEntity<Object>(message,HttpStatus.OK);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return new ResponseEntity<Object>("Cant create Tempalte "+ex.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
}
