package com.server.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.server.config.database.PersistenceConfig;
import com.server.config.security.SecurityConfig;
import com.server.config.servlet.ServletContextConfig;

@SpringBootApplication(scanBasePackageClasses = { PersistenceConfig.class, SecurityConfig.class,
		ServletContextConfig.class })
public class SpringRibbonServerApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringRibbonServerApp.class, args);
	}
}