package com.qbthon.repositories;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.qbthon.model.Eventassociates;
import com.qbthon.model.Skillsets;

@EnableScan
public interface EventAssociatesRepository extends CrudRepository<Eventassociates, String>{
	

}
