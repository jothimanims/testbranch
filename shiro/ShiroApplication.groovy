package com.ffss.datax.shiro

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author SVIGNESHWARAN
 *
 */
@ComponentScan(basePackages = 'com.ffss.datax.shiro')
@SpringBootApplication
class ShiroApplication {

	static void main(String[] args) {
		SpringApplication.run ShiroApplication, args
	}
}
