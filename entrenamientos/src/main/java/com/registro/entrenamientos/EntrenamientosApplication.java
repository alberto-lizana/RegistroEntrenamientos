package com.registro.entrenamientos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class EntrenamientosApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntrenamientosApplication.class, args);
	}

}
