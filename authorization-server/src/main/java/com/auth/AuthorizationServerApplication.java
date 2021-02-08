package com.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import com.common.config.CorsFilter;

@SpringBootApplication
@Import(value = { CorsFilter.class })
public class AuthorizationServerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServerApplication.class, args);
	}

}