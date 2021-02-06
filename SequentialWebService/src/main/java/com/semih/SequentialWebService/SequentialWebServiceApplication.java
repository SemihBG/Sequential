package com.semih.SequentialWebService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SequentialWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SequentialWebServiceApplication.class, args);
	}

}
