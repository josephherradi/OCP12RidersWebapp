package com.ocp12.ridewebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.ocp12.ridewebapp","com.ocp12.rideconsumer","com.ocp12.ridebusiness","com.ocp12.ridemodele"})
@SpringBootApplication
@EntityScan(basePackages = {"com.ocp12.ridemodele"})
@EnableAutoConfiguration

public class RideWebappApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RideWebappApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(RideWebappApplication.class);

	}
}
