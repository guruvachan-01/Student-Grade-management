package com.coder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.coder"})
public class UserManagement1Application {

	public static void main(String[] args) {
		SpringApplication.run(UserManagement1Application.class, args);
	}

}
