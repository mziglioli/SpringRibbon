package com.core.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.core.model.EntityJpaClass;

@SpringBootApplication(scanBasePackageClasses = { EntityJpaClass.class })
public class SpringRibbonApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringRibbonApp.class, args);
	}
}