package com.jeecms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MobileSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileSystemApplication.class, args);
	}
}
