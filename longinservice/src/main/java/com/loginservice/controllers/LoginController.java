package com.loginservice.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.loginservice.models.Users;
import com.loginservice.service.AuthenticationClass;

@CrossOrigin
@Controller
public class LoginController {
	
	@Autowired
	AuthenticationClass authenticationService;

	@RequestMapping(value = "/login/{username}/{password}", method = RequestMethod.POST,headers = "Accept=application/json",produces = "application/json")
	public ResponseEntity<Object> authenticate(HttpServletRequest request, @PathVariable("username") String userName,@PathVariable("password") String password){
		Users user = authenticationService.isAutheticated(userName, password);
		if(user == null) {
			return new ResponseEntity<>("Authentication failed",HttpStatus.UNAUTHORIZED);
			
		}
		else {
			return ResponseEntity.ok(user);
		}
	}
	
}
