package com.neo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class AtomikosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtomikosApplication.class, args);
	}
}
