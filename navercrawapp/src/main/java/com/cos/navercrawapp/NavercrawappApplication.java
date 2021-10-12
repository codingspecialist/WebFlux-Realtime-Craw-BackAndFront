package com.cos.navercrawapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class NavercrawappApplication {

	public static void main(String[] args) {
		SpringApplication.run(NavercrawappApplication.class, args);
	}

}
