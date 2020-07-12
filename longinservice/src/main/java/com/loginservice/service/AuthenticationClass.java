package com.loginservice.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loginservice.models.Users;
import com.loginservice.repositories.UserInfoRepository;

@Service
public class AuthenticationClass {
	@Autowired
	UserInfoRepository userInfoRepository;

	public Users isAutheticated(String username,String password) {
		
		Optional<Users> user = userInfoRepository.findByUsername(username);
		if(!user.isPresent()) {
			System.out.println("username not there");
			return null;
		}
		else {
			if(user.get().getPassword().equals(password)) {
				
				System.out.println("username found!!");
				return user.get();
			}
			else {
				return null;
			}
		}
		
	}
	
}
