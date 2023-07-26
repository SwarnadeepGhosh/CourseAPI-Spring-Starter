package com.swarna.courseapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication //annotation to tell Spring , this is a Boot Application
//@EnableSwagger2  // Enabling Swagger so that it can run
@EnableCaching //Enabling Caching
@OpenAPIDefinition(info = @Info(title = "CourseAPI", version = "2.0", description = "CourseAPI Information"))
public class CourseApiApp {

	public static void main(String[] args) {
		// In background, this will start a servlet container and host my application there
		SpringApplication.run(CourseApiApp.class, args);
	}
}


