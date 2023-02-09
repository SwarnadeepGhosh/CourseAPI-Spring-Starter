package com.swarna.courseapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//public class CorsConfig implements WebMvcConfigurer{
//
//    // @Value("${frontendURL}")
//	// private String frontendURL;
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        // WebMvcConfigurer.super.addCorsMappings(registry);
//        // registry.addMapping("/**").allowedOrigins(frontendURL)
//        registry.addMapping("/**").allowedOrigins("*")
//        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
//    }
//}

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
//            .allowedHeaders("header1", "header2", "header3")
//            .exposedHeaders("header1", "header2")
//            .allowCredentials(false).maxAge(3600);
    }
}
