package com.ocp12.ridewebapp;

import org.apache.catalina.Context;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages={"com.ocp12.ridewebapp","com.ocp12.rideconsumer","com.ocp12.ridebusiness"})
@SpringBootApplication
@EntityScan(basePackages = {"com.ocp12.ridemodele"})
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages={"com.ocp12.rideconsumer.dao"})


public class RideWebappApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RideWebappApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(RideWebappApplication.class);

	}

	@Bean
	public TomcatServletWebServerFactory tomcatFactory() {
		return new TomcatServletWebServerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				((StandardJarScanner) context.getJarScanner()).setScanManifest(false);
			}
		};
	}
}
