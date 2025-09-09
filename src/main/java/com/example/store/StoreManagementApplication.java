package com.example.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication                         /* приложение создано с использованием Spring,
												обозначает главный класс*/
@EnableJpaAuditing
public class StoreManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(StoreManagementApplication.class, args);
	}

}
