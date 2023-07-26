package com.swarna.courseapi.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/hello")
	public String sayHi() {
		return "Hi";
	}
	
	@GetMapping("/")
	public String sayHiello() {
		return "Hello there. Have a good day. \\n\t "
				+ "Visit http://courseapi-spring-boot.herokuapp.com/topics - for all topics \\n"
				+ "Please visit http://courseapi-spring-boot.herokuapp.com/topics/java/courses for all courses under specific topics"
			;
	}
}

