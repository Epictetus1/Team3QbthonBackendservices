package com.qbthon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages="com.qbthon")
public class QbthonApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(QbthonApplication.class, args);
	}


}
