package com.swarna.courseapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication //annotation to tell Spring , this is a Boot Application
@EnableSwagger2  // Enabling Swagger so that it can run
@EnableCaching //Enabling Caching
public class CourseApiApp {

	public static void main(String[] args) {
		// In background, this will start a servlet container and host my application there
		SpringApplication.run(CourseApiApp.class, args);
	}
}


