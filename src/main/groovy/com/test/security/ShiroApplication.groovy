package com.test.security

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Shiro Application deals with Authentication & Authorization.
 *
 */
@SpringBootApplication
class ShiroApplication {

	static void main(String[] args) {
		SpringApplication.run ShiroApplication, args
	}
}
