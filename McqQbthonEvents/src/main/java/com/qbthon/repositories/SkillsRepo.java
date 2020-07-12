package com.qbthon.repositories;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;


import com.qbthon.model.Skillsets;

@EnableScan
public interface SkillsRepo extends CrudRepository<Skillsets, String> {
	
	public List<Skillsets> findAll();
	

}