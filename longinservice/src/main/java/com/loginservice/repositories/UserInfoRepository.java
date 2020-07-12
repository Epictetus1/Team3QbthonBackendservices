package com.loginservice.repositories;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import com.loginservice.models.Users;


import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface UserInfoRepository extends CrudRepository<Users, String> {

	Optional<Users> findByUsername(String id);
	
}
