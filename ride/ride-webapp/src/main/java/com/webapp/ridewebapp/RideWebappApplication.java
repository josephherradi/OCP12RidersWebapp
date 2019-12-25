package com.webapp.ridewebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages= "com.webapp.ridewebapp")
@SpringBootApplication
public class RideWebappApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RideWebappApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(RideWebappApplication.class);

	}
}
