package com.example.gestionbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GestionBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionBankApplication.class, args);
	}

}
