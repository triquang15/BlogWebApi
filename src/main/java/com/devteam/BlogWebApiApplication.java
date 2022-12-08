package com.devteam;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.devteam.entity.Role;
import com.devteam.service.AuthService;

@SpringBootApplication
public class BlogWebApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogWebApiApplication.class, args);
	}
	
	 @Bean
	    CommandLineRunner run(AuthService authService) {
	        return args -> {
	            if (authService.findByRole("ROLE_ADMIN").isEmpty()) {
	                authService.createRole(new Role(null, "ROLE_ADMIN"));
	            }
	            if (authService.findByRole("ROLE_USER").isEmpty()) {
	                authService.createRole(new Role(null, "ROLE_USER"));
	            }
	        };
	    }

}
