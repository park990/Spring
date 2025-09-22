package com.review.ex0919_jaeyoon_jwt_important;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Ex0919JaeyoonJwtImportantApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ex0919JaeyoonJwtImportantApplication.class, args);
	}

}
