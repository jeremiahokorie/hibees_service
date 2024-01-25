package com.hibees_service;

import com.hibees_service.config.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
public class HibeesServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(HibeesServiceApplication.class, args);
		SpringApplication application = new SpringApplication(HibeesServiceApplication.class);
		application.addListeners(new WebSecurityConfig.SwaggerConfiguration());
	}

}
