package com.STIproject.STI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StiApplication.class, args);
	}

}
