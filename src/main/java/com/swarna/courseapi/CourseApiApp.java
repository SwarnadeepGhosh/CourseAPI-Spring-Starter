package com.swarna.courseapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //annotation to tell Spring , this is a Boot Application
public class CourseApiApp {

	public static void main(String[] args) {
		// In background, this will start a servlet container and host my application there
		SpringApplication.run(CourseApiApp.class, args);
	}
}


